#include <iostream>

using namespace std;

int main() { 
    int altura;

    cout << "Digite a altura do padrão: ";
    cin >> altura;

    char caractere = 'A';
    int caracteresNaLinha = 1;

    for(int i=0; i < altura; i++) {
        for(int j=0; j < caracteresNaLinha; j++){
            cout << caractere;
            caractere++;
        }

        cout << endl;

        caracteresNaLinha++;

    }
    
    return 0;
}