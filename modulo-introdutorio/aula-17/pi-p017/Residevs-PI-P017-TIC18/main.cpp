#include <iostream>
#include <vector>
#include <string>

using namespace std;

#define TRACO "------------------------------------------------"

class Produto
{
private:
    int codigo;
    string nome;
    double preco;

public:
    Produto() : codigo(0), nome(""), preco(0.0) {}

    Produto(string _nome, double _preco) : codigo(0), nome(_nome), preco(_preco) {}

    Produto(int _codigo, string _nome, double _preco) : codigo(_codigo), nome(_nome), preco(_preco) {}

    int getCodigo() const { return codigo; }

    string getNome() const { return nome; }

    double getPreco() const { return preco; }

    void setNome(string _nome) { nome = _nome; }

    void setPreco(double _preco) { preco = _preco; }

    void setCodigo(int _codigo) { codigo = _codigo; }
};

class Estoque
{
private:
    int codigo_atual;
    vector<Produto> itens_em_estoque;
    vector<int> qtd_em_estoque;

public:
    Estoque() : codigo_atual(0) {}

    int buscaItem_em_estoque(const string &nome_a_buscar) const
    {
        for (int i = 0; i < itens_em_estoque.size(); ++i)
        {
            if (itens_em_estoque[i].getNome() == nome_a_buscar)
            {
                return i;
            }
        }
        return -1;
    }

    void cadastrarProduto(Produto novoItem, int qtd)
    {
        if (buscaItem_em_estoque(novoItem.getNome()) != -1)
        {
            adicionarProduto(novoItem, qtd);
            return;
        }
        codigo_atual++;
        novoItem.setCodigo(codigo_atual);

        itens_em_estoque.push_back(novoItem);
        qtd_em_estoque.push_back(qtd);
    }

    void adicionarProduto(Produto novoItem, int qtd)
    {
        int posicao_do_item = buscaItem_em_estoque(novoItem.getNome());

        if (posicao_do_item != -1)
        {
            qtd_em_estoque[posicao_do_item] += qtd;
        }
        else
        {
            // Se não estiver cadastrado, cadastre o produto
            codigo_atual++;
            novoItem.setCodigo(codigo_atual);

            itens_em_estoque.push_back(novoItem);
            qtd_em_estoque.push_back(qtd);
        }
    }

    bool removerProduto(const Produto &Item, int qtd)
    {
        int posicao_do_item = buscaItem_em_estoque(Item.getNome());

        if (posicao_do_item != -1)
        {
            int quantidade = qtd_em_estoque[posicao_do_item];

            if ((quantidade - qtd) >= 0)
            {
                qtd_em_estoque[posicao_do_item] -= qtd;
                return true;
            }
            else
            {
                return false;
            }
        }

        return false; // Produto não encontrado
    }

    void listarEstoque() const
    {
        cout << "id  "
             << "Nome  "
             << "Preço  "
             << "Qtd" << endl;
        for (int i = 0; i < itens_em_estoque.size(); ++i)
        {
            cout << "#" << itens_em_estoque[i].getCodigo() << "  " << itens_em_estoque[i].getNome() << "  " << itens_em_estoque[i].getPreco() << " x " << qtd_em_estoque[i] << std::endl;
        }
    }
};

class CarrinhoDeCompras
{
private:
    vector<Produto> carrinho;
    vector<int> quantidade;

public:
    CarrinhoDeCompras() {}

    bool adicionarProduto(const Produto &_produto, int _quantidade, Estoque &estoque)
    {
        int posicao_do_item = estoque.buscaItem_em_estoque(_produto.getNome());

        if (posicao_do_item != -1 && estoque.removerProduto(_produto, _quantidade))
        {
            carrinho.push_back(_produto);
            quantidade.push_back(_quantidade);
            cout << "Produto adicionado ao carrinho com sucesso!" << endl;
            return true;
        }
        cout << "Produto não encontrado no estoque." << endl;
        return false;
    }

    bool removerProduto(const Produto &_produto, int _quantidade, Estoque &estoque)
    {
        int tamanho = carrinho.size();

        for (int i = 0; i < tamanho; i++)
        {
            if (carrinho[i].getNome() == _produto.getNome())
            {
                if (quantidade[i] >= _quantidade)
                {
                    quantidade[i] -= _quantidade;

                    // Adicione o produto diretamente de volta ao estoque
                    estoque.adicionarProduto(_produto, _quantidade);

                    if (quantidade[i] == 0)
                    {
                        carrinho.erase(carrinho.begin() + i);
                        quantidade.erase(quantidade.begin() + i);
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }

        cout << "Produto não encontrado no carrinho." << endl;
        return false;
    }

    double calcularValorTotal(const Estoque &estoque) const
    {
        double total = 0;

        // Certifique-se de que ambas as listas tenham o mesmo tamanho
        if (carrinho.size() != quantidade.size())
        {
            // Trate o erro aqui, por exemplo, lançando uma exceção
            throw runtime_error("Tamanho das listas carrinho e quantidade não corresponde.");
        }

        // Agora, percorra os elementos do carrinho e calcule o total
        for (int i = 0; i < carrinho.size(); i++)
        {
            total += carrinho[i].getPreco() * quantidade[i];
        }

        return total;
    }

    void exibirCarrinho() const
    {
        cout << endl
             << "Carrinho de Compras:" << endl;
        for (int i = 0; i < carrinho.size(); i++)
        {
            cout << "-" << carrinho[i].getNome() << " (R$ " << carrinho[i].getPreco() << ") x " << quantidade[i] << endl;
        }
        cout << endl;
    }
};

void exibirOpcoesMenu()
{
    cout << TRACO << "\nSUPERMERCADO MEIRA - SISTEMA DE GERENCIAMENTO \n\n"
         << "[1] Cadastrar produto ao estoque; \n"
         << "[2] Adicionar produto ao carrinho; \n"
         << "[3] Remover produto do carrinho; \n"
         << "[4] Calcular valor total; \n"
         << "[5] Listar produtos no estoque; \n"
         << "[0] Sair do programa. \n"
         << endl;
}

int main()
{
    int opcao_usuario = 0, quantidade;
    Produto produto;
    Estoque estoque;
    string nome;
    double preco;
    CarrinhoDeCompras carrinho;

    do
    {
        exibirOpcoesMenu();
        cout << "Opção: ";
        cin >> opcao_usuario;

        switch (opcao_usuario)
        {
        case 0: // Sair do programa.
        {
            cout << "Aviso: Fim do programa." << endl;
            break;
        }
        case 1: // Cadastro do produto ao estoque
        {
            cout << "Nome do produto: ";
            cin.ignore();
            getline(cin, nome);

            cout << "Preço: ";
            cin >> preco;
            cout << "Quantidade: ";
            cin >> quantidade;

            produto.setNome(nome);
            produto.setPreco(preco);
            estoque.cadastrarProduto(produto, quantidade);

            break;
        }
        case 2: // Adiciona produto ao carrinho
        {
            cout << "Nome do produto: ";
            cin.ignore();
            getline(cin, nome);

            cout << "Quantidade: ";
            cin >> quantidade;

            produto.setNome(nome);
            carrinho.adicionarProduto(produto, quantidade, estoque);
            carrinho.exibirCarrinho();
            break;
        }
        case 3: // Remove produto do carrinho
        {
            cout << "Nome do produto: ";
            cin.ignore();
            getline(cin, nome);

            cout << "Quantidade: ";
            cin >> quantidade;

            produto.setNome(nome);
            carrinho.removerProduto(produto, quantidade, estoque);
            carrinho.exibirCarrinho();
            break;
        }
        case 4: // Calcula valor total da compra
        {
            double valor = carrinho.calcularValorTotal(estoque);
            carrinho.exibirCarrinho();
            cout << "Total: " << valor << endl;
            break;
        }
        case 5: // Lista produtos do estoque
        {
            cout << "Itens disponíveis no estoque:" << endl;
            estoque.listarEstoque();
            break;
        }
        default:
        {
            cout << "Aviso: Insira um número entre 0 e 5." << endl;
            break;
        }
        };
    } while (opcao_usuario != 0);

    return 0;
}
