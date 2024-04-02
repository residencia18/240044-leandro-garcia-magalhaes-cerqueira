#include <iostream>
using namespace std;

int main (void){

int contador;

    for (contador=1; contador <101; contador++){

        if (contador %5 == 0 && contador %3 == 0){
            cout << contador << " FizzBuzz" << endl;
        } else if (contador %3 == 0){
            cout << contador << " Fizz" << endl;
        } else if (contador %5 == 0){
            cout << contador << " Buzz" << endl;
        } else {
            cout << contador << endl;
        }

    }











    return 0;

}

