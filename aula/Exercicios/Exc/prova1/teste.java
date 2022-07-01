public class teste {
  
  public static void main(String[] args) {
    int a = 0;
    int n = 10;
    for (int i = 1; i <= n; i <<= 1){
      a += 3;
      System.out.println("i = " + i);
    }
  }
}
