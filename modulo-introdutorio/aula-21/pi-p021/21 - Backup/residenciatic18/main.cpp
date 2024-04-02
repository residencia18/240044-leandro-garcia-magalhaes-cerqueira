#include <iostream>
#include "include/interface.h"
#include "include/ctrl_biblioteca.h"

#define TRACO "-------------------------------------------------"

using namespace std;
int main()
{

    int opcao;
    vector<string> menu = {
        "-------------------------------------------------",
        "[1] Adicionar livros na biblioteca",
        "[2] Registrar empréstimos de livros",
        "[3] Verificar a disponibilidade de um livro",
        "[4] Listar os livros emprestados (usuário específico)",
        "[5] Sair."};

    while (true)
    {
        cout << TRACO << endl;
        opcao = monta_menu(menu, "BIBLIOTECA - GERENCIAMENTO");

        cout<<endl;

        switch (opcao)
        {
        case 1:
            ctrl_biblioteca::insere_livro();
            break;
        case 2:
            ctrl_biblioteca::registrarEmprestimo();
            break;
        case 3:
            ctrl_biblioteca::verificarDisponibilidadeDelivroBiblioteca();
            break;
        case 4:
            ctrl_biblioteca::exibirTodosEmprestimosDoUsuario();
            break;
        case 5:
            cout << "Programa Finalizado.";
            exit(0);

        default:
            cout << "Opção invalida!" << endl;
            break;
        }
    }
}