package br.com.gestaodealunos.services;

import br.com.gestaodealunos.dto.UsuarioDTO;
import br.com.gestaodealunos.entities.Usuario;
import br.com.gestaodealunos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario editarUsuarioAdmin(UsuarioDTO usuarioDTO) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioDTO.getEmail());

        try{
            usuario.orElseThrow(() -> new UsernameNotFoundException("Usuario com id " + usuarioDTO.getId() + " não encontrado."));

            if(!usuarioDTO.getEmail().equals(usuario.get().getEmail())){
                throw new Exception("Email não correspondente ao cadastrado no banco.");
            }

            Usuario usuarioNoBanco = usuario.get();

            usuarioNoBanco.setNome(usuarioDTO.getNome());
            usuarioNoBanco.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.getSenha()));
            usuarioNoBanco.setDataCadastro(usuarioDTO.getDataCadastro());
            usuarioNoBanco.setDataUltimaAtualizacao(usuarioDTO.getDataUltimaAtualizacao());

            return usuarioRepository.save(usuarioNoBanco);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void deleteUsuarioAdmin(UsuarioDTO usuarioDTO) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioDTO.getEmail());

        usuario.orElseThrow(() -> new UsernameNotFoundException("Email " + usuarioDTO.getEmail() + " não encontrado."));

        usuarioRepository.deleteById(usuarioDTO.getId());
    }
}
