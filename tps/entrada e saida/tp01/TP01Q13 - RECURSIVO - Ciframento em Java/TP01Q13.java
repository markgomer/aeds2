public class TP01Q13 {

  public static String toCifraCesar(String s) {
    return toCifraCesar(s, 0);
  }
  // retorna a string enviada na cifra de Cesar
  public static String toCifraCesar(String s, int i) {
    char tmp;  // caracter a ser cifrado
    String resp = "";

    if(i < s.length()) {  // condicao de parada
      tmp = s.charAt(i);
      // verifica caracater a ser cifrado
      if (tmp >= 'a' || tmp <= 'w' || tmp >= 'A' || tmp <= 'W') {
        tmp = (char) (tmp + 3);  // aplica a cifra para caracteres diferentes de x, y e z
      } else {
        tmp = (char) (tmp - 23); // Para X, Y, e Z converter para A, B e C
      }
      // acrescenta caracter cifrado a resposta e faz chamada recursiva
      resp += tmp + toCifraCesar(s, i + 1);
    }
    return resp;
  }

  public static boolean isFim(String s){
    return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
  }

  public static void main(String args[]) {
    String[] entrada = new String[1000];
    int numEntrada = 0;

    //Leitura da entrada padrao
    do {
        entrada[numEntrada] = MyIO.readLine();
    } while (isFim(entrada[numEntrada++]) == false);
    numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

    //Para cada linha de entrada, gerando uma de saida
    for(int i = 0; i < numEntrada; i++) {  
      MyIO.println(toCifraCesar(entrada[i]));
    }
  }
}