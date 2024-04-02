#include <iostream>
#include <string>
#include <vector>

using namespace std;

class ItemSet
{
private:
    vector<string> listaItens;
    bool itemCadastrado(string s);

public:
    ItemSet();
    ItemSet(const ItemSet &b);
    ~ItemSet();
    vector<string> getListaItens();
    void setListaItens(vector<string> _listaItens);
    void inserir(string s);
    void excluir(string s);

    ItemSet operator+(ItemSet c);
    ItemSet &operator=(const ItemSet &b);
    ItemSet operator*(ItemSet c);

    
};

ItemSet::ItemSet() {}

// Construtor que recebe outro ItemSet como parâmetro
ItemSet::ItemSet(const ItemSet &b)
{
    // Copiar os itens do conjunto 'other' para o novo conjunto
    for (const std::string &item : b.listaItens)
    {
        this->inserir(item);
    }
}

// Sobrecarga do operador de atribuição '='
ItemSet &ItemSet::operator=(const ItemSet &b)
{
    if (this == &b)
    {
        return *this; // Evitar auto-atribuição
    }

    // Limpar os itens do conjunto atual
    this->listaItens.clear();

    // Copiar os itens do conjunto 'other' para o conjunto atual
    for (const std::string &item : b.listaItens)
    {
        this->inserir(item);
    }

    return *this;
}

ItemSet::~ItemSet()
{
}

vector<string> ItemSet::getListaItens()
{
    return listaItens;
}

void ItemSet::setListaItens(vector<string> _listaItens)
{
    listaItens = _listaItens;
}

bool ItemSet::itemCadastrado(string _s)
{
    for (string s : listaItens)
    {
        if (s == _s)
        {
            return true;
        }
    }

    return false;
}

void ItemSet::inserir(string s)
{
    int tam = listaItens.size();

    if (tam == 0 || !itemCadastrado(s))
    {
        listaItens.push_back(s);
    }
}

void ItemSet::excluir(string s)
{
    int tam = listaItens.size();

    if (tam >= 0 && itemCadastrado(s))
    {
        for (int i = 0; i < tam; i++)
        {
            if (listaItens[i] == s)
            {
                listaItens.erase(listaItens.begin() + i);
                break;
            }
        }
    }
}

ItemSet ItemSet::operator+(ItemSet c)
{
    ItemSet a, b;
    bool inserir;
    a.setListaItens(listaItens);

    for (string itemC : c.getListaItens())
    {
        inserir = true;

        for (string itemB : listaItens)
        {
            if (itemC == itemB)
            {
                inserir = false;
                break;
            }
        }

        if (inserir)
        {
            a.inserir(itemC);
        }
    }

    return a;
}

ItemSet ItemSet::operator*(ItemSet c){
    ItemSet b, interssecao;
    vector<string> itensU;
    
    b.setListaItens(listaItens);

    for (string itemB : listaItens)
    {
        for (string itemC : c.listaItens)
        {
            if (itemB == itemC)
            itensU.push_back(itemC);
        }
    }

    interssecao.setListaItens(itensU);

    return interssecao;
}


int main(void)
{
    ItemSet itemA, itemB, itemC;

    vector<string> listaItensB = {"1", "2", "3", "4", "5"};
    vector<string> listaItensC = {"8", "7", "6", "5", "4", "3"};

    itemB.setListaItens(listaItensB);
    itemC.setListaItens(listaItensC);


    cout << "\nB =\t";
    for (string s : itemB.getListaItens())
    {
        cout << s << "\t";
    }

    cout << "\n\nC =\t";
    for (string s : itemC.getListaItens())
    {
        cout << s << "\t";
    }

    itemA = itemB;
    cout << "\n\nA = B =>\t";
    for (string s : itemA.getListaItens())
    {
        cout << s << "\t";
    }

    //'A' recebe todos os itens de 'B' e os itens de 'C' que não se repetem em 'B'.
    itemA = itemB + itemC;
    cout << "\n\nA = B + c =>\t";
    for (string s : itemA.getListaItens())
    {
        cout << s << "\t";
    }

    //A recebe os itens de B que ocorrem também em C.
    itemA = itemB * itemC;
    cout << "\n\nA = B * C =>\t";
    for (string s : itemA.getListaItens())
    {
        cout << s << "\t";
    }

    cout << endl
         << endl;
    return 0;
}