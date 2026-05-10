import { JsonPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Pessoa } from '../../models/pessoa.model';
import { PessoaService } from '../../services/pessoa-service';

@Component({
  selector: 'app-template-form',
  imports: [FormsModule],
  templateUrl: './template-form.html',
  styleUrl: './template-form.css',
})
export class TemplateForm {

  // lembrar q se usar interface é necessário iniciar os atributos do objeto
  /** 
   * protected pessoa: Pessoa = {
   *    nome = "",
   *    email = ""
   * };
  */
  protected pessoa: Pessoa =  new Pessoa();
  private pessoaService = inject(PessoaService);
  

  protected salvar(form: NgForm){
    // console.log(form);

    if (form.invalid) return;

    console.log("Salvar!");
    console.log(this.pessoa.nome);

    this.pessoaService.add(this.pessoa);

    console.log(this.pessoaService.getAll());
    this.pessoa = new Pessoa();
    form.reset(); // reseta o formulario
    
  }

}
