#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define NOME_LEN 22
#define MAX_ENTR 100

int comportadas;
int naoComportadas;

void checarComportamento(char string[NOME_LEN]) {
  if(string[0] == '+') {
    comportadas++;
  }
  else {
    naoComportadas++;
  }
}

void swap(char i[], char j[]) {
   char temp[NOME_LEN];
   strcpy(temp, i);
   strcpy(i, j);
   strcpy(j, temp);
}

void selecaoRec(char array[MAX_ENTR][NOME_LEN], int n, int i){
    if (i < (n-1)) {
      int menor = i;
      for (int j = (i + 1); j < n; j++){
        if (strcmp(array[menor], array[j]) > 0){
          menor = j;
        }
      }
      swap(array[menor], array[i]);

      selecaoRec(array, n, i + 1);
    }
}


int main(int argc, char *argv[]) {
  int numCriancas;
  char linha[MAX_ENTR][NOME_LEN];
  char listaDeNomes[MAX_ENTR][NOME_LEN];
  comportadas = 0;
  naoComportadas = 0;

  scanf("%d", &numCriancas);

  for(int i = 0; i < numCriancas; i++) {
    scanf("%[^\n]%*c", linha[i]); //linha com tds as info
    checarComportamento(linha[i]);
    strcpy(listaDeNomes[i], linha[i] + 2);
  }

  selecaoRec(listaDeNomes, numCriancas, 0);

  for(int i = 0; i < numCriancas; i++) {
    printf("%s\n", listaDeNomes[i]);
  }

  printf("Se comportaram: %d | Nao se comportaram: %d", comportadas, naoComportadas);

  return 0;
}