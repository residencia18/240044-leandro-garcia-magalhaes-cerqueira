import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BancoService } from '../../../core/banco.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';


import { MaterialModule } from '../../../shared/material/material.module';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ReactiveFormsModule } from '@angular/forms';
import { onlyNumbersValidator } from '../onlyNumbersValidator';
import { dateFormatValidator } from '../dateFormatValidator';

@Component({
  selector: 'app-editar',
  standalone: true,
  imports: [MaterialModule, ReactiveFormsModule, CommonModule,HttpClientModule],
  templateUrl: './editar.component.html',
  styleUrl: './editar.component.css'
})
export class EditarComponent {

  suinoForm!: FormGroup;
  id:string = '';
  editadoSucesso:boolean = false;

  constructor(
    private fb: FormBuilder,
    private bancoService: BancoService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar : MatSnackBar) {}

      //Se o app não funcionar, implementar o OnInit na classe....
    ngOnInit(){
    this.suinoForm = this.fb.group({
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
    this.id = this.route.snapshot.paramMap.get('id')!;
    this.getSuino(this.id);
    }

    getSuino(id: any){
      console.log("id-->"    + id);
      this.bancoService.getSuino(id).subscribe(responseData => {
        console.log(responseData);
        this.suinoForm.setValue(responseData);
      })
    }

    salvarSuino(){
      const brinco = this.suinoForm.get('brinco')?.value;

      console.log("Salvar suíno: " + this.suinoForm.value);
      this.bancoService.editarSuino(this.id, this.suinoForm.value).subscribe(responseData => {
        if(responseData.status == 200){
          this.editadoSucesso = true;
          this.snackBar.open('Cadastro editado com sucesso!', 'Fechar', { duration: 6000 });
          this.redirecionaPrincipal();
        } else {
          console.log('Formulário inválido, verifique os campos.');
        }
      });        
    }
  
  redirecionaPrincipal(){
    setTimeout(() => {
      this.router.navigate(['listarSuinos']);
    }, 2000);
  }
 

}
