package br.com.gestaodealunos.services;

import br.com.gestaodealunos.dto.ProfessorDTO;
import br.com.gestaodealunos.dto.UsuarioDTO;
import br.com.gestaodealunos.entities.Professor;
import br.com.gestaodealunos.entities.Role;
import br.com.gestaodealunos.entities.Usuario;
import br.com.gestaodealunos.repositories.ProfessorRepository;
import br.com.gestaodealunos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProfessorService professorService;

    @Autowired
    UsuarioService usuarioService;

    private PasswordEncoder passwordEncoder;

    public Professor cadastrarProfessor(ProfessorDTO professorDTO) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findByEmail(professorDTO.getEmail());

            if(usuario != null){
                throw new Exception("Email já cadastrado no sistema!");
            }

            usuario = usuarioRepository.findById(professorDTO.getId());

            if(usuario!= null){
                throw new Exception("Usuário já cadastrado no sistema!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Professor professor = new Professor(professorDTO);
        professor.setSenha(passwordEncoder.encode(professor.getSenha()));

        Role role = new Role();
        role.setId(2L);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        professor.setRoles(roles);

        return professorRepository.save(professor);
    }

    public Usuario cadastrarUsuarioAdmin(UsuarioDTO usuarioDTO) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioDTO.getEmail());

            if(usuario != null){
                throw new Exception("Email já cadastrado no sistema!");
            }

            usuario = usuarioRepository.findById(usuarioDTO.getId());

            if(usuario!= null){
                throw new Exception("Usuário já cadastrado no sistema!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Usuario usuario = new Usuario(usuarioDTO);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        Role role = new Role();
        role.setId(1L);
        role.setId(2L);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        usuario.setRoles(roles);

        return usuarioRepository.save(usuario);
    }

    public Usuario editarUsuarioAdmin(UsuarioDTO usuarioDTO) {
        return usuarioService.editarUsuarioAdmin(usuarioDTO);
    }

    public Professor editarProfessor(ProfessorDTO professorDTO) {
        return professorService.editarProfessor(professorDTO);
    }

    public void deleteUsuarioAdmin(UsuarioDTO usuarioDTO) {
        usuarioService.deleteUsuarioAdmin(usuarioDTO);
    }

    public void deleteProfessor(ProfessorDTO professorDTO) {
        professorService.deleteProfessor(professorDTO);
    }
}
