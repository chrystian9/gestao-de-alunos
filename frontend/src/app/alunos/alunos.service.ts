import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Aluno } from './aluno';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AlunosService {

  private urlAlunos = environment.apiUrl + '/api/aluno'

  constructor(private httpClient: HttpClient) { }

  salvar(aluno: Aluno): Observable<number>{
    return this.httpClient.post<number>(this.urlAlunos+'/salvar', aluno);
  }

  salvarFoto(foto: File, nomeFoto: string, idAluno: number | null): Observable<any>{
    const formData = new FormData();
    formData.append('foto', new Blob([foto]), nomeFoto);
    return this.httpClient.post<any>(this.urlAlunos+`/${idAluno}/salvar-foto`, formData);
  }

  getFotoPerfil(idAluno: number): Observable<File | null>{
    return this.httpClient.get<File | null>(this.urlAlunos+`/${idAluno}/get-foto`, {
      responseType: 'blob' as 'json'
    })
  }

  listar(page: number, pageSize: number): Observable<any>{
    const params = new HttpParams();

    params.set('page', page);
    params.set('lisnesPerPage', pageSize);

    return this.httpClient.get<any>(this.urlAlunos+'/listar', {params});
  }

  update(aluno: Aluno): Observable<number>{
    return this.httpClient.post<number>(this.urlAlunos+'/update', aluno);
  }

  remover(aluno: Aluno): Observable<any>{
    return this.httpClient.put<any>(this.urlAlunos+'/remover', aluno);
  }

  consultaCEP(cep: string): Observable<any>{
    cep = cep.replace(/\D/g, '');

    if (cep !== '') {
      const validaCEP = /^[0-9]{8}$/;

      if (validaCEP.test(cep)) {
        return this.httpClient.get<any>(`http://viacep.com.br/ws/${cep}/json`);
      }
    }

    return of({});
  }
}