package br.com.gestaodealunos.services;

import br.com.gestaodealunos.entities.MyUserDetails;
import br.com.gestaodealunos.entities.Usuario;
import br.com.gestaodealunos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        usuario.orElseThrow(() -> new UsernameNotFoundException("Email " + email + " não encontrado."));

        return usuario.map(MyUserDetails::new).get();
    }
}
