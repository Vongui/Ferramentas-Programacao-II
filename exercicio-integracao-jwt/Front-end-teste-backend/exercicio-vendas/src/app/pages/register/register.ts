import { Component, inject, signal } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';
import { RegisterUser } from '../../model/register-user.model';
import { UserRole } from '../../model/user-role.enum';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {
  private formBuilder = inject(NonNullableFormBuilder);
  private userService = inject(UserService);
  private router = inject(Router);

  protected error = signal<string | null>(null)
  protected UserRole = UserRole;

  protected form = this.formBuilder.group({
    login: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    role: [UserRole.USER, Validators.required]
  });

  protected async register() {
    console.log("entrou na funcao register, angular");

    if (this.form.invalid) {
      return;
    }

    const user: RegisterUser = {
      login: this.form.controls.login.value,
      password: this.form.controls.password.value,
      role: this.form.controls.role.value,
    };


    try {
      console.log("entrou no try");

      await this.userService.register(user);
      this.router.navigate(['/login']);
    } catch (e: any) {
      this.error.set(e.error || 'Erro ao realizar o cadastro');
    }
  }
}
