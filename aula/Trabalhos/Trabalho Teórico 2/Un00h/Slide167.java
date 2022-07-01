class Slide167 {
  public static int multiplicacaoRecursiva(int a, int b) {
    int result = 0;
    if(b > 0) {
      result = a + multiplicacaoRecursiva(a, b-1);
    }
    return result;
  }
  
  public static void main(String[]args) {
    System.out.println(multiplicacaoRecursiva(2, 5));
  }
}