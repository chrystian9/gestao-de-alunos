package br.com.gestaodealunos.controllers;

import br.com.gestaodealunos.dto.ProfessorDTO;
import br.com.gestaodealunos.entities.Professor;
import br.com.gestaodealunos.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/professor")
@CrossOrigin("*")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping(path = "/editar-professor")
    public ResponseEntity<Professor> editarProfessor(@RequestBody ProfessorDTO professorDTO) throws Exception {

        return ResponseEntity.ok().body(professorService.editarProfessor(professorDTO));
    }

    @GetMapping(path = "/professor/{id}")
    public ResponseEntity<Professor> getProfessor(@RequestParam("id") Long id) throws Exception {

        return ResponseEntity.ok().body(professorService.getProfessor(id));
    }
}
