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


  static class No {
    public int elemento; // Conteudo do no: altura%15
    public No esq; // No da esquerda.
    public No dir; // No da direita.
    public No2 outro;
    /**
    * Construtor da classe.
    * @param elemento Conteudo do no.
    */
    No(int elemento) {
      this.elemento = elemento;
      this.esq = this.dir = null;
      this.outro = null;
    }
    /**
    * Construtor da classe.
    * @param elemento Conteudo do no.
    * @param esq No da esquerda.
    * @param dir No da direita.
    */
    No(int elemento, No esq, No dir) {
      this.elemento = elemento;
      this.esq = esq;
      this.dir = dir;
      this.outro = null;
    }
  }

  static class No2 {
    public String elemento; // Conteudo do no: nome
    public No2 esq; // No da esquerda.
    public No2 dir; // No da direita.
    /**
    * Construtor da classe.
    * @param elemento Conteudo do no.
    */
    No2(String elemento) {
      this.elemento = elemento;
      this.esq = this.dir = null;
    }
    /**
    * Construtor da classe.
    * @param elemento Conteudo do no.
    * @param esq No2 da esquerda.
    * @param dir No2 da direita.
    */
    No2(String elemento, No2 esq, No2 dir) {
      this.elemento = elemento;
      this.esq = esq;
      this.dir = dir;
    }
  }
  
  
  public static class ArvoreArvore {
    private No raiz; // Raiz da arvore.

    // Construtor da classe.
    public ArvoreArvore() {
      raiz = null;
      //inserir('M');
      //inserir('T');
      //inserir('F');
      //os outros 23 caracteres.
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String elemento) {
      MyIO.print("raiz ");
      return pesquisar(raiz, elemento);
    }

    private boolean pesquisar(No no, String x) {
      boolean resp = false;
      if(++numComparacoes!=0 && no != null) {
        resp = pesquisarSegundaArvore(no.outro, x);
        if(!resp) {
          MyIO.print("esq ");
          resp = pesquisar(no.esq, x);
          if(!resp){
            MyIO.print("dir ");
            resp = pesquisar(no.dir, x);
          }
        }
      }
      return resp;
    }
    private boolean pesquisarSegundaArvore(No2 no, String x) {
      boolean resp = false;
      if (++numComparacoes!=0 && no == null) { 
        resp = false;
        
      } else if (++numComparacoes!=0 && x.contains(no.elemento)) { 
        resp = true; 
        
      } else if (!resp) { 
        MyIO.print("ESQ ");
        resp = pesquisarSegundaArvore(no.esq, x); 
        if(!resp) {
          MyIO.print("DIR ");
          resp = pesquisarSegundaArvore(no.dir, x); 
        }
      }
      return resp;
    }    

    /**
    * Metodo publico iterativo para inserir elemento.
    * @param x Elemento a ser inserido.
    * @throws Exception Se o elemento existir.
    */
    public void inserir(int x) throws Exception {
      raiz = inserir(x, raiz);
    }
    /**
    * Metodo privado recursivo para inserir elemento.
    * @param x Elemento a ser inserido.
    * @param i No em analise.
    * @return No em analise, alterado ou nao.
    * @throws Exception Se o elemento existir.
    */
    private No inserir(int x, No i) throws Exception {
      if (++numComparacoes!=0 && i == null) {
          i = new No(x);
        } else if (++numComparacoes!=0 && x < i.elemento) {
          i.esq = inserir(x, i.esq);
        } else if (++numComparacoes!=0 && x > i.elemento) {
          i.dir = inserir(x, i.dir);
        } else {
          throw new Exception("Erro ao inserir!");
        }
      return i;
    }

    /**
     * Metodo publico iterativo para inserir elemento.
     * @param x Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(Jogador x) throws Exception {
      raiz = inserir(x, raiz);
    }
    /**
     * Metodo privado recursivo para inserir elemento.
     * @param x Elemento a ser inserido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se o elemento existir.
     */
    private No inserir(Jogador x, No i) throws Exception {
      if (++numComparacoes!=0 && i.elemento == x.altura%15) {
          i.outro = inserir2(x, i.outro);
        } else if (++numComparacoes!=0 && x.altura%15 < i.elemento) {
          i.esq = inserir(x, i.esq);
        } else {
          i.dir = inserir(x, i.dir);
        }
      return i;
    }
    /**
     * Metodo privado recursivo para inserir elemento.
     * @param x Elemento a ser inserido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se o elemento existir.
     */
    private No2 inserir2(Jogador x, No2 i) throws Exception {
      if (i == null) {
          i = new No2(x.nome);
        } else if (++numComparacoes!=0 && x.nome.compareTo(i.elemento) < 0) {
          i.esq = inserir2(x, i.esq);
        } else if (++numComparacoes!=0 && x.nome.compareTo(i.elemento) > 0) {
          i.dir = inserir2(x, i.dir);
        } else {
          throw new Exception("Erro ao inserir!");
        }
      return i;
    }
    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPre() {
      System.out.print("[ ");
      caminharPre(raiz);
      System.out.println("]");
    }
    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void caminharPre(No i) {
      if (++numComparacoes!=0 && i != null) {
        System.out.print(i.elemento + " "); // Conteudo do no.
        caminharPre(i.esq); // Elementos da esquerda.
        caminharPre(i.dir); // Elementos da direita.
      }
    }
  }


  public Jogador() { }

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



  public static void main(String[] args) throws Exception {
    ArvoreArvore aa = new ArvoreArvore();
    aa.inserir(7);
    aa.inserir(3);
    aa.inserir(11);
    aa.inserir(1);
    aa.inserir(5);
    aa.inserir(9);
    aa.inserir(12);
    aa.inserir(0);
    aa.inserir(2);
    aa.inserir(4);
    aa.inserir(6);
    aa.inserir(8);
    aa.inserir(10);
    aa.inserir(13);
    aa.inserir(14);

    // array que armazena as linhas de entrada
    int[] entrada = new int[maxEntradas];
    // Primeira leitura da entrada padrao com os ids
    int numEntrada = leituraDeEntrada(entrada);

    for(int i=0; i<numEntrada; i++) {
      Jogador jogador = new Jogador(leLinhaCSV(entrada[i]));
      aa.inserir(jogador);
    }

    long inicio = new Date().getTime();
    String chave;
    do {
      chave = MyIO.readLine();
      MyIO.print(chave + " ");
      MyIO.println(aa.pesquisar(chave)? "SIM":"NAO");
    } while(!chave.equals("FIM"));
    long fim = new Date().getTime();
   
    //log
    String conteudo = "696809\t" + (fim-inicio) + "\t" + numComparacoes;
    Arq.openWriteClose("matricula_arvoreArvore.txt", conteudo);
  }
}
