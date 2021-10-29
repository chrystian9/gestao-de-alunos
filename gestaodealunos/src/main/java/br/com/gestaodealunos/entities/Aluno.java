package br.com.gestaodealunos.entities;

import br.com.gestaodealunos.dto.AlunoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "data_cadastro")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dataCadastro;

    @Column(name = "data_ultima_atualizacao")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dataUltimaAtualizacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy="aluno")
    private Notas notas;

    /*
    @JsonIgnore
    @Column(name = "path_foto")
    private String pathFoto;
    */

    public Aluno(AlunoDTO alunoDTO){
        this.id = alunoDTO.id;
        this.nome = alunoDTO.nome;
        this.sobrenome = alunoDTO.sobrenome;
        this.dataUltimaAtualizacao= new Date();
        this.dataCadastro = new Date();
        this.endereco = new Endereco();

        this.endereco.setRua(alunoDTO.getRua());
        this.endereco.setNumero(alunoDTO.getNumero());
        this.endereco.setBairro(alunoDTO.getBairro());
        this.endereco.setCep(alunoDTO.getCep());
        this.endereco.setCidade(alunoDTO.getCidade());
        this.endereco.setEstado(alunoDTO.getEstado());
        this.endereco.setComplemento(alunoDTO.getComplemento());
        this.endereco.setNumero(alunoDTO.getNumero());
    }
}
