import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-filho',
  imports: [CommonModule],
  templateUrl: './filho.component.html',
  styleUrl: './filho.component.scss'
})
export class FilhoComponent {

  // @Input() nome: string = 'Nome';
  // @Input() valor: number = 0;
  // @Input() itens: string[] = ["Maria"];

  // @Output() avisarPai = new EventEmitter<string>();

  // avisar() {
  //   this.avisarPai.emit("Mensagem do Filho!!!");
  // }


  @Input() colunasPai: string[] = [];
  @Input() linhasPai: any[] = [];
}

