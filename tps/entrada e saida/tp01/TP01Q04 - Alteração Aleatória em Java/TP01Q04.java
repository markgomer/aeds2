import java.util.*;

public class TP01Q04 {
  
  public static String alteracaoAleatoria(String s, Random gerador) {
    // gera caracter aleatório para a letra substituída e substituta
    char sai = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
    char entra = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
    
    String resp = "";  // string de retorno
    for(int i = 0; i < s.length(); i++){  // percorre string parametrizada
      if(s.charAt(i) == sai) { 
        resp += entra;  // substitui caracter sorteado por outro sorteado
      } 
      else {
        resp += s.charAt(i);  // mantém mesmo caracter, caso não tenha sido sorteado
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

    
    Random gerador = new Random();  // cria novo objeto Random
    gerador.setSeed(4); // configura seed para 4

    // para cada linha de entrada, gera a saída solicitada
    for(int i = 0; i < numEntrada; i++){
      MyIO.println(alteracaoAleatoria(entrada[i],gerador));
    }
  }
}