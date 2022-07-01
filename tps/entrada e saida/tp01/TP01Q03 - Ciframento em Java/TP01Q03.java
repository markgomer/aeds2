public class TP01Q03 {

  // retorna a string enviada na cifra de Cesar
  public static String toCifraCesar(String s) {
    String cifrado = "";
    char tmp;

    for(int i = 0; i < s.length(); i++) {  
      tmp = s.charAt(i);
      if (tmp >= 'a' || tmp <= 'w' || tmp >= 'A' || tmp <= 'W') {
        tmp = (char) (tmp + 3);  // aplica a cifra
      } else {
        tmp = (char) (tmp - 23); // Para X, Y, e Z converter para A, B e C
      }

      cifrado += tmp;  // converte cada caracter para a cifra de César
    }

    return cifrado;
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