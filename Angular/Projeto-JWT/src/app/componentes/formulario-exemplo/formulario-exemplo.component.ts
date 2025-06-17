import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  NonNullableFormBuilder,
  ReactiveFormsModule,
  UntypedFormBuilder,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-formulario-exemplo',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './formulario-exemplo.component.html',
  styleUrl: './formulario-exemplo.component.scss',
})
export class FormularioExemploComponent {
  // private formBuilder = inject(FormBuilder); //Permite valor nulo para os atributos
  private formBuilder = inject(NonNullableFormBuilder); //Não permite valor nulo para os atributos
  // private formBuilder = inject(UntypedFormBuilder); //Antes do angular 14

  protected profileForm = this.formBuilder.group({
    nome: ['', [Validators.required, Validators.minLength(3)]],
    telefone: [''],
    idade: [0, [Validators.required, Validators.min(18)]]
  });

  // protected formGroup = new FormGroup({
  //   nome: new FormControl('', [Validators.required, Validators.minLength(3)]),
  //   telefone: new FormControl('', [Validators.required])
  // });

  public cadastrar() {
    // console.log(this.formGroup);
    console.log(this.profileForm.value);
    
    this.profileForm.reset();
    
    console.log('Após o reset');
    
    console.log(this.profileForm.value);

  }
}
