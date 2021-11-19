package br.com.gestaodealunos.repositories;

import br.com.gestaodealunos.entities.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para o Role Repository")
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Salva role criada quando sucesso")
    void save_PersistRole_QuandoSucesso(){
        Role roleToBeSaved = criaRole();
        Role roleSaved = roleRepository.save(roleToBeSaved);

        Assertions.assertThat(roleSaved).isNotNull();
        Assertions.assertThat(roleSaved.getId()).isNotNull();
        Assertions.assertThat(roleSaved.getNome()).isEqualTo(roleToBeSaved.getNome());
    }

    @Test
    @DisplayName("Salva role atualizada quando sucesso")
    void save_UpdateRole_QuandoSucesso(){
        Role roleToBeSaved = criaRole();
        Role roleSaved = roleRepository.save(roleToBeSaved);

        roleSaved.setNome("USER_ROLE");
        Role roleUpdated = roleRepository.save(roleSaved);

        Assertions.assertThat(roleUpdated).isNotNull();
        Assertions.assertThat(roleUpdated.getId()).isNotNull();
        Assertions.assertThat(roleUpdated.getNome()).isEqualTo(roleSaved.getNome());
    }

    @Test
    @DisplayName("Delete role removida quando sucesso")
    void delete_DeleteRole_QuandoSucesso(){
        Role roleToBeSaved = criaRole();
        Role roleSaved = roleRepository.save(roleToBeSaved);

        roleRepository.delete(roleSaved);

        Optional<Role> roleOptional = roleRepository.findById(roleSaved.getId());

        Assertions.assertThat(roleOptional.isPresent()).isFalse();
    }

    private Role criaRole(){
        return new Role("ADMIN_ROLE");
    }
}