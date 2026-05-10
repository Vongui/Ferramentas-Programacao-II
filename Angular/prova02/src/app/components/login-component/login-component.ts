import { Component, inject } from '@angular/core';
import { LoginService } from '../../services/login-service';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-component',
  imports: [ReactiveFormsModule],
  templateUrl: './login-component.html',
  styleUrl: './login-component.scss',
})
export class LoginComponent {
  private loginService = inject(LoginService);
  private formBuilder = inject(FormBuilder);

  protected form = this.formBuilder.group({
    email: ['', Validators.required],
    password: ['', Validators.required],
  });

  constructor() {

  }

  protected async login() {
    const res = await this.loginService.login(this.form.controls.email.value!, this.form.controls.password.value!);
    if (res) {

      console.log(res,"Logado com Sucesso!!");
      // this.loginService.getAll()
      //   .subscribe({
      //     next: (r) => {
      //       console.log(r);
      //     },
      //     error: (e) => {
      //       console.log(e);
      //     }
      //   })

    }
    else {
      console.log("Login ou/e Senha inválidos");
    }

  }

}
