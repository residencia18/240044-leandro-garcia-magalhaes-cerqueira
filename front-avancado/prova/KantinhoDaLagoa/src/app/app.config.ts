import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { routes } from './app.routes';

// Firebase
import { provideFirebaseApp, initializeApp } from '@angular/fire/app';
import { provideAuth, getAuth } from '@angular/fire/auth';

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyDoKBnGBw1LAMcxg-Xyv3q6GBJ0hAwOZg4",
  authDomain: "kantinho-da-lagoa.firebaseapp.com",
  databaseURL: "https://kantinho-da-lagoa-default-rtdb.firebaseio.com",
  projectId: "kantinho-da-lagoa",
  storageBucket: "kantinho-da-lagoa.appspot.com",
  messagingSenderId: "290295957161",
  appId: "1:290295957161:web:6b7f0f17f6b4d4ac208bb8",
  measurementId: "G-FXTW12D87P"
};

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
    provideFirebaseApp(() => initializeApp(firebaseConfig)),
    provideAuth(() => getAuth()),
  ]
};

