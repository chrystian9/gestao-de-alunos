package br.com.gestaodealunos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@Entity
public class Admin extends Usuario {

    public Admin(String nome, String email, String senha){
        super(nome, email, senha);
    }
}
