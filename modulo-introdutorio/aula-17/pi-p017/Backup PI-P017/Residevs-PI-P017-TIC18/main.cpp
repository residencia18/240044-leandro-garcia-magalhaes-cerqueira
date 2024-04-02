#include <iostream>
#include <vector>
#include <string>

using namespace std;

#define TRACO "------------------------------------------------"

class Produto {
private:
    int codigo;
    string nome;
    double preco;

public:
    Produto() {}
    Produto(string _nome, double _preco);
    Produto(int _codigo, string _nome, double _preco);
    int getCodigo();
    string getNome();
    double getPreco();
    void setPreco(double _preco);
    void setCodigo(int _codigo) {
        codigo = _codigo;
    }
};

Produto::Produto(string _nome, double _preco) {
    nome = _nome;
    preco = _preco;
};

int Produto::getCodigo() {
    return codigo;
}

string Produto::getNome() {
    return nome;
}

double Produto::getPreco() {
    return preco;
}

void Produto::setPreco(double _preco) {
    preco = _preco;
}

class Estoque {
private:
    static int codigo_atual;
    static vector<Produto> itens_em_estoque;
    static vector<int> qtd_em_estoque;

public:
    Estoque() {}
    static int buscaItem_em_estoque(string nome_a_buscar) {
        for (int i = 0; i < itens_em_estoque.size(); ++i) {
            if (itens_em_estoque[i].getNome() == nome_a_buscar) {
                return i;
            }
        }
        return -1;
    }

    static void cadastrarProduto(Produto& novoItem, int qtd) {
        if (buscaItem_em_estoque(novoItem.getNome()) != -1) {
            adicionarProduto(novoItem, qtd);
            return;
        }
        codigo_atual++;
        novoItem.setCodigo(codigo_atual);

        itens_em_estoque.push_back(novoItem);
        qtd_em_estoque.push_back(qtd);
    }

    static void adicionarProduto(Produto& novoItem, int qtd) {
        int posicao_do_item = buscaItem_em_estoque(novoItem.getNome());

        if (posicao_do_item != -1) {
            qtd_em_estoque[posicao_do_item] += qtd;
        } else {
            // Se não estiver cadastrado, cadastre o produto
            codigo_atual++;
            novoItem.setCodigo(codigo_atual);

            itens_em_estoque.push_back(novoItem);
            qtd_em_estoque.push_back(qtd);
        }
    }

    static bool removerProduto(Produto& Item, int qtd) {
        int posicao_do_item = buscaItem_em_estoque(Item.getNome());

        if (posicao_do_item != -1) {
            int quantidade = qtd_em_estoque[posicao_do_item];

            if ((quantidade - qtd) >= 0) {
                qtd_em_estoque[posicao_do_item] -= qtd;
                return true;
            } else {
                return false;
            }
        }

        return false; // Produto não encontrado
    }

    static void listarEstoque() {
        cout << "id  " << "Nome  " << "Preço  " << "Qtd" << endl;
        for (int i = 0; i < itens_em_estoque.size(); ++i) {
            cout << "#" << itens_em_estoque[i].getCodigo() << "  " << itens_em_estoque[i].getNome() << "  " << itens_em_estoque[i].getPreco() << " x " << qtd_em_estoque[i] << std::endl;
        }
    }

    // Adicione um método para adicionar um produto diretamente ao estoque
    static void adicionarProdutoAoEstoque(Produto& novoItem, int qtd) {
        int posicao_do_item = buscaItem_em_estoque(novoItem.getNome());

        if (posicao_do_item != -1) {
            qtd_em_estoque[posicao_do_item] += qtd;
        } else {
            codigo_atual++;
            novoItem.setCodigo(codigo_atual);

            itens_em_estoque.push_back(novoItem);
            qtd_em_estoque.push_back(qtd);
        }
    }
};

int Estoque::codigo_atual = 0;
vector<Produto> Estoque::itens_em_estoque;
vector<int> Estoque::qtd_em_estoque;

class CarrinhoDeCompras {
private:
    vector<Produto> carrinho;
    vector<int> quantidade;

public:
    CarrinhoDeCompras() {}

    bool adicionarProduto(Produto& _produto, int _quantidade) {
        int posicao_do_item = Estoque::buscaItem_em_estoque(_produto.getNome());

        if (posicao_do_item != -1 && Estoque::removerProduto(_produto, _quantidade)) {
            carrinho.push_back(_produto);
            quantidade.push_back(_quantidade);
            return true;
        }

        return false;
    }

    bool removerProduto(Produto& _produto, int _quantidade) {
        int tamanho = carrinho.size();

        for (int i = 0; i < tamanho; i++) {
            if (carrinho[i].getNome() == _produto.getNome()) {
                if (quantidade[i] >= _quantidade) {
                    quantidade[i] -= _quantidade;

                    // Adicione o produto diretamente de volta ao estoque
                    Estoque::adicionarProdutoAoEstoque(_produto, _quantidade);

                    if (quantidade[i] == 0) {
                        carrinho.erase(carrinho.begin() + i);
                        quantidade.erase(quantidade.begin() + i);
                    }

                    return true;
                } else {
                    return false;
                }
            }
        }

        cout << "Produto não encontrado no carrinho." << endl;
        return false;
    }

    double calcularValorTotal() {
        double total = 0;

        // Certifique-se de que ambas as listas tenham o mesmo tamanho
        if (carrinho.size() != quantidade.size()) {
            // Trate o erro aqui, por exemplo, lançando uma exceção
            throw runtime_error("Tamanho das listas carrinho e quantidade não corresponde.");
        }

        // Agora, percorra os elementos do carrinho e calcule o total
        for (int i = 0; i < carrinho.size(); i++) {
            total += carrinho[i].getPreco() * quantidade[i];
        }

        return total;
    }

    void exibirCarrinho() {
        cout << endl << "Carrinho de Compras:" << endl;
        for (int i = 0; i < carrinho.size(); i++) {
            cout << "-" << carrinho[i].getNome() << " (" << carrinho[i].getPreco() << ") x " << quantidade[i] << endl;
        }
        cout << endl;
    }
};

int main() {
    Produto p1("Maçã", 2.5);
    Produto p2("Arroz", 10.0);
    Produto p3("Leite", 4.0);
    Produto p4("Café", 8.99);

    Estoque::cadastrarProduto(p1, 10);
    Estoque::cadastrarProduto(p2, 10);
    Estoque::cadastrarProduto(p3, 10);
    Estoque::cadastrarProduto(p4, 10);

    cout << "Itens disponíveis no estoque:" << endl;
    Estoque::listarEstoque();

    CarrinhoDeCompras carrinho;
    carrinho.adicionarProduto(p1, 3);
    carrinho.adicionarProduto(p2, 2);
    carrinho.adicionarProduto(p3, 3);

    double valor = carrinho.calcularValorTotal();

    carrinho.exibirCarrinho();
    cout << "Total: " << valor << endl;

    cout << "Itens disponíveis no estoque após adicionar ao carrinho:" << endl;
    Estoque::listarEstoque();

    carrinho.removerProduto(p3, 1);
    carrinho.exibirCarrinho();
    valor = carrinho.calcularValorTotal();
    cout << "Total: " << valor << endl;
    cout << "Itens disponíveis no estoque após remover do carrinho:" << endl;
    Estoque::listarEstoque();

    return 0;
}
