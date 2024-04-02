#include <iostream>

// Função para trocar dois valores
void trocar(float &a, float &b) {
    float temp = a;
    a = b;
    b = temp;
}

// Função para ordenar quatro variáveis float em ordem crescente
void ordenar(float &a, float &b, float &c, float &d) {
    if (a > b) {
        trocar(a, b);
    }
    if (b > c) {
        trocar(b, c);
    }
    if (c > d) {
        trocar(c, d);
    }
    if (a > b) {
        trocar(a, b);
    }
    if (b > c) {
        trocar(b, c);
    }
    if (a > b) {
        trocar(a, b);
    }
}

int main() {
    float num1, num2, num3, num4;

    // Solicite ao usuário para inserir os quatro números
    std::cout << "Digite quatro números float separados por espaços: ";
    std::cin >> num1 >> num2 >> num3 >> num4;

    // Chame a função para ordenar os números
    ordenar(num1, num2, num3, num4);

    // Exiba os números ordenados
    std::cout << "Números ordenados: " << num1 << " " << num2 << " " << num3 << " " << num4 << std::endl;

    return 0;
}
