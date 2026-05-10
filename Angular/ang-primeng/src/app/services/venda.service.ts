import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Venda } from '../models/venda.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class VendaService {
  private url = environment.api;
  private http = inject(HttpClient);

  public obterTodas() {
    return this.http.get<Venda[]>(`${this.url}/vendas`);
  }

  public deleteSelectedVenda(id: number) {
    return this.http.delete(`${this.url}/vendas/${id}`);
  }
}
