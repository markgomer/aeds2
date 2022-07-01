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

    /**
   * No da arvore binaria
   * @author Max do Val Machado
   */
  static class No {
    public Jogador elemento; // Conteudo do no.
    public No esq, dir;  // Filhos da esq e dir.

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     */
    public No(Jogador elemento) {
      this(elemento, null, null);
    }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
    public No(Jogador elemento, No esq, No dir) {
      this.elemento = elemento;
      this.esq = esq;
      this.dir = dir;
    }
  }

    /**
   * Arvore binaria de pesquisa
   * @author Max do Val Machado
   */
  public static class ArvoreBinaria {
    private No raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public ArvoreBinaria() {
      raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * @param x Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String x) {
      MyIO.print("raiz ");
      return pesquisar(x, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * @param x Elemento que sera procurado.
     * @param i No em analise.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    private boolean pesquisar(String x, No i) {
      boolean resp;
      if ((++numComparacoes!=0) && i == null) {
        MyIO.println("NAO");
        resp = false;

      } else if ((++numComparacoes!=0) && x.compareTo(i.elemento.nome) == 0) {
        MyIO.println("SIM");
        resp = true;

      } else if ((++numComparacoes!=0) && x.compareTo(i.elemento.nome) < 0) {
        MyIO.print("esq ");
        resp = pesquisar(x, i.esq);

      } else {
        MyIO.print("dir ");
        resp = pesquisar(x, i.dir);
      }
      return resp;
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharCentral() {
      System.out.print("[ ");
      caminharCentral(raiz);
      System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void caminharCentral(No i) {
      if (i != null) {
        caminharCentral(i.esq); // Elementos da esquerda.
        System.out.print(i.elemento.nome + " "); // Conteudo do no.
        caminharCentral(i.dir); // Elementos da direita.
      }
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
      if (i != null) {
        System.out.print(i.elemento.nome + " "); // Conteudo do no.
        caminharPre(i.esq); // Elementos da esquerda.
        caminharPre(i.dir); // Elementos da direita.
      }
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPos() {
      System.out.print("[ ");
      caminharPos(raiz);
      System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void caminharPos(No i) {
      if (i != null) {
        caminharPos(i.esq); // Elementos da esquerda.
        caminharPos(i.dir); // Elementos da direita.
        System.out.print(i.elemento.nome + " "); // Conteudo do no.
      }
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

    /**
     * Metodo publico para inserir elemento.
     * @param x Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserirPai(Jogador x) throws Exception {
        if(raiz == null){
          raiz = new No(x);
        } else if(x.nome.compareTo(raiz.elemento.nome) < 0){
        inserirPai(x, raiz.esq, raiz);
        } else if(x.nome.compareTo(raiz.elemento.nome) > 0){
        inserirPai(x, raiz.dir, raiz);
        } else {
          throw new Exception("Erro ao inserirPai!");
        }
    }

    /**
     * Metodo privado recursivo para inserirPai elemento.
     * @param x Elemento a ser inserido.
     * @param i No em analise.
     * @param pai No superior ao em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserirPai(Jogador x, No i, No pai) throws Exception {
      if (i == null) {
          if(x.nome.compareTo(i.elemento.nome) < 0){
              pai.esq = new No(x);
          } else {
              pai.dir = new No(x);
          }
        } else if (x.nome.compareTo(i.elemento.nome) < 0) {
          inserirPai(x, i.esq, i);
        } else if (x.nome.compareTo(i.elemento.nome) > 0) {
          inserirPai(x, i.dir, i);
        } else {
          throw new Exception("Erro ao inserirPai!");
        }
    }


    /**
     * Metodo publico iterativo para remover elemento.
     * @param x Elemento a ser removido.
     * @throws Exception Se nao encontrar elemento.
     */
    public void remover(Jogador x) throws Exception {
      raiz = remover(x, raiz);
    }

    /**
     * Metodo privado recursivo para remover elemento.
     * @param x Elemento a ser removido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se nao encontrar elemento.
     */
    private No remover(Jogador x, No i) throws Exception {

      if (i == null) {
          throw new Exception("Erro ao remover!");

        } else if (x.nome.compareTo(i.elemento.nome) < 0) {
          i.esq = remover(x, i.esq);

        } else if (x.nome.compareTo(i.elemento.nome) > 0) {
          i.dir = remover(x, i.dir);

        // Sem no a direita.
        } else if (i.dir == null) {
          i = i.esq;

        // Sem no a esquerda.
        } else if (i.esq == null) {
          i = i.dir;

        // No a esquerda e no a direita.
        } else {
          i.esq = antecessor(i, i.esq);
      }

      return i;
    }

    /**
     * Metodo para trocar no removido pelo antecessor.
     * @param i No que teve o elemento removido.
     * @param j No da subarvore esquerda.
     * @return No em analise, alterado ou nao.
     */
    private No antecessor(No i, No j) {

        // Existe no a direita.
      if (j.dir != null) {
          // Caminha para direita.
        j.dir = antecessor(i, j.dir);

        // Encontrou o maximo da subarvore esquerda.
      } else {
        i.elemento = j.elemento; // Substitui i por j.
        j = j.esq; // Substitui j por j.ESQ.
      }
      return j;
    }

    /**
     * Metodo publico iterativo para remover elemento.
     * @param x Elemento a ser removido.
     * @throws Exception Se nao encontrar elemento.
     */
    public void remover2(Jogador x) throws Exception {
        if (raiz == null) {
          throw new Exception("Erro ao remover2!");
        } else if(x.nome.compareTo(raiz.elemento.nome) < 0){
          remover2(x, raiz.esq, raiz);
        } else if (x.nome.compareTo(raiz.elemento.nome) > 0){
          remover2(x, raiz.dir, raiz);
        } else if (raiz.dir == null) {
          raiz = raiz.esq;
        } else if (raiz.esq == null) {
          raiz = raiz.dir;
        } else {
          raiz.esq = antecessor(raiz, raiz.esq);
        }
    }

    /**
     * Metodo privado recursivo para remover elemento.
     * @param x Elemento a ser removido.
     * @param i No em analise.
     * @param pai do No em analise.
     * @throws Exception Se nao encontrar elemento.
     */
    private void remover2(Jogador x, No i, No pai) throws Exception {
      if (i == null) {
          throw new Exception("Erro ao remover2!");
        } else if (x.nome.compareTo(i.elemento.nome) < 0) {
          remover2(x, i.esq, i);
        } else if (x.nome.compareTo(i.elemento.nome) > 0) {
          remover2(x, i.dir, i);
        } else if (i.dir == null) {
          pai = i.esq;
        } else if (i.esq == null) {
          pai = i.dir;
        } else {
          i.esq = antecessor(i, i.esq);
      }
    }

    public String getRaiz() throws Exception {
        return raiz.elemento.nome;
    }

    public static boolean igual (ArvoreBinaria a1, ArvoreBinaria a2){
        return igual(a1.raiz, a2.raiz);
    }

    private static boolean igual (No i1, No i2){
        boolean resp;
        if(i1 != null && i2 != null){
          resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
        } else if(i1 == null && i2 == null){
          resp = true;
        } else {
          resp = false; 
        }
        return resp;
    }

  }



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
    ArvoreBinaria ab = new ArvoreBinaria();

    // array que armazena as linhas de entrada
    int[] entrada = new int[maxEntradas];
    // Primeira leitura da entrada padrao com os ids
    int numEntrada = leituraDeEntrada(entrada);

    for(int i=0; i<numEntrada; i++) {
      Jogador jogador = new Jogador(leLinhaCSV(entrada[i]));
      //if(!ab.pesquisar(jogador.nome)){
        ab.inserir(jogador);
      //}
    }

    String[] pesquisas = new String[maxEntradas];
 
    int numPesquisas = leituraDeEntrada(pesquisas);

    long inicio = new Date().getTime();
    for(int i = 0 ; i < numPesquisas; i++) {
      MyIO.print(pesquisas[i] + " ");
      ab.pesquisar(pesquisas[i]);
    }
    long fim = new Date().getTime();
   
    //log
    String conteudo = "696809\t" + (fim-inicio) + "\t" + numComparacoes;
    Arq.openWriteClose("matricula_arvoreBinaria.txt", conteudo);
  }
}
