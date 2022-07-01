/*
Marco Aurelio Silva de Souza Junior - 696809
Questao TP02Q03
*/

import java.util.*;

public class Jogador {
  static final int maxEntradas = 128;
  static int numComparacoes;
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

  // le linha do csv com id indicado e cria um objeto "Jogador"
  public static Jogador ler(int id) {
    Jogador j = new Jogador();
    Arq.openRead("/tmp/players.csv");
    
    // armazena uma linha de entrada de cada vez 
    String linha;  
      
    // mover cursor do csv ate a linha do id
    for(int n = 0; n <= id; n++) { 
      Arq.readLine();  // pula as linhas
    }
    linha = Arq.readLine();  // le a linha com id inserido e armazena a string em "linha"
    
    String[] atributos = linha.split(",", -1); // divide string de entrada e insere os atributos no array "atributos"
    
    j.setId(Integer.parseInt(atributos[0]));
    j.setNome(atributos[1]);
    j.setAltura(Integer.parseInt(atributos[2]));
    j.setPeso(Integer.parseInt(atributos[3]));
    j.setUniversidade(atributos[4]);
    j.setAnoNascimento(Integer.parseInt(atributos[5]));
    j.setCidadeNascimento(atributos[6]);
    j.setEstadoNascimento(atributos[7]);

    Arq.close();
    return j;
  }

  // pesquisa sequencial. Retorna true se a String chave estiver contida no array de String listaDeNomes
  public static boolean estaContido(String chave, String listaDeNomes[]) {
    boolean resp = false;
    int n = listaDeNomes.length;
    for(int i = 0; i < n; i++) {
      numComparacoes++;
      if(listaDeNomes[i].contains(chave)) {
        resp = true;
        i = n;
      }
    }
    return resp;
  }

  public static void main(String[] args) {
    Jogador[] jogador = new Jogador[maxEntradas];
    int[] entrada = new int[maxEntradas]; // array que armazena as linhas de entrada
    int numEntrada = 0;

    long inicio = new Date().getTime();

    //PRIMEIRA PARTE: preenchimento do vetor de jogadores 
    //Leitura da entrada padrao
    do {
      entrada[numEntrada] = MyIO.readInt();
      numComparacoes++;
    } while (entrada[numEntrada++] != -1);
    numEntrada--;   //Desconsiderar linha contendo a palavra FIM
    
    String[] listaDeNomes = new String[numEntrada];
    // percorre as entradas  
    for(int i = 0; i < numEntrada; i++) {
      jogador[i] = ler(entrada[i]);  // le os dados de "linha" e armazena nos atributos do obj "Jogador"
      listaDeNomes[i] = jogador[i].nome; // armazena os nomes dos jogadores no array
    }

    //SEGUNDA PARTE: pesquisa
    String[] chavePesq = new String[maxEntradas];
    int numPesquisa = 0;
    
    do {
      chavePesq[numPesquisa] = MyIO.readLine();
      numComparacoes++;
    } while (!chavePesq[numPesquisa++].equals("FIM"));
    numPesquisa--;   //Desconsiderar linha contendo a palavra FIM
    
    //Leitura da entrada padrao
    for (int i = 0; i < numPesquisa; i++) {
      MyIO.println(estaContido(chavePesq[i], listaDeNomes)? "SIM" : "NAO");
    }

    long fim = new Date().getTime();

    String conteudo = "696809\t" + (fim-inicio) + "\t" + numComparacoes;
    Arq.openWriteClose("matricula_sequencial.txt", conteudo);
  }
}
