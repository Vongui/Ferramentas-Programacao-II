import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {

  private api = "http://10.117.75.140:3000";
  private http = inject(HttpClient);
  private accessToken = "";
  private refreshToken = "";

  constructor() { }

  public criarUsuario() {
    const user = {
      "email": "guilherme@hotmail.com",
      "password": "test",
    }
    return this.http.post(`${this.api}/auth/signup`, user);
  }

  public login() {
    const user = {
      email: "guilherme@hotmail.com",
      password: "test",
    }
    return this.http.post(`${this.api}/auth/signin`, user);
  }

  public rotaPrivada(){
    return this.http.get(`${this.api}/feature/private`);
  }

  public refresh() {
    const refreshToken = {
      refreshToken: this.refreshToken
    }
    return this.http.post(`${this.api}/auth/refresh`, refreshToken);
  }

  public setTokens(accessToken: string, refreshToken: string){
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public getAccessToken() {
    return this.accessToken;
  }
}
