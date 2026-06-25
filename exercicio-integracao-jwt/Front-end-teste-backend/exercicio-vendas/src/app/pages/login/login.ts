import { Component, inject, signal } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';
import { AuthenticationUser } from '../../model/authentication-user.model';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  private formBuilder = inject(NonNullableFormBuilder);
  private userService = inject(UserService);
  private router = inject(Router);

  protected error = signal(null);

  protected form = this.formBuilder.group({
    login: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
  });

  protected async login() {
    const user: AuthenticationUser = {
      login: this.form.controls.login.value,
      password: this.form.controls.password.value,
    };

    try {
      await this.userService.login(user);
      this.router.navigate(['/pagina-publica'])
    } catch (e: any) {
      this.error.set(e.error)
    }
  }

  protected sair() {
    this.userService.logout();
    this.router.navigate(['/login']);
  }
}
