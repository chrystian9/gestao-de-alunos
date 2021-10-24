package br.com.gestaodealunos.controllers;

import br.com.gestaodealunos.dto.AlunoDTO;
import br.com.gestaodealunos.dto.ProfessorDTO;
import br.com.gestaodealunos.entities.Aluno;
import br.com.gestaodealunos.entities.Professor;
import br.com.gestaodealunos.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(path = "/cadastrar-professor")
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody ProfessorDTO professorDTO){

        return ResponseEntity.ok().body(adminService.cadastrarProfessor(professorDTO));
    }
}
