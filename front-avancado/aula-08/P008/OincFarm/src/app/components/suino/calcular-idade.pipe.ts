import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'calcularIdade',
  standalone: true
})
export class CalcularIdadePipe implements PipeTransform {

  transform(value: string | null): string {
    if (value) {
      const partes = value.split('/'); // Dividindo a string da data em partes usando o caractere "/"
      if (partes.length === 3) {
        const ano = Number(partes[2]); // Convertendo o ano para número
        const mes = Number(partes[1]) - 1; // Convertendo o mês para número e subtraindo 1 (JavaScript considera janeiro como mês 0)
        const dia = Number(partes[0]); // Convertendo o dia para número
        const dataNascimento = new Date(ano, mes, dia); // Criando uma nova data usando as partes (ano, mês, dia)
        const hoje = new Date();
        const diferencaMeses = (hoje.getFullYear() - dataNascimento.getFullYear()) * 12 + (hoje.getMonth() - dataNascimento.getMonth());
        return `${diferencaMeses} meses`;
      } else {
        return ''; // Retornando uma string vazia se a data estiver em um formato inválido
      }
    } else {
      return ''; // Retornando uma string vazia se o valor da data for nulo
    }
  }

}
