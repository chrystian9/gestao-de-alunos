package br.com.gestaodealunos.controllers;


import br.com.gestaodealunos.dto.AlunoDTO;
import br.com.gestaodealunos.dto.NotasDTO;
import br.com.gestaodealunos.entities.*;

import br.com.gestaodealunos.services.AlunoService;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

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
public class AlunosControllerTest {

    @InjectMocks
    private AlunosController alunosController;

    @Mock
    private AlunoService alunoService;

    @SneakyThrows
    @BeforeEach
    public void setup(){
        List<Aluno> alunos = new ArrayList<>();
        Aluno aluno = criaAluno();
        alunos.add(aluno);

        BDDMockito.when(alunoService.salvar(ArgumentMatchers.any())).thenReturn(aluno);
        BDDMockito.when(alunoService.listarAlunos()).thenReturn(alunos);

        Aluno alunoUpdated = criaAluno();
        BDDMockito.when(alunoService.update(ArgumentMatchers.any())).thenReturn(alunoUpdated);
    }

    @SneakyThrows
    @Test
    public void save_ReturnsAluno_QuandoSucesso(){
        Aluno aluno= alunosController.salvar(criaAlunoDTO()).getBody();

        Assertions.assertThat(aluno).isNotNull();
    }

    @SneakyThrows
    @Test
    public void list_ReturnsListAluno_QuandoSucesso(){//listar
        List<Aluno> alunos = alunosController.listar().getBody();

        Assertions.assertThat(alunos).isNotNull();
    }

    @SneakyThrows
    @Test
    public void update_ReturnsAluno_QuandoSucesso(){//atualizar
        Aluno aluno= alunosController.update(criaAlunoDTO()).getBody();

        Assertions.assertThat(aluno).isNotNull();
    }

    @SneakyThrows
    @Test
    public void updateNotas_ReturnsAluno_QuandoSucesso(){//atualizar nota
        Aluno aluno= alunosController.updateNotas(criaNotasDTO(), criaAluno().getId()).getBody();

        Assertions.assertThat(aluno).isNotNull();
    }
    @SneakyThrows
    @Test
    public void delete_ReturnsString_QuandoSucesso(){//deletar
        String response = alunosController.remover(criaAlunoDTO()).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo("Sucesso");
    }


    private AlunoDTO criaAlunoDTO(){
        AlunoDTO aluno = new AlunoDTO();

        aluno.setId(1L);
        aluno.setNome("Aluno Teste");
        aluno.setSobrenome("Sobrenome Teste");
        aluno.setDataUltimaAtualizacao(new Date(2000, 01, 01));
        aluno.setDataCadastro(new Date(2000, 01, 01));
        aluno.setRua("Aluno Teste");;
        aluno.setNumero(1L);
        aluno.setComplemento("Aluno Teste");;
        aluno.setBairro("Aluno Teste");;
        aluno.setCep("Aluno Teste");;
        aluno.setCidade("Aluno Teste");;
        aluno.setEstado("Aluno Teste");;
        aluno.setNotaUm(1L);
        aluno.setNotaDois(1);
        aluno.setNotaTres(1L);
        aluno.setNotaQuatro(1L);
        aluno.setNotaFinal(1L);

        return aluno;
    }

    private Aluno criaAluno(){
        Aluno aluno = new Aluno();

        aluno.setId(1L);
        aluno.setNome("Aluno Teste");
        aluno.setSobrenome("Sobrenome Teste");
        aluno.setDataUltimaAtualizacao(new Date(2000, 01, 01));
        aluno.setDataCadastro(new Date(2000, 01, 01));

        Endereco endereco = new Endereco(
                null,
                1,
                "Rua Teste",
                "Complemento Teste",
                "Bairro Teste",
                "CEP Teste",
                "Cidade Teste",
                "Estado Teste");
        aluno.setEndereco(endereco);
        aluno.setNotas(criaNotas());


        return aluno;
    }
    private NotasDTO criaNotasDTO(){
        NotasDTO notasDTO = new NotasDTO();
        notasDTO.setNotaUm(1.0);
        notasDTO.setNotaDois(1.0);
        notasDTO.setNotaTres(1.0);
        notasDTO.setNotaQuatro(1.0);
        notasDTO.setNotaFinal(1.0);

        return notasDTO;
    }

    private NotasDTO criaNotas() {
        Notas notas = new Notas();
        notas.setNotaUm(1.0);
        notas.setNotaDois(1.0);
        notas.setNotaTres(1.0);
        notas.setNotaQuatro(1.0);
        notas.setNotaFinal(1.0);

        return notas;
    }
}