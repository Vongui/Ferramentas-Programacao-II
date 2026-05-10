import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../interfaces/Usuario';
import { first, firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService{

  private api = `${environment.api}`;
  private http = inject(HttpClient);

  private token: string = "";

  constructor() { }

  async login(usuario: Partial<Usuario>){
    const usuarioLogin = await firstValueFrom(
      this.http.post<Usuario>(`${this.api}/sessions/cpf`, usuario)
    );
    console.log(usuarioLogin);
    this.token = usuarioLogin.tokenJWT;
  }

  isLogged() {
    return this.token !== "";
  }

  getToken() {
    return this.token;
  }




}
