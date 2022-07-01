/**
 * Mostre todas as comparações e movimentações do algoritmo anterior para 
 * o array abaixo:
 */

public class Slide137 {
  static int array[] = {12,4,8,2,14,17,6,18,10,16,15,5,13,9,1,11,7,3};
  
  public static void swap(int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void ordenarPorSelecao(int arr[]) {
    int n = arr.length;
    int comparacoes = 0;
    int movimentacoes = 0;
    for (int i = 0; i < (n - 1); i++) {
      int menor = i;
      for (int j = (i + 1); j < n; j++){
        comparacoes++;
        System.out.println("Comparações = " + comparacoes);
        if (array[menor] > array[j]){
          menor = j;
        }
      }
      swap(menor, i); 
      movimentacoes++;
      System.out.println("Movimentações = " + movimentacoes);
    }
  }

  public static void main(String[] args) {
    for(int i = 0; i<array.length; i++) {
      System.out.print(array[i] + " ");
    }

    System.out.println("\n");
    ordenarPorSelecao(array);
    System.out.println("\n");
    
    for(int i = 0; i<array.length; i++) {
      System.out.print(array[i] + " ");
    }
    
    System.out.println(" ");
  }
}