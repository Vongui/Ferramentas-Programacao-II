import { Component, Input } from '@angular/core';
import { FilhoComponent } from "../filho/filho.component";

@Component({
  selector: 'app-pai',
  imports: [FilhoComponent],
  templateUrl: './pai.component.html',
  styleUrl: './pai.component.scss'
})
export class PaiComponent {


  // nomeFilho = "João";
  // valorFilho = 2;
  // itensFilho: string[] = ["Cascudo"];


  colunas: string[] = ['Nome','Idade','Salário'];

  dados: any[] = [
    ['João', 25, 34343.545 ],
    ['Maria', 30, 3455.78 ],
    ['Pedro', 35, 1230.44 ]
  ];


  // receberMensagem(event: string) {

  //   console.log(event);
  // }


}
