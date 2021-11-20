package br.com.gestaodealunos.services;

import java.util.Date;
import java.util.Optional;

import br.com.gestaodealunos.entities.Role;
import br.com.gestaodealunos.entities.Usuario;
import br.com.gestaodealunos.dto.UsuarioDTO;
import br.com.gestaodealunos.repositories.AdminRepository;

import br.com.gestaodealunos.repositories.RoleRepository;
import br.com.gestaodealunos.repositories.UsuarioRepository;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup(){
        Usuario usuario = criaUsuarioAdmin();

        BDDMockito.when(usuarioRepository.save(ArgumentMatchers.any())).thenReturn(usuario);

        Optional<Usuario> optionalUsuario = Optional.empty();
        BDDMockito.when(usuarioRepository.findByEmail(ArgumentMatchers.any())).thenReturn(optionalUsuario);
        BDDMockito.when(usuarioRepository.findById(ArgumentMatchers.any())).thenReturn(null);

        BDDMockito.when(roleRepository.getById(1L)).thenReturn(new Role("ADMIN_ROLE"));
        BDDMockito.when(roleRepository.getById(2L)).thenReturn(new Role("USER_ROLE"));
    }

    @SneakyThrows
    @Test
    public void cadastrarUsuarioAdminSucessoTeste() {
        UsuarioDTO usuarioAdminDTO = criaUsuarioAdminDTO();
        Usuario cadastrado = adminService.cadastrarUsuarioAdmin(usuarioAdminDTO);

        Assertions.assertThat(cadastrado).isNotNull();
        Assertions.assertThat(cadastrado.getId()).isNotNull();
        Assertions.assertThat(cadastrado.getNome()).isEqualTo(usuarioAdminDTO.getNome());
        Assertions.assertThat(cadastrado.getEmail()).isEqualTo(usuarioAdminDTO.getEmail());
        Assertions.assertThat(cadastrado.getSenha()).isEqualTo(usuarioAdminDTO.getSenha());
        Assertions.assertThat(cadastrado.getDataCadastro()).isEqualTo(usuarioAdminDTO.getDataCadastro());
        Assertions.assertThat(cadastrado.getDataUltimaAtualizacao()).isEqualTo(usuarioAdminDTO.getDataUltimaAtualizacao());
    }


    private UsuarioDTO criaUsuarioAdminDTO(){
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setNome("Usuario Teste");
        usuarioDTO.setEmail("usuario_teste@teste.com");
        usuarioDTO.setSenha("senha");
        usuarioDTO.setDataCadastro(new Date(2000, 01, 01));
        usuarioDTO.setDataUltimaAtualizacao(new Date(2000, 01, 01));

        return usuarioDTO;
    }

    private Usuario criaUsuarioAdmin(){
        Usuario usuario = new Usuario();

        usuario.setId(1L);
        usuario.setNome("Usuario Teste");
        usuario.setEmail("usuario_teste@teste.com");
        usuario.setSenha("senha");
        usuario.setDataCadastro(new Date(2000, 01, 01));
        usuario.setDataUltimaAtualizacao(new Date(2000, 01, 01));

        return usuario;
    }

}
