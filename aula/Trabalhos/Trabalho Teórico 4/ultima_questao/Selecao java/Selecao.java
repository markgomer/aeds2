/**
 * Algoritmo de ordenacao por selecao
 * 
 * @author Max do Val Machado
 * @version 2 01/2015
 */

class Selecao extends Geracao {
  /**
   * Construtor.
   */
  public Selecao() {
    super();
  }

  /**
   * Construtor.
   * 
   * @param int tamanho do array de numeros inteiros.
   */
  public Selecao(int tamanho) {
    super(tamanho);
  }

  /**
   * Algoritmo de ordenacao por selecao.
   */
  static int comparacoes;
  static int movimentacoes;
  public static void selecao() {
    comparacoes = 0;
    movimentacoes = 0;
    for (int i = 0; i < (n - 1); i++) {
      int menor = i;
      for (int j = (i + 1); j < n; j++) {
        if (array[menor] > array[j]) {
          menor = j;
        }
        comparacoes++;
      }
      if(menor != i) {
        swap(menor, i);
        movimentacoes += 3;
      }
    }
    MyIO.print(movimentacoes); MyIO.print(", ");
    MyIO.print(comparacoes); MyIO.print(", ");
  }

  // monta array de tamanho (tamanhoDoArray) e executa (numeroDeExecucoes) vezes o algoritmo selecao
  // mostra na tela o tempo que demorou para executar a tarefa
  public static void executarAleatorio(int numeroDeExecucoes, int tamanhoDoArray) {
    for (int i = 0; i < numeroDeExecucoes; i++) {
      MyIO.print("[" + tamanhoDoArray + "]*" + numeroDeExecucoes + ", ");
      Selecao selecao = new Selecao(tamanhoDoArray);
      selecao.aleatorio(); // preenche array com rand
      long comeco = now(); // timestamp inicio
      selecao.selecao();   // ordena array
      long fim = now();    // timestamp fim
      MyIO.println("" + (fim - comeco) / 1.0); // milissegundos
    }
  }

  public static void executarCrescente(int numeroDeExecucoes, int tamanhoDoArray) {
    for (int i = 0; i < numeroDeExecucoes; i++) {
      MyIO.print("[" + tamanhoDoArray + "]*" + numeroDeExecucoes + ", ");
      Selecao selecao = new Selecao(tamanhoDoArray);
      selecao.crescente(); // preenche array com int crescentes
      long comeco = now(); // timestamp inicio
      selecao.selecao();   // ordena array
      long fim = now();    // timestamp fim
      MyIO.println("" + (fim - comeco) / 1.0); // milissegundos
    }
  }

  public static void executarDecrescente(int numeroDeExecucoes, int tamanhoDoArray) {
    for (int i = 0; i < numeroDeExecucoes; i++) {
      MyIO.print("[" + tamanhoDoArray + "]*" + numeroDeExecucoes + ", ");
      Selecao selecao = new Selecao(tamanhoDoArray);
      selecao.decrescente(); // preenche array com int decrescentes
      long comeco = now(); // timestamp inicio
      selecao.selecao();   // ordena array
      long fim = now();    // timestamp fim
      MyIO.println("" + (fim - comeco) / 1.0); // milissegundos
    }
  }


  public static void main(String[] args) {
    // executa o script:
    MyIO.println("Aleatorio");
    executarAleatorio(33, 100);
    executarAleatorio(33, 1000);
    executarAleatorio(33, 10000);
    
    MyIO.println("Crescente");
    executarCrescente(33, 100);
    executarCrescente(33, 1000);
    executarCrescente(33, 10000);
    
    MyIO.println("Decrescente");
    executarDecrescente(33, 100);
    executarDecrescente(33, 1000);
    executarDecrescente(33, 10000);
  }
}