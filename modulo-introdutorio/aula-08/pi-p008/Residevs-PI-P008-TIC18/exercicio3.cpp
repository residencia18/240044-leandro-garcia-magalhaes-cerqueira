#include <stdio.h>
#include <stdbool.h>

int main() {
    int dia, mes, ano;

    printf("Digite uma data no formato dd/mm/aaaa: ");
    scanf("%d/%d/%d", &dia, &mes, &ano);

    bool bissexto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    bool dataValida = true;
    int maxDia;

    if (mes < 1 || mes > 12) {
        dataValida = false;
    } else {
        switch (mes) {
            case 2:
                maxDia = bissexto ? 29 : 28;
                break;
            default:
                maxDia = 31;
                break;
        }

        if (dia < 1 || dia > maxDia) {
            dataValida = false;
        }
    }

    if (dataValida) {
        printf("Dia: %d\n", dia);
        printf("Mês: %d\n", mes);
        printf("Ano: %d\n", ano);

        const char *nomesMeses[] = {"janeiro", "fevereiro", "março", "abril", "maio", "junho",
                                    "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};

        printf("%d de %s de %d\n", dia, nomesMeses[mes - 1], ano);
    } else {
        printf("Data inválida.\n");
    }

    return 0;
}
