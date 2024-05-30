import { Injectable, inject, signal } from '@angular/core';
import { Observable, from, timer } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Auth, createUserWithEmailAndPassword, signInWithEmailAndPassword, user } from '@angular/fire/auth';
import { signOut, updateProfile } from 'firebase/auth';
import { UserInterface } from '../../interfaces/user';

@Injectable({
  providedIn: 'root',
})

export class AuthService {

  firebaseAuth = inject(Auth);
  user$ = user(this.firebaseAuth);
  currentUserSig = signal<UserInterface | null | undefined>(undefined);

  register(email: string, username: string, password: string): Observable<void> {
    const promise = createUserWithEmailAndPassword(this.firebaseAuth, email, password).then((response) =>
      updateProfile(response.user, { displayName: username })
    );

    return from(promise);
  }

  login(email: string, password: string): Observable<void> {
    const loginPromise = signInWithEmailAndPassword(this.firebaseAuth, email, password).then(() => {});

    const loginObservable = from(loginPromise);

    // Automatically logout after 30 seconds
    const autoLogoutObservable = loginObservable.pipe(
      switchMap(() => timer(30000)), // Wait for 30 seconds
      switchMap(() => this.logout()) // Call logout method
    );

    // Return an observable that completes after login and auto-logout
    return autoLogoutObservable;
  }

  logout(): Observable<void> {
    const promise = signOut(this.firebaseAuth);
    return from(promise);
  }
}
