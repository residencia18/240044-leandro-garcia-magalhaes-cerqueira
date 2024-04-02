/*
2. Cadastramento de eventos.
3. Acrescente a funcionalidade de criar pacotes (acrescentando neles
alguns dos eventos já definidos antes
4. Acrescente a funcionalidade de criar clientes com seus dependentes
5. Acrescente a funcionalidade de vender pacotes a clientes.
6. Permita que se consulte a lista de clientes (com dependentes),
7. Permita que se consulte a lista de pacotes (com eventos)
8. Permita que se consulte os pacotes contratados por algum cliente específico
9. Permita que se consulte os clientes que contrataram algum pacote específico.
*/

// #include "BD.h"
#include <iostream>
#include "interface.h"
#include "pacoteTurismo.h"
#include "ctrlPacote.h"

using namespace std;

void exibirListaClientes();

int main()
{

    vector<PacoteTurismo> pacotes;
    vector<Evento> eventos;
    vector<Cliente> clientes;

    int opcao;

    vector<string> menu = {"1. Cadastrar evento",
                           "2. Criar pacote de turismo",
                           "3. Criar cliente",
                           "4. Vender pacote a cliente",
                           "5. Listar clientes ",
                           "6. Listar pacotes ",
                           "7. Consultar pacotes contratados por cliente",
                           "8. Consultar clientes que contrataram um pacote",
                           "9. Sair"};

    while (true)
    {
        monta_menu(menu, "Escolha uma opcao: \n");
        opcao = obter_opcao(9);
        switch (opcao)
        {
        case 1: // 1. Cadastrar evento
            eventos.push_back(ctrl_pacote::criarEvento());
            break;
        case 2: // 2. Criar pacote de turismo
            if (eventos.empty())
            {
                cout << "Antes de criar um pacote, é necessário cadastrar eventos." << endl;
            }
            else
            {
                pacotes.push_back(ctrl_pacote::criarPacoteTurismo(eventos));
            }
            break;
        case 3: // 3. Criar cliente
            clientes.push_back(ctrl_pacote::criarCliente());
            break;
        case 4: // 4. Vender pacote a cliente
            if (clientes.empty() || eventos.empty())
            {
                cout << "Não é possível vender pacotes. Clientes ou eventos não cadastrados." << endl;
            }
            else
            {
                int clienteEscolhido, pacoteEscolhido;
                cout << "Escolha um cliente:" << endl;
                for (int i = 0; i < clientes.size(); i++)
                {
                    cout << i << ". " << clientes[i].getNome() << endl;
                }
                cin >> clienteEscolhido;
                cout << "Escolha um pacote para vender:" << endl;
                for (int i = 0; i < eventos.size(); i++)
                {
                    cout << i << ". " << eventos[i].getNome() << endl;
                }
                cin >> pacoteEscolhido;
                ctrl_pacote::venderPacote(clientes[clienteEscolhido], pacotes[pacoteEscolhido]);
            }
            break;
        case 5: // Consultar lista de clientes
            for (Cliente cliente : clientes)
            {
                cout << "Nome: " << cliente.getNome() << "\t Idade: " << cliente.getIdade() << endl
                     << endl;

                vector<Dependente> dependentes = cliente.getDependentes();
                cout << "lista dependentes:" << endl;
                for (Dependente dependente : dependentes)
                {
                    cout << "Nome: " << cliente.getNome() << "\t Idade: " << cliente.getIdade() << endl;
                }
            }
            break;
        case 6: // Consultar lista de pacotes
            for (int i = 0; i < pacotes.size(); i++)
            {
                cout << "Nome do pacote: " << pacotes[i].getNome() << ", Evento: " << pacotes[i].evento.getNome() << endl;
            }
            break;
        case 7: // Consultar pacotes contratados por cliente
            if (clientes.empty())
            {
                cout << "Não existem clientes cadastrados.\n";
            }
            else
            {
                int clienteEscolhido;
                cout << "Escolha um cliente:\n";
                for (int i = 0; i < clientes.size(); i++)
                {
                    cout << i << ". " << clientes[i].getNome() << endl;
                }
                cin >> clienteEscolhido;
                cout << "Pacotes contratados por " << clientes[clienteEscolhido].getNome() << ":\n";
                for (int i = 0; i < clientes[clienteEscolhido].pacotes.size(); i++)
                {
                    cout << "Nome do pacote: " << clientes[clienteEscolhido].pacotes[i].getNome() << ", Evento: " << clientes[clienteEscolhido].pacotes[i].evento.getNome() << endl;
                }
            }
            break;
            // case 8: // Consultar clientes que contrataram um pacote
            //     if (eventos.empty()) {
            //         cout << "Não existem pacotes cadastrados.\n";
            //     } else {
            //         int pacoteEscolhido;
            //         cout << "Escolha um pacote:\n";
            //         for (int i = 0; i < pacotes.size(); i++) {
            //             cout << i << ". " << pacotes[i].getNome() << endl;
            //         }
            //         cin >> pacoteEscolhido;
            //         cout << "Clientes que contrataram o pacote " << pacotes[pacoteEscolhido].getNome() << ":\n";
            //         for (int i = 0; i < clientes.size(); i++) {
            //             for (int j = 0; j < clientes[i].pacotes.size(); j++) {
            //                 if (clientes[i].pacotes[j].getNome() == pacotes[pacoteEscolhido].getNome()) {
            //                     cout << "Nome: " << clientes[i].getNome() << ", Idade: " << clientes[i].getIdade() << endl;
            //                     break; // Só exibe o cliente uma vez
            //                 }
            //             }
            //         }
            //     }
            //     break;
        case 9:
            exit(0);
        }
    }
    return 0;
}