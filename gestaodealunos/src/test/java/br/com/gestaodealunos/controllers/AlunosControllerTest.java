package br.com.gestaodealunos.controllers;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;


import io.restassured.http.ContentType;

@WebMvcTest
public class AlunosControllerTest {
    @Autowired
    private AlunosController alunosController;

    @MockBean
    private AlunosService alunosService;

    @BeforeEach
    public void setup(){
        standaloneSetup(this.alunosController);
    }

    @Test
    public void deveRetornarSucesso_QuandoSalvar(){
//        when(this.alunoService.salvar()).thenReturn(...)
        given().accept(ContentType.JSON).when().get("/salvar").then().statusCode(HttoStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoListar(){
//        when(this.alunoService.listar()).thenReturn(...);
        given().accept(ContentType.JSON).when().get("/listar").then().statusCode(HttoStatus.OK.value());
    }
    @Test
    public void deveRetornarSucesso_QuandoAtualizar(){
//        when(this.alunoService.cadastrarProfessor()).thenReturn(...);
        given().accept(ContentType.JSON).when().get("/{id}/update-notas").then().statusCode(HttoStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoAtualizarNota(){
//        when(this.alunoService.update()).thenReturn(...);
        given().accept(ContentType.JSON).when().get("/").then().statusCode(HttoStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoRemover(){
//        when(this.alunoService.updateNotas()).thenReturn(...);
        given().accept(ContentType.JSON).when().get("/remover").then().statusCode(HttoStatus.OK.value());
    }

}