#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
#include <err.h>
#include <time.h>

#define TAM_MAX_LINHA 128
#define MAX_ENTRADAS 128
#define STR_TAM 70
#define arquivoCSV "/tmp/players.csv"

#define max(a,b) \
   ({ __typeof__ (a) _a = (a); \
       __typeof__ (b) _b = (b); \
     _a > _b ? _a : _b; })

int numComparacoes = 0;

typedef struct {
  int id;
  int peso;
  int altura;
  char nome[STR_TAM];
  char universidade[STR_TAM];
  int anoNascimento;
  char cidadeNascimento[STR_TAM];
  char estadoNascimento[STR_TAM];
} Jogador;

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

// recebe string de uma lihna do csv e coloca os atributos no array
// "privado"
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
// "privado"
void lerLinha(FILE* csv, char linha[], int id) {
  rewind(csv);
  for(int n = 0; n <= id + 1; n++) {
    fgets(linha, TAM_MAX_LINHA, csv);
  }
}

// recebe arquivo csv e id do jogador e retorna registro de jogador
// "publico"
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
    scanf("%[^\n]%*c", entrada[numEntrada]);
  } while(strcmp(entrada[numEntrada++],"FIM")!=0);
  numEntrada--;  // desconsidera entrada com FIM
  return numEntrada;
}



// TIPO NO ===================================================================
typedef struct No {
	char elemento[STR_TAM];    // Conteudo do no.
	struct No* esq; 
  struct No* dir;            // Filhos da esq e dir.
	int nivel;                 // Numero de niveis abaixo do no
} No;

/**
 * Criacao do novo no
 * @param elemento Conteudo do no.
 */
No* novoNo(char elemento[]) {
  No* novo = (No*) malloc(sizeof(No));
  strcpy(novo->elemento, elemento);
  novo->esq = NULL;
  novo->dir = NULL;
  novo->nivel = 1;
  return novo;
}

/**
 * Retorna o número de níveis a partir de um vértice 
 * @param no nó que se deseja o nível.
 */
int getNivel(No* no) {
  return (no == NULL) ? 0 : no->nivel;
}

/**
 * Cálculo do número de níveis a partir de um vértice
 */
void setNivel(No* no) {
  no->nivel = 1 + max(getNivel(no->esq),getNivel(no->dir));
}

// ARVORE =======================================================
/**
 * Variavel global
 */
No* raiz;

/**
 * Criar arvore binaria.
 */
void start() {
  raiz = NULL;
}

/**
 * Metodo privado recursivo para pesquisar elemento.
 * @param x Elemento que sera procurado.
 * @param i No em analise.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisarRec(char x[], No* i) {
  bool resp;
  if (i == NULL) {
    //printf("NAO\n");
    resp = false;

  } else if (strcmp(x, i->elemento) == 0) {
    //printf("SIM\n");
    resp = true;

  } else if (strcmp(x, i->elemento) < 0) {
    printf("esq ");
    resp = pesquisarRec(x, i->esq);

  } else {
    printf("dir ");
    resp = pesquisarRec(x, i->dir);
  }
  return resp;
}
/**
 * Metodo publico iterativo para pesquisar elemento.
 * @param x Elemento que sera procurado.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisar(char x[]) {
  printf("raiz ");
  return pesquisarRec(x, raiz);
}

/*
BALANCEAMENTO
*/
No* rotacionarDir(No* no) {
  //printf("Rotacionar DIR(%s)\n", no->elemento);
  No* noEsq = no->esq;
  No* noEsqDir = noEsq->dir;

  noEsq->dir = no;
  no->esq = noEsqDir;

  setNivel(no);
  setNivel(noEsq);

  return noEsq;
}

No* rotacionarEsq(No* no) {
  //printf("Rotacionar ESQ(%s)\n", no->elemento);
  No* noDir = no->dir;
  No* noDirEsq = noDir->esq;

  noDir->esq = no;
  no->dir = noDirEsq;

  setNivel(no);
  setNivel(noDir);
  
  return noDir;
}


/**/
No* balancear(No* no) {
  if(no != NULL){
    int fator = getNivel(no->dir) - getNivel(no->esq);

    // Se balanceada
    if (abs(fator) <= 1){
      setNivel(no);

    // Se desbalanceada para a direita
    } else if (fator == 2){

      int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);

      //Se o filho a direita tambem estiver desbalanceado
      if (fatorFilhoDir == -1) {
        no->dir = rotacionarDir(no->dir);
      }
      no = rotacionarEsq(no);

    //Se desbalanceada para a esquerda
    } else if (fator == -2){

      int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);

      //Se o filho a esquerda tambem estiver desbalanceado
      if (fatorFilhoEsq == 1) {
        no->esq = rotacionarEsq(no->esq);
      }
      no = rotacionarDir(no);

    }else{
      errx(1, "Erro fator de balanceamento ");
    }
  }

  return no;
}
/**/

/**
 * Metodo privado recursivo para inserir elemento.
 * @param x Elemento a ser inserido.
 * @param i No** endereco do ponteiro No
 */
No* inserirRec(char x[], No** i) {
  if (*i == NULL) {
    *i = novoNo(x);

  } else if (strcmp(x, (*i)->elemento) < 0) {
    (*i)->esq = inserirRec(x, &((*i)->esq));

  } else if (strcmp(x, (*i)->elemento) > 0) {
    (*i)->dir = inserirRec(x, &((*i)->dir));

  } else {
    errx(1, "Erro ao inserir!");
  }
  return balancear(*i);
}
/**
 * Metodo publico iterativo para inserir elemento.
 * @param x Elemento a ser inserido.
 */
void inserir(char x[]) {
  inserirRec(x, &raiz);
}



// MAIN ===============================================================
int main(int argc, char *argv[]) {
  FILE *csv = fopen(arquivoCSV, "r");

  // variavel para armazenar entrada padrao com id's
  char entrada[MAX_ENTRADAS][TAM_MAX_LINHA]; 
  
  // leitura da entrada padrao, retorna quantidade de entradas inseridas
  const int numEntrada = leituraDeEntrada(entrada); 
  
  // iniciando arvore
  start();
  
  // criar registros "jogador" a partir das entradas armazenadas
  for (int i = 0; i < numEntrada; i++) {
    Jogador j = ler(csv, atoi(entrada[i])); // atoi(entrada[i]) = id do jogador
    // inserir nome na arvore
    inserir(j.nome);
  }

  // pesquisas
  char pesquisas[MAX_ENTRADAS][TAM_MAX_LINHA];
  const int numPesq = leituraDeEntrada(pesquisas);
  for(int i = 0; i < numPesq; i++) {
    printf("%s ", pesquisas[i]);
    printf("%s\n", pesquisar(pesquisas[i])? "SIM": "NAO");
  }
  /*
  */

  fclose(csv);
  
  return 0;
}
