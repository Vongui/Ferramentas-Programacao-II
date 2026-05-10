import { Component } from '@angular/core';
import { Filho } from './componentes/filho/filho';

@Component({
  selector: 'app-root',
  imports: [Filho],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected mostrar = true;
}
