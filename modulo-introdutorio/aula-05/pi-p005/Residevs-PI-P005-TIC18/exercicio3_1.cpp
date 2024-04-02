#include <iostream>

int main() {
    int valor;
    std::cout << "Digite um número inteiro: ";
    std::cin >> valor;

    std::cout << "O valor fornecido é " << (valor > SHRT_MAX ? "maior que um short int." : "esse valor pertence ao intervalo dos short int.") << std::endl;

    return 0;
}