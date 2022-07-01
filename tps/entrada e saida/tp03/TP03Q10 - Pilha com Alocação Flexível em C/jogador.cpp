#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <err.h>
#include <time.h>

#define TAM_MAX_LINHA 250
#define MAX_ENTRADAS 500
#define arquivoCSV "/tmp/players.csv"

int numComparacoes = 0;
int numMovimentacoes = 0;


typedef struct {
  int id;
  int peso;
  int altura;
  char nome[70];
  char universidade[70];
  int anoNascimento;
  char cidadeNascimento[70];
  char estadoNascimento[70];
} Jogador;


void setJogador(char* strAttr[], Jogador* J) {
  J->id = atoi(strAttr[0]);
  strcpy(J->nome, strAttr[1]);
  J->altura = atoi(strAttr[2]);
  J->peso = atoi(strAttr[3]);
  strcpy(J->universidade, strAttr[4]);
  J->anoNascimento = atoi(strAttr[5]);
  strcpy(J->cidadeNascimento, strAttr[6]);
  if(strAttr[7][0]=='\0') 
    strcpy(J->estadoNascimento, "zzz");
  else strcpy(J->estadoNascimento, strAttr[7]);
}


void imprimir(Jogador *J) {
  char* vazio = "nao informado";
  printf("## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
    //J->id,
    J->nome[0]=='\0' ? vazio : J->nome,
    J->altura,
    J->peso,
    J->anoNascimento,
    J->universidade[0]=='\0' ? vazio : J->universidade,
    J->cidadeNascimento[0]=='\0' ? vazio : J->cidadeNascimento,
    strcmp(J->estadoNascimento,"zzz") == 0 ? vazio : J->estadoNascimento
  );
}


Jogador clone(Jogador *jogador) {
  Jogador retorno;
  retorno.id = jogador->id;
  strcpy(retorno.nome, jogador->nome);
  retorno.altura = jogador->altura;
  retorno.peso = jogador->peso;
  retorno.anoNascimento = jogador->anoNascimento;
  strcpy(retorno.universidade, jogador->universidade);
  strcpy(retorno.cidadeNascimento, jogador->cidadeNascimento);
  strcpy(retorno.estadoNascimento, jogador->estadoNascimento);
  return retorno;
}


// recebe string de uma lihna do csv e coloca os atributos no array
void parseAtributos(char* input, char* output[]) {
  int i = 0;
  strtok(input, "\n"); // retira quebra de linha no final da string
  
  output[i++] = input;

  // loop vai repartindo linha nas ',' e atribuindo a output[]
  while (i < 8 && input && (input = strchr(input, ','))) {
    *input = 0;           
    output[i++] = ++input;
  }
}


// le a lihna do csv correspondente ao id procurado
void lerLinha(FILE* csv, char linha[], int id) {
  rewind(csv);
  for(int n = 0; n <= id + 1; n++) {
    fgets(linha, TAM_MAX_LINHA, csv);
  }
}


// atribui cada atributo do array de strings no objeto Jogador
Jogador ler(FILE *csv, int id) {
  Jogador J;
  char linha[TAM_MAX_LINHA]; // linha a ser lida do csv 
  char* strAttr[8]; // array com os atributos

  lerLinha(csv, linha, id); // le linha do arquivo correspondente ao id
  
  parseAtributos(linha, strAttr); // transforma a string em array de atributos
  
  setJogador(strAttr, &J); // cria o registro jogador a partir do array de atributos
  
  return J; 
}


// faz leitura dos valores a partir da entrada padrao e 
// retorna numero de entrada efetuadas
int leituraDeEntrada(char entrada[][TAM_MAX_LINHA]) {
  int numEntrada = 0; // contador de entradas
  do {
    scanf("%[^\n]%*c", entrada[numEntrada]); // *c ignora ultimo caracter lido
  } while(strcmp(entrada[numEntrada++],"FIM")!=0);
  numEntrada--;  // desconsidera entrada com FIM
  return numEntrada;
}



//TIPO CELULA ===================================================================
typedef struct CelulaDupla {
	Jogador elemento;        // Elemento inserido na celula.
	struct CelulaDupla* prox; // Aponta a celula prox.
   struct CelulaDupla* ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Jogador* elemento) {
   CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   nova->elemento = *elemento;
   nova->ant = nova->prox = NULL;
   return nova;
}

//LISTA PROPRIAMENTE DITA =======================================================
CelulaDupla* primeiro;
CelulaDupla* ultimo;


/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void start () {
  Jogador j;
  primeiro = novaCelulaDupla(&j);
  ultimo = primeiro;
}


/**
 * Insere um elemento na ultima posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirFim(Jogador* x) {
   ultimo->prox = novaCelulaDupla(x);
   ultimo->prox->ant = ultimo;
   ultimo = ultimo->prox;
}


/**
 * Remove um elemento da ultima posicao da lista.
 * @return resp int elemento a ser removido.
 */
Jogador removerFim() {
   if (primeiro->elemento.id == ultimo->elemento.id) {
      errx(1, "Erro ao remover (vazia)!");
   } 
   Jogador resp = ultimo->elemento;
   ultimo = ultimo->ant;
   ultimo->prox->ant = NULL;
   free(ultimo->prox);
   ultimo->prox = NULL;
   return resp;
}


/**
 *  Calcula e retorna o tamanho, em numero de elementos, da lista.
 *  @return resp int tamanho
 */
int tamanho() {
   int tamanho = 0; 
   CelulaDupla* i;
   for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
   return tamanho;
}


// Mostra os elementos separados por espacos.
void mostrar() {
  CelulaDupla* i;
  int cont = 0;
  for (i = primeiro->prox; i != NULL; i = i->prox) {
    printf("[%d] ", cont++);
    imprimir(&i->elemento);
  }
}


// MAIN ===============================================================
int main(int argc, char *argv[]) {
  FILE *csv = fopen(arquivoCSV, "r");

  // variavel para armazenar entrada padrao com id's
  char entrada[MAX_ENTRADAS][TAM_MAX_LINHA]; 
  
  // leitura da entrada padrao, retorna quantidade de entradas inseridas
  const int numEntrada = leituraDeEntrada(entrada); 
  
  // iniciando lista
  start();
  
  // criar registros "jogador" a partir das entradas armazenadas
  for (int i = 0; i < numEntrada; i++) {
    Jogador j = ler(csv, atoi(entrada[i])); // atoi(entrada[i]) = id do jogador
    inserirFim(&j);
  }
  
  int numOperacoes, posicao, id; 
  scanf("%d",&numOperacoes);
  
  for(int i = 0; i<numOperacoes; i++) {
    Jogador jogador;
    char operacao[2]; scanf("%s", operacao);

      if (strcmp(operacao,"I") == 0) {
        scanf("%d", &id); 
        jogador = ler(csv, id);
        inserirFim(&jogador);
      } else if (strcmp(operacao,"R") == 0) {
        printf("(R) %s\n", (removerFim()).nome);
      }
      else printf("operacao invalida\n");
  }

  mostrar();

  fclose(csv);
  
  return 0;
}
