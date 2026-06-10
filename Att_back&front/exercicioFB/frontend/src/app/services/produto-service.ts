import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Produto } from '../models/produto.model';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {
  private url = environment.api;
  private http = inject(HttpClient);

  public obterTodos() {
    return this.http.get<Produto[]>(`${this.url}/produtos`);
  }

  public deletarProdutoSelecionado(codigo: number) {
    return this.http.delete(`${this.url}/produtos/${codigo}`);
  }

  public criarProduto(produto: Produto) {
    return this.http.post<Produto>(`${this.url}/produtos`, produto);
  }

  public atualizarProduto(produto: Produto) {
    return this.http.put<Produto>(`${this.url}/produtos/${produto.codigo}`, produto);
  }

  public obterPorCodigo(codigo: number) {
  return this.http.get<Produto>(`${this.url}/produtos/${codigo}`);
}
}
