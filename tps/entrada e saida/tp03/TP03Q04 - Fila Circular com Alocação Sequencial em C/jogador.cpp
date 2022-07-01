#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define TAM_MAX_LINHA 250
#define MAX_ENTRADAS 250
#define arquivoCSV "/tmp/players.csv"


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

Jogador lista[MAX_ENTRADAS];
int n = 0; // tamanho usado de "lista"


void setJogador(char* strAttr[], Jogador* J) {
  J->id = atoi(strAttr[0]);
  strcpy(J->nome, strAttr[1]);
  J->altura = atoi(strAttr[2]);
  J->peso = atoi(strAttr[3]);
  strcpy(J->universidade, strAttr[4]);
  J->anoNascimento = atoi(strAttr[5]);
  strcpy(J->cidadeNascimento, strAttr[6]);
  strcpy(J->estadoNascimento, strAttr[7]);
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
    J->estadoNascimento[0]=='\0' ? vazio : J->estadoNascimento
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



/*
 * OPERACOES DA LISTA
 */
void inserirInicio(Jogador* jogador) {
  n++;
  for(int i = n-1; i>=1; i--) {
    lista[i] = clone(&lista[i-1]);
  }
  lista[0] = clone(jogador);
}

void inserir(int posicao, Jogador* jogador) {
  n++;
  for (int i = n-1; i>posicao; i--)
    lista[i] = clone(&lista[i-1]);

  lista[posicao] = clone(jogador);
}

void inserirFim(Jogador* jogador) {
  n++;
  lista[n-1] = clone(jogador);
}

Jogador removerInicio() {
  Jogador retorno = lista[0];
  for(int i = 0; i < n-1; i++) {
    lista[i] = clone(&lista[i+1]);
  }
  //printf("(R) %s\n", retorno.nome);
  n--;
  return retorno;
}

Jogador remover(int posicao) {
  Jogador retorno = clone(&lista[posicao]);
  for(int i = posicao; i < n-1; i++) {
    lista[i] = clone(&lista[i+1]);
  }
  printf("(R) %s\n", retorno.nome);
  n--;
  return retorno;
}

Jogador removerFim() {
  Jogador retorno = clone(&lista[n-1]);
  n--;
  printf("(R) %s\n", retorno.nome);
  return retorno;
}

// imprime lista de registros "jogador"
void imprimirLista() {
  for(int i = 0; i < n; i++) {
    printf("[%d] ", i);
    imprimir(&lista[i]);
  }
}


int calculaMedia() {
  double media = 0;
  for(int i = 0; i < n; i++) {
    media += lista[i].altura;
  }
  media /= n;
  return round(media);
}



int main(int argc, char *argv[]) {
  FILE *csv;
  csv = fopen(arquivoCSV, "r");

  // variavel para armazenar entrada padrao com id's
  char entrada[MAX_ENTRADAS][TAM_MAX_LINHA]; 
  
  // leitura da entrada padrao, retorna quantidade de entradas inseridas
  int numEntrada = leituraDeEntrada(entrada); 

  // criar registros "jogador" a partir das entradas armazenadas
  for (int i = 0; i < numEntrada; i++) {
    Jogador j = ler(csv, atoi(entrada[i])); // atoi(entrada[i]) = id
    // inserir cada registro no fim da lista
    if(n>=5) {
      removerInicio();
      inserirFim(&j); 
    } else {
      inserirFim(&j);
    }
    printf("%d\n", calculaMedia());
  }

  int numOperacoes, posicao, id; 
  scanf("%d",&numOperacoes);
  
  for(int i = 0; i<numOperacoes; i++) {
    Jogador jogador;
    char operacao[2]; scanf("%s", operacao);

      //operacao inserir
      if (strcmp(operacao,"I") == 0){ 
        scanf("%d", &id); 
        jogador = ler(csv, id);
        if(n>=5) { // checa tamanho da fila
          removerInicio();
          inserirFim(&jogador); 
        } else {
          inserirFim(&jogador);
        }
        printf("%d\n", calculaMedia());
      }

      // operacao remover
      else if (strcmp(operacao,"R") == 0){
        printf("(R) %s\n", lista[0].nome);
        removerInicio(); 
      }
      else printf("operacao invalida\n");

  }
    
  // imprime lista de registros "jogador"
  imprimirLista();

  fclose(csv);
  return 0;
  
}
