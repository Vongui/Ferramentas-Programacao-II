import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    
  },
  {
    path: 'produtos',
    loadComponent: () => import('./pages/produto-list/produto-list').then(m => m.ProdutoList)
  },
  {
    path: 'produtos/novo',
    loadComponent: () => import('./components/produto-form/produto-form').then(m => m.ProdutoForm)
  },
  {
    path: 'produtos/editar/:id',
    loadComponent: () => import('./components/produto-form/produto-form').then(m => m.ProdutoForm)
  }
];
