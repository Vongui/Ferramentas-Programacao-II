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

   public remover(id: number) {
       this.clienteService.remover(id).subscribe({
           next: (response) => {
               console.log('Cliente excluído:', response);
               this.buscarClientes();
           },
           error: (err) => {
               console.error('Erro ao excluir cliente:', err);
           }
       });
   }

  public buscarClientes() {
      this.cliente$ = this.clienteService.buscarClientes();
  }

}
