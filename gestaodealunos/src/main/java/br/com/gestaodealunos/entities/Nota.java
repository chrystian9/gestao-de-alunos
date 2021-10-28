package br.com.gestaodealunos.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "bimestre")
    private Integer bimestre;

    @NotNull
    @Column(name = "nota")
    private Double Nota;

    @ManyToOne
    @JoinColumn(name="aluno_id", nullable=false)
    private Aluno aluno;
}
