import { Component, inject, signal } from '@angular/core';
import { UserService } from '../../services/user-service';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from "@angular/router";


@Component({
  selector: 'app-user-login',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './user-login.html',
  styleUrl: './user-login.css',
})
export class UserLogin {

  protected router = inject(Router);
  protected error = signal("")

  private formBuilder = inject(FormBuilder);
  protected form = this.formBuilder.group({
    email: ["", [Validators.required, Validators.email]],
    password:["", [Validators.required, Validators.minLength(3)]]
  });

  constructor(private userService: UserService){

  }

  async login(){
    if (this.form.invalid) return;

    const user = {
      email: this.form.controls.email.value!,
      password: this.form.controls.password.value!
    };

    try {
      await this.userService.login(user);
      this.router.navigate(['/vendas'])
    } catch (e: any) {
      this.error.set(e);
    }
  }
}
