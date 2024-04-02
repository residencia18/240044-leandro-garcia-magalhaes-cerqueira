#include <iostream>
#include <random> 

using namespace std;

int main(){
    int palpite,aux, i;

    std::random_device rd;
    std::mt19937 generator(rd());

    int min = 1;
    int max = 100;

    std::uniform_int_distribution<int> distribution(min, max);

    int numeroAleatorio = distribution(generator);

    for(i = 0; aux != numeroAleatorio; i++){

        cout << "Tente adivinhar qual o número: ";
        cin >> palpite;

        if (palpite < 1 || palpite > 100){
            cout << "Por favor, insira um valor entre 1 e 100!" << endl;
        }

            if (palpite < numeroAleatorio){
                cout << "O número inserido é MENOR que o número procurado. Tente aumentar um pouco mais!" << endl;
            } else if (palpite > numeroAleatorio){
                cout << "O número inserido é MAIOR que o número procurado. Tente tente diminuir um pouco mais!" << endl;

            } else {
                cout << "Parabéns! Você encontrou o número! O número era: " << numeroAleatorio << "." << endl;
            }

            aux = palpite;
        
        }
    


    return 0;
}