package br.com.gestaodealunos.controllers;

import br.com.gestaodealunos.dto.ProfessorDTO;
import br.com.gestaodealunos.entities.Professor;
import br.com.gestaodealunos.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping(path = "/editar-professor")
    public ResponseEntity<Professor> editarProfessor(@RequestBody ProfessorDTO professorDTO){

        return ResponseEntity.ok().body(professorService.editarProfessor(professorDTO));
    }

    @DeleteMapping(path = "/delete-professor")
    public ResponseEntity<String> deleteProfessor(@RequestBody ProfessorDTO professorDTO){

        professorService.deleteProfessor(professorDTO);

        return ResponseEntity.ok().body("Sucesso");
    }
}
