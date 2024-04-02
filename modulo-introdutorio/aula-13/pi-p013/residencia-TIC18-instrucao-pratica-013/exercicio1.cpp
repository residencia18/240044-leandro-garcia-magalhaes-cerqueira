#include <iostream>
#include <vector>
#include <string>

using namespace std;

struct Passagem
{
    std::string nome;
    int idade;
    string cpf;
    int dia;
    int mes;
    int ano;
    int verifica;
};

// Inicialmente, cria uma matriz (vetor de vetores) do tipo inteiro
std::vector<std::vector<Passagem>> onibus_ida(5, std::vector<Passagem>(40));   //  Uma matriz 5x40
std::vector<std::vector<Passagem>> onibus_volta(5, std::vector<Passagem>(40)); // Uma matriz 5x40

// FUNÇÕES -------------------------------------------------------------:

void adiciona_zero()
{ // Função que preenche as posições da matriz com 0.

    /**Laço que preenche as posições da matriz "onibus_ida" com "0"**/
    for (int linha = 0; linha < onibus_ida.size(); linha++)
    {
        for (int coluna = 0; coluna < onibus_ida[linha].size(); coluna++)
        {
            onibus_ida[linha][coluna].verifica = 0;
        }
    }

    // Laço que preenche as posições da matriz "onibus_volta" com "0"
    for (int linha = 0; linha < onibus_volta.size(); linha++)
    {
        for (int coluna = 0; coluna < onibus_volta[linha].size(); coluna++)
        {
            onibus_volta[linha][coluna].verifica = 0;
        }
    }
}

void total_arrecadado_viagem()
{ // Função 1.
    int opcao, horario, total_viagem = 0;
    std::cout << "Digite [1] para verificar o total de uma passagem de ida ou [2] para o de uma passagem de volta:";
    std::cin >> opcao;

    if (opcao == 1)
    {
        cout << "De qual horário você deseja saber o total arrecadado? escolha de 0 à 4: ";
        cin >> horario;

        for (int coluna = 0; coluna < onibus_ida[horario].size(); coluna++)
        {
            if (onibus_ida[horario][coluna].verifica != 0)
            {
                total_viagem += 80;
            }
        }
        std::cout << "Total arrecadado: " << total_viagem << std::endl;
    }
    else if (opcao == 2)
    {
        cout << "De qual horário você deseja saber o total arrecadado? escolha de 0 à 4: ";
        cin >> horario;

        for (int coluna = 0; coluna < onibus_volta[horario].size(); coluna++)
        {
            if (onibus_volta[horario][coluna].verifica != 0)
            {
                total_viagem += 80;
            } 
        }
        std::cout << "Total arrecadado: " << total_viagem << std::endl;
    }
    else
    {
        std::cout << "Opção inválida.";
    }
}

void total_arrecadado_mes()
{ // Função 2.
    int mes, total_mes = 0;
    std::cout << "Insira o mês que deseja verificar: ";
    std::cin >> mes;

    /**Laço que verifica se o mês contido em determinada posição condiz com o digitado pelo usuário,
     * caso seja igual, soma o valor da passagem a uma variavel.**/
    for (int linha = 0; linha < onibus_ida.size(); linha++)
    {
        for (int coluna = 0; coluna < onibus_ida[linha].size(); coluna++)
        {
            if (onibus_ida[linha][coluna].mes == mes)
            {
                total_mes += 80;
            }
        }
    }

    /**Laço que verifica se o mês contido em determinada posição condiz com o digitado pelo usuário,
     * caso seja igual, soma o valor da passagem a uma variavel.**/
    for (int linha = 0; linha < onibus_volta.size(); linha++)
    {
        for (int coluna = 0; coluna < onibus_volta[linha].size(); coluna++)
        {
            if (onibus_volta[linha][coluna].mes == mes)
            {
                total_mes += 80;
            }
        }
    }

    std::cout << "O total arrecadado no mês " << mes << " É de: " << total_mes;
}

void nome_passageiro()
{ // Função 3.

    int opcao, poltrona, horario;
    cout << "Digite [1] para verificar o nome do passageiro em uma passagem de ida ou [2] para o de uma passagem de volta:";
    cin >> opcao;

    if (opcao == 1)
    {

        cout << "O passageiro comprou a passagem em qual horário (de 0 à 4)? ";
        cin >> horario;

        cout << "Qual a poltrona? de 0 até 39";
        cin >> poltrona;

        std::cout << onibus_ida[horario][poltrona].nome;
    }
    else if (opcao == 2)
    {

        std::cout << "O passageiro comprou a passagem em qual horário (de 0 à 4)? ";
        std::cin >> horario;

        std::cout << "Qual a poltrona? de 0 até 39: ";
        std::cin >> poltrona;

        std::cout << onibus_volta[horario][poltrona].nome;
    }
    else
    {
        std::cout << "Opção inválida.";
    }
}

void horario_mais_rentavel()
{ // Função 4.
    int total_viagem = 0;
    int maior_receita_ida = 0;
    int horario_rentavel_ida = -1;

    for (int linha = 0; linha < onibus_ida.size(); linha++)
    {

        total_viagem = 0;

        for (int coluna = 0; coluna < onibus_ida[linha].size(); coluna++)
        {

            if (onibus_ida[linha][coluna].verifica != 0)
            {
                total_viagem += 80;
            }
        }

        if (total_viagem > maior_receita_ida)
        {
            maior_receita_ida = total_viagem;
            horario_rentavel_ida = linha;
        }
    }

    int maior_receita_volta = 0;
    int horario_rentavel_volta = -1;

    for (int linha = 0; linha < onibus_volta.size(); linha++)
    {

        total_viagem = 0;

        for (int coluna = 0; coluna < onibus_volta[linha].size(); coluna++)
        {

            if (onibus_volta[linha][coluna].verifica != 0)
            {
                total_viagem += 80;
            }
        }

        if (total_viagem > maior_receita_volta)
        {
            maior_receita_volta = total_viagem;
            horario_rentavel_volta = linha;
        }
    }
    if (maior_receita_ida > maior_receita_volta)
    {
        std::cout << horario_rentavel_ida;
    }
    else
    {
        std::cout << horario_rentavel_volta;
    }
}

void media_idade()
{ // Função 5.
    int soma_idade = 0, contador = 0;
    float media_idade;

    for (int linha = 0; linha < onibus_ida.size(); linha++)
    {
        for (int coluna = 0; coluna < onibus_ida[linha].size(); coluna++)
        {
            if (onibus_ida[linha][coluna].verifica != 0)
            {
                soma_idade += onibus_ida[linha][coluna].idade;
                contador++;
            }
        }
    }

    for (int linha = 0; linha < onibus_volta.size(); linha++)
    {
        for (int coluna = 0; coluna < onibus_volta[linha].size(); coluna++)
        {
            if (onibus_volta[linha][coluna].verifica != 0)
            {
                soma_idade += onibus_volta[linha][coluna].idade;
                contador++;
            }
        }
    }

    media_idade = soma_idade / contador;
    std::cout << "A média de idade dos passageiros é de: " << media_idade << ".";
}

void cadastro()
{ // Função 6.
    int decisao, poltrona, horario;
    std::cout << "A passagem será de ida ou de volta? Digite [1] para ida ou [2] para volta: ";
    std::cin >> decisao;

    if (decisao == 1)
    {

        std::cout << "Insira o horário desejado (Escolha entre 0 e 4): ";
        std::cin >> horario;

        std::cout << "Insira o numero do assento: ";
        std::cin >> poltrona;

        if (onibus_ida[horario][poltrona].verifica == 0)
        {
            cout << "Insira o nome do passageiro: ";
            cin >> onibus_ida[horario][poltrona].nome;
            cout << "Insira o dia da viagem: ";
            cin >> onibus_ida[horario][poltrona].dia;
            cout << "Insira o mês da viagem: ";
            cin >> onibus_ida[horario][poltrona].mes;
            cout << "Insira o ano da viagem: ";
            cin >> onibus_ida[horario][poltrona].ano;
            cout << "Insira o CPF do comprador: ";
            cin >> onibus_ida[horario][poltrona].cpf;
            cout << "Insira a idade do comprador: ";
            cin >> onibus_ida[horario][poltrona].idade;

            onibus_ida[horario][poltrona].verifica = 1;
        }
        else
        {
            std::cout << "O assento já foi ocupado.";
            return;
        }
    }
    else if (decisao == 2)
    {

        std::cout << "Insira o horário desejado (Escolha entre 0 e 4): ";
        std::cin >> horario;

        std::cout << "Insira o numero do assento: ";
        std::cin >> poltrona;

        if (onibus_ida[horario][poltrona].verifica == 0)
        {
            cout << "Insira o nome do passageiro: ";
            cin >> onibus_volta[horario][poltrona].nome;
            cout << "Insira o dia da viagem: ";
            cin >> onibus_volta[horario][poltrona].dia;
            cout << "Insira o mês da viagem: ";
            cin >> onibus_volta[horario][poltrona].mes;
            cout << "Insira o ano da viagem: ";
            cin >> onibus_volta[horario][poltrona].ano;
            cout << "Insira o CPF do comprador: ";
            cin >> onibus_volta[horario][poltrona].cpf;
            cout << "Insira a idade do comprador: ";
            cin >> onibus_volta[horario][poltrona].idade;

            onibus_volta[horario][poltrona].verifica = 1;
        }
        else
        {
            std::cout << "O assento já foi ocupado.";
            return;
        }
    }
}

// PROGRAMA PRINCIPAL.
int main()
{

    adiciona_zero();

    int escolha;
    bool sair = false;

    while (sair == false)
    {
        std::cout << "Olá! Seja bem vindo ao sistema de passagem da BestBus!" << endl;
        std::cout << "======================================================" << endl;
        std::cout << "O que deseja fazer?";
        std::cout << endl;
        std::cout << "[1] - Solicitar o total arrecadado de determinada viagem." << endl;
        std::cout << "[2] - Solicitar o total arrecadado em determinado mês." << endl;
        std::cout << "[3] - Solicitar o nome do passageiro de determinada poltrona em uma determinada viagem." << endl;
        std::cout << "[4] - Solicitar o horário de viagem mais rentável." << endl;
        std::cout << "[5] - Solicitar a média de idade dos passageiros." << endl;
        std::cout << "[6] - Cadastrar passagem." << endl;
        std::cout << "[0] - Sair." << endl;

        std::cout << "Digite a opção desejada: ";
        std::cin >> escolha;
        switch (escolha)
        {
        case 1:
            total_arrecadado_viagem();
            break;

        case 2:
            total_arrecadado_mes();
            break;

        case 3:
            nome_passageiro();
            break;

        case 4:
            horario_mais_rentavel();
            break;

        case 5:
            media_idade();
            break;

        case 6:
            cadastro();
            break;

        case 0:
            sair = true;
            break;

        default:
            std::cout << "Opção inválida.";
            break;
        }
    }

    return 0;
}
