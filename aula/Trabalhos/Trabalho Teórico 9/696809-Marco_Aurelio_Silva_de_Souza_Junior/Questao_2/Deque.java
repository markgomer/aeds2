class Deque {
  static int deque[] = new int[10];

  static int[] removerDir() {
    int l = deque.length;
    int novoDeque[] = new int[l-1];
    if(deque == null || l == 0) {
      System.out.println("Deque vazio!");
    } else {
      for(int i = 0; i < l - 1; i++) {
        novoDeque[i] = deque[i];
      }
    }
    return novoDeque;
  }
  
  static int[] removerEsq() {
    int l = deque.length;
    int novoDeque[] = new int[l-1];
    if(deque == null || l == 0) {
      System.out.println("Deque vazio!");
    } else {
      for(int i = 0; i < l - 1; i++) {
        novoDeque[i+1] = deque[i];
      }
    }
    return novoDeque;
  }
  
  static int[] inserirDir(int n) {
    int l = deque.length;
    int novoDeque[] = new int[l+1];
    for(int i = 0; i < l + 1; i++) {
      novoDeque[i] = deque[i];
    }
    novoDeque[l] = n;
    return novoDeque;
  }
  
  static int[] inserirEsq(int n) {
    int l = deque.length;
    int novoDeque[] = new int[l+1];
    novoDeque[0] = n;
    for(int i = 1; i < l + 1; i++) {
      novoDeque[i] = deque[i-1];
    }
    return novoDeque;
  }
}