public class TP01Q15 {
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

  // percorre caracteres de uma string e
  // retorna true se contiver apenas vogais
  public static boolean somenteVogais(String s) {
    return somenteVogais(s, 0); // inicializa contador
  }
  public static boolean somenteVogais(String s, int i) {
    boolean resp = true;
    if(i < s.length()) {  // condicao de parada
      // se caracter inspecionado nao for vogal
      if(!isVogal(s.charAt(i))) {
        resp = false;  // interrompe chamadas recursivas e retorna false
      }
      else {
        // se caracter for vogal, faz chamada recursiva
        resp = somenteVogais(s, i + 1); 
      }
    }
    return resp;
  }
  
  // percorre caracteres de uma string e
  // retorna true se contiver apenas consoantes
  public static boolean somenteConsoantes(String s) {
    return somenteConsoantes(s, 0); // inicializa contador
  }
  public static boolean somenteConsoantes(String s, int i) {
    boolean resp = true;
    if(i < s.length()) {  // condicao de parada
      // se caracter examinado nao for consoante
      if(!isConsoante(s.charAt(i))) {
        resp = false;  // interrompe chamadas recursivas e retorna false
      }
      else {  // se caracter for consoante, faz chamada recursiva
        resp = somenteConsoantes(s, i + 1);
      }
    }
    return resp;
  }

  // percorre caracteres de uma string e
  // retorna true se o parametro contiver um numero inteiro
  public static boolean isInt(String s) {
    return isInt(s, 0);
  }
  public static boolean isInt(String s, int i) {
    boolean resp = true;
    if(i < s.length()) {  // condicao de parada
      if(!isNumber(s.charAt(i))) {  // verifica se caracter eh numeral
        resp = false;  // se nao for numero, interrompe chamadas recursivas e retorna false
      }
      else {  // se caracter for numeral, faz chamada recursiva
        resp = isInt(s, i + 1);
      }
    }
    return resp;
  }
  
  // retorna true se o parametro contiver um numero real
  public static boolean isDouble(String s) {
    return isDouble(s, 0, 0);  // inicializa contadores
  }
  public static boolean isDouble(String s, int i, int count) {
    boolean resp = true;
    if(i < s.length()) {  // condicao de parada
      if(!isNumber(s.charAt(i))) { // se nao for numero
        if(s.charAt(i) == ',' || s.charAt(i) == '.') {  // se for ponto ou virgula
          count++;  // incrementa contador
          if(count > 1) {  // se tiver mais de um ponto ou virgula
            resp = false;  // nao e numero real, interrompe chamadas
          }
          else {
            resp = isDouble(s, i + 1, count);
          }
        } 
        else {  // se nao for numero, nem ',' ou '.', nao e numero real
          resp = false;  // nao e numero real, interrompe chamadas
        }
      }
      else {  // atendendo as condicoes, faz chamada recursiva
        resp = isDouble(s, i + 1, count);
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