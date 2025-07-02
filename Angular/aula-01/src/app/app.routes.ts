
import { ClienteComponent } from './components/cliente/cliente.component';
import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./components/cliente/cliente.component').then(
        (m) => m.ClienteComponent
      )
  },

];

