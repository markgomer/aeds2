public class Jogador {

  static final String arquivoCSV = "/tmp/players.csv";
  static final int maxEntradas = 512;
  static int numMovimentacoes = 0;
  static int numComparacoes = 0;

  static class CelulaDupla {
    public Jogador elemento;
    public CelulaDupla ant, prox;

    // construtor
    public CelulaDupla() {
      this.elemento = null;
      this.ant = this.prox = null;
    }
    public CelulaDupla(Jogador elemento) {
      this.elemento = elemento;
      this.ant = this.prox = null;
    }
  }



  static class ListaDupla {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    public ListaDupla() {
      primeiro = new CelulaDupla();
      ultimo = primeiro;
    }

    public void inserirFim(Jogador x) {
      ultimo.prox = new CelulaDupla(x);
      ultimo.prox.ant = ultimo;
      ultimo = ultimo.prox;
    }

    public Jogador removerFim() throws Exception {
      if (primeiro == ultimo) {
        throw new Exception("Erro ao remover (vazia)!");
      } 
      Jogador resp = ultimo.elemento;
      ultimo = ultimo.ant;
      ultimo.prox.ant = null;
      ultimo.prox = null;
      return resp;
    }

    public void mostrar() {
      for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
        System.out.println((i.elemento).toString());
      }
    }

    public int tamanho() {
      int tamanho = 0; 
      for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
    }

  }


  int id;
  String nome;
  int altura;
  int peso;
  String universidade;
  int anoNascimento;
  String cidadeNascimento;
  String estadoNascimento;

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
      this.cidadeNascimento = (campos[6].isEmpty()) ? "nao informado": campos[6];
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
    novo.anoNascimento = this.anoNascimento;
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



  // troca dois elementos nas posicoes i e j no vetor recebido
  public static void swap(Jogador array[], int i, int j) {
    Jogador temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    numMovimentacoes += 3;
  }

  // ORDENACAO //
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
      ) j--;
      if (i <= j) {
        swap(array, i, j);
        i++;
        j--;
      }
    }
    if (esq < j)  quicksort(array, esq, j);
    if (i < dir)  quicksort(array, i, dir);
  }


  public static void main(String[] args) throws Exception {
    // array que armazena as linhas de entrada
    int[] entrada = new int[maxEntradas];

    // Primeira leitura da entrada padrao com os ids
    int numEntrada = leituraDeEntrada(entrada);

    // formar array de jogadores    
    ListaDupla lista = new ListaDupla();
    Jogador[] jogadores = new Jogador[numEntrada];
    
    for(int i = 0; i < numEntrada; i++) {
      jogadores[i] = new Jogador(leLinhaCSV(entrada[i]));
    }

    quicksort(jogadores);

    for (int i = 0; i < numEntrada; i++) {
      lista.inserirFim(jogadores[i]);
    }

    lista.mostrar();

  }
}
