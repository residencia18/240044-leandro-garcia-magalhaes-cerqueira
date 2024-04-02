import { Component, OnInit } from '@angular/core';
import { Suino } from '../../models/suino.model';
import { Location } from '@angular/common';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CalcularIdadePipe } from "../../utils/calcular-idade.pipe";
import { DatePipe } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatIcon } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BancoService } from '../../utils/banco.service';
import { RouterModule } from '@angular/router';
import { MatFormField } from '@angular/material/form-field';
import { MatLabel } from '@angular/material/form-field';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatButton } from '@angular/material/button';

@Component({
    selector: 'app-listar-suinos',
    standalone: true,
    templateUrl: './listar-suinos.component.html',
    styleUrl: './listar-suinos.component.css',
    imports: [CommonModule, FormsModule, CalcularIdadePipe, MatTableModule, MatIcon, RouterModule, MatFormField, MatLabel, MatFormFieldModule, MatButton]
})
export class ListarSuinosComponent implements OnInit {



  // Lista dos suínos
  loadedSuinos: Suino[] = [];
  filteredSuinos: Suino[] = []; // Array de suínos filtrados
  filtroBrinco: number | null = null; // Filtro para o atributo 'brinco'
  filtroBrincoPai: number | null = null; // Filtro para o atributo 'brincoPai'
  filtroBrincoMae: number | null = null; // Filtro para o atributo 'brincoMae'
  filtroDataNascimento: string = ''; // Filtro para o atributo 'dataNascimento'
  filtroDataSaida: string = ''; // Filtro para o atributo 'dataSaida'
  filtroStatus: 'Ativo' | 'Vendido' | 'Morto' | null = null; // Filtro para o atributo 'status'
  filtroSexo: 'M' | 'F' | null = null; // Filtro para o atributo 'sexo'

  // Método para filtrar os suínos com base nos filtros definidos
  filtrarSuinos(): void {
    console.log("Método chamado.");

    // Verificar se algum filtro está sendo aplicado
    const isFilterApplied =
      this.filtroBrinco !== null ||
      this.filtroBrincoPai !== null ||
      this.filtroBrincoMae !== null ||
      this.filtroDataNascimento !== '' ||
      this.filtroDataSaida !== '' ||
      this.filtroStatus !== null ||
      this.filtroSexo !== null;

    // Se nenhum filtro estiver sendo aplicado, exibir todos os suínos
    if (!isFilterApplied) {
      this.filteredSuinos = [...this.loadedSuinos]; // Cópia dos suínos carregados
      return;
    }

    // Se houver filtro aplicado, filtrar os suínos de acordo com os filtros definidos
    this.filteredSuinos = this.loadedSuinos.filter((suino) => {
      return (
        (!this.filtroBrinco || suino.brinco === this.filtroBrinco) &&
        (!this.filtroBrincoPai || suino.brincoPai === this.filtroBrincoPai) &&
        (!this.filtroBrincoMae || suino.brincoMae === this.filtroBrincoMae) &&
        (!this.filtroDataNascimento || suino.dataNascimento.includes(this.filtroDataNascimento)) &&
        (!this.filtroDataSaida || suino.dataSaida.includes(this.filtroDataSaida)) &&
        (!this.filtroStatus || suino.status === this.filtroStatus) &&
        (!this.filtroSexo || suino.sexo === this.filtroSexo)
      );
    });

    console.log(this.filteredSuinos);
}


  limparFiltros(): void {
    this.filtroBrinco = null;
    this.filtroBrincoPai = null;
    this.filtroBrincoMae = null;
    this.filtroDataNascimento = '';
    this.filtroDataSaida = '';
    this.filtroStatus = null;
    this.filtroSexo = null;
    this.filtrarSuinos();
  }



  displayedColumns: string[] = ['brinco', 'brincoPai', 'brincoMae', 'dataNascimento', 'dataSaida', 'sexo', 'status', 'idade', 'actions'];

  apagarSuino(id: string) {
    this.bancoService.apagarSuino(id).subscribe({
      next: () => {
        this.snackBar.open('Suíno apagado com sucesso', 'Fechar', { duration: 3000 });
        // Recarregar a página após 1 segundo 
        setTimeout(() => {
          window.location.reload();
        }, 1000);
      },
      error: (error: any) => {
        console.error('Erro ao apagar suíno:', error);
        this.snackBar.open('Erro ao apagar suíno', 'Fechar', { duration: 3000 });
      }
    });
  }
  
 

  
  constructor(private datePipe: DatePipe, private bancoService: BancoService, private snackBar: MatSnackBar, private location : Location) { }

  ngOnInit():void{
    this.getSuinos();
  }

  getSuinos(){
    this.bancoService.getSuinos().subscribe((responseData : Suino[]) => {
      console.log(responseData);
      this.loadedSuinos = responseData;
      this.filteredSuinos = [...this.loadedSuinos]; // Atualizar filteredSuinos com os suínos carregados
      console.log(this.loadedSuinos);     
    });
  }

   // Método para formatar a data antes de passá-la para o pipe personalizado
   formatarData(data: string | null): string {
    if (data) {
      const parts = data.split('/'); // Divida a string de data em partes (dia, mês, ano)
      const dateObj = new Date(Number(parts[2]), Number(parts[1]) - 1, Number(parts[0])); // Crie um objeto Date com as partes da data
      return this.datePipe.transform(dateObj, 'dd/MM/yyyy') || ''; // Formata a data para o formato "dd/MM/yyyy"
    } else {
      return '';
    }
  }

}
