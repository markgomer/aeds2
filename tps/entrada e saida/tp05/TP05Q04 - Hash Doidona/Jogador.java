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

  // =================================================================
  // CLASSE HASH COM RESERVA==========================================
  // =================================================================
  public static class T1 {
    Jogador tabela[];
    // m1:tamanho tabela; m2:tamanho reserva
    // m =m1+m2; reserva:no. elementos na reserva
    int m1, m2, m, reserva; 
    Jogador NULO = null;

    T2 rehash;
    Lista lista;
    ArvoreBinaria arvore;
 
    public T1 (){
      this(11, 0);
    }
 
    public T1 (int m1, int m2){
      this.m1 = m1;
      this.m2 =  m2;
      this.m = m1 + m2;
      this.tabela = new Jogador [this.m];
      for(int i = 0; i < m; i++){
        tabela[i] = NULO;
      }
      rehash = new T2();
      lista = new Lista();
      arvore = new ArvoreBinaria();

      reserva  = 0;
    }
 
    public int h(Jogador elemento) {
      return elemento.altura % m1;
    }
 
    public boolean inserir (Jogador elemento) throws Exception {
      boolean resp = false;
      if(elemento != NULO) {
        int pos = h(elemento);

        if(tabela[pos] == NULO) {
          tabela[pos] = elemento;
          resp = true;
        
        } else {
          switch(reserva%3) {
            case 0 : rehash.inserir(elemento); break;
            case 1 : lista.inserirFim(elemento); break;
            case 2 : arvore.inserir(elemento); break;
          }
          reserva++;
          resp = true;
        }
      }
      return resp;
    }
 
    public boolean pesquisar (String elemento){
      boolean resp = false;
      // pesquisar tabela principal
      for(int i = 0; i < m; i++){
        if(tabela[i]!=null && ++numComparacoes!=0 && tabela[i].nome.contains(elemento)){
          resp = true;
          i = m;
        }
      }
      // pesquisar tabela T2
      if(resp == false) {
        resp = rehash.pesquisar(elemento);
        if(resp == false) {
          resp = lista.pesquisar(elemento);
          if(resp == false) {
            resp = arvore.pesquisar(elemento);
          }
        }
      }

      return resp;
    }
  }  
 

  // =================================================================
  // CLASSE HASH COM HERASH===========================================
  // =================================================================
  public static class T2 {
    Jogador tabela[];
    int m;
    Jogador NULO = null;

    public T2() {
      this(9);
    }

    public T2(int m) {
      this.m = m;
      this.tabela = new Jogador[this.m];
      for (int i = 0; i < m; i++) {
        tabela[i] = NULO;
      }
    }

    public int h(Jogador elemento) {
      return elemento.altura % m;
    }

    public int reh(Jogador elemento) {
      return (elemento.altura + 1) % m;
    }

    public boolean inserir(Jogador elemento) {
      boolean resp = false;

      if (elemento != NULO) {

        int pos = h(elemento);

        if (tabela[pos] == null) {
          tabela[pos] = elemento;
          resp = true;

        } else {

          pos = reh(elemento);

          if (tabela[pos] == null) {
            tabela[pos] = elemento;
            resp = true;
          }
        }
      }

      return resp;
    }

    public boolean pesquisar(String elemento) {
      boolean resp = false;
      for(int i = 0; i < m; i++){
        if(tabela[i]!=null && ++numComparacoes!=0 && tabela[i].nome.equals(elemento)){
          resp = true;
          i = m;
        }
      }
      return resp;
    }
  }
 

  // =================================================================
  // CLASSE LISTA==========================================
  // =================================================================
  static class Celula {
    public Jogador elemento; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.
    public Celula() {
      this.elemento = null;
      this.prox = null;
    }
    public Celula(Jogador elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
  }

  static class Lista {
    private Celula primeiro;
    private Celula ultimo;

    public Lista() {
      primeiro = new Celula();
      ultimo = primeiro;
    }

    public void inserirFim(Jogador x) {
      ultimo.prox = new Celula(x);
      ultimo = ultimo.prox;
    }

    public void mostrar() {
      int j = 0;
      for (Celula i = primeiro.prox; i != null; i = i.prox) {
        System.out.print("[" + (j++) + "] ");
        i.elemento.imprimir();
      }
    }

    public boolean pesquisar(String x) {
      boolean resp = false;
      for (Celula i = primeiro.prox; i != null; i = i.prox) {
        if(++numComparacoes!=0 && i.elemento.getNome().contains(x)){
          resp = true;
          i = ultimo;
        }
      }
      return resp;
    }

  }


  // =================================================================
  // CLASSE ARVORE BINARIA ===========================================
  // =================================================================
  static class No {
    public Jogador elemento; // Conteudo do no.
    public No esq, dir; // Filhos da esq e dir.

    public No(Jogador elemento) {
      this(elemento, null, null);
    }

    public No(Jogador elemento, No esq, No dir) {
      this.elemento = elemento;
      this.esq = esq;
      this.dir = dir;
    }
  }


  public static class ArvoreBinaria {
    private No raiz; // Raiz da arvore.

    public ArvoreBinaria() {
      raiz = null;
    }

    public boolean pesquisar(String elemento) {
      MyIO.print("raiz ");
      return pesquisar(raiz, elemento);
    }    
    private boolean pesquisar(No no, String x) {
      boolean resp = false;
      if (++numComparacoes!=0 && no == null) { 
        resp = false;
        
      } else if (++numComparacoes!=0 && x.equals(no.elemento.nome)) { 
        resp = true; 
        
      } else if (!resp) { 
        MyIO.print("esq ");
        resp = pesquisar(no.esq, x); 
        if(!resp) {
          MyIO.print("dir ");
          resp = pesquisar(no.dir, x); 
        }
      }
      return resp;
    }    

    public void inserir(Jogador x) throws Exception {
      raiz = inserir(x, raiz);
    }

    private No inserir(Jogador x, No i) throws Exception {
      if (i == null) {
        i = new No(x);

      } else if (x.nome.compareTo(i.elemento.nome) < 0) {
        i.esq = inserir(x, i.esq);

      } else if (x.nome.compareTo(i.elemento.nome) > 0) {
        i.dir = inserir(x, i.dir);

      } else {
        throw new Exception("Erro ao inserir!");
      }

      return i;
    }

  }

  

  public static void main(String[] args) throws Exception {
    T1 hash = new T1();

    // array que armazena as linhas de entrada
    int[] entrada = new int[maxEntradas];
    // Primeira leitura da entrada padrao com os ids
    int numEntrada = leituraDeEntrada(entrada);
    
    for(int i=0; i<numEntrada; i++) {
      Jogador jogador = new Jogador(leLinhaCSV(entrada[i]));
      //if(!ab.pesquisar(jogador.nome)){
      hash.inserir(jogador);
      //}
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
    Arq.openWriteClose("matricula_hashReserva.txt", conteudo);
  }
}
