import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PaiComponent } from "./componentes/pai/pai.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, PaiComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'compartilhando-dados-pai-filho';
}
