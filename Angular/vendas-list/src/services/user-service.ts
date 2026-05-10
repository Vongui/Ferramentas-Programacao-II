import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment.development';
import { firstValueFrom } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class UserService {
  private api = `${environment.api}`;
  private http = inject(HttpClient);

  private acessToken: null | string = null;

  constructor(){}

  async login(user: { email: string, password: string}) {
    this.acessToken = null;
    
    this.acessToken = await firstValueFrom(this.http.post<any>(`${this.api}/login`, user));
    console.log(this.acessToken);
    
    
  }

  isLogged() {
    if (this.acessToken) {
      return true;
    }
    return false;
  }


}
