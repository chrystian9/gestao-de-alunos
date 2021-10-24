package br.com.gestaodealunos.services;

import br.com.gestaodealunos.dto.ProfessorDTO;
import br.com.gestaodealunos.entities.Professor;
import br.com.gestaodealunos.entities.Role;
import br.com.gestaodealunos.entities.Usuario;
import br.com.gestaodealunos.repositories.ProfessorRepository;
import br.com.gestaodealunos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

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
}
