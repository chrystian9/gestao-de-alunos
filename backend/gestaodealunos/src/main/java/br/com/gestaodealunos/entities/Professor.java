package br.com.gestaodealunos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Professor extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "data_cadastro")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dataCadastro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    @JsonIgnore
    @Column(name = "path_foto")
    private String pathFoto;

    public Professor(String nome, String email, String senha){
        super(nome, email, senha);
    }
}
