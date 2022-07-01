/*
Marco Aurelio Silva de Souza Junior - 696809
*/

import java.util.*;

public class Jogador {
  static final int k = 10;
  static final String arquivoCSV = "/tmp/players.csv";
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

  // construtor vazio
  public Jogador() {
    Jogador.qtdRegistros++;
  }

  // construtor recebe array de strings com atributos 
  public Jogador(String atributo[]) {
    this.setId(Integer.parseInt(atributo[0]));
    this.setNome(atributo[1]);
    this.setAltura(Integer.parseInt(atributo[2]));
    this.setPeso(Integer.parseInt(atributo[3]));
    this.setUniversidade(atributo[4]);
    this.setAnoNascimento(Integer.parseInt(atributo[5]));
    this.setCidadeNascimento(atributo[6]);
    this.setEstadoNascimento(atributo[7]);
  }

  // cria clone do objeto
  public Jogador clone() {
		Jogador novo = new Jogador();
		novo.id = this.id;
		novo.nome = this.nome;
		novo.altura = this.altura;
		novo.peso = this.peso;
		novo.universidade = this.universidade;
		novo.cidadeNascimento = this.cidadeNascimento;
		novo.estadoNascimento = this.estadoNascimento;
		return novo;
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
    if(estado.isEmpty()) {
      this.estadoNascimento = "ZZZ";
    } else {
      this.estadoNascimento = estado;
    }
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
    MyIO.print((getUniversidade().isEmpty() ? vazio : getUniversidade()) + " ## ");
    MyIO.print((getCidadeNascimento().isEmpty() ? vazio : getCidadeNascimento()) + " ## ");
    MyIO.println((getEstadoNascimento().equals("ZZZ") ? vazio : getEstadoNascimento()) + "]");
  }


  public static String leLinhaCSV(int id) {
    Arq.openRead(arquivoCSV);
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


  // troca dois elementos nas posicoes i e j no vetor recebido
  public static void swap(Jogador array[], int i, int j) {
    Jogador temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    numMovimentacoes += 3;
  }


  public static void quicksort(Jogador array[]) {
    int n = array.length;
    quicksort(array, 0, n-1);
  }
  private static void quicksort(Jogador array[], int esq, int dir) {
    int i = esq, j = dir;
    Jogador pivo = array[(dir+esq)/2];
    while (i <= j) {
      while (
        numComparacoes++ != 0 &&
        array[i].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0 || (
          numComparacoes++ != 0 &&
          array[i].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) == 0 &&
          numComparacoes++ != 0 &&
          array[i].getNome().compareTo(pivo.getNome()) < 0
        )
      ) i++;
      while (
        numComparacoes++ != 0 &&
        array[j].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) > 0 || (
          numComparacoes++ != 0 &&
          array[j].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) == 0 &&
          numComparacoes++ != 0 &&
          array[j].getNome().compareTo(pivo.getNome()) > 0
        )
      ) 
        j--;
      if (i <= j) {
        swap(array, i, j);
        i++;
        j--;
      }
    }
    if (esq < j)  quicksort(array, esq, j);
    if (i < k && i < dir)  quicksort(array, i, dir);
  }

  public static void main(String[] args) {
    int[] entrada = new int[maxEntradas]; // array que armazena as linhas de entrada
    
    int numEntrada = leituraDeEntrada(entrada); //Leitura da entrada padrao
    
    Jogador[] jogador = new Jogador[numEntrada]; // criar vetor de objetos Jogador
    
    for(int i = 0; i < numEntrada; i++) {
      jogador[i] = ler(entrada[i]);  // le os dados de "linha" e armazena nos atributos do obj "Jogador"
    }
    
    long inicio = new Date().getTime();
    quicksort(jogador);  // ordena vetor pelo nome dos jogadores
    long fim = new Date().getTime();

    // mostra os dados para k = 10
    for (int i = 0; i < k; i++) {
      jogador[i].imprimir();  // mostra dados na tela
    }

    // log
    String conteudo = "696809\t" + numComparacoes + "\t" + numMovimentacoes + "\t" + (fim-inicio);
    Arq.openWriteClose("matricula_quicksort_parcial.txt", conteudo);
  }
}
