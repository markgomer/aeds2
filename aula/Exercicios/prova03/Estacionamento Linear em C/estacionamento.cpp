#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <err.h>

//TIPO CELULA ===================================================================
typedef struct Celula {
	int elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* novaCelula(int elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

//PILHA PROPRIAMENTE DITA =======================================================
Celula* topo; // Sem celula cabeca.

/**
 * Cria uma fila sem elementos.
 */
void start () {
   topo = NULL;
}

/**
 * Insere elemento na pilha (politica FILO).
 * @param x int elemento a inserir.
 */
void inserir(int x) {
   Celula* tmp = novaCelula(x);
   tmp->prox = topo;
   topo = tmp;
   tmp = NULL;
}

void mostrar() {
   Celula* i;
   printf("[");
   for(i = topo; i != NULL; i = i->prox) {
      printf("%d ,", i->elemento);
   }

   printf("] \n");
}

bool checarHorarios() {
   bool resp = true;
   Celula* i;
   for(i = topo; i != NULL; i = i->prox) {
      if(topo->elemento > i->elemento) {
         resp = false;
         //i = NULL;
      }
   }
   return resp;
}


int main(int argc, char *argv[]) {

   //N: numero de carros; K: numero de vagas
   int N, K;
   //C: hora de chegada; S: hora de saida
   int C, S;
   
   do {
      start(); //inicia pilha
      bool resp = true;
      scanf("%d %d", &N, &K);
      for(int i = 0; i<N; i++) {
         scanf("%d %d", &C, &S);
         inserir(S); 
         // ao inserir, checar se o novo carro tem o menor valor de S em toda a pilha
         // se S for o menor, o estacionamento eh possivel, caso contrario, nao
         //mostrar();

         //printf("i= %d\n", i);

         if(!checarHorarios()) {
            //printf("elemento : %d\n", S);
            resp = false;
            i = N;
         }

      }
      if(N!=0) {
         printf("%s\n", resp ? "Sim" : "Nao");
      }

   } while(N!=0);
   return 0;
}