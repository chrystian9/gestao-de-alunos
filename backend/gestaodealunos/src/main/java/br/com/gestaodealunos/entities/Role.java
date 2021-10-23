package br.com.gestaodealunos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany
    private List<Usuario> usuarios;

    public Role(String nome){
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
