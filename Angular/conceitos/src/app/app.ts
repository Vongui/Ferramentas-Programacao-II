import { CommonModule, JsonPipe } from '@angular/common';
import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import dados from '../pessoa.json';
import { Pessoa } from '../models/pessoa.model';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, JsonPipe],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected pessoa: Pessoa = dados; //import de dados do json de teste -> pessoa.json

  protected titulo = 'Aula';
  protected valor = 0;
  protected nomes = ['Ana', 'João', 'Carlos', 'Bruna'];


  constructor() {
    // console.log(this.pessoa.first_name);

    // console.log(this.pessoa.address);

    // console.log(this.pessoa.phone_numbers[0].type);
    // console.log(this.pessoa.phone_numbers[0].number);

    console.log(this.pessoa)
  }

  protected getSubTitulo() {
    // Fica chamando toda vez que se atualiza a página
    console.log("teste")
    return 'Subtítulo';
  }

  protected adicionar() {
    this.valor++;
  }

  protected diminuir() {
    this.valor--;
  }

  protected exibir() {
    console.log('Método exibir.');
  }

  protected removerUltimo() {
    this.nomes.pop();
  }

  protected removerPrimeiro() {
    this.nomes.shift();
  }

  protected remover(index: number) {
    this.nomes.splice(index, 1);
  }
}
