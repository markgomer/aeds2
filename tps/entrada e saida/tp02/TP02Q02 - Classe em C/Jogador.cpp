/*
Marco Aurelio Silva de Souza Junior - 696809
Questao TP02Q02
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define MAX_ENTRADAS 42  
#define STR_LEN 50   // qtd max de caracteres de uma string
#define TAM_LIN 150  // qtd max de caracteres de uma linha do csv
#define NUM_ATTR 8  // no. de atributos do jogador

int qtdRegistros;

// propriedades
typedef struct {
  int id;
  char *nome;
  int altura;
  int peso;
  char *universidade;
  int anoNascimento;
  char *cidadeNascimento;
  char *estadoNascimento;
} Jogador ;

// getters n' setters
int getId(Jogador* J) {
  return J->id;
}
void setId(Jogador* J, int id) {
  J->id = id;
}

char* getNome(Jogador* J) {
  return J->nome;
}
void setNome(Jogador* J, char nome[]) {
  J->nome = strdup(nome);
}

int getAltura(Jogador* J) {
  return J->altura;
}
void setAltura(Jogador* J, int altura) {
  J->altura = altura;
}

int getPeso(Jogador* J) {
  return J->peso;
}
void setPeso(Jogador* J, int peso) {
  J->peso = peso;
}

char* getUniversidade(Jogador* J) {
  return J->universidade;
}
void setUniversidade(Jogador* J, char universidade[]) {
  J->universidade = strdup(universidade);
}

int getAnoNascimento(Jogador* J) {
  return J->anoNascimento;
}
void setAnoNascimento(Jogador* J, int ano) {
  J->anoNascimento = ano;
}

char* getCidadeNascimento(Jogador* J) {
  return J->cidadeNascimento;
}
void setCidadeNascimento(Jogador* J, char cidade[]) {
  J->cidadeNascimento = strdup(cidade);
}

char* getEstadoNascimento(Jogador* J) {
  return J->estadoNascimento;
}
void setEstadoNascimento(Jogador* J, char estado[]) {
  J->estadoNascimento = strdup(estado);
}

// recebe int e retorna string equivalente
// nao funcionou!
char* toStr(int num) {
  char* resp[50];
  sprintf(*resp, "%d", num);
  return *resp;
}

// mostra na tela os atributos do Jogador
void imprimir(Jogador* J) {
  char* vazio = "nao informado";
  printf("[");
  printf("%d", getId(J)); printf(" ## ");
  printf("%s", getNome(J)[0]=='\0' ? vazio : getNome(J)); printf(" ## ");
  printf("%d", getAltura(J)); printf(" ## ");
  printf("%d", getPeso(J)); printf(" ## ");
  printf("%d", getAnoNascimento(J)); printf(" ## ");
  printf("%s", getUniversidade(J)[0]=='\0' ? vazio : getUniversidade(J)); printf(" ## ");
  printf("%s", getCidadeNascimento(J)[0]=='\0' ? vazio : getCidadeNascimento(J)); printf(" ## ");
  printf("%s", getEstadoNascimento(J)[0]=='\0' ? vazio : getEstadoNascimento(J)); printf("]\n");
}

// atribui cada atributo do array de strings no objeto Jogador
Jogador ler(char* strAttr[]) {
  Jogador J;
  qtdRegistros++;
  setId(&J, atoi(strAttr[0]));
  setNome(&J, strAttr[1]);
  setAltura(&J, atoi(strAttr[2]));
  setPeso(&J, atoi(strAttr[3]));
  setUniversidade(&J, strAttr[4]);
  setAnoNascimento(&J, atoi(strAttr[5]));
  setCidadeNascimento(&J, strAttr[6]);
  setEstadoNascimento(&J, strAttr[7]);
  return J;
}

// construtores
Jogador setJogador(int id, char nome[], int altura, int peso, char universidade[], int anoNascimento, char cidadeNascimento[], char estadoNascimento[]) {
  Jogador J;
  qtdRegistros++;
  setId(&J, id);
  setNome(&J, nome);
  setAltura(&J, altura);
  setPeso(&J, peso);
  setUniversidade(&J, universidade);
  setAnoNascimento(&J, anoNascimento);
  setCidadeNascimento(&J, cidadeNascimento);
  setEstadoNascimento(&J, estadoNascimento);
  return J;
}

Jogador clone(Jogador* clone, Jogador* clonado) {
  *clone = *clonado;
}

// recebe string de uma lihna do csv e coloca os atributos na array
void atribuirAtributos(char* input, char* output[]) {
  int i = 0;
  strtok(input, "\n"); // retira quebra de linha no final da string
  output[i++] = input;
  
  // loop vai repartindo linha nas ',' e atribuindo a output[]
  while (i < NUM_ATTR && input && (input = strchr(input, ','))) {
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

int main(int argc, char *argv[]) {
  Jogador J[MAX_ENTRADAS];
  FILE *csv;
  csv = fopen("/tmp/players.csv", "r");
  char entrada[MAX_ENTRADAS][STR_LEN];
  char linha[TAM_LIN]; // linha a ser lida do csv 
  char* strAttr[NUM_ATTR]; // array de 8 strings que representam os atributos
  int id; // recebe entrada padrao para procurar no csv
  int numEntrada = 0; // contador de entradas

  // leitura da entrada padrao
  do {
    scanf("%[^\n]%*c", entrada[numEntrada]);
  } while(strcmp(entrada[numEntrada++],"FIM")!=0);
  numEntrada--;  // desconsidera ultima entrada com FIM

  // percorre as entradas
  for (int i = 0; i < numEntrada; i++) {
    id = atoi(entrada[i]);

    lerLinha(csv, linha, id);
    
    atribuirAtributos(linha, strAttr);

    J[i] = ler(strAttr);
    
    imprimir(&J[i]);
    
    rewind(csv);  
  }
  
  fclose(csv);
  return 0;
}
