import { Component } from '@angular/core';

//@angular/material
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatButton } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Suino } from '../../models/suino.model';
import { BancoService } from '../../utils/banco.service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SessaoService } from '../../utils/sessao.service';
import { Sessao } from '../../models/sessao';

@Component({
  selector: 'app-cadastro-sessao',
  standalone: true,
  imports: [MatFormFieldModule,MatInputModule,MatDatepickerModule,MatSelectModule, ReactiveFormsModule, CommonModule, MatNativeDateModule, MatButton, MatDialogModule],
  templateUrl: './cadastro-sessao.component.html',
  styleUrl: './cadastro-sessao.component.css'
})
export class CadastroSessaoComponent {

  sessaoForm!: FormGroup;
  suinos!: Suino[];
  atividadesPlanejadas: string[] = [];
  brincos: string[] = [];
  sessao!: Sessao;
  dialogoBrinco: boolean = false;
  dialogoAtividade: boolean = false;

  

  
 constructor(private servico: BancoService,private formBuilder: FormBuilder){}


 ngOnInit(): void {
  this.carregarSuinos();
  this.sessaoForm = this.formBuilder.group({
    data: ['', Validators.required],
    descricao: ['', Validators.required],
    brincos: ['', Validators.required],
    atividadesPlanejadas:['', Validators.required]
   
  });
}

  carregarSuinos() {
    this.servico.getSuinos().subscribe(suinos => {
      this.suinos = suinos;
    });
  }


  onSubmit() {
    if (this.sessaoForm.valid) {
      // Criando objeto Sessao
      const sessao = new Sessao(
        this.sessaoForm.get('data')?.value,
        this.sessaoForm.get('descricao')?.value,
        this.brincos,
        this.atividadesPlanejadas
      );

      console.log('Nova Sessão:', sessao);

      // Adicionando a sessão ao serviço
      this.servico.adicionarSessao(sessao);

      // Reseta o formulário
      this.sessaoForm.reset();
      this.atividadesPlanejadas = []; // Limpa o array de atividades
    }
  }


  addAtividade() {
      this.atividadesPlanejadas.push(this.sessaoForm.get('atividadesPlanejadas')?.value);
      this.dialogoAtividade = true;

      setTimeout(() => {
        this.dialogoAtividade = false;
      }, 2000); // Exibe a mensagem por 2 segundos

    }
  
  addSuino() {
    this.brincos.push(this.sessaoForm.get('brincos')?.value);
    this.dialogoBrinco = true;

    setTimeout(() => {
      this.dialogoBrinco = false;
    }, 2000); // Exibe a mensagem por 2 segundos
  }


}
