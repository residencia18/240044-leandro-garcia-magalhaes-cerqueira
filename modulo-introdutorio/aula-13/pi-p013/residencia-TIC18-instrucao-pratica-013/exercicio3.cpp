#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

// Estrutura para representar um produto
struct Produto {
    string codigo;
    string nome;
    double preco;
};

// Função para inserir um novo produto
void inserirProduto(Produto produtos[], int &totalProdutos) {
    if (totalProdutos >= 300) {
        cout << "Limite de produtos atingido." << endl;
        return;
    }

    Produto novoProduto;
    cout << "Digite o código do produto (13 caracteres numéricos): ";
    cin >> novoProduto.codigo;

    // Verifica se o código já está em uso
    for (int i = 0; i < totalProdutos; i++) {
        if (produtos[i].codigo == novoProduto.codigo) {
            cout << "Código já em uso. Cadastro não permitido." << endl;
            return;
        }
    }

    cout << "Digite o nome do produto (até 20 caracteres): ";
    cin.ignore();
    getline(cin, novoProduto.nome);

    cout << "Digite o preço do produto (duas casas decimais): ";
    cin >> novoProduto.preco;

    produtos[totalProdutos++] = novoProduto;
    cout << "Produto cadastrado com sucesso." << endl;
}

// Função para excluir um produto pelo código
void excluirProduto(Produto produtos[], int &totalProdutos) {
    string codigoParaExcluir;
    cout << "Digite o código do produto a ser excluído: ";
    cin >> codigoParaExcluir;

    for (int i = 0; i < totalProdutos; i++) {
        if (produtos[i].codigo == codigoParaExcluir) {
            // Move o último produto para a posição do produto a ser excluído
            produtos[i] = produtos[--totalProdutos];
            cout << "Produto excluído com sucesso." << endl;
            return;
        }
    }

    cout << "Produto não encontrado." << endl;
}

// Função para listar todos os produtos
void listarProdutos(const Produto produtos[], int totalProdutos) {
    cout << "Lista de produtos:" << endl;
    for (int i = 0; i < totalProdutos; i++) {
        cout << "Código: " << produtos[i].codigo << ", Nome: " << produtos[i].nome << ", Preço: R$ " << fixed << setprecision(2) << produtos[i].preco << endl;
    }
}

// Função para consultar o preço de um produto pelo código
void consultarPreco(const Produto produtos[], int totalProdutos) {
    string codigoParaConsultar;
    cout << "Digite o código do produto para consulta: ";
    cin >> codigoParaConsultar;

    for (int i = 0; i < totalProdutos; i++) {
        if (produtos[i].codigo == codigoParaConsultar) {
            cout << "Preço de " << produtos[i].nome << ": R$ " << fixed << setprecision(2) << produtos[i].preco << endl;
            return;
        }
    }

    cout << "Produto não encontrado." << endl;
}

int main() {
    Produto produtos[300];
    int totalProdutos = 0;
    int escolha;

    do {
        cout << "Menu:" << endl;
        cout << "1. Inserir um novo produto" << endl;
        cout << "2. Excluir um produto cadastrado" << endl;
        cout << "3. Listar todos os produtos" << endl;
        cout << "4. Consultar o preço de um produto" << endl;
        cout << "5. Sair" << endl;
        cout << "Escolha uma opção: ";
        cin >> escolha;

        switch (escolha) {
            case 1:
                inserirProduto(produtos, totalProdutos);
                break;
            case 2:
                excluirProduto(produtos, totalProdutos);
                break;
            case 3:
                listarProdutos(produtos, totalProdutos);
                break;
            case 4:
                consultarPreco(produtos, totalProdutos);
                break;
            case 5:
                cout << "Encerrando o programa." << endl;
                break;
            default:
                cout << "Opção inválida. Tente novamente." << endl;
        }
    } while (escolha != 5);

    return 0;
}
