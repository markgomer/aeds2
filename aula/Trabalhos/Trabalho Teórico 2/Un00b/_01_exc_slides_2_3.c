#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#define TAM 4


// slide 2 - ex 1
bool contem(int arr[], int x) {
  bool contido = false;
  int count = 0;
  while(!contido && count<TAM) {
    if(x == arr[count]) contido = true;
    count++;
  }
  return contido;
}


// slide 2 - ex2
bool contem_em_array_ordenada(int arr[], int x) { // array com elementos em ordem crescente
  bool contido = false;
  int count = TAM/2;

  while(!contido && count<TAM) {
    if(x == arr[count]) contido = true;
    if(arr[count] < x) count++;
    else count--;
  }
  return contido;
}


// Faça um método que receba um array de inteiros e mostre na tela o maior e o menor elementos do array.
void show_minimo_maximo(int arr[]) {
  int min, max, cont = 0;
  min = max = arr[cont];

  while(cont<TAM) {
    if(arr[cont] < min) min = arr[cont];
    if(arr[cont] > max) max = arr[cont];
    cont++;
  }
  printf("Maximo = %i\n\n",max);
  printf("Minimo = %i\n\n",min);
}


int main() {

  int A[TAM] = {1,2,3,4};

  printf("5 contido em A? %s.\n\n",contem(A,5)?"true":"false");
  printf("5 contido em A? %s.\n\n",contem2(A,5)?"true":"false");
  printf("3 contido em A? %s.\n\n",contem2(A,3)?"true":"false");
  minimo_maximo(A);

  system("pause");
  return 0;
}