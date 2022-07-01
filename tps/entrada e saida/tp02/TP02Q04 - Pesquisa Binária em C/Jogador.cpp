/*
Marco Aurelio Silva de Souza Junior - 696809
Questao TP02Q04
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

#define MAX_ENTRADAS 256  
#define STR_LEN 50   // qtd max de caracteres de uma string
#define TAM_LIN 150  // qtd max de caracteres de uma linha do csv
#define NUM_ATTR 8  // no. de atributos do jogador

int qtdRegistros = 0;
int numComparacoes = 0;

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

// atribui cada atributo do array de strings no objeto Jogador
Jogador ler(char* strAttr[]) {
  Jogador J;
  qtdRegistros++;
  J.id = atoi(strAttr[0]);
  strcpy(J.nome, strAttr[1]);
  J.altura = atoi(strAttr[2]);
  J.peso = atoi(strAttr[3]);
  strcpy(J.universidade, strAttr[4]);
  J.anoNascimento = atoi(strAttr[5]);
  strcpy(J.cidadeNascimento, strAttr[6]);
  strcpy(J.estadoNascimento, strAttr[7]);
  return J;
}

// construtores
Jogador setJogador(int id, char nome[], int altura, int peso, char universidade[], int anoNascimento, char cidadeNascimento[], char estadoNascimento[]) {
  Jogador J;
  qtdRegistros++;
  J.id = id;
  strcpy(J.nome, nome);
  J.altura = altura;
  J.peso = peso;
  strcpy(J.universidade, universidade);
  J.anoNascimento = anoNascimento;
  strcpy(J.cidadeNascimento, cidadeNascimento);
  strcpy(J.estadoNascimento, estadoNascimento);
  return J;
}

Jogador clone(Jogador* clone, Jogador* clonado) {
  *clone = *clonado;
}

// recebe string de uma lihna do csv e coloca os atributos na array
void atribuirAtributos(char* input, char* output[]) {
  int i = 0;
  numComparacoes++;
  strtok(input, "\n"); // retira quebra de linha no final da string
  output[i++] = input;
  
  // loop vai repartindo linha nas ',' e atribuindo a output[]
  while (numComparacoes++ > -1 && i < NUM_ATTR && input && (input = strchr(input, ','))) {
    *input = 0;           
    output[i++] = ++input;
  }
}

// le a lihna do csv correspondente ao id procurado
void lerLinha(FILE* csv, char linha[], int id) {
  for(int n = 0; n <= id + 1; n++) {
    fgets(linha, TAM_LIN, csv);
  }
}


// pesquisa binaria
bool estaContido(char chave[], char listaDeNomes[][STR_LEN], int length) {
  bool resp = false;
  int dir = length - 1, 
      esq = 0;
  while(esq <= dir) {
    numComparacoes++;
    if(strstr(listaDeNomes[esq], chave) != NULL) {
      resp = true;
      esq = length;
    } else if ((numComparacoes++) > -1 && strstr(listaDeNomes[dir], chave) != NULL) {
      resp = true;
      esq = length;
    } else {
      esq += 1;
      dir -= 1;
    }
  }
  return resp;
}


int main(int argc, char *argv[]) {
  Jogador J[MAX_ENTRADAS];
  clock_t inicio, fim;
  FILE *csv;
  csv = fopen("/tmp/players.csv", "r");
  char entrada[MAX_ENTRADAS][STR_LEN]; // armazenar entrada padrao com id's
  char linha[TAM_LIN]; // linha a ser lida do csv 
  char* strAttr[NUM_ATTR]; // array de 8 strings que representam os atributos
  int id; // recebe entrada padrao para procurar no csv
  int numEntrada = 0; // contador de entradas
  char listaDeNomes[MAX_ENTRADAS][STR_LEN]; // array com nomes dos respectivos id's
  char chavePesq[MAX_ENTRADAS][STR_LEN]; // nomes pesquisados apos 1o. "FIM"
  int numPesquisa = 0; // numero de nomes pesquisados

  inicio = clock();
  // leitura da entrada padrao
  do {
    scanf("%[^\n]%*c", entrada[numEntrada]);
  } while(strcmp(entrada[numEntrada++],"FIM")!=0);
  numEntrada--;  // desconsidera entrada com FIM

  // percorre as entradas
  for (int i = 0; i < numEntrada; i++) {
    id = atoi(entrada[i]);

    lerLinha(csv, linha, id); 
    
    atribuirAtributos(linha, strAttr);

    J[i] = ler(strAttr);

    strcpy(listaDeNomes[i], J[i].nome);

    //imprimir(&J[i]);
    
    rewind(csv);  
  }

  // leitura da entrada padrao
  do {
    scanf("%[^\n]%*c", chavePesq[numPesquisa]);
  } while(strcmp(chavePesq[numPesquisa++],"FIM")!=0);
  numPesquisa--;  // desconsidera entrada com FIM
 
  // checa se chave de pesquisa esta presente no array listaDeNomes
  for (int i = 0; i < numPesquisa; i++) {
    printf(estaContido(chavePesq[i], listaDeNomes, numEntrada)? "SIM\n" : "NAO\n");
  }

  fclose(csv);
  
  fim = clock();

  // log
  FILE* log = fopen("matricula_sequencial.txt", "w+");
  fprintf(log, "696809\t");
  fprintf(log, "%ld\t", (fim-inicio));
  fprintf(log, "%d", numComparacoes);
  fclose(log);

  return 0;
}
