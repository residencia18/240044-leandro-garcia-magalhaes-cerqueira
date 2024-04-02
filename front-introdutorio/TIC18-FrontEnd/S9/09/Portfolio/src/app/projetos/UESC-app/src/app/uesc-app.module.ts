import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { UescAppComponent } from './app.component';
import { NoticiasComponent } from './noticias/noticias.component';
import { DestaqueComponent } from './destaque/destaque.component';
import { ServicosComponent } from './servicos/servicos.component';
import { ResultadosComponent } from './resultados/resultados.component';
import { MenuComponent } from './menu/menu.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    UescAppComponent,
    NoticiasComponent,
    DestaqueComponent,
    ServicosComponent,
    ResultadosComponent,
    MenuComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [UescAppComponent]
})
export class UescAppModule { }
