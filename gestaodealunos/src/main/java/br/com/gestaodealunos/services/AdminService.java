package br.com.gestaodealunos.services;

import br.com.gestaodealunos.dto.ProfessorDTO;
import br.com.gestaodealunos.dto.UsuarioDTO;
import br.com.gestaodealunos.entities.Professor;
import br.com.gestaodealunos.entities.Role;
import br.com.gestaodealunos.entities.Usuario;
import br.com.gestaodealunos.repositories.ProfessorRepository;
import br.com.gestaodealunos.repositories.RoleRepository;
import br.com.gestaodealunos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
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
    RoleRepository roleRepository;

    @Autowired
    ProfessorService professorService;

    @Autowired
    UsuarioService usuarioService;

    public Professor cadastrarProfessor(ProfessorDTO professorDTO) throws Exception {
        try {
            verificaUsuarioNoBanco(professorDTO.getEmail(), professorDTO.getId());

            Professor professor = new Professor(professorDTO);
            professor.setSenha(new BCryptPasswordEncoder().encode(professor.getSenha()));

            verificaRolesNoBanco();

            Role role = roleRepository.getById(2L);

            List<Role> roles = new ArrayList<>();
            roles.add(role);

            professor.setRoles(roles);

            return professorRepository.save(professor);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Usuario cadastrarUsuarioAdmin(UsuarioDTO usuarioDTO) throws Exception {
        try {
            verificaUsuarioNoBanco(usuarioDTO.getEmail(), usuarioDTO.getId());

            Usuario usuario = new Usuario(usuarioDTO);
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

            verificaRolesNoBanco();

            List<Role> roles = new ArrayList<>();

            Role role = roleRepository.getById(1L);
            roles.add(role);

            role = roleRepository.getById(2L);
            roles.add(role);

            usuario.setRoles(roles);

            return usuarioRepository.save(usuario);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Usuario editarUsuarioAdmin(UsuarioDTO usuarioDTO) throws Exception {
        return usuarioService.editarUsuarioAdmin(usuarioDTO);
    }

    public Professor editarProfessor(ProfessorDTO professorDTO) throws Exception {
        return professorService.editarProfessor(professorDTO);
    }

    public void deleteUsuarioAdmin(UsuarioDTO usuarioDTO) {
        usuarioService.deleteUsuarioAdmin(usuarioDTO);
    }

    public void deleteProfessor(ProfessorDTO professorDTO) throws Exception {
        professorService.deleteProfessor(professorDTO);
    }

    public void verificaUsuarioNoBanco(String email, Long id) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if(usuario.isPresent() == true){
            throw new NonUniqueResultException("Email já cadastrado no sistema!");
        }

        if(id != null){
            usuario = usuarioRepository.findById(id);

            if(usuario.isPresent() == true){
                throw new NonUniqueResultException("Usuário já cadastrado no sistema!");
            }
        }
    }

    public void verificaRolesNoBanco(){
        Optional<Role> role = roleRepository.findById(1L);

        if(role.isPresent() == false){
            roleRepository.save(new Role("ADMIN_ROLE"));
        }

        role = roleRepository.findById(2L);

        if(role.isPresent() == false){
            roleRepository.save(new Role("USER_ROLE"));
        }
    }

    public List<Professor> listarProfessores(){

        return professorService.listaProfessores();
    }
}
