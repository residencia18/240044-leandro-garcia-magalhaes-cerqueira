import { Component,OnInit } from '@angular/core';
import { PesoSuino } from '../pesoSuino';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Suino } from '../../suino/suino';
import { BancoService } from '../../../core/banco.service';
import { MaterialModule } from '../../../shared/material/material.module';
import { Observable} from 'rxjs';
import Chart from 'chart.js/auto';

@Component({
  selector: 'app-controle-peso',
  standalone : true,
  imports : [MaterialModule, CommonModule,FormsModule],
  templateUrl: './controle-peso.component.html',
  styleUrls: ['./controle-peso.component.css']
})
export class ControlePesoComponent implements OnInit {

  suinos! : Suino[];
  brincoSelecionado!: string;
  pesos: PesoSuino[] = [];

  objetosComID$!: Observable<any[]>;
  
  constructor(private servico : BancoService) { }


  ngOnInit(): void {
    this.carregarSuinos();
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
        const labels = this.pesos.map(peso => peso.dataPesagem);
        const dados = this.pesos.map(peso => peso.pesoKg);

        console.log(labels);
        console.log("Pesos: ", data);

        new Chart(ctx, {
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
                            text: 'Data da Pesagem',
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
