import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Usuario } from '../interfaces/usuario.interface';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private api = 'http://localhost:8080';
  private http = inject(HttpClient);

  private token: string = '';

  constructor() {}

  async login(usuario: Partial<Usuario>) {
    const usuarioLogin = await firstValueFrom(
      this.http.post<Usuario>(`${this.api}/auth/login`, usuario)
    );
    console.log(usuarioLogin);
    this.token = usuarioLogin.tokenJWT;
  }

  isLogged() {
    return this.token !== '';
  }

  getToken() {
    return this.token;
  }

  obterTodos() {
    // const headers = { Authorization: `Bearer ${this.token}` };
    // console.log(headers);

    // return this.http.get<Usuario[]>(`${this.api}/usuarios`, { headers });
    return this.http.get<Usuario[]>(`${this.api}/usuarios`);
  }
}
