#include <iostream>

using namespace std;

int main(){ 

int altura, largura, i, j;


cout << "Insira a altura do padrão: ";
cin >> altura;

largura = altura;

char padraoA[altura][altura];

for (i=0; i < altura; i++){
    for (j=0; j < largura; j++){

    }
     
    padraoA[i][j] = '*';
    cout << padraoA[i][j];

}





    return 0;

}