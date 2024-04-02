#include <iostream>

int main() {
    double x, y;

    // Solicitar ao usuário as coordenadas (x, y)
    std::cout << "Digite o valor de x: ";
    std::cin >> x;
    std::cout << "Digite o valor de y: ";
    std::cin >> y;

    // Determinar o quadrante usando operadores ternários
    std::string quadrante = (x == 0) ? ((y == 0) ? "sobre a origem" : "sobre o eixo y") : (y == 0) ? "sobre o eixo x" : (x > 0) ? ((y > 0) ? "primeiro quadrante" : "quarto quadrante") : ((y > 0) ? "segundo quadrante" : "terceiro quadrante");

    // Exibir o resultado
    std::cout << "O ponto (" << x << ", " << y << ") está no " << quadrante << "." << std::endl;

    return 0;
}
