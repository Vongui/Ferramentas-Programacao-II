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
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule, RouterModule, 
    CardModule, InputTextModule, PasswordModule, ButtonModule, MessagesModule
  ],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  protected form = this.fb.group({
    nome: ['', [Validators.required]],
    login: ['', [Validators.required]],
    password: ['', [Validators.required, Validators.minLength(3)]]
  });

  // protected mensagensErro = signal<Message[]>([]);
  // protected carregando = signal(false);

  protected async registrar() {
    if (this.form.invalid) return;

    // this.carregando.set(true);
    // this.mensagensErro.set([]);

    try {
      const rg: Cliente = {

      }
      await this.authService.register(this.form.value);
      
      // Opcional: Você pode exibir um Toast de sucesso aqui se quiser
      this.router.navigate(['/login']);
    } catch (error: any) {
      const msg = error.error || 'Erro ao realizar o cadastro. Tente novamente.';
      this.mensagensErro.set([{ severity: 'error', summary: 'Falha', detail: msg }]);
    } finally {
      this.carregando.set(false);
    }
  }
}