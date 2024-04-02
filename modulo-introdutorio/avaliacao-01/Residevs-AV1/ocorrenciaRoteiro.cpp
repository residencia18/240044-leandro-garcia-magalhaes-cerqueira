#include <iostream>
#include <vector>

using namespace std;
struct OcorrenciaRoteiro{
    string descricao;
    string data;
    int hora;
    int numApolice;
    
    Roteiros roteiro;
}; 

void menuOcorrenciaRoteiro();
void cadastrarOcorrenciaRoteiro(vector<OcorrenciaRoteiro> &ocorrencias, vector<Roteiros> &roteiros);
Roteiros retornaRoteiro(vector<Roteiros> &roteiros, int codigo);
void listarTudo(vector<OcorrenciaRoteiro>& Ocorrencias);
void excluirOcorrenciaRoteiro(vector<OcorrenciaRoteiro> &ocorrencias, vector<Roteiros> &roteiros);
int gestaoOcorrenciaRoteiro(vector<OcorrenciaRoteiro> &ocorrencias, vector<Roteiros> &roteiros){
    bool validar = true;
    int op;

    do{
        menuOcorrenciaRoteiro();
        cin >> op;
        switch (op){
            case 1:
                cadastrarOcorrenciaRoteiro(ocorrencias, roteiros);
                break;
            case 2:
                    // excluir
                break;
            case 3:
                    // Alterar;
                break;
            case 4: 
                listarTudo(ocorrencias);   // Listar passageiro;
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

void listarTudo(vector<OcorrenciaRoteiro>& Ocorrencias) {
   for (const OcorrenciaRoteiro& o : Ocorrencias) {
        cout << "Lista de Ocorrencias do Roteiro: "<< o.roteiro.codigo <<":" << endl;
        
        cout << "Descrição: " << o.descricao << endl;
        cout << "Data: " << o.data << endl;
        cout << "Hora: " << o.hora << endl;
        
        cout << endl;
    }
}
Roteiros retornaRoteiro(vector<Roteiros> &roteiros, int codigo){
    for (const Roteiros &r : roteiros) {
        if (r.codigo == codigo){
            return r;
        }
    }
    return Roteiros{};
}
void cadastrarOcorrenciaRoteiro(vector<OcorrenciaRoteiro> &ocorrencias, vector<Roteiros> &roteiros){
    int codigo;
    int indice_roteiro;
    Roteiros roteiro;

    cout << "Digite o codigo do Roteiro: ";
    cin >> codigo;
    cout<<endl;
    
    indice_roteiro = localizarRoteiro(roteiros, codigo);//pega o indece -1 não encontrou ou é a posição
    roteiro = retornaRoteiro(roteiros, codigo);

    if((indice_roteiro != -1)){
        cout << endl <<"Dados Validos!"<< endl;
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
        OcorrenciaRoteiro ocorrenciaAtual;

        ocorrenciaAtual.descricao = descricao;
        ocorrenciaAtual.data = data;
        ocorrenciaAtual.hora = hora;
        ocorrenciaAtual.numApolice = numApolice;
        ocorrenciaAtual.roteiro = roteiro;
        

        ocorrencias.push_back(ocorrenciaAtual); //Registra a ocorrencia
    }else{
         cout << endl << "Dados Invalidos"<< endl;
    }

}
//funções
void excluirOcorrencia(vector<OcorrenciaRoteiro> &ocorrencia, vector<Roteiros> &roteiros){
    int codigo;
    int indice_roteiro;
    bool encontrou = false;
                    
    cout << "Digite o codigo: ";
    cin >> codigo;
    cout<<endl;
    
    indice_roteiro = localizarRoteiro(roteiros, codigo);//pega o indece -1 não encontrou ou é a posição
    

    if ( indice_roteiro != -1  )
    {
        for (size_t i = 0; i < ocorrencia.size(); i++) {
            if (ocorrencia[i].roteiro.codigo == codigo){
                encontrou = true;
                ocorrencia.erase(ocorrencia.begin() + i);

                cout << "-----------------------------------------------------------------------------" << endl;
                cout << "\t\tOcorrência do Roteiro "<<codigo<<" foi excluido com sucesso." << endl;
                cout << "-----------------------------------------------------------------------------" << endl;
            }
        }
        if (!encontrou){
            cout << "-----------------------------------------------------------------------------" << endl;
            cout << "\t\tOps! Ocorrência não encontrada." << endl;
            cout << "-----------------------------------------------------------------------------" << endl;
        }
    } else {
         cout << endl << "Dados Invalidos" << endl;
    }
}
void menuOcorrenciaRoteiro(){
    cout << endl<< "==== Menu Ocorrência por Roteiro ====" << endl;
    cout << "1. Incluir uma ocorrência"<< endl; //solicita o CPF e Código
    cout << "2. Excluir uma ocorrência"<< endl; //solicita o CPF e Código
    cout << "3. Alterar uma ocorrência"<< endl; //solicita o CPF e Código
    cout << "4. Listar todas as ocorrências" << endl; //Solicita o CPF
    cout << "0. Sair" <<endl;
    cout << "Digite uma opção:";
}