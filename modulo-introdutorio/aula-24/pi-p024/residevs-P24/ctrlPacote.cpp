#include <iostream>
#include <vector>
#include <string>
#include "pacoteTurismo.h"
#include "ctrlPacote.h"


using namespace std;
// Função para criar um evento
Evento ctrl_pacote :: criarEvento() {
    string nome;
    int vagas;
    cout << "Nome do evento: ";
    cin >> nome;
    cout << "Número de vagas: ";
    cin >> vagas;
    return Evento(nome, vagas);
}
// Função para criar um pacote de turismo
PacoteTurismo ctrl_pacote :: criarPacoteTurismo(vector<Evento>& eventos) {
    string nome;
    int eventoEscolhido;
    string nomeRoteiro, meioDeTransporte, hotel;

    cout << "Nome do pacote: ";
    cin >> nome;
    do
    {
        cout << "Escolha um evento para adicionar ao pacote:\n";
        for (int i = 0; i < eventos.size(); i++) {
            cout << i << ". " << eventos[i].getNome() << endl;
        }
        cin >> eventoEscolhido;
    } while (eventoEscolhido < 0 || eventoEscolhido > eventos.size());
    
    cout << "Descrição do roteiro: ";
    cin >> nomeRoteiro;

    cout << "Meio de transporte: ";
    cin >> meioDeTransporte;

    cout << "Hotel: ";
    cin >> hotel;

    return PacoteTurismo(nome, eventos[eventoEscolhido], Roteiro(nomeRoteiro), Deslocamento(meioDeTransporte), Pernoite(hotel));
}

Cliente ctrl_pacote :: criarCliente() {
    string nome;
    int idade;
    cout << "Nome do cliente: ";
    cin >> nome;
    cout << "Idade do cliente: ";
    cin >> idade;

    Cliente cliente(nome, idade);
    
    // Solicitar informações sobre dependentes
    int numDependentes;
    cout << "Quantos dependentes deseja adicionar? ";
    cin >> numDependentes;

    for (int i = 0; i < numDependentes; i++) {
        string nomeDependente;
        int idadeDependente;
        cout << "Nome do dependente " << i + 1 << ": ";
        cin >> nomeDependente;
        cout << "Idade do dependente " << i + 1 << ": ";
        cin >> idadeDependente;

        Dependente dependente(nomeDependente, idadeDependente);
        cliente.adicionarDependente(dependente);
    }
    return cliente;
}
// Função para vender um pacote a um cliente
void ctrl_pacote :: venderPacote(Cliente& cliente, PacoteTurismo pacote) {
    cliente.adicionarPacote(pacote);
}