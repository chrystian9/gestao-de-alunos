package br.com.gestaodealunos.entities;

import br.com.gestaodealunos.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @JsonIgnore
    @Column(name = "senha")
    private String senha;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @Column(name = "data_cadastro")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dataCadastro;

    @Column(name = "data_ultima_atualizacao")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dataUltimaAtualizacao;

    @JsonIgnore
    @Column(name = "active")
    private boolean active;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(
            name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
            name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public Usuario(String nome, String email, String senha, Date dataUltimaAtualizacao, Date dataCadastro){
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.dataUltimaAtualizacao= new Date();
        this.dataCadastro = new Date();
    }

    public Usuario(UsuarioDTO usuarioDTO){
        this.email = usuarioDTO.getEmail();
        this.nome = usuarioDTO.getNome();
        this.senha = usuarioDTO.getSenha();
        this.dataUltimaAtualizacao= new Date();
        this.dataCadastro = new Date();
    }
}
