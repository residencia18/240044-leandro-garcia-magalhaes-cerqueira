import { Component, OnInit } from '@angular/core';

import { MaterialModule } from '../../../shared/material/material.module';
import {FormsModule} from '@angular/forms';
import { BancoService } from '../../../core/banco.service';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { Sessao } from '../sessao';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';


@Component({
  selector: 'app-sessao',
  standalone: true,
  imports : [MatTableModule, MaterialModule, CommonModule, FormsModule, RouterModule],
  templateUrl: 'sessao.component.html',
  styleUrl: './sessao.component.css'
})
export class SessaoComponent implements OnInit {

  displayedColumns: string[] = ['dataDescricao', 'brinco', 'atividades'];

  sessao: Sessao | undefined; // Alterado para apenas um objeto de Sessao

  id?: any;
  brincos: string[] = [];
  atividades: string[] = [];
  relacionamentoBrincoAtividade: any[] = [];

  constructor(private servico: BancoService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.carregarSessao(); // Mudança para carregar uma única sessão
  }

  carregarSessao() {
    this.servico.getSessao(this.id).subscribe(responseData => {
      console.log(responseData);
      this.sessao = responseData; // Atribuição para o objeto sessao

      // Limpar os arrays antes de preenchê-los
      this.brincos = [];
      this.atividades = [];
      this.relacionamentoBrincoAtividade = [];

      // Extrair brincos e atividades da sessao
      if (this.sessao) { // Verifica se a sessao é válida
        this.sessao.brincos.forEach(brinco => {
          if (!this.brincos.includes(brinco)) {
            this.brincos.push(brinco);
          }
        });

        this.sessao.atividadesPlanejadas.forEach(atividade => {
          if (!this.atividades.includes(atividade)) {
            this.atividades.push(atividade);
          }
        });
      }

      // Inicializar os relacionamentos com status "Não aplicada"
      this.brincos.forEach(brinco => {
        this.atividades.forEach(atividade => {
          this.relacionamentoBrincoAtividade.push({ brinco, atividade, status: 'Não aplicada' });
        });
      });
    });
  }

  buscarAtividade(brinco: string, atividade: string): string {
    const relacionamento = this.relacionamentoBrincoAtividade.find(item => item.brinco === brinco && item.atividade === atividade);
    return relacionamento ? relacionamento.status : 'Não encontrado';
  }

  getIndex(brinco: string, atividade: string): number {
    return this.relacionamentoBrincoAtividade.findIndex(item => item.brinco === brinco && item.atividade === atividade);
  }

  // Adicione o método `atualizarStatusAtividade` à classe `SessaoComponent`
atualizarStatusAtividade(brinco: string, atividade: string, status: string) {
  const index = this.getIndex(brinco, atividade);
  if (index !== -1) {
    this.relacionamentoBrincoAtividade[index].status = status;
  }
}

// Método para imprimir a tela
imprimir() {
  window.print();
}


}