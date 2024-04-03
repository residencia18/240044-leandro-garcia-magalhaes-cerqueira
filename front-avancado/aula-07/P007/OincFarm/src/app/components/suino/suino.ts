export interface Suino {
    brinco: number;
    brincoPai: number;
    brincoMae: number;
    dataNascimento: string; 
    dataSaida: string; 
    status: 'Ativo' | 'Vendido' | 'Morto';
    sexo: 'M' | 'F';
    id?: string;
}
