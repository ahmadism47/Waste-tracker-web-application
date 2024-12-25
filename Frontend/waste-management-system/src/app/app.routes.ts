import { Routes } from '@angular/router';
import path from 'path';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
     {
        path: '',
        redirectTo: 'home', // if it is an rmpty path => go to home page
        pathMatch: 'full' // only direct if all the url is empty
     },
     {
        path: 'home',
        loadComponent: () => import('./components/home/home.component').then(m => m.HomeComponent)
        // dynamic import for better performance:
        // 'load component () =>' defers the loading until the route is accessed
        // m: refers to the module object after extraction from the path before it
    },  

    {
        path: 'login',
        loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent)
    },

    {
        path: 'portal',
        loadComponent: () => import('./components/portal/portal.component').then(m => m.PortalComponent),
        canActivate: [authGuard] 
    },

    {
        path: 'analysis',
        loadComponent: () => import('./components/analysis/analysis.component').then(m => m.AnalysisComponent),
        canActivate: [authGuard]
    },

    {
        path: 'contact',
        loadComponent: () => import('./components/contact/contact.component').then(m => m.ContactComponent)
    }
 ];
