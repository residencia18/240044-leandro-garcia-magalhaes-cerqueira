#include <iostream>
#include <limits> //A biblioteca "limits" serve para obtermos os valores limites de determinada variável.

using namespace std;

int main(){

//a.
    std::cout << "a." << std::endl;
    int menor_valor = std::numeric_limits<int>::min();
    int maior_valor = std::numeric_limits<int>::max();
    std::cout << "Menor valor representável por int: " << menor_valor << std::endl;
    std::cout << "Maior valor representável por int: " << maior_valor << std::endl;
    std::cout << endl;

//b.
    std::cout << "b." << std::endl;
    unsigned long int uli_menor_valor = std::numeric_limits<unsigned long int>::min();
    unsigned long int uli = std::numeric_limits<unsigned long int>::max();
    std::cout << "Maior valor representável por unsigned long int é: " << uli << std::endl;
    std::cout << "E o menor valor representável por unsigned long int é: " << uli_menor_valor << std::endl;
    std::cout << endl;
    /**Variáveis do tipo "unsigned" ou "não assinadas" representam apenas valores inteiros não negativos.
    portanto, seus menor valor possível sempre será 0 **/

//c.
    std::cout << "c." << std::endl;
    long int li = uli;
    uli = li;
    std::cout << uli << std::endl;
    std::cout << endl;
    /**Ambos os resultados foram 4294967295. Isso ocorre porque, em C++ (e também em C),
    quando você tenta atribuir um valor maior que o valor máximo permitido para um tipo de dado,
    ocorre uma operação de "overflow silencioso". Em vez de lançar um erro ou gerar um valor negativo,
    o compilador simplesmente ajusta o valor para o maior valor possível que pode ser representado pelo
    tipo de dado.*/

//d.
    std::cout << "d." << std::endl;
     li = std::numeric_limits<long int>::max();
     long int li_menor_valor = std::numeric_limits<long int>::min();
    std::cout << li << std::endl;
    std::cout << "Menor valor possível: " << li_menor_valor << std::endl;
    std::cout << endl;

//e.
    std::cout << "e." << std::endl;  
    unsigned int ui;
    ui = li;
    li = ui;
    std::cout << li << std::endl;
    std::cout << endl;


//f.
     std::cout << "f." << std::endl;
     ui = std::numeric_limits<unsigned int>::max(); 
     unsigned int ui_menor_valor = std::numeric_limits<unsigned int>::min();
    std::cout << ui << std::endl;
    std::cout << "Menor valor possível: " << ui_menor_valor << std::endl;
    std::cout << endl;
    


return 0;

}