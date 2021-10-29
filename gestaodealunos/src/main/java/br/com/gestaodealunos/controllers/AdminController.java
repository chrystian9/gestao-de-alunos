package br.com.gestaodealunos.controllers;

import br.com.gestaodealunos.dto.ProfessorDTO;
import br.com.gestaodealunos.dto.UsuarioDTO;
import br.com.gestaodealunos.entities.Professor;
import br.com.gestaodealunos.entities.Usuario;
import br.com.gestaodealunos.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(path = "/cadastrar-professor")
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody ProfessorDTO professorDTO) throws Exception {

        return ResponseEntity.ok().body(adminService.cadastrarProfessor(professorDTO));
    }

    @PostMapping(path = "/cadastrar-usuario-admin")
    public ResponseEntity<Usuario> cadastrarUsuarioAdmin(@RequestBody UsuarioDTO usuarioDTO) throws Exception {

        return ResponseEntity.ok().body(adminService.cadastrarUsuarioAdmin(usuarioDTO));
    }

    @PostMapping(path = "/editar-usuario-admin")
    public ResponseEntity<Usuario> editarUsuarioAdmin(@RequestBody UsuarioDTO usuarioDTO) throws Exception {

        return ResponseEntity.ok().body(adminService.editarUsuarioAdmin(usuarioDTO));
    }

    @PostMapping(path = "/editar-professor")
    public ResponseEntity<Professor> editarProfessor(@RequestBody ProfessorDTO professorDTO) throws Exception {

        return ResponseEntity.ok().body(adminService.editarProfessor(professorDTO));
    }

    @PutMapping(path = "/delete-usuario-admin")
    public ResponseEntity<String> deleteUsuarioAdmin(@RequestBody UsuarioDTO usuarioDTO){

        adminService.deleteUsuarioAdmin(usuarioDTO);

        return ResponseEntity.ok().body("Sucesso");
    }

    @PutMapping(path = "/delete-professor")
    public ResponseEntity<String> deleteProfessor(@RequestBody ProfessorDTO professorDTO) throws Exception {

        adminService.deleteProfessor(professorDTO);

        return ResponseEntity.ok().body("Sucesso");
    }

    @GetMapping(path = "/listar/professores")
    public ResponseEntity<List<Professor>> listarProfessores() throws Exception {

        return ResponseEntity.ok().body(adminService.listarProfessores());
    }
}
