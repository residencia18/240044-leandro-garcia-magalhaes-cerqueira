import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApresentacaoComponent } from './apresentacao/apresentacao.component';


const appRoutes: Routes = [
  {
    path:'',
    component: ApresentacaoComponent
  },
  {
    path: 'alimentacao',
    //apenas carrega o modulo quando acessar a rota alimentacao
    loadChildren: () => import('./alimentacao/alimentacao.module').then(m => m.AlimentacaoModule)
  },
  {
    path: 'reproducao',
    //apenas carrega o modulo quando acessar a rota reproducao
    loadChildren: () => import('./reproducao/reproducao.module').then(m => m.ReproducaoModule)
  },
  {
    path: 'gerenciamento',
    //apenas carrega o modulo quando acessar a rota gerenciamento
    loadChildren: () => import('./gerenciamento/gerenciamento.module').then(m => m.GerenciamentoModule)
  }

]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
