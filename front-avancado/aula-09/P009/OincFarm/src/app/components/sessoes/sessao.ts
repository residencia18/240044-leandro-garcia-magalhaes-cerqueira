// sessao.model.ts
export class Sessao {
    constructor(
      public data: Date,
      public descricao: string,
      public brincos: string[],
      public atividadesPlanejadas: string[]
    ) {}
  }