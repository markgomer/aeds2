/* Marco Aurelio Silva de Souza Junior - 696809 */

import java.util.*;

public class Jogador {
  static final int maxEntradas = 512;
  static int numComparacoes;
  static int numMovimentacoes;
  static int qtdRegistros; // quantidade de jogadores registrados

  private int id;
  private String nome;
  private int altura;
  private int peso;
  private String universidade;
  private int anoNascimento;
  private String cidadeNascimento;
  private String estadoNascimento;
  
  // Construtores
  public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
    Jogador.qtdRegistros++;
    setId(id);
    setNome(nome);
    setAltura(altura);
    setPeso(peso);
    setUniversidade(universidade);
    setAnoNascimento(anoNascimento);
    setCidadeNascimento(cidadeNascimento);
    setEstadoNascimento(estadoNascimento);
  }

  // construtor com universidade
  public Jogador(String universidade) {
    Jogador.qtdRegistros++;
    setUniversidade(universidade);
  }

  // construtor recebe array de strings com atributos 
  public Jogador(String atributo[]) {
    Jogador.qtdRegistros++;
    setId(Integer.parseInt(atributo[0]));
    setNome(atributo[1]);
    setAltura(Integer.parseInt(atributo[2]));
    setPeso(Integer.parseInt(atributo[3]));
    setUniversidade(atributo[4]);
    setAnoNascimento(Integer.parseInt(atributo[5]));
    setCidadeNascimento(atributo[6]);
    setEstadoNascimento(atributo[7]);
  }

  // cria clone do objeto
  public Jogador(Jogador clonado) {
    Jogador.qtdRegistros++;
    setId(clonado.id);
    setAltura(clonado.altura);
    setPeso(clonado.peso);
    setUniversidade(clonado.universidade);
    setAnoNascimento(clonado.anoNascimento);
    setCidadeNascimento(clonado.cidadeNascimento);
    setEstadoNascimento(clonado.estadoNascimento);
  }

  //getters n' setters
  public int getId() {
    return this.id; 
  }
  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getAltura() {
    return this.altura;
  }
  public void setAltura(int altura) {
    this.altura = altura;
  }

  public int getPeso() {
    return peso;
  }
  public void setPeso(int peso) {
    this.peso = peso;
  }

  public String getUniversidade() {
    return this.universidade;
  }
  public void setUniversidade(String universidade) {
    if(universidade.isEmpty())
      this.universidade = "ZZZ";
    else 
      this.universidade = universidade;
  }
  
  public int getAnoNascimento() {
    return this.anoNascimento;
  }
  public void setAnoNascimento(int anoNascimento) {
    this.anoNascimento = anoNascimento;
  }

  public String getCidadeNascimento() {
    return this.cidadeNascimento;
  }
  public void setCidadeNascimento(String cidade) {
    this.cidadeNascimento = cidade;
  }

  public String getEstadoNascimento() {
    return this.estadoNascimento;
  }
  public void setEstadoNascimento(String estado) {
    this.estadoNascimento = estado;
  }

  // mostra na tela todos os dados do objeto
  public void imprimir() {
    String vazio = "nao informado";
    MyIO.print("[");
    MyIO.print(getId() + " ## ");
    MyIO.print((getNome().isEmpty() ? vazio : getNome()) + " ## ");
    MyIO.print((getAltura()==-1 ? vazio : getAltura()) + " ## ");
    MyIO.print((getPeso()==-1 ? vazio : getPeso()) + " ## ");
    MyIO.print((getAnoNascimento()==-1 ? vazio : getAnoNascimento()) + " ## ");
    MyIO.print((getUniversidade().equals("ZZZ") ? vazio : getUniversidade()) + " ## ");
    MyIO.print((getCidadeNascimento().isEmpty() ? vazio : getCidadeNascimento()) + " ## ");
    MyIO.println((getEstadoNascimento().isEmpty() ? vazio : getEstadoNascimento()) + "]");
  }

  public static String leLinhaCSV(int id) {
    Arq.openRead("/tmp/players.csv");
    String resp = "";
    // mover cursor do csv ate a linha do id
    for(int n = 0; n <= id + 1; n++) { 
      resp = Arq.readLine();  // le a linha com id inserido e armazena a string em "linha"
    }
    Arq.close();
    return resp;
  }

  // le linha do csv com id indicado e cria um objeto "Jogador"
  public static Jogador ler(int id) {
    String linha = leLinhaCSV(id);  // armazena uma linha de entrada de cada vez 
    String[] atributos = linha.split(",", -1); // divide string de entrada e insere os atributos no array "atributos"
    Jogador j = new Jogador(atributos);
    return j;
  }

  // retorna quantidade de entradas efetuadas
  public static int leituraDeEntrada(int entrada[]) {
    int numEntrada = 0;
    do {
      entrada[numEntrada] = MyIO.readInt();
      numComparacoes++;
    } while (entrada[numEntrada++] != -1);
    numEntrada--;   //Desconsiderar linha contendo a palavra FIM
    return numEntrada;
  }
    
  // retorna quantidade de entradas efetuadas
  public static int leituraDeEntrada(String entrada[]) {
    int numEntrada = 0;
    do {
      entrada[numEntrada] = MyIO.readLine();
      numComparacoes++;
    } while (!entrada[numEntrada++].equals("FIM"));
    numEntrada--;   //Desconsiderar linha contendo a palavra FIM
    return numEntrada;
  }

  // ORDENACAO
  public static void sort(Jogador array[]) {
    int n = array.length;
    mergesort(array, 0, n-1);
 }
 private static void mergesort(Jogador array[], int esq, int dir) {
    if (esq < dir){
       int meio = (esq + dir) / 2;
       mergesort(array, esq, meio);
       mergesort(array, meio + 1, dir);
       intercalar(array, esq, meio, dir);
    }
 }

 public static void intercalar(Jogador array[], int esq, int meio, int dir){
    int n1, n2, i, j, k;

    //Definir tamanho dos dois subarrays
    n1 = meio-esq+1;
    n2 = dir - meio;

    Jogador[] a1 = new Jogador[n1+1];
    Jogador[] a2 = new Jogador[n2+1];

    //Inicializar primeiro subarray
    for(i = 0; i < n1; i++){
      a1[i] = array[esq+i];
      numMovimentacoes++;
    }
    
    //Inicializar segundo subarray
    for(j = 0; j < n2; j++){
      a2[j] = array[meio+j+1];
      numMovimentacoes++;
    }
    
    //Sentinela no final dos dois arrays
    a1[i] = new Jogador("ZZZZZZ");
    a2[j] = new Jogador("ZZZZZZ");

    //Intercalacao propriamente dita
    for(i = j = 0, k = esq; k <= dir; k++) {
      if(
        numComparacoes++ != 0 && 
        a1[i].getUniversidade().compareTo(a2[j].getUniversidade()) < 0 || (
          numComparacoes++ != 0 &&
          a1[i].getUniversidade().compareTo(a2[j].getUniversidade()) == 0 &&
          numComparacoes++ != 0 &&
          a1[i].getNome().compareTo(a2[j].getNome()) < 0
        )
      ) {
        array[k] = a1[i++];
        numMovimentacoes++;
      }
      else {
        array[k] = a2[j++];
        numMovimentacoes++;
      }
    }
 }

  public static void main(String[] args) {
    int[] entrada = new int[maxEntradas]; // array que armazena as linhas de entrada
    
    int numEntrada = leituraDeEntrada(entrada); //Leitura da entrada padrao
    
    Jogador[] jogador = new Jogador[numEntrada]; // criar vetor de objetos Jogador
    
    for(int i = 0; i < numEntrada; i++) {
      jogador[i] = ler(entrada[i]);  // le os dados de "linha" e armazena nos atributos do obj "Jogador"
    }

    long inicio = new Date().getTime();
    sort(jogador);  // ordena vetor pelo nome dos jogadores
    long fim = new Date().getTime();

    for (int i = 0; i < numEntrada; i++) {
      jogador[i].imprimir();  // mostra dados na tela
    }

    // log
    String conteudo = "696809\t" + numComparacoes + "\t" + numMovimentacoes + "\t" + (fim-inicio);
    Arq.openWriteClose("matricula_mergesort.txt", conteudo);
  }
}
