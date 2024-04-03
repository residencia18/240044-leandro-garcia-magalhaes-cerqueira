import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EstoqueComponent } from './estoque/estoque.component';
import { AgendamentoComponent } from './agendamento/agendamento.component';

const routes: Routes = [
  {
    path: '',
    component: EstoqueComponent,
    children: [
      {
        path: 'agendamento',
        component: AgendamentoComponent
      }
    ]
  }

];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})

export class AlimentacaoRoutingModule { }
