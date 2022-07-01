public class Jogador {

  static final String arquivoCSV = "/tmp/players.csv";
  static final int maxEntradas = 128;
  static class Lista {
    public Jogador j[];

    public Lista() {
      j = new Jogador[0];
    }
    public Lista(int qtd) {
      j = new Jogador[qtd];
    }


    // insere um registro na primeira posicao da Lista 
    public void inserirInicio(Jogador jogador) {
      Jogador novo[] = new Jogador[j.length + 1];
      novo[0] = jogador.clone();
      // remaneja os demais
      for(int i = 1; i < j.length+1; i++) {
        novo[i] = j[i-1].clone();
      }
      j = novo;
    }


    // insere um registro na posicao p da Lista, onde p < n e n o numero de registros
    public void inserir(int posicao, Jogador jogador) {
      Jogador novo[] = new Jogador[j.length + 1];
      // remaneja os registros
      int i = 0;
      while(i < posicao) {
        novo[i] = j[i++].clone();
      }
      novo[posicao] = jogador.clone();
      while(i < j.length) {
        novo[i+1] = j[i++].clone();
      }
      j = novo;
    }


    // insere um registro na ultima posicao da Lista
    public void inserirFim(Jogador jogador) {
      Jogador novo[] = new Jogador[j.length + 1];
      novo[j.length] = jogador.clone();
      for(int i = 0; i < j.length; i++) {
        novo[i] = j[i].clone();
      }
      j = novo;
    }


    // remove e retorna o primeiro registro cadastrado 
    // na Lista e remaneja os demais
    public Jogador removerInicio() {
      Jogador novo[] = new Jogador[j.length - 1];
      Jogador resp = j[0].clone();
      for(int i = 0; i < novo.length; i++) {
        novo[i] = j[i+1].clone();
      }
      j = novo;
      System.out.println("(R) " + resp.getNome());
      return resp;
    }


    //remove e retorna o registro cadastrado na p-esima 
    //posicao da Lista e remaneja os demais. 
    public Jogador remover(int posicao) throws Exception {
      if(posicao >= j.length) throw new Exception("Posicao invalida");
      
      Jogador novo[] = new Jogador[j.length - 1];
      Jogador resp = j[posicao].clone();
      
      int i = 0;
      while(i < posicao) {
        novo[i] = j[i++].clone();
      }
      while(i < novo.length) {
        novo[i] = j[i+1].clone();
        i++;
      }
      j = novo;
      System.out.println("(R) " + resp.getNome());
      return resp;
    }


    // remove e retorna o ultimo registro cadastrado na lista.
    public Jogador removerFim() {
      Jogador novo[] = new Jogador[j.length - 1];
      Jogador resp = j[j.length - 1].clone();
      for(int i = 0; i < j.length-1; i++) {
        novo[i] = j[i].clone();
      }
      j = novo;
      System.out.println("(R) " + resp.getNome());
      return resp;
    }


    public void imprimir() {
      for(int i = 0; i < j.length; i++) {
        System.out.print("[" + i + "] ");
        j[i].imprimir();
      }
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

    lista.imprimir();

  }
}
