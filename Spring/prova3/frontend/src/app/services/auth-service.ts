import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { Cliente } from '../models/cliente.model';
import { LoginResponse } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = 'http://localhost:8080/auth';
  private http = inject(HttpClient);

  public async login(credenciais: Partial<Cliente>) {
    const response = await firstValueFrom(this.http.post<LoginResponse>(`${this.url}/login`, credenciais));
    localStorage.setItem('token', response.token);
  }

  public register(cliente: Cliente){
    return firstValueFrom(this.http.post<Cliente>(`${this.url}/register`, cliente));
  }

  public logout() {
    localStorage.removeItem('token');
  }

  public getToken() {
    return localStorage.getItem('token');
  }

  public isLoggedIn() {
    return !!this.getToken();
  }
}