package br.com.gestaodealunos.controllers;

import br.com.gestaodealunos.dto.AlunoDTO;
import br.com.gestaodealunos.entities.*;
import br.com.gestaodealunos.repositories.AdminRepository;
import br.com.gestaodealunos.repositories.ProfessorRepository;
import br.com.gestaodealunos.repositories.RoleRepository;
import br.com.gestaodealunos.repositories.UsuarioRepository;
import br.com.gestaodealunos.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/aluno")
public class AlunosController {

    @Autowired
    private AlunoService alunoService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(path = "/salvarAdmin")
    public ResponseEntity<String> salvar(){
        return ResponseEntity.ok().body("teste");
    }

    @PostMapping(path = "/salvar")
    public ResponseEntity<Long> salvar(@RequestBody AlunoDTO alunoDTO){

        Aluno novoAluno = new Aluno(alunoDTO);
        Long id = alunoService.salvar(novoAluno).getId();

        return ResponseEntity.ok().body(id);
    }

    @PostMapping(path = "/{id}/salvar-foto")
    public ResponseEntity<String> salvarFoto(@RequestBody MultipartFile foto, @PathVariable("id") Long idAluno) throws IOException {

        String fotoPath = alunoService.salvarFoto(foto, idAluno);
        alunoService.atualizarPathFotoEmAluno(fotoPath, idAluno);

        return ResponseEntity.ok().body("Sucesso");
    }

    @GetMapping(path = "/{id}/get-foto")
    public ResponseEntity<Resource> getFoto(@PathVariable("id") Long idAluno) throws IOException {

        return alunoService.getFoto(idAluno);
    }

    @GetMapping(path = "/listar")
    public ResponseEntity<Page<Aluno>> listar(@RequestParam(value="page", defaultValue="0") Integer page,
                                              @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
                                              @RequestParam(value="orderBy", defaultValue="dataCadastro") String orderBy,
                                              @RequestParam(value="direction", defaultValue="DESC") String direction){

        return ResponseEntity.ok().body(alunoService.listarAlunos(page, linesPerPage, orderBy, direction));
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Long> update(@RequestBody AlunoDTO alunoDTO){

        Aluno novoAluno = new Aluno(alunoDTO);
        Long id = alunoService.update(novoAluno).getId();

        return ResponseEntity.ok().body(id);
    }

    @PutMapping(path = "/remover")
    public ResponseEntity<String> remover(@RequestBody AlunoDTO alunoDTO){

        Aluno aluno = new Aluno(alunoDTO);
        alunoService.remover(aluno);

        return ResponseEntity.ok().body("Sucesso");
    }
}
