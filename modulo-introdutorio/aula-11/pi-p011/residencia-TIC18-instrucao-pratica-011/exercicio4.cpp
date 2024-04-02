#include <iostream>

void calcular(int& X, int& Y) {
    int soma = X + Y;
    int subtracao = X - Y;

    X = soma;
    Y = subtracao;
}

int main() {
    int X, Y;

    std::cout << "Digite o valor de X: ";
    std::cin >> X;

    std::cout << "Digite o valor de Y: ";
    std::cin >> Y;

    calcular(X, Y);

    std::cout << "A soma de X e Y é: " << X << std::endl;
    std::cout << "A subtração de X e Y é: " << Y << std::endl;

    return 0;
}
