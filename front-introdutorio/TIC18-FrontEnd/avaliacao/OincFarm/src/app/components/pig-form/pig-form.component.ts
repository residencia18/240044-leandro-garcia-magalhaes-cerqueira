import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { onlyNumbersValidator } from '../../customValidators/onlyNumbersValidator';
import { dateFormatValidator } from '../../customValidators/dateFormatValidator';
import { AudioService } from '../../utils/audio.service';
import { DataBaseService } from '../../utils/data-base.service';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-pig-form',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatButtonModule, FormsModule, ReactiveFormsModule, CommonModule, MatSelectModule, HttpClientModule ],
  templateUrl: './pig-form.component.html',
  styleUrl: './pig-form.component.css'
})
export class PigFormComponent {

  exemploForm : FormGroup;

  constructor(private fb: FormBuilder, private audioService : AudioService, private dataBaseService: DataBaseService, private snackBar : MatSnackBar, private router : Router) {
    // Criar o formulário utilizando FormBuilder
    this.exemploForm = this.fb.group({
      brinco: [null, [
        Validators.required,
        onlyNumbersValidator()
      ]],
      brincoPai: [null, [
        Validators.required,
        onlyNumbersValidator()
      ]],
      brincoMae: [null, [
        Validators.required,
        onlyNumbersValidator()
      ]],
      dataNascimento: [null, [
        Validators.required,
        dateFormatValidator()
       
      ]],
      dataSaida: [null, [
        Validators.required,
        dateFormatValidator()
       
      ]],
      status: [null, Validators.required],
      sexo: [null, Validators.required],
    });

  }

  onSubmit(): void {
    //Atribuindo as informações preenchidas pelo usuário as variáveis
    const brinco = this.exemploForm.get('brinco');
    const brincoPai = this.exemploForm.get('brincoPai');
    const brincoMae = this.exemploForm.get('brincoMae');
    const brincoValue = this.exemploForm.get('brinco')!.value;
    const dataNascimento = this.exemploForm.get('dataNascimento');
    const dataSaida = this.exemploForm.get('dataSaida');
    const status = this.exemploForm.get('status');
    const sexo = this.exemploForm.get('sexo');
    this.audioService.playButtonClickSound();
  
    //Chama o método verificaBrincoExistente para impedir caso o usuário insira um brinco já existente
    // Verifique se o número do brinco já existe no Firebase
    this.dataBaseService.verificarBrincoExistente(brincoValue).subscribe((existe: boolean) => {
      if (existe) {
        alert('Este número de brinco já está cadastrado. Por favor, escolha outro número.');
      } else {
        if (
          brinco &&
          brincoPai &&
          brincoMae &&
          dataNascimento &&
          dataSaida &&
          status &&
          sexo &&
          this.exemploForm.valid
        ) {
          const dadosSuino = {
            brinco: brinco.value,
            brincoPai: brincoPai.value,
            brincoMae: brincoMae.value,
            dataNascimento: dataNascimento.value,
            dataSaida: dataSaida.value,
            status: status.value,
            sexo: sexo.value,
          };
          // Chama o método addSuino() para enviar os dados do usuário
          this.dataBaseService.addSuino(dadosSuino);
          this.snackBar.open('Suíno cadastrado com sucesso!', 'Fechar', { duration: 2000 });
          this.redirecionaPrincipal();
          console.log('Dados do suíno:', dadosSuino);
        } else {
          console.log('Formulário inválido, verifique os campos.');
          this.snackBar.open('Erro ao cadastrar o suíno.', 'Fechar', { duration: 2000 });
        }
      }
    }
    );
  }



  redirecionaPrincipal(){
    setTimeout(() => {
      this.router.navigate(['home']);
    }, 2000);
  }

}
  