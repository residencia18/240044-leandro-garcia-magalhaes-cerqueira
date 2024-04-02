import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { ListarAtendimentosComponent } from './listar-atendimentos/listar-atendimentos.component';
import { EditarAtendimentoComponent } from './editar-atendimento/editar-atendimento.component';
import { CadastrarAtendimentoComponent } from './cadastrar-atendimento/cadastrar-atendimento.component';
import { DetalharAtendimentoComponent } from './detalhar-atendimento/detalhar-atendimento.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'; 

//Imports da biblioteca Material
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import { RouterModule, Routes } from '@angular/router';

  const routes: Routes = [
    { path: 'cadastrarAtendimento', component: CadastrarAtendimentoComponent },
    { path: 'listarAtendimentos', component: ListarAtendimentosComponent },
    { path: 'editarAtendimento/:id', component: EditarAtendimentoComponent},
    { path: 'detalharAtendimento/:id', component: DetalharAtendimentoComponent },
    { path: '', redirectTo: '/cadastrarAtendimento', pathMatch: 'full' },
  ];


@NgModule({
  declarations: [
    AppComponent,
    ListarAtendimentosComponent,
    EditarAtendimentoComponent,
    CadastrarAtendimentoComponent,
    DetalharAtendimentoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatCheckboxModule,
    MatRadioModule,
    MatCardModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)

  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
