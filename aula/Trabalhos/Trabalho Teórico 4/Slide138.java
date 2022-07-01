import java.util.Random;

/*
A nova versão do método faz o mesmo número de 
comparações que o anterior, porém algumas movimentações 
são evitadas em função da linha de código "if (menor != i)"
caso o elemento a ser movimentado já esteja na posição 
ordenada. Para a execução dos métodos em arrays de tamanho
20, foi observada uma média de 4 movimentações a menos que 
a versão anterior do método.
*/

public class Slide138 {
  static int[] array = new int[20];

  // insere inteiros aleatórios entre 0 e 20 e coloca na array
  public static void fillArrayWithRand() {
    Random rand = new Random();
    for(int i = 0; i<array.length; i++) {
      int n = rand.nextInt(20);
      array[i] = n;
    }
  }

  // executa troca de posição entre os elementos da array de índices i e j
  public static void swap(int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  // método de ordenação por seleção dos slides anteriores
  public static void ordenarPorSelecaoAntigo(int arr[]) {
    System.out.println("\nMétodo antigo:");
    int n = arr.length;
    int comparacoes = 0;
    int movimentacoes = 0;
    for (int i = 0; i < (n - 1); i++) {
      int menor = i;
      for (int j = (i + 1); j < n; j++){
        comparacoes++;
        if (array[menor] > array[j]){
          menor = j;
        }
      }
      swap(menor, i); 
      movimentacoes++;
    }
    System.out.println("Comparações = " + comparacoes);
    System.out.println("Movimentações = " + movimentacoes);
  }

  // novo método de ordenação por seleção proposto no exercício
  public static void ordenarPorSelecaoNovo(int arr[]) {
    System.out.println("\nMétodo novo: ");
    int n = arr.length;
    int comparacoes = 0;
    int movimentacoes = 0;
    for (int i = 0; i < (n - 1); i++) {
      int menor = i;
      for (int j = (i + 1); j < n; j++){
        comparacoes++;
        if (array[menor] > array[j]){
          menor = j;
        }
      }
      if (menor != i){
        swap(menor, i);
        movimentacoes++;
      }
    }
    System.out.println("Comparações = " + comparacoes);
    System.out.println("Movimentações = " + movimentacoes);
  }

  // método para exibir array em linha na tela 
  public static void printArray(int arr[]) {
    for(int i = 0; i<arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    fillArrayWithRand();

    System.out.println("Primeira array: ");
    // mostra array gerada
    printArray(array);

    // executa método antigo e exibe na tela quantidade de comparações e movimentações
    ordenarPorSelecaoAntigo(array);

    // mostra array ordenada
    printArray(array);
    
    // gerar nova array para usar o novo método
    fillArrayWithRand();

    // mostra array gerada
    System.out.println("Nova array:");
    printArray(array);

    // executa método novo e exibe na tela quantidade de comparações e movimentações
    ordenarPorSelecaoNovo(array);

    // mostra array ordenada
    printArray(array);
    
    System.out.println(" ");
  }
}