#include <iostream>
#include <math.h>

using namespace std;

int main(){

//a.
    std:: cout << "a):" << std:: endl;
    int a,b,c, expressao;

//b.
    std:: cout << "b):" << std:: endl;
    std:: cout << "Insira um número inteiro: ";
    std:: cin >> a;
    std:: cout << "Agora insira outro número inteiro: " << std:: endl;
    std:: cin >> b;
    cout << endl;

//c e d.
    std:: cout << "c) e d):" << std:: endl;
    c = 4 * a + b / 3 - 5;
    std:: cout << c << std:: endl;
    cout << endl;

//e.
    std:: cout << "e):" << std:: endl;
    c = 4 * (a + b) / (3 - 5);
    std:: cout << c << std:: endl;
    cout << endl;

//f.
    std:: cout << "f):" << std:: endl;
    /*O resultado das expressões nos dois casos foram diferentes porque o programa precisa
    de uma maior definição de como deve realizar a operação, ou seja, precisa-se introduzir parênteses
    caso contrário ele irá seguir a ordem das operações matemáticas e imprimir o resultado diferente do esperado. */
    std:: cout << "Exemplo: " << std:: endl;
    c = 4 * a + (b / 3) - 5;
    std:: cout << c << std:: endl;
    cout << endl;
//g.
    std:: cout << "g):" << std:: endl;
    expressao = pow(a,2) + 2 * b + c;
    std:: cout << expressao << std:: endl;
    










    cout << 1/3 << endl;
    cout << 5/3 << endl;


    














    return 0;
}