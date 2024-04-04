import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './core/auth.guard';


export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'cadastrarSuino', loadComponent: () => import('./components/suino/pig-form/pig-form.component').then(comp => comp.PigFormComponent), canActivate: [AuthGuard] },
    { path: 'listarSuinos', loadComponent:() => import('./components/suino/listar-suinos/listar-suinos.component').then(comp => comp.ListarSuinosComponent), canActivate: [AuthGuard] },
    { path: 'editarSuino/:id', loadComponent:() => import('./components/suino/editar/editar.component').then(comp => comp.EditarComponent ), canActivate: [AuthGuard] },
    { path: 'listarPeso',  loadComponent:() => import('./components/peso/listar-pesos/listar-pesos.component').then(comp => comp.ListarPesosComponent), canActivate: [AuthGuard] },
    { path: 'controlePeso', loadComponent:() => import('./components/peso/controle-peso/controle-peso.component').then(comp => comp.ControlePesoComponent), canActivate: [AuthGuard] },
    { path: 'menuPeso', loadComponent:() => import('./components/peso/menu-peso/menu-peso.component').then(comp => comp.MenuPesoComponent), canActivate: [AuthGuard] },
    { path: 'menuSessao', loadComponent:() => import('./components/sessoes/menu-sessao/menu-sessao.component').then(comp => comp.MenuSessaoComponent), canActivate: [AuthGuard] },
    { path: 'cadastroSessao', loadComponent:() => import('./components/sessoes/cadastro-sessao/cadastro-sessao.component').then(comp => comp.CadastroSessaoComponent), canActivate: [AuthGuard] },
    { path: 'sessoes', loadComponent:() => import('./components/sessoes/lista-sessoes/lista-sessoes.component').then(comp => comp.ListaSessoesComponent), canActivate: [AuthGuard] },
    { path: 'sessoes/:id', loadComponent:() => import('./components/sessoes/sessao/sessao.component').then(comp => comp.SessaoComponent), canActivate: [AuthGuard] },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
];

