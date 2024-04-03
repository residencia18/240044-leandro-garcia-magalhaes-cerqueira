import { NgModule } from '@angular/core';
import { RegistrosComponent } from './registros/registros.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: RegistrosComponent,
  }

];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ReproducaoRoutingModule { }
