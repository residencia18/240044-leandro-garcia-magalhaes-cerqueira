#include <iostream>
#include <limits> //A biblioteca "limits" serve para obtermos os valores limites de determinada variável.
#include <iomanip>  // Para std::setprecision, que funciona como um "setador" da precisão de determinada variável.
using namespace std;

int main(){

//a.
    std::cout << "a." << std::endl;
    float menor_valor = std::numeric_limits<float>::min();
    float maior_valor = std::numeric_limits<float>::max();
    std::cout << "Menor valor representável por int: " << menor_valor << std::endl;
    std::cout << "Maior valor representável por int: " << maior_valor << std::endl;
    std::cout << endl;

//b.
    std::cout << "b." << std:: endl;
    float pif = 3.14159265358979323846f;
    cout << "Valor de pi (float): " << pif << endl;

//c.
    std::cout << "c." << std:: endl;
        // Formatação com 2 casas decimais
    cout << "2 casas decimais: " << fixed << setprecision(2) << pif << endl;

    // Formatação com 4 casas decimais
    cout << "4 casas decimais: " << fixed << setprecision(4) << pif << endl;

    // Formatação com 8 casas decimais
    cout << "8 casas decimais: " << fixed << setprecision(8) << pif << endl;

    // Formatação com 16 casas decimais
    cout << "16 casas decimais: " << fixed << setprecision(16) << pif << endl;
    /*. Quando você usa fixed, ele faz com que os números sejam exibidos na notação
    de ponto fixo, em oposição à notação científica. Em notação de ponto fixo, os números
    são exibidos com uma quantidade fixa de casas decimais após o ponto decimal, o que pode ser
    útil quando você deseja que os números sejam exibidos com precisão e sem usar notação exponencial.*/

//d.
    std::cout << "d." << std:: endl;
    double pid = 3.14159265358979323846;
    cout << "Valor de pi (double): " << pid << endl;

//e.
    std::cout << "e." << std:: endl;
    cout << "2 casas decimais: " << fixed << setprecision(2) << pid << endl;

    // Formatação com 4 casas decimais
    cout << "4 casas decimais: " << fixed << setprecision(4) << pid << endl;

    // Formatação com 8 casas decimais
    cout << "8 casas decimais: " << fixed << setprecision(8) << pid << endl;

    // Formatação com 16 casas decimais
    cout << "16 casas decimais: " << fixed << setprecision(16) << pid << endl;
    /*Sim, houveram mudanças.*/

//f.
    std::cout << "f." << std:: endl;
    long double pil = 3.14159265358979323846;
    cout << "Valor de pi (long double): " << fixed << pil << endl;
    /* A saída foi idêntica ao do tipo double.*/









    














    return 0;
}