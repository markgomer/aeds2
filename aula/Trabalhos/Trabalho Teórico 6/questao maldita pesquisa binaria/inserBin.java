import java.util.*;

public class inserBin {
  static int array[] = {12, 4, 8, 2, 14, 17, 6, 18, 10, 16, 15, 5, 13, 9, 1, 11, 7, 3};
  static int n = array.length;

  public static void mostrar() {
    System.out.print("[");
    for (int i = 0; i < n; i++) {
      System.out.print(" ("+i+")" + array[i]);
    }
    System.out.println(" ]");
  }
  public static void crescente() {
    for (int i = 0; i < n; i++) {
      array[i] = i;
    }
  }
  public static void aleatorio() {
    Random rand = new Random();
    crescente();	
    for (int i = 0; i < n; i++) {
      swap(i, Math.abs(rand.nextInt()) % n);
    }
  }
  public static void swap(int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

 
  public static int binarySearch(int inserted, int pos) {
    int dir = pos - 1, esq = 0, meio;
    while (esq <= dir) {
      meio = (esq + dir) / 2;
      if (inserted == array[meio]){
        esq = pos;
      } else if (inserted > array[meio]){
        esq = meio + 1;
      } else {
        dir = meio - 1;
      }
    }
    return esq;
  }
   
          
  public static void sort() {
    for (int i = 1; i < n; i++) {
      int pivo = array[i];
      int j = i - 1;

      if(array[j] > pivo) {
        int pos = binarySearch(array[j], j);
        array[pos] = pivo;
        
        for(int k = i; k > pos; k--) {
          array[i] = array[i-1];
        }
      }

      /*
      while ((j >= 0) && (array[j] > pivo)) {
        array[j + 1] = array[j];
        j--;
      }
      array[j + 1] = pivo;
      */
    }
	}

  public static void main(String[] args) {
    aleatorio();
    mostrar();
    sort();
    mostrar();
  }
}
