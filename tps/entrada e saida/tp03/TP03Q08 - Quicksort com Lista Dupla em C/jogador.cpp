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
  printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
    J->id,
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
 * Insere um elemento na primeira posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirInicio(Jogador* x) {
   CelulaDupla* tmp = novaCelulaDupla(x);

   tmp->ant = primeiro;
   tmp->prox = primeiro->prox;
   primeiro->prox = tmp;
   if (primeiro->elemento.id == ultimo->elemento.id) {                    
      ultimo = tmp;
   } else {
      tmp->prox->ant = tmp;
   }
   tmp = NULL;
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
 * Remove um elemento da primeira posicao da lista.
 * @return resp int elemento a ser removido.
 */
Jogador removerInicio() {
   if (primeiro->elemento.id == ultimo->elemento.id) {
      errx(1, "Erro ao remover (vazia)!");
   }

   CelulaDupla* tmp = primeiro;
   primeiro = primeiro->prox;
   Jogador resp = primeiro->elemento;
   tmp->prox = primeiro->ant = NULL;
   free(tmp);
   tmp = NULL;
   return resp;
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


/**
 * Insere um elemento em uma posicao especifica considerando que o 
 * primeiro elemento valido esta na posicao 0.
 * @param x int elemento a ser inserido.
 * @param pos int posicao da insercao.
 * @throws Exception Se <code>posicao</code> invalida.
 */
void inserir(Jogador* x, int pos) {

  int tam = tamanho();

  if(pos < 0 || pos > tam){
    errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
  } else if (pos == 0){
    inserirInicio(x);
  } else if (pos == tam){
    inserirFim(x);
  } else {
    // Caminhar ate a posicao anterior a insercao
    CelulaDupla* i = primeiro;
    for(int j = 0; j < pos; j++, i = i->prox);

    CelulaDupla* tmp = novaCelulaDupla(x);
    tmp->ant = i;
    tmp->prox = i->prox;
    tmp->ant->prox = tmp->prox->ant = tmp;
    tmp = i = NULL;
  }
}


/**
 * Remove um elemento de uma posicao especifica da lista
 * considerando que o primeiro elemento valido esta na posicao 0.
 * @param posicao Meio da remocao.
 * @return resp int elemento a ser removido.
 * @throws Exception Se <code>posicao</code> invalida.
 */
Jogador remover(int pos) {
   Jogador resp;
   int tam = tamanho();

   if (primeiro == ultimo){
      errx(1, "Erro ao remover (vazia)!");
   } else if(pos < 0 || pos >= tam){
      errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
   } else if (pos == 0){
      resp = removerInicio();
   } else if (pos == tam - 1){
      resp = removerFim();
   } else {
      // Caminhar ate a posicao anterior a insercao
      CelulaDupla* i = primeiro->prox;
      int j;
      for(j = 0; j < pos; j++, i = i->prox);

      i->ant->prox = i->prox;
      i->prox->ant = i->ant;
      resp = i->elemento;
      i->prox = i->ant = NULL;
      free(i);
      i = NULL;
   }

   return resp;
}


// Mostra os elementos separados por espacos.
void mostrar() {
  CelulaDupla* i;
  int cont = 0;
  for (i = primeiro->prox; i != NULL; i = i->prox) {
    imprimir(&i->elemento);
  }
}


/**
 * Mostra os elementos da lista de forma invertida 
 * e separados por espacos.
 */
void mostrarInverso() {
  CelulaDupla* i;
  int cont = 0;
  for (i = ultimo; i != primeiro; i = i->ant){
    printf("[%d] ", cont);
    imprimir(&i->elemento);
    cont++;
  }
  printf("] \n"); // Termina de mostrar.
}


/**
 * Procura um elemento e retorna se ele existe.
 * @param x Elemento a pesquisar.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisar(Jogador x) {
  bool resp = false;
  CelulaDupla* i;

  for (i = primeiro->prox; i != NULL; i = i->prox) {
    if(i->elemento.id == x.id){
      resp = true;
      i = ultimo;
    }
  }
  return resp;
}


// ORDENACAO ===========================================================
void swap(Jogador *i, Jogador *j) {
   Jogador temp = *i;
   *i = *j;
   *j = temp;
   numMovimentacoes+=3;
}

void quicksortRec(Jogador *array, int esq, int dir) {
    int i = esq, j = dir;
    Jogador pivo = array[(dir+esq)/2];
    numMovimentacoes++;
    while (i <= j) {
      while (
        numComparacoes++ != 0 &&
        strcmp(array[i].estadoNascimento, pivo.estadoNascimento) < 0 || (
          numComparacoes++ != 0 &&
          strcmp(array[i].estadoNascimento, pivo.estadoNascimento) == 0 &&
          numComparacoes++ != 0 &&
          strcmp(array[i].nome, pivo.nome) < 0
        )
      ) 
      i++;
      while (
        numComparacoes++ != 0 &&
        strcmp(array[j].estadoNascimento, pivo.estadoNascimento) > 0 || (
          numComparacoes++ != 0 &&
          strcmp(array[j].estadoNascimento, pivo.estadoNascimento) == 0 &&
          numComparacoes++ != 0 &&
          strcmp(array[j].nome, pivo.nome) > 0
        )
      ) 
      j--;
      if (i <= j) {
          swap(array + i, array + j);
          i++;
          j--;
      }
    }
    if (esq < j)  quicksortRec(array, esq, j);
    if (i < dir)  quicksortRec(array, i, dir);
}
void quicksort(Jogador *array, int n) {
    quicksortRec(array, 0, n-1);
}



// MAIN ===============================================================
int main(int argc, char *argv[]) {
  FILE *csv = fopen(arquivoCSV, "r");

  // variavel para armazenar entrada padrao com id's
  char entrada[MAX_ENTRADAS][TAM_MAX_LINHA]; 
  
  // leitura da entrada padrao, retorna quantidade de entradas inseridas
  const int numEntrada = leituraDeEntrada(entrada); 
  
  Jogador array[numEntrada];
  
  // criar registros "jogador" a partir das entradas armazenadas
  for (int i = 0; i < numEntrada; i++) {
    array[i] = ler(csv, atoi(entrada[i])); // atoi(entrada[i]) = id do jogador
  }

  // ordenação
  clock_t inicio = clock();
  quicksort(array, numEntrada);
  clock_t fim = clock();
  
  // iniciando lista
  start();
  for(int i = 0; i < numEntrada; i++) {
    inserirFim(&array[i]);
  }

  // imprime lista de registros "jogador"
  mostrar();

  // log
  FILE* log = fopen("matricula_quicksort2.txt", "w+");
  fprintf(log, "696809\t");
  fprintf(log, "%d\t", numComparacoes);
  fprintf(log, "%d\t", numMovimentacoes);
  fprintf(log, "%ld\t", (fim-inicio));
  
  fclose(log);
  fclose(csv);
  
  return 0;
}
