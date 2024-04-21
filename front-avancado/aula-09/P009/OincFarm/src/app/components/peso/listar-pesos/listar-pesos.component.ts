import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../../shared/material/material.module';
import {FormsModule} from '@angular/forms';
import { BancoService } from '../../../core/banco.service';
import { PesoSuino } from '../pesoSuino';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { CadastroPesoComponent } from '../cadastro-peso/cadastro-peso.component';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-listar-pesos',
  standalone: true,
  imports : [MatTableModule, CommonModule, FormsModule, MaterialModule],
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

  apagarPeso(id: string) {
    
        this.snackBar.open('Suíno apagado com sucesso', 'Fechar', { duration: 3000 });
        // Recarregar a página após 1 segundo 
        setTimeout(() => {
          window.location.reload();
        }, 1000);
      }
     
}



