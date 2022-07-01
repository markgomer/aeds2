#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <err.h>
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



// CELULA ============================================
// celula composta de objeto Jogador e ponteiro para o proximo Jogador
typedef struct Celula {
	Jogador elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* novaCelula(Jogador* elemento) {
  Celula* nova = (Celula*) malloc(sizeof(Celula));
  nova->elemento = *elemento;
  nova->prox = NULL;
  return nova;
}

//FILA PROPRIAMENTE DITA =============================
Celula* primeiro;
Celula* ultimo;

// Cria uma fila sem elementos (somente no cabeca).
void start () {
  Jogador j;
  primeiro = novaCelula(&j);
  ultimo = primeiro;
}

// Insere elemento na fila (politica FIFO).
// @param x int Elemento a inserir.
void inserir(Jogador* x) {
  ultimo->prox = novaCelula(x);
  ultimo = ultimo->prox;
}

// Remove elemento da fila (politica FIFO).
// @return Elemento removido.
Jogador remover() {
  if (primeiro == ultimo) {
    errx(1, "Erro ao remover!");
  }
  Celula* tmp = primeiro;
  primeiro = primeiro->prox;
  Jogador resp = primeiro->elemento;
  tmp->prox = NULL;
  free(tmp);
  tmp = NULL;
  return resp;
}

// Mostra os elementos separados por espacos.
void mostrar() {
  Celula* i;
  int cont = 0;
  for (i = primeiro->prox; i != NULL; i = i->prox) {
    printf("[%d] ", cont);
    imprimir(&i->elemento);
    cont++;
  }
}

// Calcula e retorna o tamanho, em numero de elementos, da lista.
int tamanho() {
  Celula* i;
  int cont = 0;
  for (i = primeiro->prox; i != NULL; i = i->prox) {
    cont++;
  }
  return cont;
}

// Calcula media aredondada das alturas dos jogadores contidos na lista
int calculaMedia() {
  double media = 0;
  Celula* i;
  for (i = primeiro->prox; i != NULL; i = i->prox) {
    media += i->elemento.altura;
  }
  media /= tamanho();
  return round(media);
}



int main(int argc, char *argv[]) {
  FILE *csv;
  csv = fopen(arquivoCSV, "r");

  // variavel para armazenar entrada padrao com id's
  char entrada[MAX_ENTRADAS][TAM_MAX_LINHA]; 
  
  // leitura da entrada padrao, retorna quantidade de entradas inseridas
  int numEntrada = leituraDeEntrada(entrada); 
  
  start();
  
  // criar registros "jogador" a partir das entradas armazenadas
  for (int i = 0; i < numEntrada; i++) {
    Jogador j = ler(csv, atoi(entrada[i])); // atoi(entrada[i]) = id
    // inserir cada registro no fim da lista
    if(tamanho()>=5) {
      remover();
      inserir(&j); 
    } else {
      inserir(&j);
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
        if(tamanho()>=5) { // checa tamanho da fila
          remover();
          inserir(&jogador); 
        } else {
          inserir(&jogador);
        }
        printf("%d\n", calculaMedia());
      }

      // operacao remover
      else if (strcmp(operacao,"R") == 0){
        printf("(R) %s\n", primeiro->prox->elemento.nome);
        remover(); 
      }
      else printf("operacao invalida\n");

  }
    
  // imprime lista de registros "jogador"
  mostrar();

  fclose(csv);
  return 0;
  
}
