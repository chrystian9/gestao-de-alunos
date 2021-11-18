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
public class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;
    
    @BeforeEach
    public void setup(){
        standaloneSetup(this.adminController);
    }

    @Test 
    public void deveRetornarSucesso_QuandoCadastrarProfessor(){
        //Acho que tenho que criar um Professor DTO
//        when(this.adminService.cadastrarProfessor()).thenReturn(...);

        given().accept(ContentType.JSON).when().get("/cadastrar-professor").then().statusCode(HttoStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoCadastrarUsuarioAdmin(){
        //Acho que tenho que criar um Professor DTO
//        when(this.adminService.cadastrarUsuarioAdmin()).thenReturn(...);
        
        given().accept(ContentType.JSON).when().get("/cadastrar-usuario-admin").then().statusCode(HttoStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoEditarUsuarioAdmin(){
        //Acho que tenho que criar um Professor DTO
//        when(this.adminService.editarUsuarioAdmin()).thenReturn(...);
        
        given().accept(ContentType.JSON).when().get("/editar-usuario-admin").then().statusCode(HttoStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoEditarProfessor(){
        //Acho que tenho que criar um Professor DTO
//        when(this.adminService.editarProfessor()).thenReturn(...);
        
        given().accept(ContentType.JSON).when().get("/editar-professor").then().statusCode(HttoStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoDeletarUsuarioAdmin(){
        //Acho que tenho que criar um Professor DTO
//        when(this.adminService.deleteUsuarioAdmin()).thenReturn(...);
        
        given().accept(ContentType.JSON).when().get("/delete-usuario-admin").then().statusCode(HttoStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoDeletarProfessor(){
        //Acho que tenho que criar um Professor DTO
//        when(this.adminService.deleteProfessor()).thenReturn(...);
        
        given().accept(ContentType.JSON).when().get("/delete-professor").then().statusCode(HttoStatus.OK.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoListarProfessores(){
        //Acho que tenho que criar um Professor DTO
//        when(this.adminService.listarProfessores()).thenReturn(...);
        
        given().accept(ContentType.JSON).when().get("/listar/professores").then().statusCode(HttoStatus.OK.value());
    }
}