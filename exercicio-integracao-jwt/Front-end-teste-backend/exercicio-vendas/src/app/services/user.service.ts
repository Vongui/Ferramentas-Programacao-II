import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { AuthenticationUser } from '../model/authentication-user.model';
import { RegisterUser } from '../model/register-user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private url = `${environment.api}/auth`;
  private http = inject(HttpClient);

  public async register(user: RegisterUser) {
    console.log("entrou na funcao register service");
    console.log(user);
    return firstValueFrom(this.http.post<RegisterUser>(`${this.url}/register`, user));
  }

  public async login(user: AuthenticationUser) {
    this.logout();
    const response = await firstValueFrom(this.http.post<any>(`${this.url}/login`, user));
    const tokenValue = response.token ? response.token : response;

    localStorage.setItem('token', tokenValue);

    console.log("token salvo no localStorage:", tokenValue);
  }

  public getToken() {
    const token = localStorage.getItem('token');
    console.log("Token recuperado:", token);
    return token;
  }

  public logout() {
    localStorage.removeItem('token');
  }

  obterRoleDoToken() {
    const token = this.getToken();
    if (!token) return null;

    try {
      const payloadBase64 = token.split('.')[1];

      const payloadJson = JSON.parse(atob(payloadBase64));
      console.log(payloadJson);

      return payloadJson.role;

    } catch (e) {
      console.error('Erro ao decodificar o token', e);
      return null;
    }
  }

  isAdmin() {
    const roles = this.obterRoleDoToken();

    if (!roles) return false;

    if (Array.isArray(roles)) {
      return roles.includes('ROLE_ADMIN') || roles.includes('ADMIN');
    }

    return roles === 'ROLE_ADMIN' || roles === 'ADMIN';
  }

}
