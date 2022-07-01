#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

#define MAX_ENTRADAS 512  
#define STR_LEN 50   // qtd max de caracteres de uma string
#define TAM_LIN 150  // qtd max de caracteres de uma linha do csv
#define NUM_ATTR 8  // no. de atributos do jogador

int qtdRegistros = 0;
int numComparacoes = 0;
int numMovimentacoes = 0;
const char arquivoCSV[] = "/tmp/players.csv";

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

// https://www.geeksforgeeks.org/radix-sort/
// achar valor máximo em arr[]
int getMax(Jogador* arr, int n) {
  int mx = arr[0].id;
  for (int i = 1; i < n; i++){
    numComparacoes++;
    if (arr[i].id > mx)
      mx = arr[i].id;
  }
    return mx;
}
 
// Fazer counting sort em arr[] de acordo com
// o digito representado por exp.
void countSort(Jogador* arr, int n, int exp)
{
  Jogador output[MAX_ENTRADAS]; // array de saida
  int i, count[4000] = { 0 };

  // armazenar contador de ocorrencias em count[]
  for (i = 0; i < n; i++){
    count[(arr[i].id / exp) % 10]++;
    numMovimentacoes++;
  }
  // Alterar count[i] para que agora contenha a
  // posicao desse digito em output[]
  for (i = 1; i < 10; i++){
    count[i] += count[i - 1];
    numMovimentacoes++;
  }
  // Montar array de saida
  for (i = n - 1; i >= 0; i--) {
    output[count[(arr[i].id / exp) % 10] - 1] = arr[i];
    numMovimentacoes++;
    count[(arr[i].id / exp) % 10]--;
  }

  // Copiar array de saida para arr[], para que arr[] agora
  // contenha numeros ordenados de acordo com o digito atual
  for (i = 0; i < n; i++){
    arr[i] = output[i];
    numMovimentacoes++;
  }
}
 
// Radix Sort
void radixsort(Jogador* arr, int n) {
  // Achar o maior numero para saber o numero de digitos
  int m = getMax(arr, n);

  // fazer counting sort para cada digito. 
  // exp eh 10^i
  // onde i eh o numero atual
  for (int exp = 1; m / exp > 0; exp *= 10)
    countSort(arr, n, exp);
}


int main(int argc, char *argv[]) {
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
  radixsort(J, numEntrada);
  clock_t fim = clock();
  
  for (int i = 0; i < numEntrada; i++) {
    imprimir(&J[i]);
  }
 
  fclose(csv);

  // log
  FILE* log = fopen("matricula_radixsort.txt", "w+");
  fprintf(log, "696809\t");
  fprintf(log, "%d\t", numComparacoes);
  fprintf(log, "%d\t", numMovimentacoes);
  fprintf(log, "%ld\t", (fim-inicio));
  fclose(log);
  return 0;
}
