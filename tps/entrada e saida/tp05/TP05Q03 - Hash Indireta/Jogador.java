import java.util.*;
public class Jogador {
  public static final String arquivoCSV = "/tmp/players.csv";
  public static final int maxEntradas = 128;
  public static int numComparacoes = 0;
  int id;
  String nome;
  int altura;
  int peso;
  String universidade;
  int anoNascimento;
  String cidadeNascimento;
  String estadoNascimento;

  public Jogador() {
  }

  public Jogador(String linha) {
    String campos[] = linha.split(",");
    this.id = Integer.parseInt(campos[0]);
    this.nome = campos[1];
    this.altura = Integer.parseInt(campos[2]);
    this.peso = Integer.parseInt(campos[3]);
    this.universidade = (campos[4].isEmpty()) ? "nao informado" : campos[4];
    this.anoNascimento = Integer.parseInt(campos[5]);
    if (campos.length > 6) {
      this.cidadeNascimento = (campos[6].isEmpty())? "nao informado": campos[6];
      if (campos.length < 8) {
        this.estadoNascimento = "nao informado";
      } else {
        this.estadoNascimento = campos[7];
      }
    } else {
      this.cidadeNascimento = "nao informado";
      this.estadoNascimento = "nao informado";
    }
  }
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

  // id,Player,height,weight,collage,born,birth_city,birth_state
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public int getAltura() {
    return altura;
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
  public void setAnoNascimento(int anoNascimento){
    this.anoNascimento = anoNascimento;
  }
  public int getAnoNascimento(){
    return anoNascimento;
  }
  public String getUniversidade() {
    return universidade;
  }
  public void setUniversidade(String universidade) {
    this.universidade = universidade;
  }
  public String getCidadeNascimento() {
    return cidadeNascimento;
  }
  public void setCidadeNascimento(String cidadeNascimento) {
    this.cidadeNascimento = cidadeNascimento;
  }
  public String getEstadoNascimento() {
    return estadoNascimento;
  }
  public void setEstadoNascimetno(String estadoNascimento) {
    this.estadoNascimento = estadoNascimento;
  }

  public void imprimir() {
    System.out.println("## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
        + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + " ##");
  }
  public String toString() {
    return "[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
      + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]";
  }

  // retorna String com conteudo da linha correspondente ao 
  // id enviado como parametro
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

  // preenche vetor enviado como parametro com leitura de entrada padrao
  // retorna quantidade de entradas efetuadas
  public static int leituraDeEntrada(int entrada[]) {
    int numEntrada = 0;
    do {
      entrada[numEntrada] = MyIO.readInt();
    } while (entrada[numEntrada++] != -1);
    numEntrada--;   //Desconsiderar linha contendo a palavra FIM
    return numEntrada;
  }

  // preenche vetor enviado como parametro com leitura de entrada padrao
  // retorna quantidade de entradas efetuadas
  public static int leituraDeEntrada(String entrada[]) {
    int numEntrada = 0;
    do {
      entrada[numEntrada] = MyIO.readLine();
    } while (!entrada[numEntrada++].equals("FIM"));
    numEntrada--;   //Desconsiderar linha contendo a palavra FIM
    return numEntrada;
  }

  // =======================================================
  // CLASSE CELULA =========================================
  // =======================================================
  public static class Celula {
    public String elemento; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.
    
    Celula(String elemento) {
      this.elemento = elemento;
      this.prox = null;
    }
    
    Celula(String elemento, Celula prox) {
      this.elemento = elemento;
      this.prox = prox;
    }
  }
  
  // =======================================================
  // CLASSE LISTA =========================================
  // =======================================================
  public static class Lista {
    private Celula primeiro; // Primeira celula: SEM elemento valido.
    private Celula ultimo; // Ultima celula: COM elemento valido.

    public Lista() {
      primeiro = new Celula("");
      ultimo = primeiro;
    }

    public boolean pesquisar(String x) {
      boolean retorno = false;
      for (Celula i = primeiro.prox; i != null; i = i.prox) {
        if (++numComparacoes!=0 && i.elemento.equals(x)) {
          retorno = true;
          i = ultimo;
        }
      }
      return retorno;
    }

    public void inserirInicio(Jogador elemento) {
      Celula tmp = new Celula(elemento.nome);
      tmp.prox = primeiro.prox;
      primeiro.prox = tmp;
      if (primeiro == ultimo) {
        ultimo = tmp;
      }
      tmp = null;
    }

    public void inserirFim(Jogador elemento) {
      Celula tmp = new Celula(elemento.nome);
      ultimo.prox = tmp;
      ultimo = ultimo.prox;
      tmp = null;
    }

  }
  

  // ======================================================
  // CLASSE HASH ==========================================
  // ======================================================
  static class HashIndiretoLista {
    Lista tabela[];
    int tamanho;
    final int NULO = -1;

    public HashIndiretoLista() {
      this(25);
    }

    public HashIndiretoLista(int tamanho) {
      this.tamanho = tamanho;
      tabela = new Lista[tamanho];
      for (int i = 0; i < tamanho; i++) {
        tabela[i] = new Lista();
      }
    }

    public int h(Jogador elemento) {
      return elemento.altura % tamanho;
    }

    boolean pesquisar(String elemento) {
      //int pos = h(elemento);
      boolean resp = false;
      for (int i = 0; i<tamanho; i++) {
        if(tabela[i].pesquisar(elemento)) {
          resp = true;
          i = tamanho;
        }
      }
      return resp;
    }

    public void inserirInicio(Jogador elemento) {
      int pos = h(elemento);
      tabela[pos].inserirInicio(elemento);
    }
    /*
    void remover(int elemento){
        int resp = NULO;
        if (pesquisar(elemento) == false){
          throw new Exception("Erro ao remover!");
        } else {
          int pos = h(elemento);
          resp = tabela[pos].remover(elemento);
        }
        return resp;
    }
    */
  }


  public static void main(String[] args) throws Exception {
    HashIndiretoLista hash = new HashIndiretoLista();

    // array que armazena as linhas de entrada
    int[] entrada = new int[maxEntradas];
    // Primeira leitura da entrada padrao com os ids
    int numEntrada = leituraDeEntrada(entrada);
    
    for(int i=0; i<numEntrada; i++) {
      Jogador jogador = new Jogador(leLinhaCSV(entrada[i]));
      hash.inserirInicio(jogador);
    }

    String[] pesquisas = new String[maxEntradas];
 
    int numPesquisas = leituraDeEntrada(pesquisas);

    long inicio = new Date().getTime();
    for(int i = 0 ; i < numPesquisas; i++) {
      MyIO.print(pesquisas[i] + " ");
      MyIO.println(hash.pesquisar(pesquisas[i])? "SIM":"NAO");
    }
    long fim = new Date().getTime();

    //log
    String conteudo = "696809\t" + (fim-inicio) + "\t" + numComparacoes;
    Arq.openWriteClose("matricula_hashIndireta.txt", conteudo);
  }
}
