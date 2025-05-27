import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Cliente } from '../models/cliente.model';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {
  private api = `${environment.api}/clientes`;
  private http = inject(HttpClient);

  constructor() { }

  public buscarClientes() {
    return this.http.get<Cliente[]>(`${this.api}`)
  }

  public remover(id: number){
    return this.http.delete(`${this.api}/${id}`)
  }

}
