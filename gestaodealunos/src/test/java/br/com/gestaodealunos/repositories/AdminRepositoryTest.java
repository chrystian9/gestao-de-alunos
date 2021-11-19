package br.com.gestaodealunos.repositories;

import br.com.gestaodealunos.entities.Admin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para o Admin Repository")
class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    @DisplayName("Salva admin criado quando sucesso")
    void save_PersistAdmin_QuandoSucesso(){
        Admin adminToBeSaved = criaAdmin();
        Admin adminSaved = adminRepository.save(adminToBeSaved);

        Assertions.assertThat(adminSaved).isNotNull();
        Assertions.assertThat(adminSaved.getId()).isNotNull();
        Assertions.assertThat(adminSaved.getNome()).isEqualTo(adminToBeSaved.getNome());
        Assertions.assertThat(adminSaved.getEmail()).isEqualTo(adminToBeSaved.getEmail());
        Assertions.assertThat(adminSaved.getSenha()).isEqualTo(adminToBeSaved.getSenha());
        Assertions.assertThat(adminSaved.getDataCadastro()).isEqualTo(adminToBeSaved.getDataCadastro());
        Assertions.assertThat(adminSaved.getDataUltimaAtualizacao()).isEqualTo(adminToBeSaved.getDataUltimaAtualizacao());
    }

    @Test
    @DisplayName("Salva admin atualizado quando sucesso")
    void save_UpdateAdmin_QuandoSucesso(){
        Admin adminToBeSaved = criaAdmin();
        Admin adminSaved = adminRepository.save(adminToBeSaved);

        adminSaved.setNome("Admin Teste Update");
        adminSaved.setSenha("senhaUpdate");
        adminSaved.setDataCadastro(new Date(2000, 01, 01));
        adminSaved.setDataUltimaAtualizacao(new Date(2021, 01, 01));
        adminSaved.setEmail("admin_teste_update@teste.com");

        Admin adminUpdated = adminRepository.save(adminSaved);

        Assertions.assertThat(adminUpdated).isNotNull();
        Assertions.assertThat(adminUpdated.getId()).isNotNull();
        Assertions.assertThat(adminUpdated.getNome()).isEqualTo(adminSaved.getNome());
        Assertions.assertThat(adminUpdated.getEmail()).isEqualTo(adminSaved.getEmail());
        Assertions.assertThat(adminUpdated.getSenha()).isEqualTo(adminSaved.getSenha());
        Assertions.assertThat(adminUpdated.getDataCadastro()).isEqualTo(adminSaved.getDataCadastro());
        Assertions.assertThat(adminUpdated.getDataUltimaAtualizacao()).isEqualTo(adminSaved.getDataUltimaAtualizacao());
    }

    @Test
    @DisplayName("Deleta admin removido quando sucesso")
    void delete_RemoveAdmin_QuandoSucesso(){
        Admin adminToBeSaved = criaAdmin();
        Admin adminSaved = adminRepository.save(adminToBeSaved);

        adminRepository.delete(adminSaved);

        Optional<Admin> adminOptional = adminRepository.findById(adminSaved.getId());

        Assertions.assertThat(adminOptional.isPresent()).isFalse();
    }

    private Admin criaAdmin(){
        return new Admin(
                "Admin Teste",
                "admin_teste@teste.com",
                "senha",
                new Date(),
                new Date());
    }
}