import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AlunoCadastroComponent } from './aluno-cadastro/aluno-cadastro.component';
import { AlunoListaComponent } from './aluno-lista/aluno-lista.component';
import { ModalAlunoComponent } from './aluno-modal/modal-aluno.component';

@NgModule({
  declarations: [
    AlunoCadastroComponent,
    AlunoListaComponent,
    ModalAlunoComponent
  ],
  imports: [
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    CommonModule
  ],
  exports: [
  ]
})
export class AlunosModule { }
