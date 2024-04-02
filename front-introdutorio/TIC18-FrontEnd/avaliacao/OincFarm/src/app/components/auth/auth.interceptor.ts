import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent, HttpParams } from '@angular/common/http';
import { Observable} from 'rxjs';
import { exhaustMap, take } from 'rxjs/operators';
import { AutenticaService } from './autentica.service';
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AutenticaService) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    return this.authService.getUser().pipe(
      take(1),
      exhaustMap(user => {
        if(!user){
          return next.handle(req);
        }
        const modifiedReq = req.clone({
          params: new HttpParams().set('auth', user.token)
        });
        return next.handle(modifiedReq);
      })
    );
  }
}
