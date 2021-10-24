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

    @Autowired
    FotoService fotoService;

    public Aluno salvar(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public void salvarFoto(MultipartFile foto, Long idAluno) throws IOException {
        String fotoPath = fotoService.salvarFoto(foto, idAluno);
        atualizarPathFotoEmAluno(fotoPath, idAluno);
    }

    public Aluno atualizarPathFotoEmAluno(String fotoPath, Long idAluno) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);

        Aluno aluno = alunoOptional.get();
        aluno.setPathFoto(fotoPath);

        return alunoRepository.save(aluno);
    }

    public ResponseEntity<Resource> getFoto(Long idAluno) throws IOException {
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);

        return fotoService.getFoto(Path.of(alunoOptional.get().getPathFoto()));
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
