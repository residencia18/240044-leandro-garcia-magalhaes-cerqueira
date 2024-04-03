import { Routes } from '@angular/router';
import { PigFormComponent } from './components/suino/pig-form/pig-form.component';
import { ListarSuinosComponent } from './components/suino/listar-suinos/listar-suinos.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { EditarComponent } from './components/suino/editar/editar.component';
import { ControlePesoComponent } from './components/peso/controle-peso/controle-peso.component';
import { MenuPesoComponent } from './components/peso/menu-peso/menu-peso.component';
import { AuthGuard } from './core/auth.guard';
import { ListarPesosComponent } from './components/peso/listar-pesos/listar-pesos.component';
import { MenuSessaoComponent } from './components/sessoes/menu-sessao/menu-sessao.component';
import { CadastroSessaoComponent } from './components/sessoes/cadastro-sessao/cadastro-sessao.component';
import { ListaSessoesComponent } from './components/sessoes/lista-sessoes/lista-sessoes.component';
import { SessaoComponent } from './components/sessoes/sessao/sessao.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent, canActivate : [AuthGuard]},
    { path: 'cadastrarSuino', component: PigFormComponent, canActivate : [AuthGuard] },
    { path: 'listarSuinos', component: ListarSuinosComponent, canActivate : [AuthGuard] },
    { path: 'editarSuino/:id', component: EditarComponent, canActivate : [AuthGuard] },
    { path: 'listarPeso', component: ListarPesosComponent, canActivate : [AuthGuard] },
    { path: 'controlePeso', component: ControlePesoComponent, canActivate : [AuthGuard] },
    { path: 'menuPeso', component : MenuPesoComponent, canActivate : [AuthGuard]},
    { path: 'menuSessao', component : MenuSessaoComponent, canActivate : [AuthGuard]},
    { path: 'cadastroSessao', component : CadastroSessaoComponent, canActivate : [AuthGuard]},
    { path: 'sessoes', component : ListaSessoesComponent, canActivate : [AuthGuard]},
    { path: 'sessoes/:id', component : SessaoComponent, canActivate : [AuthGuard]},
    { path: '', redirectTo: '/login', pathMatch: 'full' },
];

