package br.com.gestaodealunos.repositories;

import br.com.gestaodealunos.entities.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Notas, Long> {

}
