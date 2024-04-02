#include <iostream>
#include <math.h>
#include <cctype>
#include <iomanip> //lib que possui a função setprecision();

using namespace std;

int main(){

//a.
    std:: cout << "a):" << std:: endl;
    double raio,diametro,perimetro,area;

    std:: cout << "Por favor, insira o valor do raio do círculo: ";
    std:: cin >> raio;
    std:: cout << endl ;

    diametro = 2 * raio; // calcula o diametro do circulo com base no valor do raio.
    std::cout << std::fixed << std::setprecision(2);
    std:: cout << "Diâmetro: " << diametro << endl ;


//b.
    std:: cout << "b):" << std:: endl;
    perimetro = 2 * M_PI * raio; // calcula o perímetro do circulo.
    std::cout << std::fixed << std::setprecision(2);
    std:: cout << "Perímetro: " << perimetro << endl ;

//c.
    std:: cout << "c):" << std:: endl;
    area = M_PI * pow(raio,2); // calcula a área do circulo.
    std::cout << std::fixed << std::setprecision(2);
    std:: cout << "Área: " << area << endl ;






















    return 0;

}