import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private token = false;

  constructor() {
    
  }

  setToken() {
    if (this.token) {
      return this.token = false;
    }
    else{
      return this.token = true;
    }
  }

  getToken() {
    return this.token;
  }

  isLogged() {
    this.setToken();
    // return this.token;
  }


}
