import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, UntypedFormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-reactive-form',
  imports: [ReactiveFormsModule],
  templateUrl: './reactive-form.html',
  styleUrl: './reactive-form.css',
})
export class ReactiveForm {
  // tem o tipo any para os atributos dentro do builder
  private untypedFormBuilder = inject(UntypedFormBuilder);
  // o contrário do untyped
  private formBuilder = inject(FormBuilder);

  // Criação dos atributos do formulário e validações
  protected form = this.formBuilder.group({
    nome: ['', [Validators.required, Validators.minLength(3)]],
    email: ['', [Validators.required, Validators.email]]
  })

  constructor(){}


  protected salvar(){
    console.log(this.form.value);  

    this.form.reset();

    // Formas de colocar valores diretamente no forms
    // Passar todos os valores do control.group
    // this.form.setValue({
    //   nome: "Test",
    //   email: "teste@gmail.com"
    // });

    // Pode passar qual atributo do controls group deseja ser atribuido
  //   this.form.patchValue({
  //     nome: "teste2"
  //   });
  
  }

}
