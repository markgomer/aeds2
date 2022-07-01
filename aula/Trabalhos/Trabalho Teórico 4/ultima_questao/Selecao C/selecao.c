#include <stdio.h>
#include <time.h>
#include "selecao.h"

#define MAX_SIZE 10000

int comparacoes;
int movimentacoes;
clock_t comeco, fim;

void selecao(int* array, int n) {
  comparacoes = 0;
  movimentacoes = 0;
  for (int i = 0; i < (n - 1); i++) {
    int menor = i;
    for (int j = (i + 1); j < n; j++){
        if (array[menor] > array[j]){
          menor = j;
        }
        comparacoes++;
    }
    if(menor!=i) {
      swap(&array[menor], &array[i]);
      movimentacoes += 3;
    }
  }
  printf("%d, ", movimentacoes);
  printf("%d, ", comparacoes);
}

void executarAleatorio(int numeroDeExecucoes, int tamanhoDoArray, int* array) {
  for (int i = 0; i < numeroDeExecucoes; i++) {
    printf("[%d]*%d, ", tamanhoDoArray, numeroDeExecucoes);
    aleatorio(array, tamanhoDoArray); // preenche array com rand
    comeco = clock();             // timestamp inicio
    selecao(array, tamanhoDoArray);   // ordena array
    fim = clock();                       // timestamp fim
    printf("%lf\n", (double)(fim - comeco) / (double)(CLOCKS_PER_SEC));
  }
}

void executarCrescente(int numeroDeExecucoes, int tamanhoDoArray, int* array) {
  for (int i = 0; i < numeroDeExecucoes; i++) {
    printf("[%d]*%d, ", tamanhoDoArray, numeroDeExecucoes);
    crescente(array, tamanhoDoArray); // preenche array com int crescentes
    comeco = clock();             // timestamp inicio
    selecao(array, tamanhoDoArray);   // ordena array
    fim = clock();                       // timestamp fim
    printf("%lf\n", (double)(fim - comeco) / (double)(CLOCKS_PER_SEC));
  }
}

void executarDecrescente(int numeroDeExecucoes, int tamanhoDoArray, int* array) {
  for (int i = 0; i < numeroDeExecucoes; i++) {
    printf("[%d]*%d, ", tamanhoDoArray, numeroDeExecucoes);
    decrescente(array, tamanhoDoArray); // preenche array com decrescentes
    comeco = clock();             // timestamp inicio
    selecao(array, tamanhoDoArray);   // ordena array
    fim = clock();                       // timestamp fim
    printf("%lf\n", (double)(fim - comeco) / (double)(CLOCKS_PER_SEC));  
  }
}


int main() {
  int array[MAX_SIZE];

  aleatorio(array, 100);

  // executa o script:
  printf("Aleatorio\n");
  executarAleatorio(33, 100, array);
  executarAleatorio(33, 1000, array);
  executarAleatorio(33, 10000, array);
    
  printf("Crescente\n");
  executarCrescente(33, 100, array);
  executarCrescente(33, 1000, array);
  executarCrescente(33, 10000, array);
  
  printf("Decrescente\n");
  executarDecrescente(33, 100, array);
  executarDecrescente(33, 1000, array);
  executarDecrescente(33, 10000, array);

  return 0;
}