import { Itens } from './itens';

export interface Venda {
  codigo: number,
  data: string,
  cliente: string,
  itens: Itens[],
}
