import { AutenticacaoService } from './../../service/autenticacao.service';

import { Component, inject } from '@angular/core';
import {
  FormsModule,
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';
import { Router } from '@angular/router';
import { catchError, from, map, switchMap, take, tap, throwError } from 'rxjs';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  private router = inject(Router);
  private usuarioService = inject(UsuarioService);
  private formBuilder = inject(NonNullableFormBuilder);
  private autenticacaoService = inject(AutenticacaoService)
  protected form = this.formBuilder.group({
    nomeLogin: ['', [Validators.required]],
    senha: ['', [Validators.required]],
  });

  constructor() {
    console.log("contrutor login component")

    from([1,2,3,4,5])
    .pipe(
      tap( (item) => console.log(`Valor ${item}`)),
      map( (item) => {
        if (item == 0) {
          throw new Error("Valor zero")
        }
        return item;
        }
      )
    )
    .subscribe({
      next: (item) => {
        console.log(`Valor ${item}`)
      },
      error: (error) => console.log("Erro no subscribe ", error),
      complete: () => console.log("Fim"),
    })
  }

  protected criarUsuario() {
    this.autenticacaoService
    .criarUsuario()
    .subscribe( (res) => console.log(res));
  }

  protected login() {
    this.autenticacaoService
    .login()
    .subscribe( (res: any) => {
      console.log(res);
      this.autenticacaoService.setTokens(res.accessToken, res.refreshToken);
    });
  }

  protected acessarRotaPrivada() {
    this.autenticacaoService
      .rotaPrivada()
      .pipe(
        take(1),
        catchError( (error: any) => {
          //Erro diferente de 401(não autorizado)
          if (error.status !== 401) {
            return throwError( () => error);
          }
          //Se for o erro 401 - token espirou
          return this.autenticacaoService.refresh()
          .pipe(
            tap( (res: any) => {
              this.autenticacaoService.setTokens(res.accessToken, res.refreshToken)
            }),
            //chama rota privada
            switchMap( () => this.autenticacaoService.rotaPrivada())
          )

        })
      )
      .subscribe( (res) => console.log(res));
  }

  protected async acessar() {
    console.log(this.form.value);
    await this.usuarioService.login(this.form.value);

    this.usuarioService.obterTodos().subscribe({
      next: (usuarios) => {
        console.log(usuarios);
      },
      error: (erro) => {
        console.log(erro);
      },
    });
    // this.router.navigate(['/cliente']);
  }

  buscarUsuarios() {
    this.usuarioService.obterTodos().subscribe({
      next: (usuarios) => {
        console.log(usuarios);
      },
      error: (erro) => {
        console.log(erro);
      },
    });
  }
}

