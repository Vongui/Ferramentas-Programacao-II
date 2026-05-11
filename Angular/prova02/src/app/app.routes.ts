import { Routes } from '@angular/router';
import { AuthGuard } from './guards/auth-guard';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login'
  },
  {
    path: 'login',
    loadComponent: () => import('./components/login-component/login-component').then(m => m.LoginComponent)
  },
  {
    path: 'tarefas',
    loadComponent: () => import('./components/tarefa-component/tarefa-component').then(m => m.TarefaComponent),
    canActivate: [AuthGuard]
  },
];
