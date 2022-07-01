public class TP01Q06 {
  
  // converte caracter para maiusculo
  public static char toUpper(char c){
    return c >= 'a' && c <= 'z' ? (char)(c - 32) : c ;
  }

  // retorna true se caracter for vogal
  public static boolean isVogal(char c){
    c = toUpper(c);
    return c =='A' || c =='E' || c =='I' || c =='O' || c =='U';
  }

  // retorna true se caracter for consoante
  public static boolean isConsoante(char c) {
    c = toUpper(c);
    return isLetra(c) && !isVogal(c);
  }

  // retorna true se caracter for letra
  public static boolean isLetra(char c){
    c = toUpper(c);
    return c >= 'A' && c <= 'Z';
  }

  // retorna true se caracter for numeral
  public static boolean isNumber(char c) {
    return c >= '0' && c <= '9';
  }

  // retorna true se o parametro contiver apenas vogais
  public static boolean somenteVogais(String s) {
    boolean resp = true;
    for(int i = 0; i < s.length(); i++) {
      if(!isVogal(s.charAt(i))) {
        resp = false;
      }
    }
    return resp;
  }
  
  // retorna true se o parametro contiver apenas consoantes
  public static boolean somenteConsoantes(String s) {
    boolean resp = true;
    for(int i = 0; i < s.length(); i++) {
      if(!isConsoante(s.charAt(i))) {
        resp = false;
      }
    }
    return resp;
  }
  
  /// retorna true se o parametro contiver um numero inteiro
  public static boolean isInt(String s) {
    boolean resp = true;
    for(int i = 0; i < s.length(); i++) {
      if(!isNumber(s.charAt(i))) {
        resp = false;
      }
    }
    return resp;
  }
  
  // retorna true se o parametro contiver um numero real
  public static boolean isDouble(String s) {
    boolean resp = true;
    int count = 0;  // para contar pontos e virgulas
    for(int i = 0; i < s.length(); i++) {  // percorre caracteres de "s"
      if(!isNumber(s.charAt(i))) { // se nao for numero
        if(s.charAt(i) == ',' || s.charAt(i) == '.') {  // se for ponto ou virgula
          count++;  // incrementa contador
          if(count > 1) {  // se tiver mais de um ponto ou virgula
            resp = false;  // nao e numero real
            i = s.length(); // interrompe laco
          }
        } else {  // se nao for numero, nem ',' ou '.', nao e numero real
          resp = false;
        }
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
    } while (!isFim(entrada[numEntrada++]));
    numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

    // para cada linha de entrada, gera a saida solicitada
    for(int i = 0; i < numEntrada; i++){
      MyIO.print(somenteVogais(entrada[i])? "SIM " : "NAO ");
      MyIO.print(somenteConsoantes(entrada[i])? "SIM " : "NAO ");
      MyIO.print(isInt(entrada[i])? "SIM " : "NAO ");
      MyIO.println(isDouble(entrada[i])? "SIM" : "NAO");
    }
  }
}