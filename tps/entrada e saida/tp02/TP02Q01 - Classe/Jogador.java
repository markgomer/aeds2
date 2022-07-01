/*
Marco Aurelio Silva de Souza Junior - 696809
Questao TP02Q01
*/

public class Jogador {
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

  // le linha de entrada e cria um objeto "Jogador"
  public static Jogador ler(String str) {
    Jogador j = new Jogador();
    String[] atributos = str.split(",", -1); // divide string de entrada e insere os atributos no array "atributos"
    
    j.setId(Integer.parseInt(atributos[0]));
    j.setNome(atributos[1]);
    j.setAltura(Integer.parseInt(atributos[2]));
    j.setPeso(Integer.parseInt(atributos[3]));
    j.setUniversidade(atributos[4]);
    j.setAnoNascimento(Integer.parseInt(atributos[5]));
    j.setCidadeNascimento(atributos[6]);
    j.setEstadoNascimento(atributos[7]);
    return j;
  }


  public static void main(String[] args) {
    final int maxEntradas = 42;
    Jogador[] jogador = new Jogador[maxEntradas];
    int[] entrada = new int[maxEntradas]; // array que armazena as linhas de entrada
    int numEntrada = 0;
    
    //Leitura da entrada padrao
    do {
      entrada[numEntrada] = MyIO.readInt();
    } while (entrada[numEntrada++] != -1);
    numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
   
    // armazena uma linha de entrada de cada vez 
    String linha;  
    // percorre as entradas  
    for(int i = 0; i < numEntrada; i++) {
      Arq.openRead("/tmp/players.csv");
      // mover cursor do csv ate a linha do id
      for(int n = 0; n <= entrada[i]; n++) { 
        Arq.readLine();  // pula as linhas
      }
      linha = Arq.readLine();  // le a linha com id inserido e armazena a string em "linha"
      jogador[i] = new Jogador();
      jogador[i] = ler(linha);  // le os dados de "linha" e armazena nos atributos do obj "Jogador"
      jogador[i].imprimir();  // exibe na tela os atributos do objeto
      Arq.close();
    }
  }
}
