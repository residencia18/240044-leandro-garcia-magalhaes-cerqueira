#include <iostream>
#include <vector>

using namespace std;

void menuOcorrencia();
void cadastrarOcorrencia(vector<Embarca> &embarques);
void listarPorPassageiro(vector<Embarca>& embarques);
void listarPorRoteiro(vector<Embarca>& embarques);
void excluirOcorrencia(vector<Embarca> &embarques);
void alterarOcorrencia(vector<Embarca> &embarques);
void registrarOcorrenciaPorRoteiro(vector<Embarca> &embarques);
void listarTodasOcorrencias(vector<Embarca>& embarques);
int gestaoOcorrencia(vector<Embarca> &embarques){
    bool validar = true;
    int op;

    do{
        menuOcorrencia();
        cin >> op;
        switch (op){
            case 1:
                cadastrarOcorrencia(embarques);
                break;
            case 2:
                excluirOcorrencia(embarques);// excluir
                break;
            case 3:
                alterarOcorrencia(embarques);   // Alterar;
                break;
            case 4: 
                listarPorPassageiro(embarques);   // Listar passageiro;
                break;
            case 5:
                listarPorRoteiro(embarques);   // Listar roteiro;
                break;
            case 6:
                registrarOcorrenciaPorRoteiro(embarques);   // registrar roteiro;
                break;
            case 0:
                validar = false;
                break;
            default:
                cout << "Opção Invalida!!!" <<endl;
                break;
        }
    } while (validar);
    

    return 0;
}
int obterIndiceDoEmbarque(vector<Embarca> &embarques, string cpf, int codigo);

void listarPorPassageiro(vector<Embarca>& embarques) {
    string cpf;
    string nome="";
    cout <<endl << "Informe o CPF: ";
    cin.ignore();
    getline(cin, cpf);
    
    for (const Embarca& e : embarques) {
        //Verifica se o cpf digitado e se tem ocorrencia para o embarque
        if(e.passageiro.cpf == cpf){
            if (nome==""){
                nome=e.passageiro.nome;
                cout << "Lista de Ocorrencias do Passageiro: "<< nome <<":" << endl;
            }
            
                cout << "Roteiro: " << e.roteiro.codigo << endl;
                cout << "Descrição: " << e.ocorrencia.descricao << endl;
                cout << "Data: " << e.data.data << endl;
                cout << "Hora: " << e.duracao << endl;
            
                cout << endl;
            
        }
    }
}

void listarPorRoteiro(vector<Embarca>& embarques) {
    int codigo;
    cout <<endl << "Informe o Código: ";
    cin>>codigo;
    string nome="";
    
    for (const Embarca& e: embarques) {
        if(e.roteiro.codigo == codigo){
            if (nome==""){
                nome= e.roteiro.codigo;
                cout << "Lista de Ocorrencias do Roteiro: "<< nome <<":" << endl;
            }
                cout << "Descrição: " << e.ocorrencia.descricao << endl;
                cout << "Data: " << e.data.data << endl;
                cout << "Hora: " << e.duracao << endl;
        
                cout << endl;
            
            
        }
    }
}

void cadastrarOcorrencia(vector<Embarca> &embarques){
    int codigo;
    string cpf;
    Embarca embarque;
    cout <<endl << "Informe o CPF: ";
    cin.ignore();
    getline(cin, cpf);
                    
    cout << "Digite o codigo: ";
    cin >> codigo;
    cout<<endl;
    
    for (Embarca &e : embarques) {
        if (e.passageiro.cpf == cpf && e.roteiro.codigo == codigo){
            //CadastarOcorrencia
            string descricao;
            string data;
            int hora;
            int numApolice;
            cout <<"Descreva o que aconteceu:" <<endl;
            cin.ignore(); 
            getline(cin, descricao);
            
            cout <<"Informe a data: " << endl;
            cin.ignore(); 
            getline(cin, data);

            cout <<"Informe a hora: " << endl;
            cin >> hora;
            
            cout <<"Informe o numero do Apolice: " << endl;
            cin >> numApolice;

            embarque.ocorrencia.descricao = descricao;
            embarque.ocorrencia.data = data;
            embarque.ocorrencia.hora = hora;
            embarque.ocorrencia.numApolice = numApolice;
            
            return;
        }
    }
    cout << "\t\tOps! Não encontrei os dados." << endl;
    
}
//funções
void excluirOcorrencia(vector<Embarca> &embarques){
    int codigo;
    string cpf;
    string nomePassageiro;
    bool encontrou = false;
    cout <<endl << "Informe o CPF: ";
    cin.ignore();
    getline(cin, cpf);
                    
    cout << "Digite o codigo: ";
    cin >> codigo;
    cout<<endl;
    for (size_t i = 0; i < embarques.size(); i++) {
        if (embarques[i].passageiro.cpf == cpf && embarques[i].roteiro.codigo == codigo){
           encontrou = true;
           nomePassageiro = embarques[i].passageiro.nome;
           embarques[i].ocorrencia = {};

           cout << "-----------------------------------------------------------------------------" << endl;
           cout << "\t\tOcorrência de "<<nomePassageiro<<" do Roteiro "<<codigo<<" foi excluido com sucesso." << endl;
           cout << "-----------------------------------------------------------------------------" << endl;
        }
    }
    if (!encontrou){
        cout << "-----------------------------------------------------------------------------" << endl;
        cout << "\t\tOps! Ocorrência não encontrada." << endl;
        cout << "-----------------------------------------------------------------------------" << endl;
    }
}

void alterarOcorrencia(vector<Embarca> &embarques)
{
    int codigo;
    string cpf;
    int indice_embarque;

    cout <<endl << "Informe o CPF: ";
    cin.ignore();
    getline(cin, cpf);
                    
    cout << "Digite o codigo: ";
    cin >> codigo;
    cout<<endl;

    // localizar o índice do embarque a ser alterado
    indice_embarque = obterIndiceDoEmbarque(embarques, cpf, codigo);

    if ( indice_embarque != -1 ) 
    {
        cout << "Dados Validos!" << endl;

        string nomePassageiro = embarques[indice_embarque].passageiro.nome;
        
        //AlterarOcorrencia
        string flag;
        string descricao;
        string data;
        int hora;
        int numApolice;

        cout << "Deseja alterar a descricao da ocorrencia (s/n)? ";
        cin >> flag;

        if ( flag == "s" ) {
            cout <<"Descreva o que aconteceu:" <<endl;
            cin.ignore(); 
            getline(cin, descricao);
        } else {
            embarques[indice_embarque].ocorrencia.descricao = descricao;
        }
        
        cout << "Deseja alterar a data (s/n)? ";
        cin >> flag;

        if ( flag == "s" ) {
            cout <<"Informe a data: " << endl;
            cin.ignore(); 
            getline(cin, data);
        } else {
            embarques[indice_embarque].ocorrencia.data = data;
        }

        cout << "Deseja alterar a hora (s/n)? ";
        cin >> flag;

        if ( flag == "s" ) {
            cout <<"Informe a hora: " << endl;
            cin >> hora;
        } else {
            embarques[indice_embarque].ocorrencia.hora = hora;
        }

        cout << "Deseja alterar o numero do Apolice (s/n)? ";
        cin >> flag;

        if ( flag == "s" ) {
            cout <<"Informe o numero do Apolice: " << endl;
            cin >> numApolice;
        } else {
            embarques[indice_embarque].ocorrencia.numApolice = numApolice;
        }

        cout << "-----------------------------------------------------------------------------" << endl;
        cout << "\t\tOcorrência de "<<nomePassageiro<<" do Roteiro "<<codigo<<" foi alterada com sucesso." << endl;
        cout << "-----------------------------------------------------------------------------" << endl;
    } 
    else {
        cout << "Dados Invalidos!" << endl;
        cout << "-----------------------------------------------------------------------------" << endl;
        cout << "\t\tOps! Ocorrência não encontrada." << endl;
        cout << "-----------------------------------------------------------------------------" << endl;
    }
} 

int obterIndiceDoEmbarque(vector<Embarca> &embarques, string cpf, int codigo)
{
    int indice_embarque = -1;
    int i = 0;

    for (Embarca embarque: embarques) {
        if ( embarque.passageiro.cpf == cpf && embarque.roteiro.codigo == codigo ) {
            indice_embarque = i;
        }

        i++;
    }

    return indice_embarque;
}
void registrarOcorrenciaPorRoteiro(vector<Embarca> &embarques){
     int codigo;
    Ocorrencia ocorrencia_atual;

    cout << "Digite o codigo do roteiro: " << endl;
    cin >> codigo;

    cout << "Descreva o que aconteceu:" << endl;
    cin.ignore(); 
    getline(cin, ocorrencia_atual.descricao);
        
    cout <<"Informe a data: " << endl;
    cin.ignore(); 
    getline(cin, ocorrencia_atual.data);

    cout <<"Informe a hora: " << endl;
    cin >> ocorrencia_atual.hora;
    
    cout <<"Informe o numero do Apolice: " << endl;
    cin >> ocorrencia_atual.numApolice;

    // rodar em todos os embarques
    for (Embarca embarque: embarques) {
        //ver se o embarque está vinculado ao código em questão
        if (embarque.roteiro.codigo == codigo) {
            embarque.ocorrencia = ocorrencia_atual; //sem perguntar nada
            //embarque.ocorrencia.push_back(ocorrencia_atual.ocorrencia); //se for vetor

            /*
            // se for pra perguntar sobre sobrescrever
            if (embarque.ocorrencia.descricao.empty())
            {
                cout << "Já existe uma ocorrencia vinculada a esse roteiro no embarque do passageiro " << embarque.passageiro.nome << ".\n
                Deseja sobrescrever (s/n)?"

                if (flag == "s") {
                    embarque.ocorrencia = ocorrencia_atual.ocorrencia; //sem perguntar nada
                    embarque.ocorrencia.push_back(ocorrencia_atual.ocorrencia); //se for vetor
                }
            }
            */
        }
    }
}
void listarTodasOcorrencias(vector<Embarca>& embarques) {
    for (Embarca& e : embarques) {
        std::cout<< endl << "Descrição: " << e.ocorrencia.descricao << std::endl;
        std::cout << "Data: " << e.ocorrencia.data << std::endl; 
        std::cout << "Número da Apólice: " << e.ocorrencia.numApolice << std::endl;
        std::cout << "Embarque: " << e.roteiro.codigo << std::endl; 
        std::cout << "Passageiro: " << e.passageiro.nome << std::endl; 
        std::cout << "--------------------------" << std::endl;
    }
}
void menuOcorrencia(){
    cout << endl<< "==== Menu Ocorrência ====" << endl;
    cout << "1. Incluir uma ocorrência"<< endl; //solicita o CPF e Código
    cout << "2. Excluir uma ocorrência"<< endl; //solicita o CPF e Código
    cout << "3. Alterar uma ocorrência"<< endl; //solicita o CPF e Código
    cout << "4. Listar todas as ocorrências por Passageiro" << endl; //Solicita o CPF
    cout << "5. Listar todas as ocorrências por Roteiro" << endl; //Solicita o Codigo
    cout << "6. Registrar ocorrências por Roteiro" << endl; //Solicita o Codigo
    cout << "0. Sair" <<endl;
    cout << "Digite uma opção:";
}