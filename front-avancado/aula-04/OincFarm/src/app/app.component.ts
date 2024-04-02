import { Component, HostListener} from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { PigFormComponent } from "./components/pig-form/pig-form.component";
import { ListarSuinosComponent } from './components/listar-suinos/listar-suinos.component';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule } from '@angular/common/http'; // Importe o HttpClientModule

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, PigFormComponent, ListarSuinosComponent, HttpClientModule, HomeComponent]
})
export class AppComponent {

  constructor(private router : Router) {}

goToHome() {
  this.router.navigate(['/home']);
}
  title = 'OincFarm';

  

  


}
