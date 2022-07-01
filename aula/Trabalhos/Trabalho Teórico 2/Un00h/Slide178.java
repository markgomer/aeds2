class Slide178 {

  static boolean isPalindromo(String s) {
    return isPalindromo(s, 0);  /* chamada para inicializacao do contador */
  }

  static boolean isPalindromo(String s, int i) {
    boolean resp;
    if (i >= s.length() / 2) {  /* condicao de parada, se o contador chegar no meio da string */
      resp = true;
    } else if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {  /* se o caracter for diferente de seu simetrico na string */
      resp = false;
    } else {
      resp = isPalindromo(s, i + 1);  /* chamada recursiva */
    }
    return resp;
  }

  public static void main(String[] args) {
    String s = "socorram me subi no onibus em marrocos";
    System.out.println(isPalindromo(s));
  }
}