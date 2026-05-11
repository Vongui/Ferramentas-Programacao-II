import { Component, inject } from '@angular/core';
import { LoginService } from '../../services/login-service';
import { Router } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login-component',
  imports: [ReactiveFormsModule],
  templateUrl: './login-component.html',
  styleUrl: './login-component.scss',
})
export class LoginComponent {
  private loginService = inject(LoginService);
  private route = inject(Router);


  constructor() {

  }

  protected login() {
    console.log("teste");
    
    this.loginService.isLogged();
    console.log("guard token:", this.loginService.getToken());
    
    this.route.navigate(['/tarefas']);
  }

}
