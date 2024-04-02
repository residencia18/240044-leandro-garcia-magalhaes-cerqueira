import { Component, OnInit } from '@angular/core';

//@angular/material
import { MatFormField} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatOption } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatButton } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import {FormsModule} from '@angular/forms';
import { BancoService } from '../../utils/banco.service';
import { PesoSuino } from '../../models/pesoSuino';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { CadastroPesoComponent } from '../cadastro-peso/cadastro-peso.component';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
@Component({
  selector: 'app-listar-pesos',
  standalone: true,
  imports : [MatTableModule, MatIconModule, MatFormField, MatInputModule, MatSelectModule, MatButton, MatOption, CommonModule, FormsModule],
  templateUrl: './listar-pesos.component.html',
  styleUrl: './listar-pesos.component.css'
})
export class ListarPesosComponent implements OnInit {
  pesos: PesoSuino[] = [];
  displayedColumns: string[] = ['brincoSuino', 'dataPesagem', 'pesoKg', 'actions'];

  constructor(
     private servico: BancoService,
     private dialog: MatDialog, 
     private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.carregarPesos();
    this.servico.atualizarListaPesos().subscribe(() => {
      this.carregarPesos();
    });
  }

  carregarPesos(): void {
    this.servico.obterTodosPesos().subscribe(pesos => {
      if (pesos) {
        this.pesos = pesos;
      } else {
        console.log('Nenhum peso encontrado.');
      }
    });
  }

  abrirDialog(): void {
    const dialogRef = this.dialog.open(CadastroPesoComponent, {
      width: '800px',
      data: { opcao: 'adicionar', peso: {}, indice: 0 }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.carregarPesos();
    });
  }

  abrirDialogEditar(id: string): void {
    const peso = this.pesos.find(p => p.id === id);
    if (peso) {
      const dialogRef = this.dialog.open(CadastroPesoComponent, {
        width: '800px',
        data: { opcao: 'editar', peso: peso, indice: this.pesos.indexOf(peso) }
      });

      dialogRef.afterClosed().subscribe(result => {
        this.carregarPesos();
      });
    }
  }

  mostrarNotificacao(mensagem: string): void {
    this.snackBar.open(mensagem, 'Fechar', {
      duration: 3000, // 3 segundos
    });
  }
}
