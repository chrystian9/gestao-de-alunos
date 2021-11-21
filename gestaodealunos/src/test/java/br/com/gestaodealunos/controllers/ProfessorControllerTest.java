package br.com.gestaodealunos.controllers;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.Mockito.*;

import br.com.gestaodealunos.dto.ProfessorDTO;
import br.com.gestaodealunos.entities.Endereco;
import br.com.gestaodealunos.entities.Professor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;


import java.util.Date;

@WebMvcTest
public class ProfessorControllerTest {
    @Autowired
    private ProfessorController professorController;

    @MockBean
    private ProfessorController professorService;

    @BeforeEach
    public void setup(){
        Professor professorUpdated = criaProfessor();
        BDDMockito.when(professorService.editarProfessor(ArgumentMatchers.any())).thenReturn(professorUpdated);

        BDDMockito.when(professorService.getProfessor(ArgumentMatchers.any())).thenReturn(professorUpdated);
    }

    @SneakyThrows
    @Test
    @DisplayName("Edita professor quando sucesso")
    void edit_ReturnsProfessorUpdated_QuandoSucesso(){
        Professor professorUpdated = professorController.editarProfessor(criaProfessorDTO()).getBody();

        Assertions.assertThat(professorUpdated).isNotNull();
        Assertions.assertThat(professorUpdated.getDisciplina()).isEqualTo("disciplinaUpdated");
    }

    @SneakyThrows
    @Test
    void get_ReturnsProfessor_QuandoSucesso(){
        Professor professor = professorController.getProfessor(criaProfessor().getId()).getBody();

        Assertions.assertThat(professor).isNotNull();
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
}