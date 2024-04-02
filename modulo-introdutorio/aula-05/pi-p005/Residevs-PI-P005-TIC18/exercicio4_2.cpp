#include <iostream>
#include <cmath>

int main() {
    double a, b, c;

    // Solicita ao usuário para digitar os coeficientes a, b e c
    std::cout << "Digite o coeficiente a: ";
    std::cin >> a;
    std::cout << "Digite o coeficiente b: ";
    std::cin >> b;
    std::cout << "Digite o coeficiente c: ";
    std::cin >> c;

    // Calcula o discriminante (Δ) para determinar o número de raízes reais
    double discriminante = b * b - 4 * a * c;

    if (discriminante > 0) {
        // Duas raízes reais distintas
        double raiz1 = (-b + sqrt(discriminante)) / (2 * a);
        double raiz2 = (-b - sqrt(discriminante)) / (2 * a);
        std::cout << "O polinômio tem duas raízes reais distintas." << std::endl;
        std::cout << "Raiz 1: " << raiz1 << std::endl;
        std::cout << "Raiz 2: " << raiz2 << std::endl;
    } else if (discriminante == 0) {
        // Uma raiz real (raiz dupla)
        double raiz = -b / (2 * a);
        std::cout << "O polinômio tem uma raiz real (raiz dupla)." << std::endl;
        std::cout << "Raiz: " << raiz << std::endl;
    } else {
        // Nenhuma raiz real
        std::cout << "O polinômio não possui raízes reais." << std::endl;
    }

    // Solicita ao usuário para digitar um valor para x
    double x;
    std::cout << "Digite um valor para x: ";
    std::cin >> x;

    // Calcula p(x)
    double px = a * x * x + b * x + c;

    std::cout << "O valor de p(x) é: " << px << std::endl;

    return 0;
}