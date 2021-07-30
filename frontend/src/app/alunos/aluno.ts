export class Aluno {
    id: number | null = null;
    nome: string = '';
    sobrenome: string = '';
    endereco: Endereco = new Endereco();
    dataUltimaAtualizacao: Date = new Date();
    dataCadastro: Date = new Date();
}

export class Endereco{
    cep: string = '';
    numero: number = -1;
    rua: string = '';
    complemento: string = '';
    bairro: string = '';
    cidade: string = '';
    estado: string = '';
}