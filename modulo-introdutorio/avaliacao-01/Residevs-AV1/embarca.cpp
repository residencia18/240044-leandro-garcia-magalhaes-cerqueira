#include <iostream>
#include <vector>
#include <string>
#include <ctime>

using namespace std;

struct Ocorrencia{
    string descricao;
    string data;
    int hora;
    int numApolice;
    
}; 
struct Embarca{
    string realizada;
    Data data;//data real de embarque
    int duracao;// duração real;
    Passageiros passageiro;//todos os dados de passageiros
    Roteiros roteiro;//todos os dados de roteiro
    Ocorrencia ocorrencia;
   
}; 

void cadastraEmbaque();
void cadastrarEmbarque(int umaPessoa, int umRoteiro, vector<Embarca> &embarques, vector<Passageiros> &passageiros, vector<Roteiros> &roteiros);
void listarEmbarques(vector<Embarca>& embarques);
void excluirEmbarque(vector<Embarca>& embarques, int indice);
int buscarEmbarques(vector<Embarca>& embarques, int codigo, string& cpf);
void alterarEmbarca(vector<Embarca>& vetorEmbarca, int indice);
void listarPassageirosEmbarcados(const vector<Embarca> &vetorEmbarca, int codigoRoteiro);

int menuEmbarque();
bool switCaseEmbarque(int op, vector<Passageiros> &passageiros, vector<Roteiros> &roteiros, vector<Embarca> &embarques);





int gestaoDeEmbarque(vector<Passageiros> &passageiros, vector<Roteiros> &roteiros, vector<Embarca> &embarques){

    bool continuar = true;
    do{
       continuar = switCaseEmbarque(menuEmbarque(), passageiros, roteiros, embarques); 
    } while (continuar);
    

    return 0;
}

bool switCaseEmbarque(int op, vector<Passageiros> &passageiros, vector<Roteiros> &roteiros, vector<Embarca> &embarques){
    int codigo;
    string cpf;

    int indice_pessoa;//posição(indice) no meu vector para buscar
    int indice_roteiro;//posição(indice) no meu vector para buscar
   // int indice_embarque;//indice do embarque
    int indice_embarque;
    switch (op){
            case 1:
                    //incluir embarque
                    //solicitar cpf e o codigo do passageiro;
                     cin.ignore();
                    cout <<endl << "Digite um CPF: ";
                    cin >> cpf;
                    cin.ignore();
                    cout << "Digite o codigo: ";
                    cin >> codigo;
                    cout<<endl;

                    ///verificar se o cpf esta cadastrado em passsageiro e verificar se roteiro esta cadastrado
                    indice_pessoa = localizarPassageiroPorCPF(passageiros, cpf);//pega o indece -1 não encontrou ou é a posição
                    indice_roteiro = localizarRoteiro(roteiros, codigo);//pega o indece -1 não encontrou ou é a posição
                    
                    
                    if((indice_roteiro != -1) && (indice_pessoa !=-1)){
                         cout << endl <<"Dados Validos!"<< endl;
                        //CadastarEmbarque
                            cadastrarEmbarque(indice_pessoa, indice_roteiro, embarques, passageiros, roteiros);
                    }else{
                         cout << endl << "Dados Invalidos"<< endl;
                     }
                break;
            case 2:
                //Excuir
               
                cin.ignore();
                cout << "Digite o codigo: ";
                cin >> codigo;

                listarPassageirosEmbarcados(embarques, codigo);

                cin.ignore();
                cout << "Digite um CPF: ";
                getline(cin, cpf);

                
                indice_embarque = buscarEmbarques(embarques, codigo, cpf);

                if (indice_embarque != -1) {
                    excluirEmbarque(embarques, indice_embarque);
                } else {
                    cout <<endl<< "Embarque não encontrado." << endl;
                }

                break;
            case 3:
                
                // Alterar
                cin.ignore();
                cout << "Digite o codigo: ";
                cin >> codigo;
                listarPassageirosEmbarcados(embarques, codigo);

                cin.ignore();
                cout << "Digite um CPF: ";
                getline(cin, cpf);
                alterarEmbarca(embarques, buscarEmbarques(embarques, codigo, cpf));
                
                break;
            case 4: 
                    listarEmbarques(embarques);
                // Lista;
                break;
            case 5:
                    
                break;
            case 0:
                return false;
                break;
            default:
                cout << "Opção Invalida!!!" <<endl;
                break;
        }
    return true;
}

int menuEmbarque(){

     int op;
    cout << endl <<"Bem vindo a Vans TransPaGente" << endl;
    cout << "1-Incluir um Novo embarque"<< endl;
    cout << "2-Excluir registro de embarque"<< endl; // pode ser por cpf
    cout << "3-Alterar "<< endl; 
    cout << "4-Listar todos os embarques" << endl; //listar o que os pasageiros e seus respectivos roteiro?
    cout << "0-Sair" <<endl;
    cout << "Digite uma opção:";
    cin >> op;
    
    return op; //rotana o opçao desejada
}

void cadastrarEmbarque(int indice_pessoa, int indice_roteiro, vector<Embarca> &embarques, vector<Passageiros> &passageiros, vector<Roteiros> &roteiros){
    Embarca embarcar;//struct do tipo embarcar

    string realizada;
    Data data;//data real de embarque //struc do data
    //int duracao;// duração real;

    cin.ignore(); 
    cout <<"Data de embarque string(): " << endl;
    getline(cin, data.data);
    embarcar.data.data = data.data;

    cin.ignore(); 
    // cout <<"Hora real do embarque (int): " << endl;
    // cin >> data.hora;
    
     // Obter a hora atual
    time_t rawtime;
    struct tm* timeinfo;

    time(&rawtime);
    timeinfo = localtime(&rawtime);

    // Calcular os segundos desde a meia-noite
    int segundosDesdeMeiaNoite = timeinfo->tm_hour * 3600 + timeinfo->tm_min * 60 + timeinfo->tm_sec;

    embarcar.data.hora = segundosDesdeMeiaNoite;
    
    // cout <<"Duracao da Vigem int(): " << endl;
    // cin >> duracao;
    embarcar.duracao = 1;


    cin.ignore(); 
    cout <<"O passageiro embarcou S/N char():" <<endl;//validar esse parametro
    getline(cin, realizada);
    embarcar.realizada = realizada;///tipo struc
    
    
    

    embarcar.passageiro = passageiros[indice_pessoa]; //passando os dados de um passageiro
    embarcar.roteiro = roteiros[indice_roteiro]; //passando os dados de um roteiro

    embarques.push_back(embarcar); //Registra o Embarque na coleção de embarques
}

// Função para excluir um elemento do vetor por índice
void excluirEmbarque(vector<Embarca>& embarques, int indice) {
    if (indice !=-1){
        embarques.erase(embarques.begin() + indice);
        cout << "Embarque na posição " << indice << " excluído com sucesso!" << std::endl;
    } else {
        cout << "Índice inválido. Nenhum embarque foi excluído." << std::endl;
    }
}

void listarEmbarques(vector<Embarca>& embarques){
   
    if (!embarques.empty()){
        cout <<endl<< "Lista de Embarques:" << endl;
        for (const Embarca& embarque : embarques) {
            cout << endl << "----------Roteiro----------" <<endl;

            cout << "Origem do Roteiro: " << embarque.roteiro.origem << endl;
            cout << "Destino do Roteiro: " << embarque.roteiro.destino << endl;
            cout << "Data e Hora Previstas: " << embarque.roteiro.data_hora_prevista.data << " " << embarque.roteiro.data_hora_prevista.hora << endl;
                
            cout << endl << "----------Dados do Passageiro----------" <<endl;
            cout << "CPF do Passageiro: " << embarque.passageiro.cpf << endl;
            cout << "Nome do Passageiro: " << embarque.passageiro.nome << endl;
            cout << "Data de Nascimento: " << embarque.passageiro.dataNascimento << endl;
            cout << "Autorização: " << embarque.passageiro.dataNascimento <<endl;

            cout << endl << "----------Embarque---------" <<endl;
            cout << "Realizada: " << embarque.realizada << std::endl;
            cout << "Data de Embarque: " << embarque.data.data << std::endl;
            cout << "Hora de Embarque: " << embarque.data.hora << std::endl;
            cout << "Duracao da viagem: " << embarque.duracao << std::endl;
       }
    }else{
        cout <<endl<<"Embarques não localizados" <<endl;
    }
}

int buscarEmbarques(vector<Embarca>& embarques, int codigo, string& cpf) {
    // Declara um iterador para percorrer o vetor
    vector<Embarca>::iterator it;

    // Percorre o vetor usando o iterador
    for (it = embarques.begin(); it != embarques.end(); ++it) {
        if (it->passageiro.cpf == cpf && it->roteiro.codigo == codigo) {
            // Encontrou um embarque com o CPF e código correspondentes
            // Calcula o índice do embarque encontrado
            int indice = distance(embarques.begin(), it);
            return indice; // Retorna o índice do embarque encontrado
        }
    }

    // Se não encontrou, retorna -1 para indicar que o embarque não foi encontrado
    return -1;
}

// Função para alterar campos de uma estrutura Embarca
void alterarEmbarca(vector<Embarca>& vetorEmbarca, int indice) {
    if (indice < 0 ) {
        cout << "Embarque não localizado" << endl;
        return;
    }

    Embarca& embarca = vetorEmbarca[indice];

    char confirmacao;

    // Perguntar se deseja alterar o campo 'realizada'
    cout << "Deseja alterar o campo 'realizada' (S/N)? ";
    cin >> confirmacao;
    if (confirmacao == 'S' || confirmacao == 's') {
        cout << "Nova 'realizada': ";
        cin >> embarca.realizada;
    }

    // Perguntar se deseja alterar o campo 'data'
    cout << "Deseja alterar o campo 'data' (S/N)? ";
    cin >> confirmacao;
    if (confirmacao == 'S' || confirmacao == 's') {
        cout << "Nova data de embarque: ";
        cin >> embarca.data.data;
        cout << "Nova hora de embarque: ";
        cin >> embarca.data.hora;
    }

    // Perguntar se deseja alterar o campo 'duracao'
    cout << "Deseja alterar o campo 'duracao' (S/N)? ";
    cin >> confirmacao;
    if (confirmacao == 'S' || confirmacao == 's') {
        cout << "Nova duração: ";
        cin >> embarca.duracao;
    }

    // Perguntar se deseja alterar o campo 'passageiro'
    cout << "Deseja alterar o campo 'passageiro' (S/N)? ";
    cin >> confirmacao;
    if (confirmacao == 'S' || confirmacao == 's') {
        Passageiros novoPassageiro;
        cout << "Novo nome do passageiro: ";
        cin >> novoPassageiro.nome;
        cout << "Novo CPF: ";
        cin >> novoPassageiro.cpf;
        cout << "Nova data de nascimento: ";
        cin >> novoPassageiro.dataNascimento;
        cout << "Novo número de autorização: ";
        cin >> novoPassageiro.numAltorizacao;
        embarca.passageiro = novoPassageiro;
    }

    // Perguntar se deseja alterar o campo 'roteiro'
    cout << "Deseja alterar o campo 'roteiro' (S/N)? ";
    cin >> confirmacao;
    if (confirmacao == 'S' || confirmacao == 's') {
        Roteiros novoRoteiro;
        cout << "Novo código do roteiro: ";
        cin >> novoRoteiro.codigo;
        cout << "Nova origem: ";
        cin >> novoRoteiro.origem;
        cout << "Novo destino: ";
        cin >> novoRoteiro.destino;
        cout << "Nova data e hora prevista: ";
        cin >> novoRoteiro.data_hora_prevista.data;
        cin >> novoRoteiro.data_hora_prevista.hora;
        embarca.roteiro = novoRoteiro;
    }

    // Solicitar confirmação
    cout << "Confirmar alterações (S/N)? ";
    cin >> confirmacao;

    if (confirmacao == 'S' || confirmacao == 's') {
        cout << "Embarca alterada com sucesso." << endl;
    } else {
        cout << "Alterações canceladas." << endl;
        // Reverter as alterações se necessário
    }
}

void listarPassageirosEmbarcados(const vector<Embarca> &vetorEmbarca, int codigoRoteiro)
{
    cout << "Passageiros embarcados no roteiro de código " << codigoRoteiro << ":" << endl;

    for (const Embarca &passageiro : vetorEmbarca)
    {
        if (passageiro.roteiro.codigo == codigoRoteiro)
        {
            cout << "Código do Passageiro: " << passageiro.roteiro.codigo << endl << " - Nome: " << passageiro.passageiro.nome << endl;
        }
    }

    cout << "Fim da lista de passageiros." << endl;
}