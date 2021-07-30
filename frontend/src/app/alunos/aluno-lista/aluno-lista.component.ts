import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Aluno } from '../aluno';
import { ModalAlunoComponent } from '../aluno-modal/modal-aluno.component';
import { AlunosService } from '../alunos.service';

@Component({
  templateUrl: './aluno-lista.component.html',
  styleUrls: ['./aluno-lista.component.css']
})
export class AlunoListaComponent implements OnInit {
  
  page = 1;
  pageSize = 4;
  collectionSize = 0;
  alunos: Aluno[] = [];

  constructor(private alunoService: AlunosService, private modalService: NgbModal, private router: Router) {
    this.refreshAlunos();
  }

  ngOnInit(){
  }
  
  openModalVizualizar(aluno: Aluno){
    const modalRef = this.modalService.open(ModalAlunoComponent, {size:'lg', centered:true})

    modalRef.componentInstance.aluno = aluno;
  }

  refreshAlunos() {
    this.alunoService.listar(this.page, this.pageSize).subscribe((resp) => {
      this.alunos = resp.content;
      this.collectionSize = resp.totalElements;
      this.page = resp.number;
    });
  }

  editarAluno(aluno: Aluno){
    this.router.navigate(['/aluno-cadastro'], { state: { aluno : aluno, edicao : true}});
  }

  removerAluno(aluno: Aluno){
    this.alunoService.remover(aluno).subscribe();
  }
}
