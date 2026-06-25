import { Cliente } from './cliente.model';
import { ItemVenda } from './item-venda.model';

export interface Venda {
  id?: number;
  data?: string;
  cliente: Cliente;
  itens: ItemVenda[];
}