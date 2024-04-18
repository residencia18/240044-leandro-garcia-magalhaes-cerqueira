import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../../core/core.module';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../core/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MaterialModule } from '../../shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { signInWithEmailAndPassword } from '@angular/fire/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, CoreModule, MaterialModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  fb = inject(FormBuilder);
  http = inject(HttpClient);
  authService = inject(AuthService);
  router = inject(Router);

  hide = true;
  formLogin!: FormGroup;
  isLogin: boolean = true;
  isLoading = false;
  error: string = '';
  isError: boolean = false;

  constructor(private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.createForm();
  }

  register(): void {
    this.isLoading = true;
    this.isLogin = false;
  
    const rawForm = this.formLogin.getRawValue();
    this.authService.register(rawForm.email, rawForm.username, rawForm.password)
      .subscribe({
        next: () => {
          this.snackBar.open('Cadastro realizado com sucesso', 'Fechar', { duration: 6000 });
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

  login(): void {
    this.isLoading = true;
    this.isLogin = true;
  
    const rawForm = this.formLogin.getRawValue();
    this.authService.login(rawForm.email, rawForm.password)
      .subscribe({
        next: () => {
          this.snackBar.open('Login realizado com sucesso', 'Fechar', { duration: 6000 });
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
    this.formLogin = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

 
  redirectToRegister(): void {
    this.isLogin = !this.isLogin;
  }

  private handleAuthError(error: any) {
    const errorMessages: Record<string, string> = {
      'EMAIL_EXISTS': 'O e-mail já está em uso.',
      'INVALID_LOGIN_CREDENTIALS': 'Senha ou e-mail inválidos.'
    };
  
    const errorMessage = error.error?.error?.message || 'Ocorreu um erro ao cadastrar o usuário.';
    this.error = errorMessages[errorMessage] || errorMessage;
  
    this.snackBar.open(this.error, 'Fechar', { duration: 6000 });
  
    this.isError = true;
    this.isLoading = false;
  }
  
  
}
