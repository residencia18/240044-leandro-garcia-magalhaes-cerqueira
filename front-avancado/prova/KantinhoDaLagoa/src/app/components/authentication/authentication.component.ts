import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../security/authentication/authentication.component';

@Component({
  selector: 'app-authentication',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './authentication.component.html',
  styleUrl: './authentication.component.css'
})
export class AuthenticationComponent {

  fb = inject(FormBuilder);
  http = inject(HttpClient);
  authService = inject(AuthService);
  router = inject(Router);

  hide = true;
  authForm!: FormGroup;
  isLogin: boolean = true;
  isLoading = false;
  error: string = '';
  isError: boolean = false;

  constructor() {}

  ngOnInit(): void {
    this.createForm();
  }

  guest(): void {
    this.isLoading = true;
    this.isLogin = false;
  }

  login(): void {
    this.isLoading = true;
    this.isLogin = true;
  
    const rawForm = this.authForm.getRawValue();
    this.authService.login(rawForm.email, rawForm.password)
      .subscribe({
        next: () => {
          this.isLoading = false;
          this.isLogin = true;
          this.isError = false;
          this.router.navigateByUrl('/home');
        },
        error: (error: any) => {
          this.isError = true;
          console.log(error);
          this.handleAuthError(error);
          this.isLoading = false;
        }
      });
  }

  createForm(): void {
    this.authForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

 
  redirectToHome(): void {
    this.isLogin = !this.isLogin;
    this.router.navigateByUrl("/home");
  }

  private handleAuthError(error: any) {
    const errorMessages: Record<string, string> = {
      'EMAIL_EXISTS': 'O e-mail já está em uso.',
      'INVALID_LOGIN_CREDENTIALS': 'Senha ou e-mail inválidos.'
    };
  
    const errorMessage = error.error?.error?.message || 'Ocorreu um erro ao cadastrar o usuário.';
    this.error = errorMessages[errorMessage] || errorMessage;
  
    this.isError = true;
    this.isLoading = false;
  }

}
