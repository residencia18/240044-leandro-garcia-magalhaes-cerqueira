import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AutenticaService } from './autentica.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoGuard implements CanActivate {

  constructor(private authService: AutenticaService, private router: Router) {}

  canActivate(): Observable<boolean> {
    return this.authService.getUser().pipe(
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
