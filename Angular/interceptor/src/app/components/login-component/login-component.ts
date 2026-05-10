import { Component, inject } from '@angular/core';
import { UsuarioService } from '../../services/usuario-service';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-component',
  imports: [ReactiveFormsModule],
  templateUrl: './login-component.html',
  styleUrl: './login-component.scss',
})
export class LoginComponent {
  private usuarioService = inject(UsuarioService);
  private formBuilder = inject(FormBuilder);

  protected form = this.formBuilder.group({
    email: ["", [Validators.required, Validators.email]],
    password:["", [Validators.required, Validators.minLength(3)]]
  });

  constructor() {
    
  }

  protected async login() {
    const res = await this.usuarioService.login(this.form.controls.email.value!, this.form.controls.password.value!);
    if (res) {
      
      console.log(res,"Logado com Sucesso!!");

      this.usuarioService.getAll()
        .subscribe({
          next: (r) => {
            console.log(r);
          },
          error: (e) => {
            console.log(e);
          }
        })
    }
    else {
      console.log("Login ou/e Senha inválidos");
    }
      
  }

}
