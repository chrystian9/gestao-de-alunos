package br.com.gestaodealunos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProfessorDTO {

    public Long id;
    public String email;
    public String senha;
    public String nome;
    public String sobrenome;
    public String disciplina;
    public Date dataCadastro;
    public Date dataUltimaAtualizacao;
    public String rua;
    public Integer numero;
    public String complemento;
    public String bairro;
    public String cep;
    public String cidade;
    public String estado;
}
