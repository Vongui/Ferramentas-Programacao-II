import { Component, input, Input } from '@angular/core';

@Component({
  selector: 'app-botao',
  imports: [],
  templateUrl: './botao.html',
  styleUrl: './botao.css',
})
export class Botao {

  //Passar um informação do pai para o filho
  @Input()
  public titulo = "";

  //Jeito mais moderno para passar informação do pai para o filho
  public corFundo = input("blue"); //signal

}
