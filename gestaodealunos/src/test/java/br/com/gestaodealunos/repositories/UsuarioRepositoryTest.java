package br.com.gestaodealunos.repositories;

import br.com.gestaodealunos.entities.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para o Usuario Repository")
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Salva usuario criada quando sucesso")
    void save_PersistUsuario_QuandoSucesso(){
        Usuario usuarioToBeSaved = criaUsuario();
        Usuario usuarioSaved = usuarioRepository.save(usuarioToBeSaved);

        Assertions.assertThat(usuarioSaved).isNotNull();
        Assertions.assertThat(usuarioSaved.getId()).isNotNull();
        Assertions.assertThat(usuarioSaved.getNome()).isEqualTo(usuarioToBeSaved.getNome());
        Assertions.assertThat(usuarioSaved.getEmail()).isEqualTo(usuarioToBeSaved.getEmail());
        Assertions.assertThat(usuarioSaved.getSenha()).isEqualTo(usuarioToBeSaved.getSenha());
        Assertions.assertThat(usuarioSaved.getDataCadastro()).isEqualTo(usuarioToBeSaved.getDataCadastro());
        Assertions.assertThat(usuarioSaved.getDataUltimaAtualizacao()).isEqualTo(usuarioToBeSaved.getDataUltimaAtualizacao());
    }

    @Test
    @DisplayName("Salva usuario atualizado quando sucesso")
    void save_UpdateUsuario_QuandoSucesso(){
        Usuario usuarioToBeSaved = criaUsuario();
        Usuario usuarioSaved = usuarioRepository.save(usuarioToBeSaved);

        usuarioSaved.setNome("Admin Teste Update");
        usuarioSaved.setSenha("senhaUpdate");
        usuarioSaved.setDataCadastro(new Date(2000, 01, 01));
        usuarioSaved.setDataUltimaAtualizacao(new Date(2021, 01, 01));
        usuarioSaved.setEmail("admin_teste_update@teste.com");

        Usuario usuarioUpdated = usuarioRepository.save(usuarioSaved);

        Assertions.assertThat(usuarioUpdated).isNotNull();
        Assertions.assertThat(usuarioUpdated.getId()).isNotNull();
        Assertions.assertThat(usuarioUpdated.getNome()).isEqualTo(usuarioSaved.getNome());
        Assertions.assertThat(usuarioUpdated.getEmail()).isEqualTo(usuarioSaved.getEmail());
        Assertions.assertThat(usuarioUpdated.getSenha()).isEqualTo(usuarioSaved.getSenha());
        Assertions.assertThat(usuarioUpdated.getDataCadastro()).isEqualTo(usuarioSaved.getDataCadastro());
        Assertions.assertThat(usuarioUpdated.getDataUltimaAtualizacao()).isEqualTo(usuarioSaved.getDataUltimaAtualizacao());
    }

    @Test
    @DisplayName("Deleta usuario removido quando sucesso")
    void delete_RemoveUsuario_QuandoSucesso(){
        Usuario usuarioToBeSaved = criaUsuario();
        Usuario usuarioSaved = usuarioRepository.save(usuarioToBeSaved);

        usuarioRepository.delete(usuarioSaved);

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioSaved.getId());

        Assertions.assertThat(usuarioOptional.isPresent()).isFalse();
    }

    @Test
    @DisplayName("Retorna usuario por email quando sucesso")
    void findByEmail_ReturnUsuario_QuandoSucesso(){
        Usuario usuarioToBeSaved = criaUsuario();
        Usuario usuarioSaved = usuarioRepository.save(usuarioToBeSaved);

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuarioSaved.getEmail());

        Assertions.assertThat(usuarioOptional.isPresent()).isTrue();
        Assertions.assertThat(usuarioOptional.get()).isEqualTo(usuarioSaved);
    }

    @Test
    @DisplayName("Retorna vazio quando usuario não encontrado")
    void findByEmail_ReturnVazio_QuandoUsuarioNaoEncontrado(){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail("usuario_teste@teste.com");

        Assertions.assertThat(usuarioOptional.isPresent()).isFalse();
    }

    private Usuario criaUsuario(){
        return new Usuario(
                "Usuario Teste",
                "usuario_teste@teste.com",
                "senha",
                new Date(),
                new Date());
    }
}