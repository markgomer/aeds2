import java.util.*;
public class Jogador {
  public static final String arquivoCSV = "../../players.csv";
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
  public static int leituraDeEntrada(String entrada[]) {
    int numEntrada = 0;
    do {
      entrada[numEntrada] = MyIO.readLine();
    } while (!entrada[numEntrada++].equals("FIM"));
    numEntrada--;   //Desconsiderar linha contendo a palavra FIM
    return numEntrada;
  }

  static class No {
    public char elemento;
    public int tamanho = 255;
    public No[] prox;
    public boolean folha;

    public No() {
      this(' ');
    }

    public No(char elemento) {
      this.elemento = elemento;
      prox = new No[tamanho];
      for (int i = 0; i < tamanho; i++)
        prox[i] = null;
      folha = false;
    }

    public static int hash(char x) {
      return (int) x;
    }
  }

  static class ArvoreTrie {
    private No raiz;

    public ArvoreTrie() {
      raiz = new No();
    }

    public void inserir(String s) throws Exception {
      inserir(s, raiz, 0);
    }

    private void inserir(String s, No no, int i) throws Exception {
      System.out.print("\nEM NO(" + no.elemento + ") (" + i + ")");
      if (no.prox[s.charAt(i)] == null) {
        System.out.print("--> criando filho(" + s.charAt(i) + ")");
        no.prox[s.charAt(i)] = new No(s.charAt(i));

        if (i == s.length() - 1) {
          System.out.print("(folha)");
          no.prox[s.charAt(i)].folha = true;
        } else {
          inserir(s, no.prox[s.charAt(i)], i + 1);
        }

      } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1) {
        inserir(s, no.prox[s.charAt(i)], i + 1);

      } else {
        throw new Exception("Erro ao inserir!");
      }
    }

    public boolean pesquisar(String s) throws Exception {
      return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, No no, int i) throws Exception {
      boolean resp;
      if (no.prox[s.charAt(i)] == null) {
        resp = false;
      } else if (i == s.length() - 1) {
        resp = (no.prox[s.charAt(i)].folha == true);
      } else if (i < s.length() - 1) {
        resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
      } else {
        throw new Exception("Erro ao pesquisar!");
      }
      return resp;
    }

    public void mostrar() {
      mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
      if (no.folha == true) {
        System.out.println("Palavra: " + (s + no.elemento));
      } else {
        for (int i = 0; i < no.prox.length; i++) {
          if (no.prox[i] != null) {
            System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
            mostrar(s + no.elemento, no.prox[i]);
          }
        }
      }
    }

    public int contarAs() {
      int resp = 0;
      if (raiz != null) {
        resp = contarAs(raiz);
      }
      return resp;
    }

    public int contarAs(No no) {
      int resp = (no.elemento == 'A') ? 1 : 0;

      if (no.folha == false) {
        for (int i = 0; i < no.prox.length; i++) {
          if (no.prox[i] != null) {
            resp += contarAs(no.prox[i]);
          }
        }
      }
      return resp;
    }
  }

  public static void main(String[] args) throws Exception {
    ArvoreTrie a1 = new ArvoreTrie();
    ArvoreTrie a2 = new ArvoreTrie();

    // array que armazena as linhas de entrada
    int[] entrada = new int[maxEntradas];
    // Primeira leitura da entrada padrao com os ids
    int numEntrada = leituraDeEntrada(entrada);

    long inicio = new Date().getTime();

    // primeira arvore
    for(int i=0; i<numEntrada; i++) {
      Jogador jogador = new Jogador(leLinhaCSV(entrada[i]));
      ab.inserir(jogador);
    }
    ab.caminharCentral();

    long fim = new Date().getTime();
   
    //log
    String conteudo = "696809\t" + (fim-inicio) + "\t" + numComparacoes;
    Arq.openWriteClose("matricula_arvoreTrie.txt", conteudo);
  }
}
