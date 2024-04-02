#include <stdio.h>
#include <string.h>

int main(int argc, char const *argv[]) {
  char numero[10], numeroInvertido[10];
  int i, j;

  printf("Digite um numero : ");
  scanf("%s", numero);

  for (i = 0, j = strlen(numero) - 1; i < strlen(numero); i++, j--) {
    numeroInvertido[j] = numero[i];
  }

  if (!(strcmp(numeroInvertido, numero)))
    printf("E um palindromo \n");
  else
    printf("Nao e um palindromo \n");

  return 0;
}