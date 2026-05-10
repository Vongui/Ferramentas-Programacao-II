import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'vendas',
    pathMatch: 'full',
  },
  {
    path: 'vendas',
    loadComponent: () => import('./pages/venda-list/venda-list').then((p) => p.VendaList),
  },
];
