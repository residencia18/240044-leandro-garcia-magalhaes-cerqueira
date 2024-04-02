#include <iostream>
#include <cmath> // Para usar a função "abs()", que serve para calcular o valor absoluto.

using namespace std;

int main (){

//a.
    int a, b, c;

//b.
    std::cout << "b." << std:: endl;
    std::cout << "insira um número inteiro: " << std:: endl;
    cin >> a;
    std::cout << "Agora insira outro número inteiro: " << std:: endl;
    cin >> b;
    cout << endl;

//c.
    std::cout << "c." << std:: endl;
    c = a + b;
    cout << hex << static_cast<int>(c) << endl;
    cout << endl;

//d.
    std::cout << "d." << std:: endl;
    c = a * b;
    cout << oct << static_cast<int>(c) << endl;
    cout << endl;

//e.
    std::cout << "e." << std:: endl;
    c = abs(a - b);
    cout << c << endl;
    cout << endl;

//f.
    std::cout << "f." << std:: endl;
    //Sim, existe. Com a biblioteca <math.h>

//g.
    std::cout << "g." << std:: endl;
    if(b == 0){
        std:: cout << "A divisão não é possível." << std:: endl;
    } else {
        c = a / b;
    }
//h.
    std::cout << "h." << std:: endl;
    if(a%b == 0){
        std:: cout << "A é divisível de forma exata por B." << std:: endl;
        std:: cout << c << std:: endl;
    }

    

    
























    return 0;
}