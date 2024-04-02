#include <iostream>
#include <vector>
#include <string>



using namespace std;
struct Data{
    string data;
    int hora;
};
struct Roteiros{
    int codigo;
    string origem;
    string destino;
    Data data_hora_prevista;
};

//Prototipos de função
int menuRoteiro();

bool gereciarRoteiros(int op, vector<Roteiros> &roteiros);
void cadastrarRoteiro(vector<Roteiros>& novoRoteiro);
void listarRoteiros(const vector<Roteiros> &roteiros);
int localizarRoteiro(const vector<Roteiros> &roteiros, int codigo);
void alterarRoteiro(vector<Roteiros> &roteiros, int codigo);
void excluirRoteiro(vector<Roteiros> &roteiros, int codigo);

int gestaoDeRoteiros(vector<Roteiros> &roteiros){
    
    bool continuar = true;

    do{
        // chamar meu menu retorna um opçao para a funçao gerenciarRoteiros.
       continuar = gereciarRoteiros(menuRoteiro(),roteiros);
       //retona true ou false caso o usario queria continuar ou sair do gestão de roteiros 
       //e consequentimente gerenciar roteiros
    } while (continuar);//continuar no menuRoteiro
    return 0;//retonar ao menu principal
}

bool gereciarRoteiros(int op, vector<Roteiros> &roteiros){
    int codigo;
    switch (op) {
        case 1:
            cadastrarRoteiro(roteiros);
            break;

        case 2:
            cout << "Digite o Codigo:";
            cin >>codigo;
            excluirRoteiro(roteiros, codigo);
            break;

        case 3:
            //Alterar roteiro
            cout << "Digite o Codigo:";
            cin >>codigo;
            alterarRoteiro(roteiros, codigo);
            break;

        case 4:
            listarRoteiros(roteiros);
            break;
        case 5:
            //Localizar por codigo
            int posicao;
            cout << "Digite o Codigo:";
            cin >>codigo;
            posicao = localizarRoteiro(roteiros, codigo);

            if (posicao != -1)
            {
                cout <<"Codigo: " <<roteiros[posicao].codigo<< endl;
                cout <<"Origin: " <<roteiros[posicao].origem << endl;
                cout <<"Destino:" << roteiros[posicao].destino << endl;
            }else{
                cout << "Roteiro não encontrado"<<endl;
            }

            break;
        case 0:
            
return false; // Sai do menu;
        
        default:
            return true; //continuar caso o usuario tenha digitado um valor errado;
    }

    return true; //continuar no menu;
}

int localizarRoteiro(const std::vector<Roteiros> &roteiros, int codigo) {
    // Usando um loop for-each com iteradores
    int posicao = 0;
    for (auto it = roteiros.begin(); it != roteiros.end(); ++it) {
        if (it->codigo == codigo) {
            return posicao;  // Encontrado, retorna a posição
        }
        posicao++;
    }
    
    return -1;  // Não encontrado, retorna -1
}

void excluirRoteiro(vector<Roteiros> &roteiros, int codigo) {
    int posicao = localizarRoteiro(roteiros, codigo);

    if (posicao != -1) {
        roteiros.erase(roteiros.begin() + posicao); // Remove o roteiro na posição encontrada
        std::cout << "Roteiro com código " << codigo << " excluído." << std::endl;
    } else {
        std::cout << "Roteiro com código " << codigo << " não encontrado." << std::endl;
    }
}

void alterarRoteiro(vector<Roteiros> &roteiros, int codigo) {
    int posicao = localizarRoteiro(roteiros, codigo);
    string nova_origem;
    string novo_destino;
    Data data;
    if (posicao != -1) {
        // Exibir o objeto Roteiros encontrado na posição 'posicao'.
        cout << "Código: " << roteiros[posicao].codigo << endl;
        cout << "Origem: " << roteiros[posicao].origem << endl;
        cout << "Destino: " << roteiros[posicao].destino << endl;

        // Perguntar ao usuário se deseja alterar cada campo.
        string resposta;
        
        cout << "Deseja alterar o código? (S/N): ";
        cin >> resposta;
        if (resposta == "S" || resposta == "s") {
            cout <<"Digite um outro codigo int() :";
            cin >> codigo;
             while (localizarRoteiro(roteiros, validarCodigo(codigo))!=-1){//o codigo foi encontrado
                cout << "Codigo já registrado!"<<endl;
                cout <<"Digite um outro codigo int() :";
                cin >> codigo;
            }
        }
        
        cout << "Deseja alterar a origem? (S/N): ";
        cin >> resposta;
        if (resposta == "S" || resposta == "s") {
            cout << "Nova origem: ";
            cin.ignore();  // Para limpar o buffer do teclado
            getline(cin, nova_origem);
            roteiros[posicao].origem = validarCampo(nova_origem, "origem");
        }
        
        cout << "Deseja alterar o destino? (S/N): ";
        cin >> resposta;
        if (resposta == "S" || resposta == "s") {
            cout << "Novo destino string():";
            cin.ignore();  // Para limpar o buffer do teclado
            getline(cin, novo_destino);
            roteiros[posicao].destino = validarCampo(novo_destino, "destino");
        }
        cout << "Deseja alterar a Data e hora? (S/N): ";
        cin >> resposta;
        if (resposta == "S" || resposta == "s") {
            cout << "Nova Data string()";
            cin.ignore();  // Para limpar o buffer do teclado
            getline(cin, data.data);
            roteiros[posicao].data_hora_prevista.data = data.data;
           
            cout << "Nova Hora int()";
            cin >> data.hora;
            roteiros[posicao].data_hora_prevista.hora = data.hora;

        }
    }else{
         cout << "Roteiro não encontrado." << endl;
    }
    
}
// Função para cadastrar roteiro


void cadastrarRoteiro(vector<Roteiros>& novoroteiro){
    int codigo = 0 ;
    string origem;
    string destino;
    Data datahoraprevista;

    Roteiros item_novo;
    
    cout <<"Digite o codigo int():";
    cin >> codigo;
    while (true)
    {
       
        if (localizarRoteiro(novoroteiro, validarCodigo(codigo))!=-1){
            cout <<"Digite um outro codigo int():";
            cin >> codigo;
        }else{
           break; 
        }
    }
    ///o codigo foi encontrado
        
    item_novo.codigo = codigo;

    cin.ignore();
    cout << "Digite a origem string(): ";
    getline(cin, origem);
    item_novo.origem = validarCampo(origem, "origem");
   
    cout<< "Digite o destino string() : ";
    getline(cin, destino);
    item_novo.destino = validarCampo(destino, "destino");
    
    cout<< "Digite a data string(): ";
    cin >> datahoraprevista.data;

    cout<< "Digite a hora int(): : ";
    cin >> datahoraprevista.hora;

    
    item_novo.data_hora_prevista = datahoraprevista;

    novoroteiro.push_back(item_novo);

}
// Função para listar os roteiros

void listarRoteiros(const vector<Roteiros>& roteiros) {
    if (roteiros.empty()) {
        cout <<endl << "Nenhum roteiro disponível." << endl;
    } else {
        cout <<endl<< "Lista de Roteiros:" << endl;
        for (const Roteiros& roteiro : roteiros) {
            cout << "Código: " << roteiro.codigo << endl;
            cout << "Origem: " << roteiro.origem << endl;
            cout << "Destino: " << roteiro.destino << endl;
            cout << "Data e hora: " << roteiro.data_hora_prevista.data <<" " << roteiro.data_hora_prevista.hora << endl;
            cout << "-------------------" << endl;
        }
    }
}

// Função exibir menuRoteiro
int menuRoteiro(){
    int op;
    cout << "Bem vindo a Vans TransPaGente" << endl;
    cout << "------------------------------"<< endl;
    cout << "1-Incluir um Novo Roteiro?"<< endl;
    cout << "2-Excluir um Roteiro inserido"<< endl; // pode ser por cpf
    cout << "3-Alterar (apenas por Código)"<< endl; //buscar por cpf
    cout << "4-Listar " << endl; //listar o que os pasageiros e seus respectivos roteiro?
    cout << "5-Localizar Roterio(Por Código)" << endl;
    cout << "0-Sair" << endl;
    cout << "Digite uma opção:";
    cin >> op;
    return op;
}
