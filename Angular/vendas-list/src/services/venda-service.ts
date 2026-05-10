import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Venda } from '../models/venda';


@Injectable({
  providedIn: 'root',
})
export class VendaService {
  private api = `${environment.api}/vendas`;

  private http =  inject(HttpClient);

  constructor() {

  }

  listar() {
    return this.http.get<Venda[]>(this.api);
  }


}
