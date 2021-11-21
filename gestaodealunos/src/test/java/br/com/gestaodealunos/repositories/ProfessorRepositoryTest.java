package br.com.gestaodealunos.repositories;

import br.com.gestaodealunos.entities.Endereco;
import br.com.gestaodealunos.entities.Professor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

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

    @Test
    @DisplayName("Salva professor alterado quando sucesso")
    void save_UpdateProfessor_QuandoSucesso(){
        Professor professorToBeSaved = criaProfessor();
        Professor professorSaved = professorRepository.save(professorToBeSaved);

        professorSaved.setEmail("professor_updated_teste@teste.com");
        professorSaved.setSenha("senha_updated");
        professorSaved.setNome("Professor Updated Teste");
        professorSaved.setSobrenome("Sobrenome Updated Teste");
        professorSaved.setDisciplina("Disciplina Updated Teste");
        professorSaved.setDataCadastro(new Date(2000, 01, 01));
        professorSaved.setDataUltimaAtualizacao(new Date(2000, 01, 01));
        professorSaved.getEndereco().setRua("Rua Updated Teste");
        professorSaved.getEndereco().setComplemento("Complemento Updated Teste");
        professorSaved.getEndereco().setNumero(2);
        professorSaved.getEndereco().setBairro("Bairro Updated Teste");
        professorSaved.getEndereco().setCidade("Cidade Updated Teste");
        professorSaved.getEndereco().setEstado("Estado Updated Teste");
        professorSaved.getEndereco().setCep("CEP Updated Teste");

        Professor professorUpdated = professorRepository.save(professorSaved);

        Assertions.assertThat(professorUpdated).isNotNull();
        Assertions.assertThat(professorUpdated.getId()).isNotNull();
        Assertions.assertThat(professorUpdated.getNome()).isEqualTo(professorSaved.getNome());
        Assertions.assertThat(professorUpdated.getEmail()).isEqualTo(professorSaved.getEmail());
        Assertions.assertThat(professorUpdated.getSenha()).isEqualTo(professorSaved.getSenha());
        Assertions.assertThat(professorUpdated.getDataCadastro()).isEqualTo(professorSaved.getDataCadastro());
        Assertions.assertThat(professorUpdated.getDataUltimaAtualizacao()).isEqualTo(professorSaved.getDataUltimaAtualizacao());

        Assertions.assertThat(professorUpdated.getEndereco().getId()).isNotNull();
        Assertions.assertThat(professorUpdated.getEndereco().getRua()).isEqualTo(professorSaved.getEndereco().getRua());
        Assertions.assertThat(professorUpdated.getEndereco().getNumero()).isEqualTo(professorSaved.getEndereco().getNumero());
        Assertions.assertThat(professorUpdated.getEndereco().getBairro()).isEqualTo(professorSaved.getEndereco().getBairro());
        Assertions.assertThat(professorUpdated.getEndereco().getComplemento()).isEqualTo(professorSaved.getEndereco().getComplemento());
        Assertions.assertThat(professorUpdated.getEndereco().getCep()).isEqualTo(professorSaved.getEndereco().getCep());
        Assertions.assertThat(professorUpdated.getEndereco().getCidade()).isEqualTo(professorSaved.getEndereco().getCidade());
        Assertions.assertThat(professorUpdated.getEndereco().getEstado()).isEqualTo(professorSaved.getEndereco().getEstado());
    }

    @Test
    @DisplayName("Deleta professor removido quando sucesso")
    void delete_RemoveProfessor_QuandoSucesso(){
        Professor professorToBeSaved = criaProfessor();
        Professor professorSaved = professorRepository.save(professorToBeSaved);

        professorRepository.delete(professorSaved);

        Optional<Professor> professorOptional = professorRepository.findById(professorSaved.getId());

        Assertions.assertThat(professorOptional.isPresent()).isFalse();
    }

    private Professor criaProfessor(){
        Professor professor = new Professor();

        professor.setNome("Professor Teste");
        professor.setSobrenome("Sobrenome Teste");
        professor.setEmail("professor_teste@teste.com");
        professor.setSenha("senha");
        professor.setDisciplina("Disciplina Teste");
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