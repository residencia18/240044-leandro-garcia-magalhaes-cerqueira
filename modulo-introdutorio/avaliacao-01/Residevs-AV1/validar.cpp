// Inclui o arquivo de cabeçalho que contém a declaração da função
#include <string>
#include <iostream>
#include <limits>
#include <ctime>
#include <regex>

using namespace std;

//validar se está vazio o campo
string validarCampo(string campo, string nomeCampo) {
    while (campo.empty() || campo == " ") {
        cout << "O campo "<< nomeCampo <<" esta vázio!"<<endl;
        cout << "Digite "<<nomeCampo <<":"<<endl;
        getline(cin, campo); // Lê uma nova linha de entrada do usuário
    }

    return campo;
}

int validarCodigo(int codigo) {

    while (codigo == 0 ) {
        cout << "O código não pode ser zero. Por favor, insira um valor válido: ";
        cin >> codigo;
        cout<<endl;
    }

    return codigo;
}

bool validarCPF(const string& cpf) {
    // Verifica se o CPF tem o tamanho correto (14 caracteres contando com os pontos e o hífen)
    if (cpf.length() != 14 || cpf[3] != '.' || cpf[7] != '.' || cpf[11] != '-') {
         // Verifique se os caracteres restantes são dígitos
        for (int i = 0; i < 14; i++) {
            if (i == 3 || i == 7 || i == 11) {
                continue; // Pula pontos e o hífen
            }
            if (!isdigit(cpf[i])) {
                cout << "CPF inválido" << endl;
                cout << "Formato xxx.xxx.xxx-xx" << endl;
                return false;
            }
            return false;
        }
        cout << "CPF inválido" << endl;
        cout << "Formato xxx.xxx.xxx-xx" << endl;
         return false;
    }


   

    return true;
}

bool obterCPFValido(string cpf) {

    while (true) {

        if (validarCPF(cpf)) {
            cout << "CPF válido! " << endl;
            return false;
        } else {
            cout <<"CPF inválido"<<endl;
            cout <<"Fomato xxx.xxx.xxx-xx"<<endl;
           return true;
            
        }
    }
}


bool validarData(const string& data) {
    // Verifica se a data tem o formato correto (DD/MM/AAAA)
    if (data.length() != 10 ||
        data[2] != '/' || data[5] != '/' ||
        !isdigit(data[0]) || !isdigit(data[1]) ||
        !isdigit(data[3]) || !isdigit(data[4]) ||
        !isdigit(data[6]) || !isdigit(data[7]) || !isdigit(data[8]) || !isdigit(data[9])) {
        cout << "Formato de data inválido. Use o formato DD/MM/AAAA." << endl;
        return false;
    }

    // Extrai dia, mês e ano da string
    int dia = stoi(data.substr(0, 2));
    int mes = stoi(data.substr(3, 2));
    int ano = stoi(data.substr(6, 4));

    // Verifica se os valores são válidos
    if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || ano < 1900) {
        cout << "Data inválida." << endl;
        return false;
    }

    // Você pode adicionar verificações adicionais, como anos bissextos, se necessário

    return true;
}

// Função para verificar se uma pessoa é maior ou menor de idade
bool verificarIdade(const string& dataNascimento) {
    
    // Obter a data atual
    time_t agora = time(0);
    tm* dataAtual = localtime(&agora);
    int anoAtual = dataAtual->tm_year + 1900;
    int mesAtual = dataAtual->tm_mon + 1;
    int diaAtual = dataAtual->tm_mday;

    // Extrair dia, mês e ano da data de nascimento
    int diaNascimento, mesNascimento, anoNascimento;
    sscanf(dataNascimento.c_str(), "%d/%d/%d", &diaNascimento, &mesNascimento, &anoNascimento);

    // Calcular a diferença de idade
    int idade = anoAtual - anoNascimento;

    // Verificar se a pessoa já fez aniversário neste ano
    if (mesAtual < mesNascimento || (mesAtual == mesNascimento && diaAtual < diaNascimento)) {
        idade--;
    }

    // Determinar se é maior ou menor de idade
    if (idade >= 18) {
        return true;
    } else {
        return false;
    }
}

bool validar_data_hora(const std::string& data_hora) {
    // O formato desejado é "YYYY-MM-DD HH:MM:SS"
    std::regex padrao("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");

    return std::regex_match(data_hora, padrao);
}