package br.com.gestaodealunos.repositories;

import br.com.gestaodealunos.entities.Aluno;
import br.com.gestaodealunos.entities.Endereco;
import br.com.gestaodealunos.entities.Notas;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para o Aluno Repository")
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Salva aluno criado quando sucesso")
    void save_PersistAluno_QuandoSucesso() {
        Aluno alunoToBeSaved = criaAluno();
        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);

        Assertions.assertThat(alunoSaved).isNotNull();
        Assertions.assertThat(alunoSaved.getId()).isNotNull();
        Assertions.assertThat(alunoSaved.getNome()).isEqualTo(alunoToBeSaved.getNome());
        Assertions.assertThat(alunoSaved.getSobrenome()).isEqualTo(alunoToBeSaved.getSobrenome());
        Assertions.assertThat(alunoSaved.getDataCadastro()).isEqualTo(alunoToBeSaved.getDataCadastro());
        Assertions.assertThat(alunoSaved.getDataUltimaAtualizacao()).isEqualTo(alunoToBeSaved.getDataUltimaAtualizacao());

        Assertions.assertThat(alunoSaved.getNotas().getId()).isNotNull();
        Assertions.assertThat(alunoSaved.getNotas().getNotaUm()).isEqualTo(alunoToBeSaved.getNotas().getNotaUm());
        Assertions.assertThat(alunoSaved.getNotas().getNotaDois()).isEqualTo(alunoToBeSaved.getNotas().getNotaDois());
        Assertions.assertThat(alunoSaved.getNotas().getNotaTres()).isEqualTo(alunoToBeSaved.getNotas().getNotaTres());
        Assertions.assertThat(alunoSaved.getNotas().getNotaQuatro()).isEqualTo(alunoToBeSaved.getNotas().getNotaQuatro());
        Assertions.assertThat(alunoSaved.getNotas().getNotaFinal()).isEqualTo(alunoToBeSaved.getNotas().getNotaFinal());

        Assertions.assertThat(alunoSaved.getEndereco().getId()).isNotNull();
        Assertions.assertThat(alunoSaved.getEndereco().getRua()).isEqualTo(alunoToBeSaved.getEndereco().getRua());
        Assertions.assertThat(alunoSaved.getEndereco().getNumero()).isEqualTo(alunoToBeSaved.getEndereco().getNumero());
        Assertions.assertThat(alunoSaved.getEndereco().getBairro()).isEqualTo(alunoToBeSaved.getEndereco().getBairro());
        Assertions.assertThat(alunoSaved.getEndereco().getComplemento()).isEqualTo(alunoToBeSaved.getEndereco().getComplemento());
        Assertions.assertThat(alunoSaved.getEndereco().getCep()).isEqualTo(alunoToBeSaved.getEndereco().getCep());
        Assertions.assertThat(alunoSaved.getEndereco().getCidade()).isEqualTo(alunoToBeSaved.getEndereco().getCidade());
        Assertions.assertThat(alunoSaved.getEndereco().getEstado()).isEqualTo(alunoToBeSaved.getEndereco().getEstado());
    }

    @Test
    @DisplayName("Salva aluno atualizado quando sucesso")
    void save_UpdateAluno_QuandoSucesso(){
        Aluno alunoToBeSaved = criaAluno();
        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);

        alunoSaved.setNome("Admin Teste Update");
        alunoSaved.setSobrenome("Admin Teste Update");
        alunoSaved.setDataCadastro(new Date(2000, 01, 01));
        alunoSaved.setDataUltimaAtualizacao(new Date(2021, 01, 01));

        alunoSaved.getEndereco().setRua("Rua Teste Update");
        alunoSaved.getEndereco().setNumero(2);
        alunoSaved.getEndereco().setBairro("Bairro Teste Update");
        alunoSaved.getEndereco().setCidade("Cidade Teste Update");
        alunoSaved.getEndereco().setEstado("Estado Teste Update");
        alunoSaved.getEndereco().setCep("CEP Teste Update");
        alunoSaved.getEndereco().setComplemento("Complemento Teste Update");

        alunoSaved.getNotas().setNotaUm(24D);
        alunoSaved.getNotas().setNotaDois(24D);
        alunoSaved.getNotas().setNotaTres(24D);
        alunoSaved.getNotas().setNotaQuatro(24D);
        alunoSaved.getNotas().setNotaFinal(96D);

        Aluno alunoUpdated = alunoRepository.save(alunoSaved);

        Assertions.assertThat(alunoUpdated).isNotNull();
        Assertions.assertThat(alunoUpdated.getId()).isNotNull();
        Assertions.assertThat(alunoUpdated.getNome()).isEqualTo(alunoSaved.getNome());
        Assertions.assertThat(alunoUpdated.getSobrenome()).isEqualTo(alunoSaved.getSobrenome());
        Assertions.assertThat(alunoUpdated.getDataCadastro()).isEqualTo(alunoSaved.getDataCadastro());
        Assertions.assertThat(alunoUpdated.getDataUltimaAtualizacao()).isEqualTo(alunoSaved.getDataUltimaAtualizacao());

        Assertions.assertThat(alunoUpdated.getNotas().getId()).isNotNull();
        Assertions.assertThat(alunoUpdated.getNotas().getNotaUm()).isEqualTo(alunoSaved.getNotas().getNotaUm());
        Assertions.assertThat(alunoUpdated.getNotas().getNotaDois()).isEqualTo(alunoSaved.getNotas().getNotaDois());
        Assertions.assertThat(alunoUpdated.getNotas().getNotaTres()).isEqualTo(alunoSaved.getNotas().getNotaTres());
        Assertions.assertThat(alunoUpdated.getNotas().getNotaQuatro()).isEqualTo(alunoSaved.getNotas().getNotaQuatro());
        Assertions.assertThat(alunoUpdated.getNotas().getNotaFinal()).isEqualTo(alunoSaved.getNotas().getNotaFinal());

        Assertions.assertThat(alunoUpdated.getEndereco().getId()).isNotNull();
        Assertions.assertThat(alunoUpdated.getEndereco().getRua()).isEqualTo(alunoSaved.getEndereco().getRua());
        Assertions.assertThat(alunoUpdated.getEndereco().getNumero()).isEqualTo(alunoSaved.getEndereco().getNumero());
        Assertions.assertThat(alunoUpdated.getEndereco().getBairro()).isEqualTo(alunoSaved.getEndereco().getBairro());
        Assertions.assertThat(alunoUpdated.getEndereco().getComplemento()).isEqualTo(alunoSaved.getEndereco().getComplemento());
        Assertions.assertThat(alunoUpdated.getEndereco().getCep()).isEqualTo(alunoSaved.getEndereco().getCep());
        Assertions.assertThat(alunoUpdated.getEndereco().getCidade()).isEqualTo(alunoSaved.getEndereco().getCidade());
        Assertions.assertThat(alunoUpdated.getEndereco().getEstado()).isEqualTo(alunoSaved.getEndereco().getEstado());
    }

    @Test
    @DisplayName("Deleta aluno removido quando sucesso")
    void delete_RemoveAluno_QuandoSucesso(){
        Aluno alunoToBeSaved = criaAluno();
        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);

        alunoRepository.delete(alunoSaved);

        Optional<Aluno> alunoOptional = alunoRepository.findById(alunoSaved.getId());

        Assertions.assertThat(alunoOptional.isPresent()).isFalse();
    }

    private Aluno criaAluno(){
        Aluno aluno = new Aluno();

        aluno.setNome("Aluno Teste");
        aluno.setSobrenome("Sobrenome Teste");
        aluno.setDataCadastro(new Date());
        aluno.setDataUltimaAtualizacao(new Date());

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

        Notas notas = new Notas(null, 25D, 25D, 25D, 25D, 100D, aluno);

        aluno.setNotas(notas);

        return aluno;
    }
}