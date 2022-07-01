#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

#define MAX_ENTRADAS 512  
#define STR_LEN 50   // qtd max de caracteres de uma string
#define TAM_LIN 150  // qtd max de caracteres de uma linha do csv
#define NUM_ATTR 8  // no. de atributos do jogador
#define arquivoCSV "/tmp/players.csv"


int qtdRegistros = 0;
int numComparacoes = 0;
int numMovimentacoes = 0;


// propriedades
typedef struct {
  int id;
  char nome[STR_LEN];
  int altura;
  int peso;
  char universidade[STR_LEN];
  int anoNascimento;
  char cidadeNascimento[STR_LEN];
  char estadoNascimento[STR_LEN];
} Jogador ;


// construtores
void setJogador(char* strAttr[], Jogador* J) {
  J->id = atoi(strAttr[0]);
  strcpy(J->nome, strAttr[1]);
  J->altura = atoi(strAttr[2]);
  J->peso = atoi(strAttr[3]);
  strcpy(J->universidade, strAttr[4]);
  J->anoNascimento = atoi(strAttr[5]);
  strcpy(J->cidadeNascimento, strAttr[6]);
  strcpy(J->estadoNascimento, strAttr[7]);

  qtdRegistros++;
}


void clone(Jogador* clone, Jogador* clonado) {
  *clone = *clonado;
}


// mostra na tela os atributos do Jogador
void imprimir(Jogador* J) {
  char* vazio = "nao informado";
  printf("[");
  printf("%d", J->id); printf(" ## ");
  printf("%s", J->nome[0]=='\0' ? vazio : J->nome); printf(" ## ");
  printf("%d", J->altura); printf(" ## ");
  printf("%d", J->peso); printf(" ## ");
  printf("%d", J->anoNascimento); printf(" ## ");
  printf("%s", J->universidade[0]=='\0' ? vazio : J->universidade); printf(" ## ");
  printf("%s", J->cidadeNascimento[0]=='\0' ? vazio : J->cidadeNascimento); printf(" ## ");
  printf("%s", J->estadoNascimento[0]=='\0' ? vazio : J->estadoNascimento); printf("]\n");
}


// recebe string de uma lihna do csv e coloca os atributos na array
void atribuirAtributos(char* input, char* output[]) {
  int i = 0;
  strtok(input, "\n"); // retira quebra de linha no final da string
  numComparacoes++;
  
  output[i++] = input;
  numMovimentacoes++;

  // loop vai repartindo linha nas ',' e atribuindo a output[]
  while (numComparacoes++ > -1 && i < NUM_ATTR && input && (input = strchr(input, ','))) {
    *input = 0;           
    output[i++] = ++input;
  }
}


// le a lihna do csv correspondente ao id procurado
void lerLinha(FILE* csv, char linha[], int id) {
  rewind(csv);
  for(int n = 0; n <= id + 1; n++) {
    fgets(linha, TAM_LIN, csv);
  }
}


// atribui cada atributo do array de strings no objeto Jogador
Jogador ler(FILE *csv, int id) {
  Jogador J;
  char linha[TAM_LIN]; // linha a ser lida do csv 
  char* strAttr[NUM_ATTR];

  lerLinha(csv, linha, id); 
  
  atribuirAtributos(linha, strAttr);
  
  setJogador(strAttr, &J);
  
  qtdRegistros++;
  return J;
}


// retorna numero de entrada efetuadas
int leituraDeEntrada(char entrada[][STR_LEN]) {
  int numEntrada = 0; // contador de entradas
  do {
    scanf("%[^\n]%*c", entrada[numEntrada]);
  } while(strcmp(entrada[numEntrada++],"FIM")!=0);
  numEntrada--;  // desconsidera entrada com FIM
  return numEntrada;
}


/* 
 * ORDENACAO 
*/

void swap(Jogador *i, Jogador *j) {
   Jogador temp = *i;
   *i = *j;
   *j = temp;
   numMovimentacoes+=3;
}

void construir(Jogador *array, int tamHeap){
  for(
    int i = tamHeap; 
    i > 1 && 
    numComparacoes++!=0 &&
    array[i].altura > array[i/2].altura || (
      numComparacoes++!=0 &&
      array[i].altura == array[i/2].altura &&
      numComparacoes++!=0 &&
      strcmp(array[i].nome, array[i/2].nome) > 0
    ); 
    i /= 2
  ) {
    swap(array + i, array + i/2);
  }
}

int getMaiorFilho(Jogador *array, int i, int tamHeap){
  int filho;
  if(
    2*i == tamHeap || 
    array[2*i].altura > array[2*i+1].altura || (
      array[2*i].altura == array[2*i+1].altura &&
      strcmp(array[2*i].nome, array[2*i+1].nome) > 0
    )
  ) {
    filho = 2*i;
  } else {
    filho = 2*i + 1;
  }
  return filho;
}

void reconstruir(Jogador *array, int tamHeap){
  int i = 1;
  while(i <= (tamHeap/2)){
    int filho = getMaiorFilho(array, i, tamHeap);
    if(
      array[i].altura < array[filho].altura || (
        array[i].altura == array[filho].altura &&
        strcmp(array[i].nome, array[filho].nome) < 0
      )
    ) {
      swap(array + i, array + filho);
      i = filho;
    } else {
      i = tamHeap;
    }
  }
}

void heapsort(Jogador *array, int n, int k) {
    //Alterar o vetor ignorando a posicao zero
    Jogador arrayTmp[n+1];
    for(int i = 0; i < n; i++){
      arrayTmp[i+1] = array[i];
    }
    //Contrucao do heap
    for(int tamHeap = 2; tamHeap <= n; tamHeap++){
      construir(arrayTmp, tamHeap);
    }

    //Ordenacao propriamente dita
    int tamHeap = n;
    while(tamHeap > 1){
        swap(arrayTmp + 1, arrayTmp + tamHeap--);
        reconstruir(arrayTmp, tamHeap);
    }

    //Alterar o vetor para voltar a posicao zero
    for(int i = 0; i < k; i++){
      array[i] = arrayTmp[i+1];
    }
}


int main(int argc, char *argv[]) {
  int k = 10;
  Jogador J[MAX_ENTRADAS];
  FILE *csv;
  csv = fopen(arquivoCSV, "r");

  char entrada[MAX_ENTRADAS][STR_LEN]; // armazenar entrada padrao com id's
  
  int numEntrada = leituraDeEntrada(entrada); // leitura de entrada padrao

  // percorre as entradas
  for (int i = 0, id; i < numEntrada; i++) {
    id = atoi(entrada[i]);  // recebe valor da entrada padrao para procurar no csv
    J[i] = ler(csv, id);
  }
    
  clock_t inicio = clock();
  heapsort(J, numEntrada, k);
  clock_t fim = clock();
  
  for (int i = 0; i < k; i++) {
    imprimir(&J[i]);
  }
 
  fclose(csv);

  // log
  FILE* log = fopen("matricula_insercao_parcial.txt", "w+");
  fprintf(log, "696809\t");
  fprintf(log, "%d\t", numComparacoes);
  fprintf(log, "%d\t", numMovimentacoes);
  fprintf(log, "%ld\t", (fim-inicio));
  fclose(log);
  
  return 0;
}
