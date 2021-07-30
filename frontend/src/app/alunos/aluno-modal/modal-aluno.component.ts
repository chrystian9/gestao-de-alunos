import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Aluno } from '../aluno';
import { AlunosService } from '../alunos.service';

@Component({
  templateUrl: './modal-aluno.component.html',
  styleUrls: ['./modal-aluno.component.css']
})
export class ModalAlunoComponent implements OnInit { 

    aluno: Aluno = new Aluno();
    imagemPerfil: File | null = null;
    imagemPerfilURL: string = '';

    constructor(public activeModal: NgbActiveModal, private alunosService: AlunosService){
    }

    ngOnInit(){
      if(this.aluno.id != null){
        this.alunosService.getFotoPerfil(this.aluno.id).subscribe((value: any) => {
          this.imagemPerfil = value;
        });
        if(this.imagemPerfil != null){
          let fotoReader = new FileReader();
          fotoReader.onloadend = () => { 
            this.imagemPerfilURL = typeof fotoReader.result != 'string' ? '' : fotoReader.result; 
          }
          fotoReader.readAsDataURL(this.imagemPerfil);
        }
      }
    }
}