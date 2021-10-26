package br.com.gestaodealunos.repositories;

import br.com.gestaodealunos.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
