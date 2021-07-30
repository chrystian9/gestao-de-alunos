import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Aluno } from '../aluno';
import { AlunosService } from '../alunos.service';

@Component({
  selector: 'app-investigacoes-cadastro',
  templateUrl: './aluno-cadastro.component.html',
  styleUrls: ['./aluno-cadastro.component.css']
})
export class AlunoCadastroComponent implements OnInit {

  formulario!: FormGroup;
  imagemPerfilURL!: string;
  nomeFotoPerfil!: string;
  extencaoFotoPerfil!: string;
  aluno: Aluno;
  edicao: boolean = false;

  constructor(private formBuilder: FormBuilder, private alunoService: AlunosService, private router: Router) {
    const nav = this.router.getCurrentNavigation();
    if(nav?.extras.state != undefined){
      this.aluno = nav.extras.state?.aluno;
      this.edicao = nav.extras.state?.edicao;
    }else{
      this.aluno = new Aluno();
    }
  }

  ngOnInit(){
    this.formulario = this.formBuilder.group({
      nome: [this.edicao == true ? this.aluno.nome : null, Validators.required],
      sobrenome: [this.edicao == true ? this.aluno.sobrenome : null, Validators.required],
      
      endereco: this.formBuilder.group({
        cep: [this.edicao == true ? this.aluno.endereco.cep : null, Validators.required],
        numero: [this.edicao == true ? this.aluno.endereco.numero : null, Validators.required],
        rua: [this.edicao == true ? this.aluno.endereco.rua : null, Validators.required],
        complemento: [this.edicao == true ? this.aluno.endereco.complemento : null, Validators.required],
        bairro: [this.edicao == true ? this.aluno.endereco.bairro : null, Validators.required],
        cidade: [this.edicao == true ? this.aluno.endereco.cidade : null, Validators.required],
        estado: [this.edicao == true ? this.aluno.endereco.estado : null, Validators.required]
      }),

      foto: [this.edicao == true && this.aluno.id != null ? 
          this.alunoService.getFotoPerfil(this.aluno.id)
            .subscribe((value: any) => { 
              this.formulario.value.foto = value;
              let fotoReader = new FileReader();
              fotoReader.onloadend = () => { 
                this.imagemPerfilURL = typeof fotoReader.result != 'string' ? '' : fotoReader.result; 
              }
              fotoReader.readAsDataURL(this.formulario.value.foto);
          }) : null]
    })
  }

  onChangeFoto(event: any){
    this.formulario.get('foto')?.setValue(<File> event?.srcElement.files[0]);

    if(this.formulario.value.foto != null){
      this.extencaoFotoPerfil = this.formulario.value.foto.name.split('/').pop();
      this.extencaoFotoPerfil = this.extencaoFotoPerfil.indexOf('.') < 1 ? '' : this.formulario.value.foto.name.split('.').pop();

      let fotoReader = new FileReader();
      fotoReader.onloadend = () => { 
        this.imagemPerfilURL = typeof fotoReader.result != 'string' ? '' : fotoReader.result; 
      }
      fotoReader.readAsDataURL(this.formulario.value.foto);
    }
  }

  onRemoveFoto(){
    this.formulario.get('foto')?.setValue(null);
  }

  reset(){
    this.formulario.reset()
  }

  onSubmit(){
    this.aluno.nome = this.formulario.value.nome;
    this.aluno.sobrenome = this.formulario.value.sobrenome;
    this.aluno.endereco.rua = this.formulario.value.endereco.rua;
    this.aluno.endereco.numero = this.formulario.value.endereco.numero;
    this.aluno.endereco.bairro = this.formulario.value.endereco.bairro;
    this.aluno.endereco.cidade = this.formulario.value.endereco.cidade;
    this.aluno.endereco.estado = this.formulario.value.endereco.estado;
    this.aluno.endereco.cep = this.formulario.value.endereco.cep;
    this.aluno.endereco.complemento = this.formulario.value.endereco.complemento;

    if(this.edicao){
      this.aluno.dataUltimaAtualizacao = new Date();
    }

    if(this.edicao){
      this.alunoService.update(this.aluno).subscribe((value: number) => {
        this.alunoService.salvarFoto(this.formulario.value.foto, this.aluno.nome.replace(/[ ]/gi, '')+'.'+this.extencaoFotoPerfil, value).subscribe();
        this.reset();
        this.router.navigate(['/aluno-lista'], {replaceUrl: true});
      });
    }else{
      this.alunoService.salvar(this.aluno).subscribe((value: number) => {
        this.alunoService.salvarFoto(this.formulario.value.foto, this.aluno.nome.replace(/[ ]/gi, '')+'.'+this.extencaoFotoPerfil, value).subscribe();
        this.reset();    
        this.router.navigate(['/aluno-lista'], {replaceUrl: true});
      });
    }
  }

  verificaRequired(campo: string): boolean{
    if(!this.formulario.get(campo)?.valid && this.formulario.get(campo)?.touched && this.formulario.get(campo)?.errors?.required){
      return true;
    }

    return false;
  }
  
  consultaCEP(){
    const cep = this.formulario.get('endereco.cep')?.value;

    if (cep != null && cep !== '') {
      this.alunoService.consultaCEP(cep)
      .subscribe(dados => {
        this.formulario.get('endereco.cep')?.setValue(dados.cep);
        this.formulario.get('endereco.estado')?.setValue(dados.uf);
        this.formulario.get('endereco.cidade')?.setValue(dados.localidade);
      });
    }
  }
}
