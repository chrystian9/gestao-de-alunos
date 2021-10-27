package br.com.gestaodealunos.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FotoService {

    private static String diretorioFotosPerfil = System.getProperty("user.dir")+"/src/main/resources/fotos-perfil";

    public String salvarFoto(MultipartFile foto, Long id) throws IOException {
        String nomeFoto = StringUtils.cleanPath(foto.getOriginalFilename());

        Path fotoPath = Paths.get(diretorioFotosPerfil, id.toString(), nomeFoto).toAbsolutePath().normalize();

        if(!Files.exists(Paths.get(diretorioFotosPerfil).toAbsolutePath().normalize())){
            File folder = new File(diretorioFotosPerfil);
            folder.mkdir();
        }

        if(!Files.exists(Paths.get(diretorioFotosPerfil, id.toString()).toAbsolutePath().normalize())){
            File folder = new File(Paths.get(diretorioFotosPerfil, id.toString()).toAbsolutePath().normalize().toUri());
            folder.mkdir();
        }else{
            File folder = new File(fotoPath.toUri());
            if (folder.isDirectory()) {
                File[] sun = folder.listFiles();
                for (File toDelete : sun) {
                    toDelete.delete();
                }
            }
        }

        copy(foto.getInputStream(), fotoPath, REPLACE_EXISTING);

        return fotoPath.toString();
    }

    public ResponseEntity<Resource> getFoto(Path pathFoto) throws IOException {

        try{
            if(!Files.exists(pathFoto)){
                throw new FileNotFoundException("Foto não encontrada no servidor");
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        Resource resource = new UrlResource(pathFoto.toUri());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", pathFoto.getFileName().toString());
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(pathFoto)))
                .headers(httpHeaders).body(resource);
    }
}
