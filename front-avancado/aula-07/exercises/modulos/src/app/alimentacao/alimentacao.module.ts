import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlimentacaoRoutingModule } from './alimentacao-routing.module';
import { EstoqueComponent } from './estoque/estoque.component';
import { AgendamentoComponent } from './agendamento/agendamento.component';



@NgModule({
  declarations: [
    EstoqueComponent,
    AgendamentoComponent
  ],
  imports: [
    CommonModule,
    AlimentacaoRoutingModule
  ]
})
export class AlimentacaoModule { }
