import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

//FireAuth
import { provideFirebaseApp, initializeApp } from '@angular/fire/app'
import { getAuth, provideAuth } from '@angular/fire/auth';


const firebaseConfig = {
  apiKey: "AIzaSyC4sbUW-Qyw1wOYaIa2uHb7OcEf6MCrTic",
  authDomain: "oincfarm-97b0a.firebaseapp.com",
  databaseURL: "https://oincfarm-97b0a-default-rtdb.firebaseio.com",
  projectId: "oincfarm-97b0a",
  storageBucket: "oincfarm-97b0a.appspot.com",
  messagingSenderId: "847158716055",
  appId: "1:847158716055:web:c848bfc19be7f4279febd2",
  measurementId: "G-48F7H0QHKZ"
};

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideAnimationsAsync(),
    provideHttpClient(),
    DatePipe,importProvidersFrom(),
    { provide: MatDialogRef, useValue: {} },
    { provide: MAT_DIALOG_DATA, useValue: {} },
    importProvidersFrom([provideFirebaseApp(() => initializeApp(firebaseConfig)),
    provideAuth(() => getAuth())
    ]),
    
  ]
};
