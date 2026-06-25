import { Produto } from './produto.model';

export interface ItemVenda {
  id?: number;
  quantidade: number;
  preco?: number;
  produto: Produto;
}