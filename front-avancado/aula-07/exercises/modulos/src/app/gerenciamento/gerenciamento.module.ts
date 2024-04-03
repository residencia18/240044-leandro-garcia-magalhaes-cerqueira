import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RastreamentoComponent } from './rastreamento/rastreamento.component';
import { GerenciamentoRoutingModule } from './gerenciamento-routing.module';


@NgModule({
  declarations: [
    RastreamentoComponent
  ],
  imports: [
    CommonModule,
    GerenciamentoRoutingModule
  ]
})
export class GerenciamentoModule { }
