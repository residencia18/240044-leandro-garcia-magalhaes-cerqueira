import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrosComponent } from './registros/registros.component';
import { ReproducaoRoutingModule } from './reproducao-routing.module';



@NgModule({
  declarations: [
    RegistrosComponent
  ],
  imports: [
    CommonModule,
    ReproducaoRoutingModule
  ]
})
export class ReproducaoModule { }
