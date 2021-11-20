package br.com.gestaodealunos.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.gestaodealunos.entities.Usuario;
import br.com.gestaodealunos.dto.UsuarioDTO;
import br.com.gestaodealunos.repositories.AdminRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService ;
    @Mock
    private AdminRepository adminRepository = new;

    @BeforeEach
    void setup(){

    }

    @Test
    public void cadastrarUsuarioAdminSucessoTeste() throws Exception {
        UsuarioDTO usuarioDTO = criaUsuarioAdminDTO();
        Usuario usuario = criaUsuarioAdmin();

        BDDMockito.when(adminRepository.save(ArgumentMatchers.any())).thenReturn(usuario);
        Usuario cadastrado = adminService.cadastrarUsuarioAdmin(usuarioDTO);
    Assertions.assertEquals(cadastrado,usuario);

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
