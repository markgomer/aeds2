class Slide183 {
  /* 
  Faca um metodo recursivo que receba um string e retorne um n�mero
  inteiro indicando a quantidade de caracteres NOT vogal AND NOT
  consoante maiuscula da string recebida como parametro 
  */
  
  /* converte caracter para maiusculo */
  static char toUpper(char c){
    return (c >= 'a' && c <= 'z') ? (char)(c - 32) : c ;
  }

  /* retorna true se caracter for vogal */
  static boolean isVogal(char c) {
    c = toUpper(c);
    return c == 'A' || c =='E' || c == 'I' || c == 'O' || c == 'U' ? true : false;
  }

  /* retorna true se caracter for maiusculo */
  static boolean isMaiuscula(char c) {
    return c >= 'A' && c <= 'Z' ? true : false;
  }

  /* chamada para inicializacao do contador na recursividade */
  static int notVogalAndNotMaiuscula(String s) {
    return notVogalAndNotMaiuscula(s,0);
  }

  static int notVogalAndNotMaiuscula(String s, int i) {
    int resp = 0;
    if(i < s.length()) {
      char c = s.charAt(i);
      if ( !isVogal(c) && !isMaiuscula(c) ) {
        resp = 1 + notVogalAndNotMaiuscula(s, i + 1);
      }
      else {
        resp = notVogalAndNotMaiuscula(s, i + 1);
      }
    }
    return resp;
  }

  /*
  Faca um metodo recursivo que receba um array de inteiros e os ordene
  */
  static int array[] = {5,4,3,2,1};

  static void swap(int i, int j) {
    int aux = array[i];
    array[i] = array[j];
    array[j] = aux;
  }
  public static void ordenarArray() {
    ordenarArray(0);
  }
  public static void ordenarArray(int i) {
    int menor = i;
    if(i < array.length - 1) {
      for(int j = i + 1; j < array.length; j++) {
        if (array[menor] > array[j]) {
          menor = j;
        }
      }
      swap(menor, i);
      ordenarArray(i + 1);
    }
  }

  /*
  https://github.com/icei-pucminas/AEDII/blob/124e3db8494c9b9a700bcb062ed2c033658c6702/fonte/U4%20-%20Ordena%C3%A7%C3%A3o%20em%20mem%C3%B3ria%20principal/java/Geracao.java#L9
  */
  public static void mostrar() {
    System.out.print("[ ");

    for (int i = 0; i < array.length; i++) {
       System.out.print("("+i+")" + array[i] + " ");
    }

    System.out.println("] ");
  }

  public static void main(String[] args) {
    /* testes */
    System.out.println(notVogalAndNotMaiuscula("1Aaaaa")==1?true:false);
    System.out.println(notVogalAndNotMaiuscula("1Aa*aAa")==2?true:false);
    System.out.println(notVogalAndNotMaiuscula("1AaBaCc")==2?true:false);
    System.out.println(notVogalAndNotMaiuscula("1AB C  +ad")==6?true:false);
    System.out.println(notVogalAndNotMaiuscula("1ABCDE")==1?true:false);
    System.out.println(notVogalAndNotMaiuscula("aaaaDDBBBS")==0?true:false);

    ordenarArray();
    mostrar();
  }
}