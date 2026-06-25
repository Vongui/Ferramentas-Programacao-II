import { Routes } from '@angular/router';
import { AutorizacaoGuard } from './guards/autorizacao-guard';
import { GuardAdminGuard } from './guards/guard-admin-guard';

export const routes: Routes = [
    {
        path:'',
        pathMatch: 'full',
        redirectTo: 'login'
    },
    {
        path: 'login',
        loadComponent: () => import ('./pages/login/login').then(c => c.Login)
    },
    {
        path: 'register',
        loadComponent: () => import ('./pages/register/register').then(c => c.Register)
    },
    {
        path: 'pagina-protegida',
        loadComponent: () => import ('./pages/pagina-protegida/pagina-protegida').then(c => c.PaginaProtegida),
        canActivate: [AutorizacaoGuard]
    },
    {
        path: 'pagina-publica',
        loadComponent: () => import ('./pages/pagina-publica/pagina-publica').then(c => c.PaginaPublica)
    },
    {
      path: 'pagina-admin',
      loadComponent: () => import ('./pages/pagina-admin/pagina-admin').then(c => c.PaginaAdmin),
      canActivate: [AutorizacaoGuard, GuardAdminGuard]
    }
];
