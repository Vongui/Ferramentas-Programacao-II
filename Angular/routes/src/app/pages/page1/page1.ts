import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-page1',
  imports: [RouterLink],
  templateUrl: './page1.html',
  styleUrl: './page1.css',
})
export class Page1 {
  private router = inject(Router);

  redirecionar() {
    const pessoa = {
      nome: 'John',
      idade: '23',
    }
    this.router.navigate(['/page2/10'], {state: pessoa})
  }
}
