import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { PostComponent } from "./componentes/post/post.component";
import { ClienteComponent } from './componentes/cliente/cliente.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, PostComponent, ClienteComponent, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  protected title = 'Angular';
  protected nomeCompleto!: string;

  protected nomes: string[] = ["César", "Ana", "Vítor"];

  constructor( ) {
     this.nomeCompleto = "César Alberto";
  }

  alterarNome() {
    this.nomeCompleto = "José Carlos";
  }

  getNomeCompleto(): string {
    return this.nomeCompleto;
  }

  exibir(): void {

  }


}
