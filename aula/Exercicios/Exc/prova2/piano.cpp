#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int getMaisFrequente(int array[], int n) { 
  int max_count = 1, resp = array[0], curr_count = 1; 
  for (int i = 1; i < n; i++) { 
    if (array[i] == array[i - 1]) 
      curr_count++; 
    else { 
      if (curr_count > max_count) { 
        max_count = curr_count; 
        resp = array[i - 1]; 
      } 
      curr_count = 1; 
    } 
  } 
  
  if (curr_count > max_count) { 
    max_count = curr_count; 
    resp = array[n - 1]; 
  } 
  
  return resp; 
} 


int main(int argc, char const *argv[]) {

  int N; scanf("%d",&N);
  const int numTeclas = N;
  int Q; scanf("%d",&Q);
  const int numAcordes = Q;

  // iniciar teclado
  int teclado[numTeclas];
  for(int i = 0; i < numTeclas; i++) {
    teclado[i] = 1;
  }
  

  for(int i = 0; i < numAcordes; i++) {
    // 2 notas
    int teclaX; scanf("%d",&teclaX);
    int teclaY; scanf("%d",&teclaY);

    // notas dentro do acorde
    const int n = teclaY - teclaX + 1; // tamanho do subteclado
    int subteclado[n];
    int x = teclaX;
    for(int j = 0; j <= n; j++) {
      subteclado[j] = teclado[x++];
    }

    // aplica a formula do enunciado
    subteclado[i] = (subteclado[i] + getMaisFrequente(subteclado, n) ) % 9;

    // substitui as notas no teclado original
    x = teclaX;
    for(int j = 0; j <= n; j++) {
      teclado[i++] = subteclado[j];
    }
  }

  for(int i = 0; i < numTeclas; i++) {
    printf("%d\n",teclado[i]);
  }

  return 0;
}
