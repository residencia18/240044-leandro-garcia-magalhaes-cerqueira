#include <string>
#include <iostream>
#include <vector>



//falta trocar a forma que altera deve-se perguntar.
using namespace std;

// Definição da estrutura Passageiros para armazenar informações de passageiros
struct Passageiros {
    string nome;
    string cpf;
    string dataNascimento;
    int numAltorizacao;
};

// Protótipos de funções

void menuPassageiro();
int switCase(vector<Passageiros> &passageiros);
void cadastrarPessoa(vector<Passageiros> &novaPessoa);
void listarPassageiros(vector<Passageiros> &passageiros);
int localizarPassageiroPorCPF(vector<Passageiros> &passageiros, string &cpf);
void alterarDadosPassageiroPorCPF(vector<Passageiros> &passageiros,string &cpf);
bool removerPassageiroPorCPF(vector<Passageiros> &passageiros, string &cpf);
// Função principal para gerenciar passageiros
int gestaoDePassageiros(vector<Passageiros> &passageiros) {
    bool continuar = true;

    do {
        menuPassageiro(); // Exibe o menu de opções
        continuar = switCase(passageiros); // Chama a função switCase para realizar a operação escolhida

    } while (continuar);

    return 0;
}
// Função que exibe o menu de opções
void menuPassageiro() {
    cout << endl<< "==== Menu Passageiro ====" << endl;
    cout << "1. Cadastrar Passageiro" << endl;
    cout << "2. Editar Passageiro" << endl;
    cout << "3. Remover Passageiro" << endl;
    cout << "4. Listar Passageiros" << endl;
    cout << "5. Localizar Passageiro" << endl;
    cout << "0. Sair" << endl;
    cout << "Escolha uma opção: ";
}

// Função que implementa o menu e direciona para a operação escolhida
int switCase(vector<Passageiros> &passageiros) {
    string cpf;
    int op;
    string mensagem;
    cin >> op;
    

    switch (op) {
        case 1:
        //incluir
            cadastrarPessoa(passageiros);
            break;
        case 2:
        //alterar
            cout <<endl<< "Digite o CPF do passageiro que deseja alterar:";
            cin.ignore(); // Limpa a nova linha pendente deixada pelo cin
            getline(cin, cpf);
            alterarDadosPassageiroPorCPF(passageiros, cpf);
            break;
        case 3:
            // Remover Passageiro;
            cout <<endl<< "Digite o CPF do passageiro:";
            cin.ignore(); // Limpa a nova linha pendente deixada pelo cin
            getline(cin, cpf);
            mensagem = removerPassageiroPorCPF(passageiros, cpf)? "Removido": "Não foi possivel remover";
            cout << mensagem << endl;
            break;
        case 4:
            //listar
            listarPassageiros(passageiros); // Chama a função para listar passageiros
            break;
        case 5:
            int posicao;
            //localizar e exibir
            cout <<endl<< "Digite o CPF do passageiro que deseja localizar: ";
            cin.ignore(); // Limpa a nova linha pendente deixada pelo cin
            getline(cin, cpf);
            posicao = localizarPassageiroPorCPF(passageiros, cpf);
            if (posicao!=-1)
            {
                cout << "CPF: " << passageiros[posicao].cpf<<endl;
                cout << "Nome: "<<passageiros[posicao].nome<<endl;
                cout << "Data de nascimento: " << passageiros[posicao].dataNascimento<<endl;
                
                cout << "Nº autorização: " << passageiros[posicao].numAltorizacao<<endl;
                /* code */
            }else{
                cout<<"0 resultados"<<endl;
            }
            

            //if;
            break;
        case 0:
            return 0; // Sai do programa
    }
    return true; // Continua no loop do menu
}

// Função para cadastrar um novo passageiro
void cadastrarPessoa(vector<Passageiros> &novaPessoa) {
    string nome;
    string cpf;
    string dataNascimento;
    
    Passageiros novoPassageiro;
    
    cin.ignore();
   
    do{
        cout << "Digite o CPF string(): ";
        getline(cin, cpf);
       
    }while (obterCPFValido(cpf));

    novoPassageiro.cpf = cpf;
    
    cout << "Digite o nome string(): ";
    getline(cin, nome);
    
    novoPassageiro.nome = validarCampo(nome, "nome");

    do{
        cout << "Digite a Data de nascimento (DD/MM/AAAA) string(): ";
        getline(cin, dataNascimento);
    } while (!validarData(dataNascimento));// Valida a data usando a função validarData
   
    novoPassageiro.dataNascimento = dataNascimento;
    if (verificarIdade(dataNascimento)){
        /* code */
        novoPassageiro.numAltorizacao = 1;//altorizado
    }else{
        novoPassageiro.numAltorizacao = 0;//não altorizado
    }

    novaPessoa.push_back(novoPassageiro);
    
}

// Função para listar os passageiros
void listarPassageiros(vector<Passageiros> &passageiros) {
    if (!passageiros.empty()) {
        cout<<endl;
        cout<< "---------------------------" <<endl;
        cout << "Lista de Passageiros:" << endl;
        cout<< "---------------------------" <<endl;
        for (size_t i = 0; i < passageiros.size(); i++) {
            cout << "Passageiro " << i + 1 << ": " <<endl; 
            cout << "CPF: " << passageiros[i].cpf << endl;
            cout << "Nome: " << passageiros[i].nome << endl;
             cout << "Data Nascimento: " << passageiros[i].dataNascimento << endl;
            cout << "Codigo de Altorizao: " << passageiros[i].numAltorizacao << endl;
            cout<< "---------------------------" <<endl;
        }
    } else {
        cout<<endl;
        cout << "Passageiros não encontrados!" << endl;
    }
     cout<<endl;
}
//Função para localizar os passageiros
int localizarPassageiroPorCPF(vector<Passageiros> &passageiros, string &cpf) {
    for (auto i = 0u; i < passageiros.size(); i++) {
        if (passageiros[i].cpf == cpf) {
            return i; // Retorna a posição (índice) do passageiro encontrado
           
        }
    }
    return -1; // Retorna -1 para indicar que o passageiro não foi encontrado
}

//Função para alterar dados
void alterarDadosPassageiroPorCPF(vector<Passageiros> &passageiros,string &cpf) {
    int posicao = localizarPassageiroPorCPF(passageiros, cpf);

    string nome;
    string dataNascimento;
    
    if (posicao != -1) {
        cout << "Encontrado passageiro com CPF: " << cpf << endl;

        // Ações para o campo Nome
        cout << "Deseja fazer alguma ação para o campo Nome? (s/n): ";
        char escolha;
        cin >> escolha;
        cin.ignore();
        if (escolha == 's' || escolha == 'S') {
            
            // Realize a ação desejada para o campo Nome, por exemplo:
            cout << "Digite o novo nome: ";
            getline(cin, nome);
            passageiros[posicao].nome = validarCampo(nome, "nome");
        }

        // Ações para o campo CPF
        cout << "Deseja fazer alguma ação para o campo CPF? (s/n): ";
        cin >> escolha;
        cin.ignore();
        if (escolha == 's' || escolha == 'S') {
            
            do{
                cout << "Digite o CPF: ";
                getline(cin, cpf);

            }while (obterCPFValido(cpf) );
            passageiros[posicao].cpf = cpf;
        }

        // Ações para o campo Data de Nascimento
        cout << "Deseja fazer alguma ação para o campo Data de Nascimento? (s/n): ";
        cin >> escolha;
        cin.ignore();
        if (escolha == 's' || escolha == 'S') {
            //Realize a ação desejada para o campo Data de Nascimento, por exemplo:
            do{
                cout << "Digite a Data de nascimento (DD/MM/AAAA): ";
                getline(cin, dataNascimento);
            } while (!validarData(dataNascimento));

            passageiros[posicao].dataNascimento = dataNascimento;
        }
        
        // Ações para o campo Número de Autorização
        cout << "Deseja fazer alguma ação para o campo Número de Autorização? (s/n): ";
        cin >> escolha;
        cin.ignore();
        if (escolha == 's' || escolha == 'S') {
            // Realize a ação desejada para o campo Número de Autorização, por exemplo:
            cout << "Digite o novo número de autorização: ";
            cin >> passageiros[posicao].numAltorizacao;
        }
    } else {
        cout << "Passageiro com CPF " << cpf << " não encontrado." << endl;
    }
}

//Função para remover dados
bool removerPassageiroPorCPF(vector<Passageiros> &passageiros, string &cpf) {

    int posicao = localizarPassageiroPorCPF(passageiros, cpf);

    if (posicao != -1) {
        passageiros.erase(passageiros.begin() + posicao); // Remove o passageiro da lista
               return true; // Indica que a remoção foi bem-sucedida
    } else {
        cout << "Passageiro não encontrado." << endl;
        return false; // Indica que o passageiro não foi encontrado na lista
    }
}
