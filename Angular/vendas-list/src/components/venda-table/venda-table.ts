import { Component, input } from '@angular/core';
import { Venda } from '../../models/venda';


@Component({
  selector: 'app-venda-table',
  imports: [],
  templateUrl: './venda-table.html',
  styleUrl: './venda-table.css',
})
export class VendaTable {

  public vendas = input<Venda[]>([]);


}
