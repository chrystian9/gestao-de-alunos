package br.com.gestaodealunos.controllers;

import br.com.gestaodealunos.dto.ProfessorDTO;
import br.com.gestaodealunos.dto.UsuarioDTO;
import br.com.gestaodealunos.entities.Endereco;
import br.com.gestaodealunos.entities.Professor;
import br.com.gestaodealunos.entities.Role;
import br.com.gestaodealunos.entities.Usuario;
import br.com.gestaodealunos.services.AdminService;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @SneakyThrows
    @BeforeEach
    void setup(){
        List<Professor> professores = new ArrayList<>();
        Professor professor = criaProfessor();
        professores.add(professor);

        BDDMockito.when(adminService.cadastrarProfessor(ArgumentMatchers.any())).thenReturn(professor);

        BDDMockito.when(adminService.listarProfessores()).thenReturn(professores);

        Professor professorUpdated = criaProfessor();
        professorUpdated.setDisciplina("disciplinaUpdated");

        BDDMockito.when(adminService.editarProfessor(ArgumentMatchers.any())).thenReturn(professorUpdated);

        BDDMockito.when(adminService.cadastrarUsuarioAdmin(ArgumentMatchers.any())).thenReturn(criaUsuarioAdmin());

        Usuario usuarioUpdated = criaUsuarioAdmin();
        usuarioUpdated.setEmail("usuario_updated_teste@teste.com");

        BDDMockito.when(adminService.editarUsuarioAdmin(ArgumentMatchers.any())).thenReturn(usuarioUpdated);
    }

    @SneakyThrows
    @Test
    @DisplayName("Lista de professores quando sucesso")
    void list_ReturnsListProfessor_QuandoSucesso(){
        List<Professor> professores = adminController.listarProfessores().getBody();

        Assertions.assertThat(professores).isNotNull();
        Assertions.assertThat(professores).isNotEmpty().hasSize(1);
        Assertions.assertThat(professores.get(0).getNome()).isEqualTo(criaProfessor().getNome());
    }

    @SneakyThrows
    @Test
    @DisplayName("Edita professor quando sucesso")
    void edit_ReturnsProfessorUpdated_QuandoSucesso(){
        Professor professorUpdated = adminController.editarProfessor(criaProfessorDTO()).getBody();

        Assertions.assertThat(professorUpdated).isNotNull();
        Assertions.assertThat(professorUpdated.getDisciplina()).isEqualTo("disciplinaUpdated");
    }

    @SneakyThrows
    @Test
    @DisplayName("Salvar professor quando sucesso")
    void save_ReturnsProfessor_QuandoSucesso(){
        Professor expectedProfessor = criaProfessor();
        Professor professor = adminController.cadastrarProfessor(criaProfessorDTO()).getBody();

        Assertions.assertThat(professor).isNotNull();
        Assertions.assertThat(professor.getId()).isNotNull();
        Assertions.assertThat(professor.getNome()).isEqualTo(expectedProfessor.getNome());
        Assertions.assertThat(professor.getEmail()).isEqualTo(expectedProfessor.getEmail());
        Assertions.assertThat(professor.getSenha()).isEqualTo(expectedProfessor.getSenha());
        Assertions.assertThat(professor.getDisciplina()).isEqualTo(expectedProfessor.getDisciplina());
        Assertions.assertThat(professor.getSobrenome()).isEqualTo(expectedProfessor.getSobrenome());
        Assertions.assertThat(professor.getDataCadastro()).isEqualTo(expectedProfessor.getDataCadastro());
        Assertions.assertThat(professor.getDataUltimaAtualizacao()).isEqualTo(expectedProfessor.getDataUltimaAtualizacao());

        Assertions.assertThat(professor.getEndereco().getNumero()).isEqualTo(expectedProfessor.getEndereco().getNumero());
        Assertions.assertThat(professor.getEndereco().getRua()).isEqualTo(expectedProfessor.getEndereco().getRua());
        Assertions.assertThat(professor.getEndereco().getComplemento()).isEqualTo(expectedProfessor.getEndereco().getComplemento());
        Assertions.assertThat(professor.getEndereco().getBairro()).isEqualTo(expectedProfessor.getEndereco().getBairro());
        Assertions.assertThat(professor.getEndereco().getCidade()).isEqualTo(expectedProfessor.getEndereco().getCidade());
        Assertions.assertThat(professor.getEndereco().getEstado()).isEqualTo(expectedProfessor.getEndereco().getEstado());
        Assertions.assertThat(professor.getEndereco().getCep()).isEqualTo(expectedProfessor.getEndereco().getCep());
    }

    @SneakyThrows
    @Test
    @DisplayName("Deleta professor quando sucesso")
    void deleteProfessor_ReturnsString_QuandoSucesso(){
        String response = adminController.deleteProfessor(criaProfessorDTO()).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo("Sucesso");
    }

    @SneakyThrows
    @Test
    @DisplayName("Salvar usuario admin quando sucesso")
    void save_ReturnsUsuarioAdmin_QuandoSucesso(){
        Usuario expectedUsuarioAdmin = criaUsuarioAdmin();
        Usuario usuarioAdmin = adminController.cadastrarUsuarioAdmin(criaUsuarioAdminDTO()).getBody();

        Assertions.assertThat(usuarioAdmin).isNotNull();
        Assertions.assertThat(usuarioAdmin.getId()).isNotNull();
        Assertions.assertThat(usuarioAdmin.getNome()).isEqualTo(expectedUsuarioAdmin.getNome());
        Assertions.assertThat(usuarioAdmin.getEmail()).isEqualTo(expectedUsuarioAdmin.getEmail());
        Assertions.assertThat(usuarioAdmin.getSenha()).isEqualTo(expectedUsuarioAdmin.getSenha());
        Assertions.assertThat(usuarioAdmin.getDataCadastro()).isEqualTo(expectedUsuarioAdmin.getDataCadastro());
        Assertions.assertThat(usuarioAdmin.getDataUltimaAtualizacao()).isEqualTo(expectedUsuarioAdmin.getDataUltimaAtualizacao());

        Assertions.assertThat(usuarioAdmin.getRoles().get(0)).isEqualTo(expectedUsuarioAdmin.getRoles().get(0));
        Assertions.assertThat(usuarioAdmin.getRoles().get(1)).isEqualTo(expectedUsuarioAdmin.getRoles().get(1));
    }

    @SneakyThrows
    @Test
    @DisplayName("Edita usuario admin quando sucesso")
    void edit_ReturnsUsuarioAdminUpdated_QuandoSucesso(){
        Usuario usuarioUpdated = adminController.editarUsuarioAdmin(criaUsuarioAdminDTO()).getBody();

        Assertions.assertThat(usuarioUpdated).isNotNull();
        Assertions.assertThat(usuarioUpdated.getEmail()).isEqualTo("usuario_updated_teste@teste.com");
    }

    @SneakyThrows
    @Test
    @DisplayName("Deleta usuario admin quando sucesso")
    void deleteUsuarioAdmin_ReturnsString_QuandoSucesso(){
        String response = adminController.deleteUsuarioAdmin(criaUsuarioAdminDTO()).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo("Sucesso");
    }

    private Professor criaProfessor(){
        Professor professor = new Professor();

        professor.setId(1L);
        professor.setNome("Professor Teste");
        professor.setSobrenome("Sobrenome Teste");
        professor.setEmail("professor_teste@teste.com");
        professor.setSenha("senha");
        professor.setDisciplina("disciplina");
        professor.setDataUltimaAtualizacao(new Date(2000, 01, 01));
        professor.setDataCadastro(new Date(2000, 01, 01));

        Endereco endereco = new Endereco(
                null,
                1,
                "Rua Teste",
                "Complemento Teste",
                "Bairro Teste",
                "CEP Teste",
                "Cidade Teste",
                "Estado Teste");
        professor.setEndereco(endereco);

        return professor;
    }

    private ProfessorDTO criaProfessorDTO(){
        ProfessorDTO professorDTO = new ProfessorDTO();

        professorDTO.setNome("Professor Teste");
        professorDTO.setSobrenome("Sobrenome Teste");
        professorDTO.setNumero(1);
        professorDTO.setRua("Rua Teste");
        professorDTO.setBairro("Bairro Teste");
        professorDTO.setComplemento("Complemento Teste");
        professorDTO.setCep("CEP Teste");
        professorDTO.setCidade("Cidade Teste");
        professorDTO.setEstado("Estado Teste");
        professorDTO.setEmail("professor_teste@teste.com");
        professorDTO.setSenha("senha");
        professorDTO.setDisciplina("disciplina");
        professorDTO.setDataUltimaAtualizacao(new Date(2000, 01, 01));
        professorDTO.setDataCadastro(new Date(2000, 01, 01));

        return professorDTO;
    }

    private Usuario criaUsuarioAdmin(){
        Usuario usuario = new Usuario();

        usuario.setId(1L);
        usuario.setNome("Usuario Teste");
        usuario.setEmail("usuario_teste@teste.com");
        usuario.setSenha("senha");
        usuario.setDataCadastro(new Date(2000, 01, 01));
        usuario.setDataUltimaAtualizacao(new Date(2000, 01, 01));

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ADMIN_ROLE"));
        roles.add(new Role("USER_ROLE"));

        usuario.setRoles(roles);

        return usuario;
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
}