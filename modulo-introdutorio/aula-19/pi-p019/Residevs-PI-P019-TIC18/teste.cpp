#include <iostream>
#include <vector>
#include <string>

class ItemSet {
private:
    std::vector<std::string> items;

public:
    // Construtor vazio
    ItemSet(){}

    // Construtor que recebe outro ItemSet como parâmetro
    ItemSet(const ItemSet& other) {
        // Copiar os itens do conjunto 'other' para o novo conjunto
        for (const std::string& item : other.items) {
            this->inserir(item);
        }
    }

    // Método para inserir um item no conjunto, caso ele não exista
    void inserir(const std::string& s) {
        // Verificar se o item já existe no conjunto
        for (const std::string& item : items) {
            if (item == s) {
                return; // Item já existe, não faz nada
            }
        }
        // Se o item não existir, adicioná-lo ao conjunto
        items.push_back(s);
    }

    // Método para excluir um item do conjunto, caso ele exista
    void excluir(const std::string& s) {
        for (auto it = items.begin(); it != items.end(); ++it) {
            if (*it == s) {
                items.erase(it); // Remove o item do conjunto
                return;
            }
        }
    }

    // Sobrecarga do operador de atribuição '='
    ItemSet& operator=(const ItemSet& other) {
        if (this == &other) {
            return *this; // Evitar auto-atribuição
        }

        // Limpar os itens do conjunto atual
        this->items.clear();

        // Copiar os itens do conjunto 'other' para o conjunto atual
        for (const std::string& item : other.items) {
            this->inserir(item);
        }

        return *this;
    }

    // Método para imprimir os itens do conjunto
    void imprimir() const {
        for (const std::string& item : items) {
            std::cout << item << " ";
        }
        std::cout << std::endl;
    }
};

int main() {
    // Teste da classe ItemSet

    ItemSet A;
    A.inserir("item1");
    A.inserir("item2");
    A.inserir("item3");

    ItemSet B;
    B.inserir("item2");
    B.inserir("item4");
    
    // ItemSet C(B);
    // std::cout << "Conjunto C: ";
    // C.imprimir();



    // Imprimir os conjuntos originais
    std::cout << "Conjunto A: ";
    A.imprimir();
    std::cout << "Conjunto B: ";
    B.imprimir();

    // Atribuir os itens de B a A
    A = B;

    // Imprimir os conjuntos após a atribuição
    std::cout << "Conjunto A (após atribuição): ";
    A.imprimir();

    // Excluir um item de A
    A.excluir("item2");

    // Imprimir A após exclusão
    std::cout << "Conjunto A (após exclusão): ";
    A.imprimir();

    return 0;
}
