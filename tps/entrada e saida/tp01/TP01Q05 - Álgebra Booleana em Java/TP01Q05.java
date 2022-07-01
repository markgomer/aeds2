public class TP01Q05 {
  
  static final int max = 3;

  static class Leitor {
    int cursorPos;  // posicao do cursor
    String linha;  // onde cada linha da entrada e armazenada
    boolean[] valores = new boolean[max];  // array com valores dos operandos
  }
  
  static boolean calculaExpressaoBooleana(Leitor leitor) {
    boolean resp = true;

    System.out.println(leitor.linha.charAt(leitor.cursorPos));
    // identificar operacoes
    switch(leitor.linha.charAt(leitor.cursorPos++)) {  // identifica proximo caracter
      case 'A': resp = leitor.valores[0];
        break;  
      case 'B': resp = leitor.valores[1];
        break;
      case 'C': resp = leitor.valores[2];
        break;  
        
      // operador = and
      case 'a':
        leitor.cursorPos += 3;  // pula o "nd("
        resp = calculaExpressaoBooleana(leitor);  // chamada recursiva
        while(leitor.linha.charAt(leitor.cursorPos) == ',') {  // continua lendo se encontrar uma virgula
          resp = and(resp, calculaExpressaoBooleana(leitor));  // acumulando os resultados em resp
        }
        leitor.cursorPos++;  // pula o ')'
        break;
      
      // operador = or
      case 'o':  
        leitor.cursorPos += 2;  // pula o "r("
        resp = calculaExpressaoBooleana(leitor);  // chamada recursiva
        while(leitor.linha.charAt(leitor.cursorPos) == ',') {  // continua lendo se encontrar uma virgula
          resp = or(resp, calculaExpressaoBooleana(leitor));  // acumulando os resultados em resp
        }
        leitor.cursorPos++;  // pula o ')'
        break;
      
      // operador = not
      case 'n':  
        leitor.cursorPos += 3;  // pula o "ot("
        resp = not(calculaExpressaoBooleana(leitor));  // chamada recursiva
        leitor.cursorPos++;  // pula o ')'
        break;
    }
    
    return resp;
  }

  // retorna string recebida sem espacos em branco
  static String removeEspacos(String s) {
    String semEspacos = "";  // string para retorno
    for(int i = 0; i < s.length(); i++) {  // percorre string recebida
      if(s.charAt(i) != ' ') {  // ignora espaco em branco
        semEspacos += s.charAt(i);
      } 
    }
    return semEspacos + '\0';
  }
  
  // converte inteiro recebido para true ou false
  static boolean toBool(int x) {return x == 0 ? false : true;}
  
  // operacoes booleanas
  static boolean and(boolean A, boolean B)  {return A && B;}
  static boolean or(boolean A, boolean B)   {return A || B;}
  static boolean not(boolean A)             {return !A;}


  public static void main(String[] args) throws InterruptedException {
    Leitor leitor = new Leitor();
    int numValores = 0;
    //Leitura da entrada padrao
    
    while (leitor.linha != "0") {
      leitor.cursorPos = 0;  // reseta cursor
      leitor.linha = removeEspacos(MyIO.readLine());  // remove espacos em branco da string
      
      // primeiro char da linha = numero de operandos da expressao 
      // "-48": converte da tabela ASCII para decimal
      numValores = (int)(leitor.linha.charAt(0) - 48); 
      leitor.cursorPos++;  // anda 1 posicao com cursor

      // popula array de valores
      for(int i = 0; i < numValores; i++) {
        // anda 1 posicao com cursor / "-48": converte de ASCII para decimal        
        leitor.valores[i] = toBool((int)(leitor.linha.charAt(leitor.cursorPos++) - 48));  
      }
      // retorna resultado da expressao lida
      MyIO.println(calculaExpressaoBooleana(leitor)? "1" : "0");
    }
  }
}