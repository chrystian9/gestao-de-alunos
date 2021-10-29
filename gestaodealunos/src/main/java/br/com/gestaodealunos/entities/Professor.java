package br.com.gestaodealunos.entities;

import br.com.gestaodealunos.dto.ProfessorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @NotNull
    @Column(name = "disciplina")
    private String disciplina;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    /*
    @JsonIgnore
    @Column(name = "path_foto")
    private String pathFoto;
    */

    public Professor(ProfessorDTO professorDTO){
        super(professorDTO.getNome(),
                professorDTO.getEmail(),
                professorDTO.getSenha(),
                professorDTO.getDataUltimaAtualizacao(),
                professorDTO.getDataCadastro());

        this.sobrenome = professorDTO.getSobrenome();
        this.endereco = new Endereco();

        this.endereco.setRua(professorDTO.getRua());
        this.endereco.setNumero(professorDTO.getNumero());
        this.endereco.setBairro(professorDTO.getBairro());
        this.endereco.setCep(professorDTO.getCep());
        this.endereco.setCidade(professorDTO.getCidade());
        this.endereco.setEstado(professorDTO.getEstado());
        this.endereco.setComplemento(professorDTO.getComplemento());
        this.endereco.setNumero(professorDTO.getNumero());
    }
}
