import { Routes } from '@angular/router';
import { Page1 } from './pages/page1/page1';
import { Page2 } from './pages/page2/page2';

export const routes: Routes = [
    {
        path: 'page1', //URL
        loadComponent: () => import('./pages/page1/page1').then(m => m.Page1),
        // component: Page1 //Classe-Component
    },
    {
        path: 'page2/:id',
        loadComponent: () => import('./pages/page2/page2').then(m => m.Page2),

    }

];
