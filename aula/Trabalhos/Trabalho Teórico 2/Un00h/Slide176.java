class Slide176 {
  public static int maiorElemento(int array[], int tamanho) {
    int maior;

    if(tamanho <= 0) {
      return maior = array[0];
    }
    
    else {
      maior = maiorElemento(array, tamanho-1);
      if(array[tamanho-1] > maior) {
        maior = array[tamanho-1];
      }
    }
    return maior;
  }

  public static void main(String[] args) {
    int array[] = {2,5,4,5,6,2,1};
    System.out.println(maiorElemento(array, 7));
  }
}