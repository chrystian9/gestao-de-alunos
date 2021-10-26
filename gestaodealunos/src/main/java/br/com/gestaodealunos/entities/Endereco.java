package br.com.gestaodealunos.entities;

import br.com.gestaodealunos.dto.EnderecoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "rua")
    private String rua;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @JsonIgnore
    @OneToOne(mappedBy = "endereco", cascade = CascadeType.ALL)
    private Aluno aluno;

    public Endereco(EnderecoDTO enderecoDTO){
        this.rua = enderecoDTO.rua;
        this.numero = enderecoDTO.numero;
        this.bairro = enderecoDTO.bairro;
        this.cidade = enderecoDTO.cidade;
        this.estado = enderecoDTO.estado;
        this.complemento = enderecoDTO.complemento;;
        this.cep = enderecoDTO.cep;
    }
}
