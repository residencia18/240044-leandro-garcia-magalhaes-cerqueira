#include <iostream>

int main() {
    int n, termo1 = 0, termo2 = 1, proximoTermo;

    // Solicita ao usuário o número limite da sequência de Fibonacci
    std::cout << "Digite um número inteiro positivo para o limite da sequência de Fibonacci: ";
    std::cin >> n;

    // Verifica se o número fornecido é menor ou igual a 0
    if (n <= 0) {
        std::cout << "Por favor, digite um número inteiro positivo." << std::endl;
        return 1;  // Sai do programa com código de erro
    }

    // Imprime a sequência de Fibonacci até o número limite fornecido
    std::cout << "Sequência de Fibonacci até " << n << ":" << std::endl;

    while (termo1 <= n) {
        // Imprime o valor do termo atual
        std::cout << termo1 << " ";

        // Calcula o próximo termo da sequência de Fibonacci
        proximoTermo = termo1 + termo2;

        // Atualiza os termos anteriores
        termo1 = termo2;
        termo2 = proximoTermo;
    }

    std::cout << std::endl;

    return 0;  // Retorna 0 para indicar que o programa foi concluído com sucesso
}
