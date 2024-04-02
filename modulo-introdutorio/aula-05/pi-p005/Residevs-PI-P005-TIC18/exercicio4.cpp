#include <iostream>
#include <cmath>
#include <iomanip>

int main() {
    double x, y, z;

    // Solicita ao usuário para digitar dois números de ponto flutuante
    std::cout << "Digite o valor de x: ";
    std::cin >> x;
    std::cout << "Digite o valor de y: ";
    std::cin >> y;

    // Calcula o valor de f(x) = 5x + 2
    double fx = 5 * x + 2;

    // Verifica em qual lado da curva f(x) = 5x + 2 o ponto (x, y) se encontra
    if (y > fx) {
        std::cout << "O ponto (" << x << ", " << y << ") está acima da curva f(x) = 5x + 2" << std::endl;
    } else if (y < fx) {
        std::cout << "O ponto (" << x << ", " << y << ") está abaixo da curva f(x) = 5x + 2" << std::endl;
    } else {
        std::cout << "O ponto (" << x << ", " << y << ") está na curva f(x) = 5x + 2" << std::endl;
    }

    // Calcula a distância euclidiana do ponto (x, y) ao centro de coordenadas
    z = sqrt(x * x + y * y);

    // Calcula o produto entre x e y e imprime em notação científica
    double produto = x * y;
    std::cout << "O produto de x e y em notação científica: " << std::scientific << produto << std::endl;

    return 0;
}
