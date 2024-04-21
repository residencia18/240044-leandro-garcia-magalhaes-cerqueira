import { Component, HostListener, OnInit, inject} from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { PigFormComponent } from "./components/suino/pig-form/pig-form.component";
import { ListarSuinosComponent } from './components/suino/listar-suinos/listar-suinos.component';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule } from '@angular/common/http'; // Importe o HttpClientModule
import { AuthService } from './core/auth.service';

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, PigFormComponent, ListarSuinosComponent, HttpClientModule, HomeComponent]
})
export class AppComponent implements OnInit {

  authService = inject(AuthService)

  ngOnInit(): void {
     this.authService.user$.subscribe(user => {
      if (user){
        this.authService.currentUserSig.set({
          email: user.email!,
          username: user.displayName!,
        });
      } else {
        this.authService.currentUserSig.set(null);
      }
      console.log(this.authService.currentUserSig())
     }) 
  }

  constructor(private router : Router) {}

  goToHome() {
    this.router.navigate(['/home']);
  }
    title = 'OincFarm';

  logout(): void {
      this.authService.logout();
      this.router.navigate(['/login']);
  }



  

  


}
