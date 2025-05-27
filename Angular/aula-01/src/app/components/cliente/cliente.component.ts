import { Component, inject, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ClientesService } from '../../services/clientes.service';
import { Cliente } from '../../models/cliente.model';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-cliente',
  imports: [AsyncPipe],
  templateUrl: './cliente.component.html',
  styleUrl: './cliente.component.scss'
})
export class ClienteComponent implements OnInit {

  private clienteService = inject(ClientesService);
  protected cliente$ = new Observable<Cliente[]>();

  constructor() {
  }

  ngOnInit(): void {
    console.log("ngOnInit");
    this.buscarClientes();
  }

  public async remover(id: number){
    const clienteExcluido = await this.clienteService.remover(id);
    console.log(clienteExcluido)

    this.buscarClientes()
  }

  public buscarClientes() {
      this.cliente$ = this.clienteService.buscarClientes();
  }

}
