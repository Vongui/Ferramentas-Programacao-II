import { Component, inject } from '@angular/core';
import { FormBuilder,ReactiveFormsModule, Validators } from '@angular/forms';
import { TarefaService } from '../../services/tarefa-service';
import { Observable } from 'rxjs';
import { Tarefa } from '../../models/tarefa';
import { Table, TableModule } from 'primeng/table';
import { AsyncPipe, DatePipe } from '@angular/common';
import { ButtonModule } from 'primeng/button';


@Component({
  selector: 'app-tarefa-component',
  imports: [ReactiveFormsModule, TableModule, AsyncPipe, DatePipe, ButtonModule],
  templateUrl: './tarefa-component.html',
  styleUrl: './tarefa-component.scss',
})
export class TarefaComponent {
  private tarefaService = inject(TarefaService);
  private formBuilder = inject(FormBuilder);
  protected form = this.formBuilder.group({
    titulo: ['', [Validators.required, Validators.minLength(3)]],
    descricao: ['', [Validators.required]],
    prioridade: [1, [Validators.required, Validators.min(1), Validators.max(3)]],
    data: [new Date(), [Validators.required]]
  });
  protected selectedTarefa!: Tarefa;

  protected tarefas$ = new Observable<Tarefa[]>();

  constructor() {
    this.obterTarefas();
    console.log(this.tarefas$);

  }

  protected salvar() {
    if (this.form.valid) {
      console.log("entrou!!!");
      this.tarefaService.salvar(this.form.controls.titulo.value!, this.form.controls.descricao.value!,
        this.form.controls.prioridade.value!, this.form.controls.data.value!
      ).subscribe({
        next: (res) => {
          console.log(res);
          this.form.reset();
          this.obterTarefas();
        },
        error: (e) => {
          console.log(e);
        }

      });
    }
    else {
      console.log("Formulário invalido");
    }
  }

  protected obterTarefas() {
    this.tarefas$ = this.tarefaService.get();
  }

  protected remover() {
    console.log(this.selectedTarefa);

    this.tarefaService.del(this.selectedTarefa.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.obterTarefas();
        },
        error: (e) => {
          console.log(e);
        }
      });
  }
}
