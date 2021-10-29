package br.com.gestaodealunos.controllers;

import br.com.gestaodealunos.dto.AlunoDTO;
import br.com.gestaodealunos.dto.NotasDTO;
import br.com.gestaodealunos.entities.Aluno;
import br.com.gestaodealunos.services.AlunoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/aluno")
@CrossOrigin("*")
public class AlunosController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping(path = "/salvar")
    public ResponseEntity<Long> salvar(@RequestBody AlunoDTO alunoDTO){
        Long id = alunoService.salvar(alunoDTO).getId();

        return ResponseEntity.ok().body(id);
    }

    /*
    @PostMapping(path = "/{id}/salvar-foto")
    public ResponseEntity<String> salvarFoto(@RequestBody MultipartFile foto, @PathVariable("id") Long idAluno) throws IOException {

        alunoService.salvarFoto(foto, idAluno);

        return ResponseEntity.ok().body("Sucesso");
    }

    @GetMapping(path = "/{id}/get-foto")
    public ResponseEntity<Resource> getFoto(@PathVariable("id") Long idAluno) throws IOException {

        return alunoService.getFoto(idAluno);
    }
    */

    @GetMapping(path = "/listar")
    public ResponseEntity<List<AlunoDTO>> listar(){

        return ResponseEntity.ok().body(alunoService.listarAlunos());
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Long> update(@RequestBody AlunoDTO alunoDTO){
        Long id = alunoService.update(alunoDTO).getId();

        return ResponseEntity.ok().body(id);
    }

    @PostMapping(path = "/{id}/update-notas")
    public ResponseEntity<String> updateNotas(@RequestBody NotasDTO notas, @PathVariable("id") Long idAluno) throws NotFoundException {
        alunoService.updateNotas(notas, idAluno);

        return ResponseEntity.ok().body("Sucesso");
    }

    @PutMapping(path = "/remover")
    public ResponseEntity<String> remover(@RequestBody AlunoDTO alunoDTO){
        alunoService.remover(alunoDTO);

        return ResponseEntity.ok().body("Sucesso");
    }
}
