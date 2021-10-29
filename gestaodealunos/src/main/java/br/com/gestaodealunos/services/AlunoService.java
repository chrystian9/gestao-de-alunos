package br.com.gestaodealunos.services;

import br.com.gestaodealunos.dto.AlunoDTO;
import br.com.gestaodealunos.dto.NotasDTO;
import br.com.gestaodealunos.entities.Aluno;
import br.com.gestaodealunos.entities.Notas;
import br.com.gestaodealunos.repositories.AlunoRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    FotoService fotoService;

    public Aluno salvar(AlunoDTO alunoDTO){
        Aluno aluno = new Aluno(alunoDTO);

        return alunoRepository.save(aluno);
    }

    /*
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
    */

    public List<AlunoDTO> listarAlunos(){
        List<AlunoDTO> alunosDTO = new ArrayList<>();

        List<Aluno> alunos = alunoRepository.findAll();

        for (Aluno aluno: alunos) {
            AlunoDTO alunoDTO = new AlunoDTO(aluno);
            alunosDTO.add(alunoDTO);
        }

        return alunosDTO;
    }

    public Aluno update(AlunoDTO alunoDTO){
        Aluno aluno = new Aluno(alunoDTO);

        aluno.setDataUltimaAtualizacao(new Date());

        return alunoRepository.save(aluno);
    }

    public void remover(AlunoDTO alunoDTO){
        Aluno aluno = new Aluno(alunoDTO);

        aluno = alunoRepository.findById(aluno.getId()).get();

        alunoRepository.delete(aluno);
    }

    public void updateNotas(NotasDTO notasDTO, Long idAluno) throws NotFoundException {
        Aluno aluno = alunoRepository.getById(idAluno);

        if(aluno == null){
            throw new NotFoundException("Aluno não encontrado");
        }

        Notas notas = aluno.getNotas();

        if(notas == null){
            notas = new Notas();
        }

        notas.setNotaFinal(notasDTO.notaFinal);
        notas.setNotaUm(notasDTO.notaUm);
        notas.setNotaDois(notasDTO.notaDois);
        notas.setNotaTres(notasDTO.notaTres);
        notas.setNotaQuatro(notasDTO.notaQuatro);
        notas.setAluno(aluno);

        aluno.setNotas(notas);

        alunoRepository.save(aluno);
    }
}
