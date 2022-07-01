public class TP01Q11 {
  
  public static boolean ehPalindromo(String s) {
    return ehPalindromo(s,0);
  }

  // retorna true se parametro for um palindromo
  public static boolean ehPalindromo(String s, int i) {
    boolean resp = true;
    if(i < s.length()/2) {  // condicao de parada, metade da string
      if(s.charAt(i) != s.charAt(s.length()-i-1)) {  // se caracter examinado for diferente de seu oposto na string
        resp = false;  // nao eh palindromo, interrrompe funcao
      } 
      else {
        // caso caracteres sejam iguais, passa para proxima chamada
        resp = ehPalindromo(s, i + 1);  
      }
    }
    return resp;
  }

  public static boolean isFim(String s){
    return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
  }

  public static void main(String[] args) {
    String[] entrada = new String[1000];
    int numEntrada = 0;

    //Leitura da entrada padrao
    do {
       entrada[numEntrada] = MyIO.readLine();
    } while (isFim(entrada[numEntrada++]) == false);
    numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

    //Para cada linha de entrada, diz se eh palindromo
    for(int i = 0; i < numEntrada; i++){
       MyIO.println(ehPalindromo(entrada[i]) ? "SIM" : "NAO");
    }
 }
}