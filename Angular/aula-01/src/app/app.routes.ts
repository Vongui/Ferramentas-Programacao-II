
import { ClienteComponent } from './components/cliente/cliente.component';
import { PostComponent } from './components/post/post.component';
import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./components/cliente/cliente.component').then(
        (m) => m.ClienteComponent
      )
  },

  {
    path: 'post',
    loadComponent: () =>
      import('./components/post/post.component').then(
        (m) => m.PostComponent
      )
  }
];

