package br.com.gestaodealunos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AlunoDTO {
    public Long id;
    public String nome;
    public String sobrenome;
    public Date dataCadastro;
    public Date dataUltimaAtualizacao;
    public EnderecoDTO endereco;
}
