import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TarefaComponent } from './component/tarefa.component/tarefa.component';
import { Tarefa } from './interfaces/tarefa.interface';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TarefaComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {

}
