package br.com.gestaodealunos.dto;

import br.com.gestaodealunos.entities.Aluno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoDTO {
    public Long id;
    public String nome;
    public String sobrenome;
    public Date dataCadastro;
    public Date dataUltimaAtualizacao;
    public String rua;
    public Integer numero;
    public String complemento;
    public String bairro;
    public String cep;
    public String cidade;
    public String estado;
    public Double notaUm;
    public Double notaDois;
    public Double notaTres;
    public Double notaQuatro;
    public Double notaFinal;

    public AlunoDTO(Aluno aluno){
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.sobrenome = aluno.getSobrenome();
        this.dataCadastro = aluno.getDataCadastro();
        this.dataUltimaAtualizacao = aluno.getDataUltimaAtualizacao();
        this.rua = aluno.getEndereco().getRua();
        this.numero = aluno.getEndereco().getNumero();
        this.complemento = aluno.getEndereco().getComplemento();
        this.bairro = aluno.getEndereco().getBairro();
        this.cep = aluno.getEndereco().getCep();
        this.cidade = aluno.getEndereco().getCidade();
        this.estado = aluno.getEndereco().getEstado();
        this.notaUm = aluno.getNotas().getNotaUm();
        this.notaDois = aluno.getNotas().getNotaDois();
        this.notaTres = aluno.getNotas().getNotaTres();
        this.notaQuatro = aluno.getNotas().getNotaQuatro();
        this.notaFinal = aluno.getNotas().getNotaFinal();
    }
}
