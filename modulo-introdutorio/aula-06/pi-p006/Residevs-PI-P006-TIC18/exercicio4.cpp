#include <iostream>
#include <math.h>
#include <cctype>
#include <cstdio>
#include <stdio.h> // Para a inclusão da função printf e outras funções padrão da linguagem C.

using namespace std;

int main(){

//a.
    std:: cout << "a):" << std:: endl;
    double x,y,z;

//b.
    std:: cout << "b):" << std:: endl;
    std:: cout << "Insira um número de ponto flutuante: ";
    scanf("%lf", &x);
    cout << endl;
    std:: cout << "Agora insira outro número, também de ponto flutuante: ";
    scanf("%lf", &y);
    cout << endl;
//c.
    std:: cout << "c):" << std:: endl;
    z = x + y;
    std:: cout << z << std:: endl;

//d.
    std:: cout << "d):" << std:: endl;
    z = (x+y)/2;
    cout << endl;

//e.
    std:: cout << "e):" << std:: endl;
    z = x*y;
    cout << endl;

//f.
    std:: cout << "f):" << std:: endl;
    x > y ? z = x : z = y;
    std:: cout << z << std:: endl;

//g.
    std:: cout << "g):" << std:: endl;
    x < y ? z = x : z = y;
    std:: cout << z << std:: endl;
























    return 0;
}