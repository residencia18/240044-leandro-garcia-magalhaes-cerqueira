import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard  {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): Observable<boolean> {
    return this.authService.user$.pipe(
      map(user => {
        if (user) {
          return true; // Se houver um usuário, ele está autenticado
        } else {
          this.router.navigate(['/login']);
          return false; // Se não houver usuário, não está autenticado
        }
      })
    );
  }
}
