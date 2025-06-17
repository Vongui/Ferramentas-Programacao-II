import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Cliente } from '../interfaces/cliente.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClienteService {
  private api = `${environment.api}/clientes`;
  private http = inject(HttpClient);

  constructor() {}

  public buscarClientes() {
    return this.http.get<Cliente[]>(`${this.api}`);
  }

  public remover(id: number) {
    return this.http.delete<Cliente>(`${this.api}/${id}`);
  }
}
