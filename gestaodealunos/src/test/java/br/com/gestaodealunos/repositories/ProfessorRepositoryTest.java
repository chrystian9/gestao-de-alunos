package br.com.gestaodealunos.repositories;

import br.com.gestaodealunos.entities.Endereco;
import br.com.gestaodealunos.entities.Professor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para o Professor Repository")
class ProfessorRepositoryTest {

    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    @DisplayName("Salva professor criado quando sucesso")
    void save_PersistProfessor_QuandoSucesso(){
        Professor professorToBeSaved = criaProfessor();
        Professor professorSaved = professorRepository.save(professorToBeSaved);

        Assertions.assertThat(professorSaved).isNotNull();
        Assertions.assertThat(professorSaved.getId()).isNotNull();
        Assertions.assertThat(professorSaved.getNome()).isEqualTo(professorToBeSaved.getNome());
        Assertions.assertThat(professorSaved.getEmail()).isEqualTo(professorToBeSaved.getEmail());
        Assertions.assertThat(professorSaved.getSenha()).isEqualTo(professorToBeSaved.getSenha());
        Assertions.assertThat(professorSaved.getDataCadastro()).isEqualTo(professorToBeSaved.getDataCadastro());
        Assertions.assertThat(professorSaved.getDataUltimaAtualizacao()).isEqualTo(professorToBeSaved.getDataUltimaAtualizacao());

        Assertions.assertThat(professorSaved.getEndereco().getId()).isNotNull();
        Assertions.assertThat(professorSaved.getEndereco().getRua()).isEqualTo(professorToBeSaved.getEndereco().getRua());
        Assertions.assertThat(professorSaved.getEndereco().getNumero()).isEqualTo(professorToBeSaved.getEndereco().getNumero());
        Assertions.assertThat(professorSaved.getEndereco().getBairro()).isEqualTo(professorToBeSaved.getEndereco().getBairro());
        Assertions.assertThat(professorSaved.getEndereco().getComplemento()).isEqualTo(professorToBeSaved.getEndereco().getComplemento());
        Assertions.assertThat(professorSaved.getEndereco().getCep()).isEqualTo(professorToBeSaved.getEndereco().getCep());
        Assertions.assertThat(professorSaved.getEndereco().getCidade()).isEqualTo(professorToBeSaved.getEndereco().getCidade());
        Assertions.assertThat(professorSaved.getEndereco().getEstado()).isEqualTo(professorToBeSaved.getEndereco().getEstado());
    }

    private Professor criaProfessor(){
        Professor professor = new Professor();

        professor.setNome("Professor Teste");
        professor.setSobrenome("Sobrenome Teste");
        professor.setSenha("senha");
        professor.setDisciplina("disciplina");
        professor.setDataUltimaAtualizacao(new Date());
        professor.setDataCadastro(new Date());

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
}