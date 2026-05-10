import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ButtonCheck } from './component/button-check/button-check';
import { Menu } from './component/menu/menu';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Menu],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected readonly title = signal('ang-primeng');
}
