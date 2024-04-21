import { Component, Input } from '@angular/core';
import { Suino } from '../suino';
import { PesoSuino } from '../../peso/pesoSuino';
import Chart from 'chart.js/auto';
import { BancoService } from '../../../core/banco.service';
import { DatePipe } from '@angular/common';
import { HistoricoSuinoListDTO } from '../historico-animal/historico-suino-list-dto';

@Component({
  selector: 'app-grafico-atividade',
  standalone: true,
  imports: [],
  templateUrl: './grafico-atividade.component.html',
  styleUrl: './grafico-atividade.component.css'
})
export class GraficoAtividadeComponent {

 
  suinos!: Suino[];
  @Input() brincoSelecionado!: string;
  pesos: PesoSuino[] = [];
  chart!: Chart;
  @Input() historicoSuinoList: HistoricoSuinoListDTO[] = [];

  // Inicialize um objeto para armazenar as contagens das atividades
   contagemAtividades: { [atividade: string]: number } = {};

  constructor(){}

  ngOnInit(): void {
    this.contarAtividades();
    this.renderizarGrafico();
  }

  ngOnDestroy(): void {
    // Certifique-se de destruir o gráfico ao sair do componente
    if (this.chart) {
      this.chart.destroy();
    }
  }

  //SESSÃO ATIVIDADES

// Método para contar a quantidade de vezes que cada atividade se repete
contarAtividades(): void {
  // Iterar sobre cada item da lista
  this.historicoSuinoList.forEach(item => {
      // Verificar se a atividade já existe no objeto de contagem
      if (this.contagemAtividades[item.atividade]) {
          // Se sim, incrementar o contador para essa atividade
          this.contagemAtividades[item.atividade]++;
      } else {
          // Se não, inicializar o contador para essa atividade com 1
          this.contagemAtividades[item.atividade] = 1;
      }
  });
  // Agora contagemAtividades conterá as contagens de cada atividade
  console.log('Contagem de atividades:', this.contagemAtividades);

}


  // Método para renderizar o gráfico de atividades
  renderizarGrafico(): void {
    // Extrair os rótulos (atividades) e os dados (quantidades)
    const labels = Object.keys(this.contagemAtividades);
    const dados = Object.values(this.contagemAtividades);

    // Configurar o contexto do gráfico
    const ctx = document.getElementById('grafico-atividade') as HTMLCanvasElement;

    // Destruir o gráfico existente antes de criar um novo
    if (this.chart) {
        this.chart.destroy();
    }

    // Criar o novo gráfico de barras
    this.chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Quantidade',
                data: dados,
                backgroundColor: 'rgb(75, 192, 192)',
                borderColor: 'rgb(75, 192, 192)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}
}