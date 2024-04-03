import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RastreamentoComponent } from './rastreamento/rastreamento.component';

const routes: Routes = [
  {
    path: '',
    component: RastreamentoComponent,
  }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class GerenciamentoRoutingModule { }
