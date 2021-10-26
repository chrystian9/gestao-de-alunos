package br.com.gestaodealunos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
public class Admin extends Usuario {

    public Admin(String nome, String email, String senha, Date dataUltimaAtualizacao, Date dataCadastro){
        super(nome, email, senha, dataUltimaAtualizacao, dataCadastro);
    }
}
