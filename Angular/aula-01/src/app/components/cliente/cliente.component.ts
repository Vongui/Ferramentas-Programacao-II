import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Cliente } from '../../models/cliente.model';


@Component({
  selector: 'app-cliente',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './cliente.component.html',
  styleUrl: './cliente.component.scss'
})
export class ClienteComponent {

  private formBuilder = inject(FormBuilder);

  protected profileForm = this.formBuilder.group({
    nome: ['', [Validators.required, Validators.minLength(3)]],
    telefone: [''],
    idade: [0, Validators.required]
  });

    public cadastrar() {
    console.log(this.profileForm.valid);
  }
}
