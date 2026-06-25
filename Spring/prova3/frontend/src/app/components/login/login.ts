import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { MessagesModule } from 'primeng/messages';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule, RouterModule, 
    CardModule, InputTextModule, PasswordModule, ButtonModule, MessagesModule
  ],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  protected form = this.fb.group({
    login: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });

  // protected mensagensErro = signal<Message[]>([]);
  // protected carregando = signal(false);

  protected async fazerLogin() {
    if (this.form.invalid) return;

    // this.carregando.set(true);
    // this.mensagensErro.set([]);

    try {
      await this.authService.login(this.form.value);
      this.router.navigate(['/produtos']);
    } catch (error: any) {
      // this.mensagensErro.set([{ severity: 'error', summary: 'Erro', detail: 'Login ou senha incorretos.' }]);
    } finally {
      // this.carregando.set(false);
    }
  }
}