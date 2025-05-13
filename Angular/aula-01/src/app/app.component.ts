import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ClienteComponent } from "./components/cliente/cliente.component";
import { PostComponent } from "./components/post/post.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ClienteComponent, PostComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'aula-01';
  nomeCompleto!: string;

  nomes = ['Ana', 'Cesar', 'Vitor']

  constructor(){

  }

  alterarNome() {
    this.nomeCompleto = "Mudei poha";
  }

  getNomeCompleto(): string{
    this.nomeCompleto = "Vai tomar no cu";
    this.exibir();
    return this.nomeCompleto;
  }

  getNome(): string{
    return "Gui"
  }

  exibir(){


  }

}
