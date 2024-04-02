import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Usuario } from '../../models/usuario';

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
  private readonly apiKey = 'AIzaSyB5w1o6q6kFHmEjJ1mao5AdPHHaX-dHv_Q';
  private readonly signUpUrl = `https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=${this.apiKey}`;
  private readonly signInUrl = `https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${this.apiKey}`;

  private userSubject = new BehaviorSubject<Usuario | null>(null);
  private estaAutenticado = false;

  constructor(private http: HttpClient) {
    const userData = sessionStorage.getItem('userData');
    if (userData) {
      this.userSubject.next(JSON.parse(userData));
    }
  }

  getUser(): Observable<Usuario | null> {
    return this.userSubject.asObservable();
  }

  logout(): void {
    this.userSubject.next(null);
    sessionStorage.removeItem('userData');
    this.setAutenticado(false);
  }

  signUpUser(email: string, password: string): Observable<AuthResponseData> {
    return this.authenticate(email, password, this.signUpUrl);
  }

  loginUser(email: string, password: string): Observable<AuthResponseData> {
    return this.authenticate(email, password, this.signInUrl);
  }

  private authenticate(email: string, password: string, url: string): Observable<AuthResponseData> {
    return this.http.post<AuthResponseData>(url, {
      email,
      password,
      returnSecureToken: true
    }).pipe(
      tap(resData => {
        const expiracaoData = new Date(new Date().getTime() + +resData.expiresIn * 1000);
        const user = new Usuario(
          resData.email,
          resData.localId,
          resData.idToken,
          expiracaoData
        );
        this.userSubject.next(user);
        sessionStorage.setItem('userData', JSON.stringify(user));
        this.setAutenticado(true);
      })
    );
  }

  private setAutenticado(isAuthenticated: boolean): void {
    this.estaAutenticado = isAuthenticated;
  }
}
