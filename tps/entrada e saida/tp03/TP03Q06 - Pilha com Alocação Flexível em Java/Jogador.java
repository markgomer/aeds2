public class Jogador {

  static final String arquivoCSV = "/tmp/players.csv";
  static final int maxEntradas = 128;
  
  // celula composta de objeto Jogador e ponteiro para o proximo Jogador
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


    public void inserirInicio(Jogador x) {
      Celula tmp = new Celula(x);
      tmp.prox = primeiro.prox;
      primeiro.prox = tmp;
      if (primeiro == ultimo) {           
        ultimo = tmp;
      }
        tmp = null;
    }


    public void inserirFim(Jogador x) {
      ultimo.prox = new Celula(x);
      ultimo = ultimo.prox;
    }


    public Jogador removerInicio() throws Exception {
      if (primeiro == ultimo) {
        throw new Exception("Erro ao remover (vazia)!");
      }

      Celula tmp = primeiro;
      primeiro = primeiro.prox;
      Jogador resp = primeiro.elemento;
      tmp.prox = null;
      tmp = null;

      System.out.println("(R) " + resp.getNome());
      return resp;
    }    


    public Jogador removerFim() throws Exception {
      if (primeiro == ultimo) {
        throw new Exception("Erro ao remover (vazia)!");
      } 

      // Caminhar ate a penultima celula:
      Celula i;
      for(i = primeiro; i.prox != ultimo; i = i.prox);

      Jogador resp = ultimo.elemento; 
      ultimo = i; 
      i = ultimo.prox = null;

      System.out.println("(R) " + resp.getNome());  
      return resp;
    }


    public void inserir(int pos, Jogador x) throws Exception {

        int tamanho = tamanho();

        if(pos < 0 || pos > tamanho){
        throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0){
          inserirInicio(x);
        } else if (pos == tamanho){
          inserirFim(x);
        } else {
        // Caminhar ate a posicao anterior a insercao
          Celula i = primeiro;
          for(int j = 0; j < pos; j++, i = i.prox);
      
          Celula tmp = new Celula(x);
          tmp.prox = i.prox;
          i.prox = tmp;
          tmp = i = null;
        }
    }


    public Jogador remover(int pos) throws Exception {
      Jogador resp;
      int tamanho = tamanho();

      if (primeiro == ultimo){
        throw new Exception("Erro ao remover (vazia)!");

        } else if(pos < 0 || pos >= tamanho) {
          throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0){
          resp = removerInicio();
        } else if (pos == tamanho - 1){
          resp = removerFim();
        } else {
        // Caminhar ate a posicao anterior a insercao
          Celula i = primeiro;
          for(int j = 0; j < pos; j++, i = i.prox);
      
          Celula tmp = i.prox;
          resp = tmp.elemento;
          i.prox = tmp.prox;
          tmp.prox = null;
          i = tmp = null;
        }
      System.out.println("(R) " + resp.getNome());
      return resp;
    }    


    public void mostrar() {
      int j = 0;
      for (Celula i = primeiro.prox; i != null; i = i.prox) {
        System.out.print("[" + (j++) + "] ");
        i.elemento.imprimir();
      }
    }


    public boolean pesquisar(Jogador x) {
      boolean resp = false;
      for (Celula i = primeiro.prox; i != null; i = i.prox) {
          if(i.elemento.getNome().contains(x.getNome())){
              resp = true;
              i = ultimo;
          }
      }
      return resp;
    }


    public int tamanho() {
        int tamanho = 0; 
        for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
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




  public static void main(String[] args) throws Exception {
    // array que armazena as linhas de entrada
    int[] entrada = new int[maxEntradas];

    // Primeira leitura da entrada padrao com os ids
    int numEntrada = leituraDeEntrada(entrada);

    // formar array de jogadores    
    Lista lista = new Lista();
    
    for(int i = 0; i < numEntrada; i++) {
      Jogador jogador = new Jogador(leLinhaCSV(entrada[i]));
      lista.inserirFim( jogador );
    }

    // segunda leitura de entrada
    int numOperacoes = MyIO.readInt();
    char operacao;

    for (int i = 0; i < numOperacoes; i++) {
      operacao = MyIO.readChar();
      MyIO.readChar();
      switch(operacao) {
        //case "I*": lista.inserir(MyIO.readInt(), new Jogador( leLinhaCSV(MyIO.readInt()) )); break;
        //case "II": lista.inserirInicio(new Jogador( leLinhaCSV(MyIO.readInt()) )); break;
        case 'I': lista.inserirFim(new Jogador( leLinhaCSV(MyIO.readInt()) )); break;
        //case "R*": lista.remover(MyIO.readInt()); break;
        //case "RI": lista.removerInicio(); break;
        case 'R': lista.removerFim(); break;
        default: System.out.println("operacao invalida"); break;
      }
    }

    lista.mostrar();

  }
}
