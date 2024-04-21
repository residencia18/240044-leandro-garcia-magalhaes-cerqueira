import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import { BancoService } from '../../../core/banco.service';
import { DatePipe } from '@angular/common';
import { PesoSuino } from '../../peso/pesoSuino';
import { Suino } from '../suino';

@Component({
  selector: 'app-grafico-peso',
  standalone: true,
  imports: [],
  templateUrl: './grafico-peso.component.html',
  styleUrl: './grafico-peso.component.css'
})
export class GraficoPesoComponent implements OnInit, OnDestroy {

  suinos!: Suino[];
  @Input() brincoSelecionado!: string;
  pesos: PesoSuino[] = [];
  chart!: Chart; // Referência para o gráfico

  constructor(private servico: BancoService, private datePipe: DatePipe){}


  ngOnInit(): void {
    this.carregarSuinos();
    this.renderizarGrafico();
  }

  ngOnDestroy(): void {
    // Certifique-se de destruir o gráfico ao sair do componente
    if (this.chart) {
      this.chart.destroy();
    }
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
