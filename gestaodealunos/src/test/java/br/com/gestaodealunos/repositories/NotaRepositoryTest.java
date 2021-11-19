package br.com.gestaodealunos.repositories;

import br.com.gestaodealunos.entities.Aluno;
import br.com.gestaodealunos.entities.Notas;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para o Nota Repository")
class NotaRepositoryTest {

    @Autowired
    private NotaRepository notaRepository;

    @Test
    @DisplayName("Salva notas criadas quando sucesso")
    void save_PersistNotas_QuandoSucesso(){
        Notas notasToBeSaved = criaNotas();
        Notas notasSaved = notaRepository.save(notasToBeSaved);

        Assertions.assertThat(notasSaved).isNotNull();
        Assertions.assertThat(notasSaved.getId()).isNotNull();
        Assertions.assertThat(notasSaved.getNotaUm()).isEqualTo(notasToBeSaved.getNotaUm());
        Assertions.assertThat(notasSaved.getNotaDois()).isEqualTo(notasToBeSaved.getNotaDois());
        Assertions.assertThat(notasSaved.getNotaTres()).isEqualTo(notasToBeSaved.getNotaTres());
        Assertions.assertThat(notasSaved.getNotaQuatro()).isEqualTo(notasToBeSaved.getNotaQuatro());
        Assertions.assertThat(notasSaved.getNotaFinal()).isEqualTo(notasToBeSaved.getNotaFinal());
    }

    @Test
    @DisplayName("Salva notas atualizadas quando sucesso")
    void save_UpdateNotas_QuandoSucesso(){
        Notas notasToBeSaved = criaNotas();
        Notas notasSaved = notaRepository.save(notasToBeSaved);

        notasSaved.setNotaUm(24D);
        notasSaved.setNotaDois(24D);
        notasSaved.setNotaTres(24D);
        notasSaved.setNotaQuatro(24D);
        notasSaved.setNotaFinal(96D);

        Notas notasUpdated = notaRepository.save(notasSaved);

        Assertions.assertThat(notasUpdated).isNotNull();
        Assertions.assertThat(notasUpdated.getId()).isNotNull();
        Assertions.assertThat(notasUpdated.getNotaUm()).isEqualTo(notasSaved.getNotaUm());
        Assertions.assertThat(notasUpdated.getNotaDois()).isEqualTo(notasSaved.getNotaDois());
        Assertions.assertThat(notasUpdated.getNotaTres()).isEqualTo(notasSaved.getNotaTres());
        Assertions.assertThat(notasUpdated.getNotaQuatro()).isEqualTo(notasSaved.getNotaQuatro());
        Assertions.assertThat(notasUpdated.getNotaFinal()).isEqualTo(notasSaved.getNotaFinal());
    }

    @Test
    @DisplayName("Deleta notas removidas quando sucesso")
    void delete_RemoveNotas_QuandoSucesso(){
        Notas notasToBeSaved = criaNotas();
        Notas notasSaved = notaRepository.save(notasToBeSaved);

        notaRepository.delete(notasSaved);

        Optional<Notas> notasOptional = notaRepository.findById(notasSaved.getId());

        Assertions.assertThat(notasOptional.isPresent()).isFalse();
    }

    private Notas criaNotas(){
        return new Notas(
                null,
                25D,
                25D,
                25D,
                25D,
                100D,
                new Aluno());
    }
}