import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private baseUrl = environment.api;
  private http = inject(HttpClient);

  private token = "";

  async login(username: string, password: string) {
    const user = { username, password };
    try {
      const res = await firstValueFrom(this.http.post<any>(`${this.baseUrl}/login`, user));
      if (res) {
        this.setToken(res.token);
      }
      return true;
    } catch (error) {
      console.log(error);
      return false;
    }
  }

  getAll() {
    return this.http.get<any[]>(`${this.baseUrl}/users`);
  }

  setToken(token: string) {
    this.token = token;
  }

  getToken() {
    return this.token;
  }

  isLogged() {
    if (this.token) {
      return true;
    }
    return false;
  }


}
