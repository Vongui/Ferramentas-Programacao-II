import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../interfaces/Usuario';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {

  private api = `${environment.api}`;
  private http = inject(HttpClient);
  private accessToken = "";
  private refreshToken = "";
  private user!: { cpf: string | undefined; password: string | undefined; };

  constructor() { }

  public login(){
    const user = this.getUser();
    return this.http.post(`${this.api}/sessions/cpf`, user);
  }

  public setTokens(accessToken: string, refreshToken: string){
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public getAccessToken(){
    return this.accessToken;
  }

  public setUser(cpf: string | undefined, password: string | undefined){
     this.user = {
      cpf: cpf,
      password: password
    };
  }

  public getUser(){
    return this.user;
  }

}
