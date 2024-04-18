import { Component, OnInit, OnDestroy } from '@angular/core';
import { PesoSuino } from '../pesoSuino';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Suino } from '../../suino/suino';
import { BancoService } from '../../../core/banco.service';
import { MaterialModule } from '../../../shared/material/material.module';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';

import Chart from 'chart.js/auto';

@Component({
  selector: 'app-controle-peso',
  standalone: true,
  imports: [MaterialModule, CommonModule, FormsModule],
  templateUrl: './controle-peso.component.html',
  styleUrls: ['./controle-peso.component.css']
})
export class ControlePesoComponent implements OnInit, OnDestroy {

  suinos!: Suino[];
  brincoSelecionado!: string;
  pesos: PesoSuino[] = [];
  monitorou = false;

  objetosComID$!: Observable<any[]>;
  chart!: Chart; // Referência para o gráfico

  constructor(private servico: BancoService, private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.carregarSuinos();
  }

  ngOnDestroy(): void {
    // Certifique-se de destruir o gráfico ao sair do componente
    if (this.chart) {
      this.chart.destroy();
    }
  }

  monitorar() {
    this.monitorou = !this.monitorou;
  }

  carregarSuinos() {
    this.servico.getSuinos().subscribe(suinos => {
      this.suinos = suinos;
    });
  }

  renderizarGrafico(): void {
    // Aqui você pode usar this.suinoSelecionado para acessar o valor selecionado
    console.log('Suíno selecionado:', this.brincoSelecionado);

    this.servico.getPesosSuino(this.brincoSelecionado).subscribe(data => {
      console.log('Lista de objetos pesoSuino:', data);
      this.pesos = data;

      const ctx = document.getElementById('grafico-peso') as HTMLCanvasElement;
      const labels = this.pesos.map(peso => this.datePipe.transform(peso.dataPesagem, 'dd-MM-yyyy'));
      const dados = this.pesos.map(peso => peso.pesoKg);

      console.log(labels);
      console.log("Pesos: ", data);

      // Destrua o gráfico existente antes de criar um novo
      if (this.chart) {
        this.chart.destroy();
      }

      this.chart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: labels,
          datasets: [{
            label: 'Peso do Suíno',
            data: dados,
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            backgroundColor: 'rgba(255, 255, 255, 1.0)', // Cor de preenchimento dos dados
            tension: 0.1
          }]
        },
        options: {
          scales: {
            x: {
              title: {
                display: true,
                text: 'Data da Pesagem (dia-mes-ano)',
                color: 'white' // Cor do texto do eixo X
              }
            },
            y: {
              title: {
                display: true,
                text: 'Peso (kg)',
                color: 'white' // Cor do texto do eixo Y
              }
            }
          }
        }
      });
    });
  }
}
