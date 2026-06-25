import { Produto } from './../../models/produto.model';
import { Component, inject } from '@angular/core';
import { AsyncPipe, CurrencyPipe, DatePipe } from '@angular/common';
import { TableModule } from 'primeng/table';
import { IconField } from 'primeng/iconfield';
import { InputIcon } from 'primeng/inputicon';
import { InputTextModule} from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { Button } from 'primeng/button';
import { ProdutoService } from '../../services/produto-service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-produto-list',
  imports: [
    AsyncPipe,
    TableModule,
    IconField,
    InputIcon,
    InputTextModule,
    FormsModule,
    Button,
    CurrencyPipe
  ],
  templateUrl: './produto-list.html',
  styleUrl: './produto-list.scss',
})
export class ProdutoList {
  private produtoService = inject(ProdutoService);
  private router = inject(Router);

  protected produtos$ = new Observable<Produto[]>();

  constructor() {
    this.obterProdutos();
  }

  obterProdutos(){
    this.produtos$ = this.produtoService.obterTodos();
  }

  novoProduto() {
    this.router.navigate(['/produtos/novo']);
  }

  alterar(produto: Produto) {
    this.router.navigate(['/produtos/editar', produto.id]);
  }

  excluir(produto: Produto) {
    if (confirm(`Tem certeza que deseja excluir o produto ${produto.descricao}?`)) {
      this.produtoService.deletarProdutoSelecionado(produto.id).subscribe({
        next: () => {
          this.obterProdutos();
        },
        error: (err) => {
          console.error('Erro ao excluir produto:', err);
          alert('Não foi possível excluir o produto.');
        }
      });
    }
  }

}
