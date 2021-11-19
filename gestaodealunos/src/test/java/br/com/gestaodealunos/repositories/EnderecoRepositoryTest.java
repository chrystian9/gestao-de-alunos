package br.com.gestaodealunos.repositories;

import br.com.gestaodealunos.entities.Endereco;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para o Endereco Repository")
class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    @DisplayName("Salva endereco criado quando sucesso")
    void save_PersistEndereco_QuandoSucesso(){
        Endereco enderecoToBeSaved = criaEndereco();
        Endereco enderecoSaved = enderecoRepository.save(enderecoToBeSaved);

        Assertions.assertThat(enderecoSaved).isNotNull();
        Assertions.assertThat(enderecoSaved.getId()).isNotNull();
        Assertions.assertThat(enderecoSaved.getRua()).isEqualTo(enderecoToBeSaved.getRua());
        Assertions.assertThat(enderecoSaved.getBairro()).isEqualTo(enderecoToBeSaved.getBairro());
        Assertions.assertThat(enderecoSaved.getComplemento()).isEqualTo(enderecoToBeSaved.getComplemento());
        Assertions.assertThat(enderecoSaved.getCidade()).isEqualTo(enderecoToBeSaved.getCidade());
        Assertions.assertThat(enderecoSaved.getEstado()).isEqualTo(enderecoToBeSaved.getEstado());
        Assertions.assertThat(enderecoSaved.getCep()).isEqualTo(enderecoToBeSaved.getCep());
        Assertions.assertThat(enderecoSaved.getNumero()).isEqualTo(enderecoToBeSaved.getNumero());
    }

    @Test
    @DisplayName("Salva endereco atualizado quando sucesso")
    void save_UpdateEndereco_QuandoSucesso(){
        Endereco enderecoToBeSaved = criaEndereco();
        Endereco enderecoSaved = enderecoRepository.save(enderecoToBeSaved);

        enderecoSaved.setRua("Rua Teste Update");
        enderecoSaved.setNumero(2);
        enderecoSaved.setBairro("Bairro Teste Update");
        enderecoSaved.setComplemento("Complemento Teste Update");
        enderecoSaved.setCidade("Cidade Teste Update");
        enderecoSaved.setEstado("Estado Teste Update");
        enderecoSaved.setCep("CEP Teste Update");

        Endereco enderecoUpdated = enderecoRepository.save(enderecoSaved);

        Assertions.assertThat(enderecoUpdated).isNotNull();
        Assertions.assertThat(enderecoUpdated.getId()).isNotNull();
        Assertions.assertThat(enderecoUpdated.getRua()).isEqualTo(enderecoSaved.getRua());
        Assertions.assertThat(enderecoUpdated.getBairro()).isEqualTo(enderecoSaved.getBairro());
        Assertions.assertThat(enderecoUpdated.getComplemento()).isEqualTo(enderecoSaved.getComplemento());
        Assertions.assertThat(enderecoUpdated.getCidade()).isEqualTo(enderecoSaved.getCidade());
        Assertions.assertThat(enderecoUpdated.getEstado()).isEqualTo(enderecoSaved.getEstado());
        Assertions.assertThat(enderecoUpdated.getCep()).isEqualTo(enderecoSaved.getCep());
        Assertions.assertThat(enderecoUpdated.getNumero()).isEqualTo(enderecoSaved.getNumero());
    }

    @Test
    @DisplayName("Deleta endereco removido quando sucesso")
    void delete_RemoveEndereco_QuandoSucesso(){
        Endereco enderecoToBeSaved = criaEndereco();
        Endereco enderecoSaved = enderecoRepository.save(enderecoToBeSaved);

        enderecoRepository.delete(enderecoSaved);

        Optional<Endereco> enderecoOptional = enderecoRepository.findById(enderecoSaved.getId());

        Assertions.assertThat(enderecoOptional.isPresent()).isFalse();
    }

    private Endereco criaEndereco(){
        return new Endereco(
                null,
                1,
                "Rua Teste",
                "Complemento Teste",
                "Bairro Teste",
                "CEP Teste",
                "Cidade Teste",
                "Estado Teste");
    }
}