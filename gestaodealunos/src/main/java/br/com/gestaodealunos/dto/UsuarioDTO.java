package br.com.gestaodealunos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UsuarioDTO {

    public Long id;
    public String email;
    public String senha;
    public String nome;
    public Date dataCadastro;
    public Date dataUltimaAtualizacao;
}
