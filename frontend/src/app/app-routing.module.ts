import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlunoCadastroComponent } from './alunos/aluno-cadastro/aluno-cadastro.component';
import { AlunoListaComponent } from './alunos/aluno-lista/aluno-lista.component';

const routes: Routes = [
  { path: 'aluno-cadastro', component: AlunoCadastroComponent },
  { path: 'aluno-lista', component: AlunoListaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
