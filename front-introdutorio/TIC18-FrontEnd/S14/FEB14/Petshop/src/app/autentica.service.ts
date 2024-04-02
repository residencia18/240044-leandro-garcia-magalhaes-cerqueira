import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { Usuario } from './autenticacao/usuario.model';

interface AuthResponseData {
  idToken: string;
  email: string;
  refreshToken: string;
  expiresIn: string;
  localId: string;
  registered?: boolean;
}


@Injectable({
  providedIn: 'root'
})
export class AutenticaService {

   //COMPARTILHA O ESTADO DE AUTENTICAÇÃO ------------------------------(

  private estaAutenticadoSubject = new BehaviorSubject<boolean>(false);
  estaAutenticado$ = this.estaAutenticadoSubject.asObservable();

  // Método para definir o estado de autenticação
  setAutenticado(autenticado: boolean) {
    this.estaAutenticadoSubject.next(autenticado);
  }

  // Método para obter o estado de autenticação atual
  getAutenticado(): boolean {
    return this.estaAutenticadoSubject.value;
  }

   //COMPARTILHA O ESTADO DE AUTENTICAÇÃO ------------------------------)


  usuario = new BehaviorSubject<Usuario>(new Usuario('', '', '', new Date()));

  constructor(private http: HttpClient) { }

  signupUser(email: string, password: string) {
   return this.http.post<AuthResponseData>('https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyD1pAjeZ8olPv6dd2NHIR0i3NbVnuzOPNM', 
   {
      email: email,
      password: password,
      returnSecureToken: true
   }).pipe(
      tap(resData => {
        const expiracaoData = new Date(new Date().getTime() + +resData.expiresIn * 1000);
        const usuario = new Usuario(
          resData.email,
          resData.localId,
          resData.idToken,
          expiracaoData
        );

        this.usuario.next(usuario);
        localStorage.setItem('userData', JSON.stringify(usuario));
      })
   );
  }

  loginUser(email: string, password: string) {
    return this.http.post<AuthResponseData>('https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyD1pAjeZ8olPv6dd2NHIR0i3NbVnuzOPNM',
    {
      email: email,
      password: password,
      returnSecureToken: true
   }).pipe(
    tap(resData => {
      const expiracaoData = new Date(new Date().getTime() + +resData.expiresIn * 1000);
        const usuario = new Usuario(
          resData.email,
          resData.localId,
          resData.idToken,
          expiracaoData
        );
        this.usuario.next(usuario);
        localStorage.setItem('userData', JSON.stringify(usuario));
    }),
   );
  }

  autoLogin() {
    const userData :{
      email: string;
      id: string;
      _token: string;
      _tokenExpirationDate: string;
    
    } = JSON.parse(localStorage.getItem('userData') as string);
    if(!userData) {
      return;
    }

    const loadedUser = new Usuario(
      userData.email,
      userData.id,
      userData._token,
      new Date(userData._tokenExpirationDate)
    );

    if(loadedUser.token) {
      this.usuario.next(loadedUser);
    }


  }

  logout() {
    this.usuario.next(new Usuario('', '', '', new Date()));

  }


}
