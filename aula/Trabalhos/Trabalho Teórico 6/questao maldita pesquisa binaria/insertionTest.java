class insertionTest {
  static int array[] = {12, 4, 8, 2, 14, 17, 6, 18, 10, 16, 15, 5, 13, 9, 1, 11, 7, 3};
  static int n = array.length;

  public static void sort() {
		for (int i = 1; i < n; i++) {
			int tmp = array[i];  // movimentacao
      System.out.println("Movimentacao: " + tmp + " <--> " + array[i]);
      int j = i - 1;
      while ((j >= 0) && (array[j] > tmp)) {  // comparacao
        if (array[j] >= tmp) System.out.println("Comparacao: " + array[j] + " >= " + tmp);
        
        array[j + 1] = array[j];  System.out.println("Movimentacao: " + array[j+1] + " <--> " + array[j]);
        
        j--;
      }
        array[j + 1] = tmp; // movimentacao
        System.out.println("Movimentacao: " + array[j+1] + " <--> " + tmp);
      }
  }
  
  public static void main(String args[]) {
    sort();
  }
}