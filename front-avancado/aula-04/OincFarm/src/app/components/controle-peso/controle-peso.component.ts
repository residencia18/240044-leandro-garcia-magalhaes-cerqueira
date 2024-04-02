import { Component, Input, OnInit } from '@angular/core';
import { PesoSuino } from '../../models/pesoSuino';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButton } from '@angular/material/button';
import { MatFormField } from '@angular/material/form-field';
import { Suino } from '../../models/suino.model';
import { BancoService } from '../../utils/banco.service';
import { MatOption } from '@angular/material/core';
import { AngularFireDatabase} from '@angular/fire/compat/database';
import { Observable, catchError, map } from 'rxjs';
import Chart from 'chart.js/auto';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-controle-peso',
  standalone : true,
  imports : [MatFormField, MatInputModule, MatSelectModule, MatButton, MatOption, CommonModule,FormsModule],
  templateUrl: './controle-peso.component.html',
  styleUrls: ['./controle-peso.component.css']
})
export class ControlePesoComponent implements OnInit {

  suinos! : Suino[];
  brincoSelecionado!: string;
  pesos: PesoSuino[] = [];

  objetosComID$!: Observable<any[]>;
  
  constructor(private servico : BancoService, private db: AngularFireDatabase, private http: HttpClient) { }


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
