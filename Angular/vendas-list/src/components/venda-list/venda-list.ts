import { Component, inject, signal } from '@angular/core';
import { VendaTable } from '../venda-table/venda-table';
import { VendaService } from '../../services/venda-service';
import { Venda } from '../../models/venda';

@Component({
  selector: 'app-venda-list',
  imports: [VendaTable],
  templateUrl: './venda-list.html',
  styleUrl: './venda-list.css',
})
export class VendaList {
  private vendaService = inject(VendaService);

  protected vendas = signal<Venda[]>([]);

  constructor() {}

  ngOnInit() {
    this.vendaService.listar().subscribe({
      next: (m) => {
        this.vendas.set(m);
      },
      error: (err) => {
        this.vendas.set([]);
      },
    });
  }
}
