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
public class ProfessorControllerTest {
    @Autowired
    private ProfessorController professorController;

    @MockBean
    private ProfessorController professorService;

    @BeforeEach
    public void setup(){
        standaloneSetup(this.professorController);
    }

    @Test
    public void deveRetornarSucesso_QuandoEditar(){
//        when(this.professorService.salvar()).thenReturn(...);
        given().accept(ContentType.JSON).when().get("/editar-professor").then().statusCode(HttoStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoPegar(){
//        when(this.professorService.listar()).thenReturn(...);
        given().accept(ContentType.JSON).when().get("/professor/{id}").then().statusCode(HttoStatus.OK.value());
    }

}