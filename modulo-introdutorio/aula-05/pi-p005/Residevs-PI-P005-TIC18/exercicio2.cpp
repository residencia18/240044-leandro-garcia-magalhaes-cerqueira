#include <iostream>
#include <cctype>

using namespace std;

int main(){

//a.
    char ch1, ch2, ch3;

//b.
    std::cout << "b." << std:: endl;
    std::cout << "Insira um caractere: ";
    cin >> ch1;

//c.
    std::cout << "c." << std:: endl;
    isupper(ch1) ? (cout << "É maiúsculo."):(cout << "Não é maiúsculo.");
    cout << endl;
    islower(ch1) ? (cout << "É minúsculo."):(cout << "Não é minúsculo.");
    cout << endl;
    isdigit(ch1) ? (cout << "É um digito."):(cout << "É outro tipo de caractere.");
    cout << endl;

//d.
    std::cout << "d." << std:: endl;
    //Sim, existe. Utilizando a biblioteca padrão "cctype";

//e.
    std::cout << "e." << std:: endl;
    ch2 = 81;
    // Imprimindo o valor em formato decimal
    cout << "Em decimal: " << static_cast<int>(ch2) << endl;

    // Imprimindo o valor em formato octal
    cout << "Em octal: " << oct << static_cast<int>(ch2) << endl;

    // Imprimindo o valor em formato hexadecimal
    cout << "Em hexadecimal: " << hex << static_cast<int>(ch2) << endl;

    // Imprimindo o valor como caractere
    cout << "Em formato de caractere: " << ch2 << endl;

//f.
    std::cout << "f." << std:: endl;

    if (isupper(ch2)){
        ch3 = tolower(ch2); // tolower serve para transformar um caractere maiúsculo em minúsculo.
    cout << "Em formato de caractere: " << ch3 << endl;

       // Imprimindo o valor em formato decimal
    cout << "Em decimal: " << static_cast<int>(ch3) << endl;

    // Imprimindo o valor em formato octal
    cout << "Em octal: " << oct << static_cast<int>(ch3) << endl;

    // Imprimindo o valor em formato hexadecimal
    cout << "Em hexadecimal: " << hex << static_cast<int>(ch3) << endl;

    // Imprimindo o valor como caractere
    cout << "Em formato de caractere: " << ch3 << endl;


    }







    










    return 0;
}