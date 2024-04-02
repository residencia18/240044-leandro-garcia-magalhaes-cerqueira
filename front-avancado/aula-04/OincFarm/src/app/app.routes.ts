import { Routes } from '@angular/router';
import { PigFormComponent } from './components/pig-form/pig-form.component';
import { ListarSuinosComponent } from './components/listar-suinos/listar-suinos.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/auth/login.component';
import { CadastroPesoComponent } from './components/cadastro-peso/cadastro-peso.component';
import { EditarComponent } from './components/editar/editar.component';
import { ControlePesoComponent } from './components/controle-peso/controle-peso.component';
import { MenuPesoComponent } from './components/menu-peso/menu-peso.component';
import { AutenticacaoGuard } from './components/auth/auth.guard';
import { ListarPesosComponent } from './components/listar-pesos/listar-pesos.component';
import { MenuSessaoComponent } from './components/menu-sessao/menu-sessao.component';
import { CadastroSessaoComponent } from './components/cadastro-sessao/cadastro-sessao.component';
import { ListaSessoesComponent } from './components/lista-sessoes/lista-sessoes.component';
import { SessaoComponent } from './components/sessao/sessao.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent, canActivate : [AutenticacaoGuard]},
    { path: 'cadastrarSuino', component: PigFormComponent, canActivate : [AutenticacaoGuard] },
    { path: 'listarSuinos', component: ListarSuinosComponent, canActivate : [AutenticacaoGuard] },
    { path: 'editarSuino/:id', component: EditarComponent, canActivate : [AutenticacaoGuard] },
    { path: 'listarPeso', component: ListarPesosComponent, canActivate : [AutenticacaoGuard] },
    { path: 'controlePeso', component: ControlePesoComponent, canActivate : [AutenticacaoGuard] },
    { path: 'menuPeso', component : MenuPesoComponent, canActivate : [AutenticacaoGuard]},
    { path: 'menuSessao', component : MenuSessaoComponent, canActivate : [AutenticacaoGuard]},
    { path: 'cadastroSessao', component : CadastroSessaoComponent, canActivate : [AutenticacaoGuard]},
    { path: 'sessoes', component : ListaSessoesComponent, canActivate : [AutenticacaoGuard]},
    { path: 'sessoes/:id', component : SessaoComponent, canActivate : [AutenticacaoGuard]},
    { path: '', redirectTo: '/login', pathMatch: 'full' },
];

