#include <iostream>
using namespace std;

int main() {
    int num, originalNum, reversedNum = 0, remainder;

    cout << "Digite um número: ";
    cin >> num;

    originalNum = num; // Salvar uma cópia do número original

    while (num > 0) {
        remainder = num % 10;    // Obter o último dígito do número
        reversedNum = reversedNum * 10 + remainder;  // Construir o número reverso
        num /= 10;  // Remover o último dígito do número
    }

    if (originalNum == reversedNum) {
        cout << originalNum << " é um palíndromo." << endl;
         cout << remainder <<endl;
         cout << num;
    } else {
        cout << originalNum << " não é um palíndromo." << endl;
        cout << remainder <<endl;
        cout << num;
    }

    return 0;
}
