#include <iostream>

using namespace std;

int main (){

//a.
    std::cout << "a." << std::endl;

    /*Variáveis booleanas funcionam da seguinte forma, valores diferentes de 0 são true
    e valores iguais a 0 são false. Partindo desse princípio, qualquer variável que possa
    receber o valor 0, pode ser convertida em true ou false. E para essa condição ser verificada,
    geralmente utilizamos expressões condicionais e operadores lógicos.*/

    unsigned long int ulongVar = 0;
    long int longVar = 42;
    float floatVar = 3.14;
    long longVar = 0;
    double doubleVar = 0.0;

    bool boolDeULong = ulongVar;  // ulongVar é 0, portanto, false
    bool boolDeLong = longVar;    // longVar é diferente de 0, portanto, true
    bool boolDeFloat = floatVar;  // floatVar é diferente de 0, portanto, true
    bool boolDeLong = longVar;    // longVar é 0, portanto, false
    bool boolDeDouble = doubleVar;// doubleVar é 0, portanto, false

    if (boolDeULong) {
        std::cout << "boolDeULong é true." << std::endl;
    } else {
        std::cout << "boolDeULong é false." << std::endl;
    }

    if (boolDeLong) {
        std::cout << "boolDeLong é true." << std::endl;
    } else {
        std::cout << "boolDeLong é false." << std::endl;
    }

    if (boolDeFloat) {
        std::cout << "boolDeFloat é true." << std::endl;
    } else {
        std::cout << "boolDeFloat é false." << std::endl;
    }

    if (boolDeLong) {
        std::cout << "boolDeLong é true." << std::endl;
    } else {
        std::cout << "boolDeLong é false." << std::endl;
    }

    if (boolDeDouble) {
        std::cout << "boolDeDouble é true." << std::endl;
    } else {
        std::cout << "boolDeDouble é false." << std::endl;
    }

    return 0;
}



