package br.com.gestaodealunos.services;

import br.com.gestaodealunos.entities.Aluno;
import br.com.gestaodealunos.repositories.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.util.Optional;

import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@AllArgsConstructor
@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    private static String diretorioFotosPerfil = System.getProperty("user.dir")+"/src/main/resources/fotos-perfil";

    public Aluno salvar(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public String salvarFoto(MultipartFile foto, Long idAluno) throws IOException {
        String nomeFoto = StringUtils.cleanPath(foto.getOriginalFilename());

        Path fotoPath = Paths.get(diretorioFotosPerfil, idAluno.toString(), nomeFoto).toAbsolutePath().normalize();

        if(!Files.exists(Paths.get(diretorioFotosPerfil).toAbsolutePath().normalize())){
            File folder = new File(diretorioFotosPerfil);
            folder.mkdir();
        }

        if(!Files.exists(Paths.get(diretorioFotosPerfil, idAluno.toString()).toAbsolutePath().normalize())){
            File folder = new File(Paths.get(diretorioFotosPerfil, idAluno.toString()).toAbsolutePath().normalize().toUri());
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

    public Aluno atualizarPathFotoEmAluno(String fotoPath, Long idAluno) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);

        Aluno aluno = alunoOptional.get();
        aluno.setPathFoto(fotoPath);

        return alunoRepository.save(aluno);
    }

    public ResponseEntity<Resource> getFoto(Long idAluno) throws IOException {
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);

        Path pathFoto = Path.of(alunoOptional.get().getPathFoto());

        if(!Files.exists(pathFoto)){
            throw new FileNotFoundException("Foto não encontrada no servidor");
        }

        Resource resource = new UrlResource(pathFoto.toUri());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", pathFoto.getFileName().toString());
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(pathFoto)))
                .headers(httpHeaders).body(resource);
    }

    public Page<Aluno> listarAlunos(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<Aluno> alunos = alunoRepository.findAll(pageRequest);

        return alunos;
    }

    public Aluno update(Aluno aluno){

        return alunoRepository.save(aluno);
    }

    public void remover(Aluno aluno){
        aluno = alunoRepository.findById(aluno.getId()).get();
        alunoRepository.delete(aluno);
    }
}
