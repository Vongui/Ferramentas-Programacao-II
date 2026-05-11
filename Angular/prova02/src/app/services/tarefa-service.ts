import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Tarefa } from '../models/tarefa';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TarefaService {
  private api = environment.api;
  private http = inject(HttpClient);


  salvar(titulo: string, descricao: string, prioridade: number, data: Date) {
    const tarefa = {
      titulo: titulo,
      descricao: descricao,
      prioridade: prioridade,
      dataLimite: data
    };
    console.log("chegou aqui");
    

    return this.http.post<Tarefa>(`${this.api}/tarefas`, tarefa);
  }

  get() {
    return this.http.get<Tarefa[]>(`${this.api}/tarefas`);
  }

  del(id: number) {
    return this.http.delete<Tarefa>(`${this.api}/tarefas/${id}`);
  }

}
