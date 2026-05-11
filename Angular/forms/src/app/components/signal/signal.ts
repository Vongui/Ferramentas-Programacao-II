import { JsonPipe } from '@angular/common';
import { Component, computed, effect, signal } from '@angular/core';

@Component({
  selector: 'app-signal',
  imports: [JsonPipe],
  templateUrl: './signal.html',
  styleUrl: './signal.css',
})
export class Signal {
  protected exemploCount = signal(0);
  protected exemploObj = signal({
    nome: "Ana",
    idade: 20
  });
  protected showComputed = signal(false);
  //Computed => calcula um valor com base em sinais dos quais ele é dependente, não roda sozinho precisa ser chamado/renderizado
  protected exemploComputed = computed(() => {
    console.log("Computed");
    if (this.showComputed()){

      return this.exemploCount() *2;
    }
    return "Não calculado"

  });

  constructor(){
    //Effect => uma função que roda automaticamente quando algo muda
    effect(() => {
      console.log(`Effect: ${this.exemploCount()}`);
    })
  }

  protected alterarShow() {
    this.showComputed.update(atual => !atual);
  }

  protected increment() {
    // altera o valor do signal a partir do valor atual dele
    this.exemploCount.update(atual => ++atual);
  }

  protected setObj() {
    this.exemploObj.update( atual => {
      return {
        ...atual, // espalhamento -> mantém o valor dos atributos não citados
        idade: 22
      }
    })
  }

}
