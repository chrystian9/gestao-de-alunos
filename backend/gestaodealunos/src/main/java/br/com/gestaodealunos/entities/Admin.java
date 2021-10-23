package br.com.gestaodealunos.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Admin extends Usuario {
    public Admin(String nome, String email, String senha){
        super(nome, email, senha);
    }
}
