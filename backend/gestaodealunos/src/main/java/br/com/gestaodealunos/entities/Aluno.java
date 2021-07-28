package br.com.gestaodealunos.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

import java.nio.file.Path;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotNull
    private String nome;

    @OneToMany(mappedBy="aluno")
    private Endereco endereco;

    @Column(name = "path_foto")
    private Path pathFoto;
}
