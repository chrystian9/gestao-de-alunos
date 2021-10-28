package br.com.gestaodealunos.controllers;

import br.com.gestaodealunos.dto.AlunoDTO;
import br.com.gestaodealunos.dto.NotasDTO;
import br.com.gestaodealunos.entities.Aluno;
import br.com.gestaodealunos.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/aluno")
public class AlunosController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping(path = "/salvar")
    public ResponseEntity<Long> salvar(@RequestBody AlunoDTO alunoDTO){

        Aluno novoAluno = new Aluno(alunoDTO);
        Long id = alunoService.salvar(novoAluno).getId();

        return ResponseEntity.ok().body(id);
    }

    @PostMapping(path = "/{id}/salvar-foto")
    public ResponseEntity<String> salvarFoto(@RequestBody MultipartFile foto, @PathVariable("id") Long idAluno) throws IOException {

        alunoService.salvarFoto(foto, idAluno);

        return ResponseEntity.ok().body("Sucesso");
    }

    @GetMapping(path = "/{id}/get-foto")
    public ResponseEntity<Resource> getFoto(@PathVariable("id") Long idAluno) throws IOException {

        return alunoService.getFoto(idAluno);
    }

    @GetMapping(path = "/listar")
    public ResponseEntity<List<Aluno>> listar(){

        return ResponseEntity.ok().body(alunoService.listarAlunos());
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Long> update(@RequestBody AlunoDTO alunoDTO){

        Aluno novoAluno = new Aluno(alunoDTO);
        Long id = alunoService.update(novoAluno).getId();

        return ResponseEntity.ok().body(id);
    }

    @PostMapping(path = "/{id}/update-notas")
    public ResponseEntity<Long> update(@RequestBody NotasDTO notasDTO, @RequestParam("id") Long idAluno){

        Long id = alunoService.updateNotas(notasDTO, idAluno);

        return ResponseEntity.ok().body("Sucesso");
    }

    @PutMapping(path = "/remover")
    public ResponseEntity<String> remover(@RequestBody AlunoDTO alunoDTO){

        Aluno aluno = new Aluno(alunoDTO);
        alunoService.remover(aluno);

        return ResponseEntity.ok().body("Sucesso");
    }
}
