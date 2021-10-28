package br.com.gestaodealunos.services;

import br.com.gestaodealunos.entities.Aluno;
import br.com.gestaodealunos.entities.Nota;
import br.com.gestaodealunos.repositories.AlunoRepository;
import br.com.gestaodealunos.repositories.NotaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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

        return fotoService.getFoto(Paths.get(alunoOptional.get().getPathFoto()));
    }

    public List<Aluno> listarAlunos(){
        return alunoRepository.findAll();
    }

    public Aluno update(Aluno aluno){

        return alunoRepository.save(aluno);
    }

    public void remover(Aluno aluno){
        aluno = alunoRepository.findById(aluno.getId()).get();
        alunoRepository.delete(aluno);
    }

    public void updateNotas(List<NotasDTO> notasDTO, Long idAluno) {
        List<Nota> notas = notasDTO.getNotas();

        Aluno aluno = alunoRepository.getById(idAluno);

        aluno.setNotas(notas);

        alunoRepository.save(aluno);
    }
}
