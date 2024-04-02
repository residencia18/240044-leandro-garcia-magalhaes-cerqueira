import { Component, OnInit } from '@angular/core';

//@angular/material
import { MatFormField} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatOption } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import {FormsModule} from '@angular/forms';
import { BancoService } from '../../utils/banco.service';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { Sessao } from '../../models/sessao';
import { RouterModule } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { MatButton } from '@angular/material/button';

// Interface para definir a estrutura dos dados da tabela
interface DadosDaTabela {
  [key: string]: string;
}

@Component({
  selector: 'app-listar-sessoes',
  standalone: true,
  imports : [MatTableModule, MatFormField, MatInputModule, MatSelectModule, MatOption, CommonModule, FormsModule, RouterModule, MatIcon, MatButton],
  templateUrl: './lista-sessoes.component.html',
  styleUrl: './lista-sessoes.component.css'
})
export class ListaSessoesComponent implements OnInit {

  sessoes: Sessao[] = [];
  displayedColumns: string[] = ['dataDescricao', 'brinco', 'atividades', 'acao'];

  constructor(
     private servico: BancoService,
     ) { }

  ngOnInit(): void {
    this.carregarSessoes();
  }

  carregarSessoes(){
    this.servico.getSessoes().subscribe((responseData : Sessao[]) => {
      console.log(responseData);
      this.sessoes = responseData;  
    });
  }

  titulosDasColunas: string[] = ['Coluna 1', 'Coluna 2', 'Coluna 3'];

  // Usando a interface para definir o tipo dos dados da tabela
  dadosDaTabela: DadosDaTabela[] = [
    { coluna1: 'Valor 1', coluna2: 'Valor 2', coluna3: 'Valor 3' },
    { coluna1: 'Outro Valor 1', coluna2: 'Outro Valor 2', coluna3: 'Outro Valor 3' }
    // Adicione mais objetos conforme necessário
  ];

}