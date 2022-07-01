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



  static class NoAN{
    public boolean cor;
    public String elemento;
    public NoAN esq, dir;
    public NoAN (){
        this("");
    }
    public NoAN (String elemento){
        this(elemento, false, null, null);
    }
    public NoAN (String elemento, boolean cor){
        this(elemento, cor, null, null);
    }
    public NoAN (String elemento, boolean cor, NoAN esq, NoAN dir){
      this.cor = cor;
      this.elemento = elemento;
      this.esq = esq;
      this.dir = dir;
    }
  }
  

  /**
   * Arvore binaria de pesquisa
   * @author Max do Val Machado
   */
  public static class Alvinegra {
    private NoAN raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public Alvinegra() {
      raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String elemento) {
      MyIO.print("raiz ");
      return pesquisar(elemento, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * @param elemento Elemento que sera procurado.
     * @param i NoAN em analise.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    private boolean pesquisar(String elemento, NoAN i) {
        boolean resp;
      if (++numComparacoes!=0 && i == null) {
          resp = false;

        } else if (++numComparacoes!=0 && elemento.compareTo(i.elemento) == 0) {
          resp = true;

        } else if (++numComparacoes!=0 && elemento.compareTo(i.elemento) < 0) {
          MyIO.print("esq ");
          resp = pesquisar(elemento, i.esq);
          
        } else {
          MyIO.print("dir ");
          resp = pesquisar(elemento, i.dir);
        }
        return resp;
    }

    /**
     * Metodo publico iterativo para inserir elemento.
     * @param elemento Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(String elemento) throws Exception {
    
        //Se a arvore estiver vazia
        if(raiz == null){
          raiz = new NoAN(elemento, false);
          //System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento + ").");

        //Senao, se a arvore tiver um elemento 
        } else if (raiz.esq == null && raiz.dir == null){
          if (raiz.elemento.compareTo(elemento) > 0){
              raiz.esq = new NoAN(elemento, true);
              //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e esq(" + raiz.esq.elemento +").");
          } else {
              raiz.dir = new NoAN(elemento, true);
              //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e dir(" + raiz.dir.elemento +").");
          }

        //Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null){

          if(raiz.elemento.compareTo(elemento) > 0){
              raiz.esq = new NoAN(elemento);
              //System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");

          } else if (raiz.dir.elemento.compareTo(elemento) > 0){
              raiz.esq = new NoAN(raiz.elemento);
              raiz.elemento = elemento;
              //System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");

          } else {
              raiz.esq = new NoAN(raiz.elemento);
              raiz.elemento = raiz.dir.elemento;
              raiz.dir.elemento = elemento;
              //System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
          }

          raiz.esq.cor = raiz.dir.cor = false;
          
        //Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null){
          
          if(raiz.elemento.compareTo(elemento) < 0){
              raiz.dir = new NoAN(elemento);
              //System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
          } else if (raiz.esq.elemento.compareTo(elemento) < 0){
              raiz.dir = new NoAN(raiz.elemento);
              raiz.elemento = elemento;
              //System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
          } else {
              raiz.dir = new NoAN(raiz.elemento);
              raiz.elemento = raiz.esq.elemento;
              raiz.esq.elemento = elemento;
              //System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
          }

          raiz.esq.cor = raiz.dir.cor = false;

        //Senao, a arvore tem tres ou mais elementos
        } else {
          //System.out.println("Arvore com tres ou mais elementos...");
          inserir(elemento, null, null, null, raiz);
        }

        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i){

        //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if(pai.cor == true){

          //4 tipos de reequilibrios e acoplamento
          if(pai.elemento.compareTo(avo.elemento) > 0){ // rotacao a esquerda ou direita-esquerda
              if(i.elemento.compareTo(pai.elemento) > 0){
                avo = rotacaoEsq(avo);
              } else {
                avo = rotacaoDirEsq(avo);
              }

          } else { // rotacao a direita ou esquerda-direita
              if(i.elemento.compareTo(pai.elemento) < 0){
                avo = rotacaoDir(avo);
              } else {
                avo = rotacaoEsqDir(avo);
              }
          }

          if (bisavo == null){
              raiz = avo;
          } else {
              if(avo.elemento.compareTo(bisavo.elemento) < 0){
                bisavo.esq = avo;
              } else {
                bisavo.dir = avo;
              }
          }

          //reestabelecer as cores apos a rotacao
          avo.cor = false;
          avo.esq.cor = avo.dir.cor = true;
          //System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e avo.esq / avo.dir(" + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
        } //if(pai.cor == true)
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * @param elemento Elemento a ser inserido.
     * @param avo NoAN em analise.
     * @param pai NoAN em analise.
     * @param i NoAN em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserir(String elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
      if (i == null) {

          if(elemento.compareTo(pai.elemento) < 0){
              i = pai.esq = new NoAN(elemento, true);
          } else {
              i = pai.dir = new NoAN(elemento, true);
          }

          if(pai.cor == true){
              balancear(bisavo, avo, pai, i);
          }

        } else {

          //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
          if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
              i.cor = true;
              i.esq.cor = i.dir.cor = false;
              if(i == raiz){
                i.cor = false;
              }else if(pai.cor == true){
                balancear(bisavo, avo, pai, i);
              }
          }
          if (elemento.compareTo(i.elemento) < 0) {
              inserir(elemento, avo, pai, i, i.esq);
          } else if (elemento.compareTo(i.elemento) > 0) {
              inserir(elemento, avo, pai, i, i.dir);
          } else {
              throw new Exception("Erro inserir (elemento repetido)!");
          }
        }
    }

    private NoAN rotacaoDir(NoAN no) {
        //System.out.println("Rotacao DIR(" + no.elemento + ")");
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        //System.out.println("Rotacao ESQ(" + no.elemento + ")");
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
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
    Alvinegra an = new Alvinegra();

    // array que armazena as linhas de entrada
    int[] entrada = new int[maxEntradas];
    // Primeira leitura da entrada padrao com os ids
    int numEntrada = leituraDeEntrada(entrada);

    for(int i=0; i<numEntrada; i++) {
      Jogador jogador = new Jogador(leLinhaCSV(entrada[i]));
      an.inserir(jogador.nome);
    }

    long inicio = new Date().getTime();
    String chave;
    do {
      chave = MyIO.readLine();
      MyIO.print(chave + " ");
      MyIO.println(an.pesquisar(chave)? "SIM":"NAO");
    } while(!chave.equals("FIM"));
    long fim = new Date().getTime();
   
    //log
    String conteudo = "696809\t" + (fim-inicio) + "\t" + numComparacoes;
    Arq.openWriteClose("matricula_alvinegra.txt", conteudo);
  }
}
