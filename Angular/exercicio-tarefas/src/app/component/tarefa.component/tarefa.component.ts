import { Component } from '@angular/core';
import { Tarefa } from '../../interfaces/tarefa.interface';

@Component({
  selector: 'app-tarefa',
  standalone: true,
  imports: [],
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css'],
})
export class TarefaComponent {
  protected tarefas: Tarefa[] = [
    { id: 1, descricao: 'Comprar leite', concluida: false },
    { id: 2, descricao: 'Ler capítulo do livro', concluida: true },
    { id: 3, descricao: 'Responder e-mails', concluida: false },
    { id: 4, descricao: 'Fazer backup do projeto', concluida: false },
    { id: 5, descricao: 'Planejar próxima sprint', concluida: true },
  ];

  protected get totalTarefas(): number {
    return this.tarefas.length;
  }

  protected get tarefasConcluidas(): number {
    return this.tarefas.filter(t => t.concluida).length;
  }

  protected get tarefasPendentes(): number {
    return this.tarefas.filter(t => !t.concluida).length;
  }

  protected alternarTarefa(index: number): void {
    this.tarefas[index].concluida = !this.tarefas[index].concluida;
  }
}
