import { Component, inject, OnInit } from '@angular/core';
import { ClienteService } from '../../services/cliente.service';
import { Observable } from 'rxjs';
import { Cliente } from '../../interfaces/cliente.interface';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-cliente',
  imports: [AsyncPipe],
  templateUrl: './cliente.component.html',
  styleUrl: './cliente.component.scss',
})
export class ClienteComponent implements OnInit {
  private clienteService = inject(ClienteService);
  protected cliente$ = new Observable<Cliente[]>();

  constructor() {
    this.buscarClientes();
  }

  ngOnInit(): void {
    console.log('ngOnInit');
  }

  public async remover(id: number) {
    this.clienteService.remover(id).subscribe(() => {
      this.buscarClientes();
    });

  }

  public buscarClientes() {
    console.log('buscar cliente');

    this.cliente$ = this.clienteService.buscarClientes();
  }
}
