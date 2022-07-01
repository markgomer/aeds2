/**
 * O programa recebe entradas pelo terminal referentes a uma expressão booleana e retorna
 * o resultado da expressão.
 * são lidas várias expressões de uma vez e o resultado é mostrado ao final.
 * para parar de inserir expressões, insira a expressão apenas com um 0.
 * 
 * deve-se inserir a quantidade de variáveis que serão introduzidas 
 * e o valor inicial de cada uma.
 * 
 * exemplo:
 * 2 0 1 and(not(A) , not(B))
 * ^ numero de variaveis (A e B)
 * "0 1" = valor inicial de A (false) e B(true), respectivamente
 * 
 * exemplos: 
 * 3 0 0 0 and(or(A , B) , not(and(B , C)))
 * 3 1 1 1 or(and(A , B , C) , and(or(A , B) , C)) 
 * 
 */
 
public class TP01Q14 {
  
  // numero máximo de variáveis dentro de uma expressão booleana
  static final int max = 3;

  static int cursorPos;  // posicao do cursor de leitura
  static boolean[] valores = new boolean[max];  // array com valores dos operandos
  

  // recebe linha da expressao booleana e retorna o resultado em boolean
  static boolean calculaExpressaoBooleana(String linha) {
    boolean resp = true; // resposta será inserida nessa variável

    // identificar operacoes
    // cursorPos++ anda com o cursor para a direita da expressão
    switch(linha.charAt(cursorPos++)) {  // identifica proximo caracter
      case 'A': resp = valores[0]; // se o caracter lido for 'A', insere seu valor no array
        break;  
      case 'B': resp = valores[1];
        break;
      case 'C': resp = valores[2];
        break;  
        
      // operador = and
      // se o caracter lido for 'a', será o início de uma operação 'and'
      case 'a':
        cursorPos += 3;  // pula o "nd("
        resp = calculaExpressaoBooleana(linha);  // chamada recursiva
        while(linha.charAt(cursorPos) == ',') {  // continua lendo se encontrar uma virgula
          cursorPos++;
          // resp recebe o valor do método and(bool,bool)
          resp = and(resp, calculaExpressaoBooleana(linha));
        }
        cursorPos++;  // pula o ')'
        break;
      
      // operador = or
      // se o caracter lido for 'o', será o início de uma operação 'or'
      case 'o':  
        cursorPos += 2;  // pula o "r("
        resp = calculaExpressaoBooleana(linha);  // chamada recursiva
        while(linha.charAt(cursorPos) == ',') {  // continua lendo se encontrar uma virgula
          cursorPos++;
          resp = or(resp, calculaExpressaoBooleana(linha));  // acumulando os resultados em resp
        }
        cursorPos++;  // pula o ')'
        break;
      
      // operador = not
      // se o caracter lido for 'n', será o início de uma operação 'not'
      case 'n':  
        cursorPos += 3;  // pula o "ot("
        resp = not(calculaExpressaoBooleana(linha));  // chamada recursiva
        cursorPos++;  // pula o ')'
        break;
    }
    
    return resp;
  }


  /**
   * recebe String relativa a uma expressão booleana e
   * retorna a mesma sem os espaços em branco
   */
  static String removeEspacos(String s) {
    String semEspacos = "";  // string para retorno
    for(int i = 0; i < s.length(); i++) {  // percorre string recebida
      if(s.charAt(i) != ' ') {  // ignora espaco em branco
        semEspacos += s.charAt(i);
      } 
    }
    return semEspacos;
  }

  
  // converte inteiro recebido para true ou false
  // 0 => false
  // qualquer outro valor retorna true
  static boolean toBool(int x) {
    return x == 0 ? false : true;
  }
  
  // operacoes booleanas
  static boolean and(boolean A, boolean B)  {return A && B;}
  static boolean or(boolean A, boolean B)   {return A || B;}
  static boolean not(boolean A)             {return !A;}


  /**
   * método para determinar se deve continuar lendo expressões
   * se receber um 0, retorna true
   * qualquer outra String, retorna false
   */
  public static boolean isFim(String s){
    return (s.length() >= 1 && s.charAt(0) == '0');
  }


  public static void main(String[] args) throws InterruptedException {
    // array onde serão armazenadas as expressões booleanas
    String[] entrada = new String[1000];
    
    int numEntrada = 0; // contador do número de expressões inseridas
    
    // contador do número de variaveis que a expressão booleana irá conter
    // o máximo são 3
    int numVariaveis; 
    
    // faz a leitura da entrada padrao
    // até receber uma expressão igual a 0 
    do {
      // faz a leitura de uma linha inserida
      // remove os espaços na expressão
      // insere no array de entradas, na posição do contador 'numEntrada'
      entrada[numEntrada] = removeEspacos(MyIO.readLine());
      
      // checar se a entrada é igual a zero,
      // se não, continua lendo a próxima linha
      // incrementa o contador 'numEntrada'
    } while (isFim(entrada[numEntrada++]) == false);
    numEntrada--;   //Desconsiderar ultima linha contendo a string 0


    for(int i = 0; i < numEntrada; i++) {  
      cursorPos = 0;  // reseta cursor de leitura
      // primeiro char da linha = numero de operandos da expressao 
      // "-48": converte da tabela ASCII para decimal
      numVariaveis = (int)(entrada[i].charAt(0) - 48); 
      cursorPos++;  // anda 1 posicao com cursor

      // popula array de valores
      for(int j = 0; j < numVariaveis; j++) {
        // anda 1 posicao com cursor / "-48": converte de ASCII para decimal        
        valores[j] = toBool((int)(entrada[i].charAt(cursorPos++) - 48));  
      }

      // escreve na saida o resultado da expressao lida
      MyIO.println(calculaExpressaoBooleana(entrada[i])? "1" : "0");
    }
  }
}