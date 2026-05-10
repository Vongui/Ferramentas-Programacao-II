import { Component, inject } from '@angular/core';
import { VendaService } from '../../services/venda.service';
import { map, Observable } from 'rxjs';
import { Venda } from '../../models/venda.model';
import { AsyncPipe, DatePipe } from '@angular/common';
import { TableModule } from 'primeng/table';
import { IconField } from 'primeng/iconfield';
import { InputIcon } from 'primeng/inputicon';
import { InputTextModule} from 'primeng/inputtext';
import { ToggleSwitch } from 'primeng/toggleswitch';
import { FormsModule } from '@angular/forms';
import { Button } from 'primeng/button';

@Component({
  selector: 'app-venda-list',
  imports: [
    AsyncPipe,
    TableModule,
    DatePipe,
    IconField,
    InputIcon,
    InputTextModule,
    ToggleSwitch,
    FormsModule,
    Button,
  ],
  templateUrl: './venda-list.html',
  styleUrl: './venda-list.scss',
})
export class VendaList {
  private vendaService = inject(VendaService);

  protected vendas$ = new Observable<Venda[]>();
  protected metaKey: boolean = true;
  protected selectedVenda: Venda[] = [];

  constructor() {
    this.obterVendas();
    console.log(this.selectedVenda);
  }

  protected obterVendas() {
    this.vendas$ = this.vendaService.obterTodas();
  }

  protected excluir() {
    this.selectedVenda.forEach((venda: Venda) => {
      this.vendaService.deleteSelectedVenda(venda.id)
        .subscribe()
    });

    this.selectedVenda = [];
    this.obterVendas();
  }
}
