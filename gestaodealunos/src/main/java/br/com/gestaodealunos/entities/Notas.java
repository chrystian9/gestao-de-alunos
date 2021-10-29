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
public class Notas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nota_um")
    private Double notaUm;

    @NotNull
    @Column(name = "nota_dois")
    private Double notaDois;

    @NotNull
    @Column(name = "nota_tres")
    private Double notaTres;

    @NotNull
    @Column(name = "nota_quatro")
    private Double notaQuatro;

    @NotNull
    @Column(name = "nota_final")
    private Double notaFinal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="aluno_id", nullable=false)
    private Aluno aluno;
}
