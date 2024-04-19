import { Component } from '@angular/core';
import { Suino } from '../suino';
import { PesoSuino } from '../../peso/pesoSuino';
import { Sessao } from '../../sessoes/sessao';
import { BancoService } from '../../../core/banco.service';
import { DatePipe } from '@angular/common';
import { MaterialModule } from '../../../shared/material/material.module';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HistoricoSuino } from '../historico-suino';
import { HistoricoSuinoListDTO } from './historico-suino-list-dto';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-historico-animal',
  standalone: true,
  imports: [CommonModule, MaterialModule, FormsModule],
  templateUrl: './historico-animal.component.html',
  styleUrl: './historico-animal.component.css'
})
export class HistoricoAnimalComponent {
pesoHistorico() {
throw new Error('Method not implemented.');
}

  historicoSuinoList: HistoricoSuinoListDTO[] = [];
  suinos!: Suino[];
  suino: Suino | undefined;
  pesos: PesoSuino[] = [];
  sessoes: Sessao[] = [];
  brincoSelecionado!: string;
  historicoCarregado: boolean = false;

  dataSource = new MatTableDataSource<HistoricoSuinoListDTO>();


  constructor(private bancoService: BancoService, private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.carregarSuinos();
  }

  carregarSuinos() {
    this.bancoService.getSuinos().subscribe(suinos => {
      this.suinos = suinos;
    });
  }

  geraHistorico(){

    this.bancoService.getSuino(this.brincoSelecionado).subscribe(suino => {
      console.log("Suino selecionado: ", suino);
      this.suino = suino;
    })

    this.bancoService.getPesosSuino(this.brincoSelecionado).subscribe(pesos => {
      console.log("Pesos: ", pesos);
      this.pesos = pesos;
    })

    this.bancoService.getSessoesByBrinco(this.brincoSelecionado).subscribe((sessoes: Sessao[]) => {
    // Aqui você tem acesso aos dados das sessões filtradas
    this.sessoes = sessoes;
    console.log("Sessões filtradas: ", this.sessoes);
  });

  let historicoSuino : HistoricoSuino = {
    pesos: this.pesos,
    sessoes: this.sessoes
  };

  this.getHistorico(historicoSuino);

  this.historicoCarregado = true;
    
  }

getHistorico(historicoSuino : HistoricoSuino): void {
  
  this.historicoSuinoList = [];

  //Peso -------------

  historicoSuino.pesos.forEach(peso => {

    let dataFormatada : string  = this.datePipe.transform(peso.dataPesagem, 'dd-MM-yyyy') ?? "";

    let historico: HistoricoSuinoListDTO = {
      data: dataFormatada,
      atividade: 'Pesagem',
      resultado: peso.pesoKg.toString() + " Kg"
    }

    this.historicoSuinoList.push(historico)}
  )
  
  //Sessao ------------
  
  historicoSuino.sessoes.forEach(sessao => {

  let dataFormatada : string  = this.datePipe.transform(sessao.data, 'dd-MM-yyyy') ?? "";

    sessao.atividadesPlanejadas.forEach(atividade => {
      let historico: HistoricoSuinoListDTO = {
        data: dataFormatada,
        atividade: atividade,
        resultado: 'Concluída'
      }

      this.historicoSuinoList.push(historico);
    });

  });

  // Ordena historicoSuinoList pela data
  this.historicoSuinoList.sort((a, b) => {
    // Converte as datas para o formato adequado e depois compara
    const dataA = new Date(a.data.replace(/(\d{2})-(\d{2})-(\d{4})/, '$2/$1/$3'));
    const dataB = new Date(b.data.replace(/(\d{2})-(\d{2})-(\d{4})/, '$2/$1/$3'));
    return dataA.getTime() - dataB.getTime(); // Ordena em ordem crescente; para ordem decrescente, inverta a ordem
  });

  this.dataSource = new MatTableDataSource<HistoricoSuinoListDTO>(this.historicoSuinoList)
}

// LISTAGEM
displayedColumns: string[] = ['data', 'atividade', 'resultado'];








//GRAFICO HERE



}
