package br.com.gestaodealunos.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    @Column(name = "senha")
    private String senha;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @Column(name = "data_ultima_atualizacao")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private String dataUltimaAtualizacao;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(
            name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
            name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public Usuario(String nome, String email, String senha){
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }
}
