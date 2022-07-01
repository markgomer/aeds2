/* Marco Aurelio Silva de Souza Junior - 696809 */

import java.util.*;

public class Jogador {
  static final int maxEntradas = 512;
  static final String arquivoCSV = "/tmp/players.csv";
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
    MyIO.print((getUniversidade().isEmpty() ? vazio : getUniversidade()) + " ## ");
    MyIO.print((getCidadeNascimento().isEmpty() ? vazio : getCidadeNascimento()) + " ## ");
    MyIO.println((getEstadoNascimento().isEmpty() ? vazio : getEstadoNascimento()) + "]");
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


  public static void swap(Jogador array[], int i, int j) {
    Jogador temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    numMovimentacoes+=3;
  }


  public static void heapSort(Jogador jogador[]) {
    int n = jogador.length;
    //Alterar o vetor ignorando a posicao zero
    Jogador[] tmp = new Jogador[n+1];
    for(int i = 0; i < n; i++){
      tmp[i+1] = jogador[i];
      numMovimentacoes++;
    }

    //Contrucao do heap
    for(int tamHeap = 2; tamHeap <= n; tamHeap++){
      constroi(tmp, tamHeap);
    }

    //Ordenacao propriamente dita
    int tamHeap = n;
    while(tamHeap > 1){
      swap(tmp, 1, tamHeap--);
      reconstroi(tmp, tamHeap);
    }

    //Alterar o vetor para voltar a posicao zero
    numMovimentacoes++;
    for(int i = 0; i < n; i++){
      jogador[i] = tmp[i+1];
    }
  }


  public static void constroi(Jogador jogador[], int tamHeap){
    for(
      int i = tamHeap; 
      i > 1 && (
        numComparacoes++!=0 &&
        jogador[i].getAltura() > jogador[i/2].getAltura() || (
          numComparacoes++!=0 &&
          jogador[i].getAltura() == jogador[i/2].getAltura() &&
          numComparacoes++!=0 &&
          jogador[i].getNome().compareTo(jogador[i/2].getNome()) > 0
        )
      ) ;
      i /= 2) {
      swap(jogador, i, i/2);
    }
  }


  public static void reconstroi(Jogador jogador[], int tamHeap){
    int i = 1;
    while(i <= tamHeap/2) {
      int filho = getMaiorFilho(jogador, i, tamHeap);
      if(
        numComparacoes++!=0 &&
        jogador[i].getAltura() < jogador[filho].getAltura() || (
          numComparacoes++!=0 &&
          jogador[i].getAltura() == jogador[filho].getAltura() &&
          numComparacoes++!=0 &&
          jogador[i].getNome().compareTo(jogador[filho].getNome()) < 0
        )
      ) {
        swap(jogador, i, filho);
        i = filho;
      }else{
        i = tamHeap;
      }
    }
  }


  public static int getMaiorFilho(Jogador jogador[], int i, int tamHeap){
    int filho;
    if (
      2*i == tamHeap ||
      numComparacoes++!=0 &&
      jogador[2*i].getAltura() > jogador[2*i+1].getAltura() || (
        numComparacoes++!=0 &&
        jogador[2*i].getAltura() == jogador[2*i+1].getAltura() &&
        numComparacoes++!=0 &&
        jogador[2*i].getNome().compareTo(jogador[2*i+1].getNome()) > 0
      )
    ) {
      filho = 2*i;
    } else {
      filho = 2*i + 1;
    }
    return filho;
  }

  public static void main(String[] args) {
    int[] entrada = new int[maxEntradas]; // array que armazena as linhas de entrada
    
    int numEntrada = leituraDeEntrada(entrada); //Leitura da entrada padrao
    
    Jogador[] jogador = new Jogador[numEntrada]; // criar vetor de objetos Jogador
    
    for(int i = 0; i < numEntrada; i++) {
      jogador[i] = ler(entrada[i]);  // le os dados de "linha" e armazena nos atributos do obj "Jogador"
    }

    long inicio = new Date().getTime();
    heapSort(jogador);
    long fim = new Date().getTime();

    for (int i = 0; i < numEntrada; i++) {
      jogador[i].imprimir();  // mostra dados na tela
    }

    // log
    String conteudo = "696809\t" + numComparacoes + "\t" + numMovimentacoes + "\t" + (fim-inicio);
    Arq.openWriteClose("matricula_heapsort.txt", conteudo);
  }
}
