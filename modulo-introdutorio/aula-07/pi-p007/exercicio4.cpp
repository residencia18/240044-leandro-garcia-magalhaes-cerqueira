#include <iostream>

using namespace std;

int main(){

int contador;

    for(contador = 1; contador <=100; contador++){

        if (contador == 2 || contador == 3 || contador == 5 || contador == 7){
            cout << contador << " é primo." << endl;
        } else if(contador %1 == 0 && contador %contador == 0 && contador!= 1 && contador %2 != 0 && contador %3 != 0 && contador %5 != 0 && contador %7 != 0){
            cout << contador << " é primo." << endl;
        }
        } 
    

    return 0;
}