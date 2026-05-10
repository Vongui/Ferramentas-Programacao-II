import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { FormsModule, ReactiveFormsModule, NonNullableFormBuilder, Validators } from '@angular/forms';
import { AutenticacaoService } from '../../services/autenticacao.service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  private router = inject(Router);
  private usuarioService = inject(UsuarioService);
  private formBuilder = inject(NonNullableFormBuilder);
  private autenticaService = inject(AutenticacaoService);
  protected form = this.formBuilder.group({
    cpf: ['', [Validators.required, Validators.minLength(6)]],
    password: ['', [Validators.required, Validators.minLength(6)]]
  });

  protected login(){
    console.log(this.form.value);
    this.autenticaService.login()
    .subscribe( (res: any) => {
      console.log(res);
      this.autenticaService.setUser(this.form.value.cpf, this.form.value.password);
      this.autenticaService.setTokens(res.accessToken, res.refreshToken);
    } );

  }


}
