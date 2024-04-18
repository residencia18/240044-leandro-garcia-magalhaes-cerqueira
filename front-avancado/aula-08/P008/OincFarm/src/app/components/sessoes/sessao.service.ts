// sessao.service.ts
import { Injectable } from '@angular/core';
import { Sessao } from './sessao';

@Injectable({
  providedIn: 'root'
})
export class SessaoService {
  private sessoes: Sessao[] = [];

  constructor() { }

  adicionarSessao(sessao: Sessao) {
    this.sessoes.push(sessao);
  }

  getSessoes() {
    return this.sessoes.slice();
  }
}
