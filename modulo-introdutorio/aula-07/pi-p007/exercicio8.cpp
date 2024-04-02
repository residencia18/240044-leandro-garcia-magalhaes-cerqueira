#include <iostream>

using namespace std;

int main (){

int num, i, divisores=0;

cout << "Insira um número para verificarmos se ele é perfeito ou não: ";
cin >> num;

for(i=1; i < num; i++){


    if(num %i == 0){
        divisores = divisores + i;
    }
}

if (num == divisores){
    cout << "Sim! O número " << num << " é um número perfeito!" << endl;
    cout << "A soma de seus divisores resulta em: " << divisores << endl;
} else {
    cout << "Não! O número " << num << " não é um número perfeito!" << endl;
    cout << "A soma de seus divisores resulta em: " << divisores << endl;

}

    return 0;

}
