#include <iostream>

using namespace std;


int main (){
    int matricula[10],i;
    float nota1[10], nota2[10], nota3[10], media[10];
    bool decisao = false;

    do{

    int sair;
    
    for(i=0; i < 10; i++){
    cout << "Insira o número de matrícula do aluno: ";
    cin >> matricula[i];
        cout << endl;
    cout << "Agora insira a NOTA 1 desse aluno: ";
    cin >> nota1[i];
        cout << endl;
    cout << "Agora insira a NOTA 2 desse aluno: ";
    cin >> nota2[i];
        cout << endl;
    cout << "Agora insira a NOTA 3 desse aluno: ";
    cin >> nota3[i];
        cout << endl;

    media[i] = (nota1[i] + nota2[i] + nota3[i]) / 3;

    cout << "Deseja adicionar mais um aluno? Digite (1) para sim ou (2) para sair: ";
    cin >> sair;
    cout << endl;

        if(sair == 2){
            decisao = true;
            media[i+1] = 99;
            break;
        }
    }


  } while (decisao == false);

      for(i=0; media[i] != 99; i++){
        cout << "MATRÍCULA  " << "  NOTA1  " << "  NOTA2  " << "  NOTA3  " << "  MEDIA  " << endl;
        cout << "=============================================" << endl;
        cout << matricula[i] << "    " << nota1[i] << "    " << nota2[i] << "    " << nota3[i] << "    " << media[i] << endl;
        cout << endl;
      }

    return 0;
}