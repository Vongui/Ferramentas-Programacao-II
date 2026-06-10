import { Status } from "./status.enum";

export interface Produto {
  codigo: number,
  descricao: string,
  preco: number,
  quantidade: number,
  status: Status
}
