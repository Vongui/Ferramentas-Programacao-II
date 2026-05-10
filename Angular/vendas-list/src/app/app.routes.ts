import { Routes } from '@angular/router';
import { AuthGuard } from './auth-guard';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login'
  },
  {
    path: 'vendas',
    loadComponent: () => import('../components/venda-list/venda-list').then(m => m.VendaList),
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    loadComponent: () => import('../components/user-login/user-login').then(m => m.UserLogin),
  }
];
