import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../../core/core.module';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms'; 
import { AuthService } from '../../core/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MaterialModule } from '../../shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, CoreModule, MaterialModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  hide = true;
  formLogin!: FormGroup;
  isLogin: boolean = true;
  isLoading = false;
  error: string = '';
  isError: boolean = false;

  constructor(
    private readonly formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private snackBar: MatSnackBar,
  ) { }

  
  ngOnInit(): void {
    this.criarFormulario();
  }

  criarFormulario(): void {
    this.formLogin = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  login() {
    if (!this.formLogin.valid) {
      return;
    }

    const email = this.formLogin.value.email;
    const senha = this.formLogin.value.senha;

    let authObservable: Observable<any>;

    authObservable = this.authService.loginUser(email, senha);

    authObservable.subscribe({
      next: responseData => {
        console.log(responseData);
        this.isLoading = false;
        this.isError = false;
        this.router.navigate(['/home']);
      },
      error: (error: any) => { // Especificando explicitamente o tipo do parâmetro error
        console.log(error);
        this.handleAuthError(error);
      }
    });
  }

  register() {
    this.isLoading = true;
    this.isLogin = false;
    const email = this.formLogin.value.email;
    const senha = this.formLogin.value.senha;

    this.authService.signUpUser(email, senha).subscribe(
      responseData => {
        this.snackBar.open('Cadastro realizado com sucesso', 'Fechar', { duration: 6000 });
        this.isLoading = false;
        this.isLogin = true;
        this.isError = false;
        this.router.navigate(['/home']);
      },
      (error: any) => { // Especificando explicitamente o tipo do parâmetro error
        console.log(error);
        this.isError = true;
        this.error = 'Ocorreu um erro ao cadastrar o usuário.'
        this.isLoading = false;
      }
    );
  }
  
  redirectToRegister(): void {
    this.isLogin = !this.isLogin;
  }

  private handleAuthError(error: any) {
    if (error.error && error.error.error && error.error.error.message) {
      switch (error.error.error.message) {
        case 'EMAIL_EXISTS':
          this.error = 'O e-mail já está em uso.';
          this.snackBar.open('O e-mail já está em uso.', 'Fechar', { duration: 6000 });
          break;
        case 'INVALID_LOGIN_CREDENTIALS':
          this.error = 'Senha ou e-mail inválidos.';
          this.snackBar.open('Senha ou e-mail inválidos.', 'Fechar', { duration: 6000 });
          break;
        default:
          this.error = 'Ocorreu um erro ao cadastrar o usuário.';
          this.snackBar.open('Ocorreu um erro ao cadastrar o usuário.', 'Fechar', { duration: 6000 });
          break;
      }
      this.isError = true;
      this.isLoading = false;
    }
  }

}







