import { Injectable } from '@angular/core';
import { Pessoa } from '../models/pessoa.model';

@Injectable({
  providedIn: 'root',
})
export class PessoaService {
  private pessoas: Pessoa[] = [];

  public add(pessoa: Pessoa){
    this.pessoas.push(pessoa);
  }

  public getAll(){
    return this.pessoas;
  }

}
