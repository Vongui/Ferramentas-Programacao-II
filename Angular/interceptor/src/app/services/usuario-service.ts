import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../models/usuario-model';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private baseUrl = environment.api;
  private http = inject(HttpClient);
  private token = "";


  //Não é mais necessário pois existe o interceptor que já faz esse trabalho
  // public getHeader() {
  //   return new HttpHeaders()
  //     .set('Authorization', `Bearer ${this.token}`)
  //     .set('Content-Type', 'application/json');
  // }

  public getAll(){
    return this.http.get<Usuario[]>(`${this.baseUrl}/users`);
  }

  public async login(email: string, senha: string){
    const user = {
      email, senha
    }
    // return this.http.post<string>(`${this.baseUrl}/login`, user)
    //   .subscribe({
    //     next: (t) => {
    //       this.setToken(t);
    //     },
    //     error: (e) => {
    //       console.log(e);
    //     }
    //   });
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

  public getToken(){
    return this.token;
  }

  private setToken(token: string) {
    this.token = token;
  }

}

