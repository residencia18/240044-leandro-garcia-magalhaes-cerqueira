import { Component, OnInit } from '@angular/core';

import { MaterialModule } from '../../../shared/material/material.module';
import {FormsModule} from '@angular/forms';
import { BancoService } from '../../../core/banco.service';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { Sessao } from '../sessao';
import { RouterModule } from '@angular/router';


// Interface para definir a estrutura dos dados da tabela
interface DadosDaTabela {
  [key: string]: string;
}

@Component({
  selector: 'app-listar-sessoes',
  standalone: true,
  imports : [MatTableModule,CommonModule, FormsModule, RouterModule, MaterialModule],
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