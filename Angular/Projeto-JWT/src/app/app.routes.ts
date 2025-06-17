import { Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
    {
        path:'',
        redirectTo: '/cliente',
        pathMatch: 'full'
    },
  {
    path: 'cliente',
    loadComponent: () =>
      import('./componentes/cliente/cliente.component').then(
        (m) => m.ClienteComponent
      ),
      canActivate: [AuthGuard]
  },
  {
    path: 'formulario',
    loadComponent: () =>
      import(
        './componentes/formulario-exemplo/formulario-exemplo.component'
      ).then((m) => m.FormularioExemploComponent),
      canActivate: [AuthGuard]
  },
  {
    path: 'post',
    loadComponent: () =>
      import('./componentes/post/post.component').then((m) => m.PostComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./componentes/login/login.component').then((m) => m.LoginComponent),
  },
];
