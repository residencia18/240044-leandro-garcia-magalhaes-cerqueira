import { Routes } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { AuthenticationComponent } from './components/authentication/authentication.component';
// import { AuthGuard } from './core/auth.guard';

export const routes: Routes = [
    { path: 'authentication', component: AuthenticationComponent },
    { path: 'home', component: HomeComponent },
    { path: 'beachgallery', loadComponent: () => import('./components/beach-gallery/beach-gallery.component').then(comp => comp.BeachGalleryComponent) },
    { path: 'externalareagallery', loadComponent:() => import('./components/external-area-gallery/external-area-gallery.component').then(comp => comp.ExternalAreaGalleryComponent)},
    { path: 'internalareagallery', loadComponent:() => import('./components/internal-area-gallery/internal-area-gallery.component').then(comp => comp.InternalAreaGalleryComponent)},
    { path: 'reservationform', loadComponent:() => import('./components/reservation-form/reservation-form.component').then(comp => comp.ReservationFormComponent)},
    { path: '', redirectTo: '/authentication', pathMatch: 'full' },
];
