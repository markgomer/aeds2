class TP01Q01 {
   public static boolean ehPalindromo(String str) {
      boolean resp = true;
      int tam = str.length();
      // percorre string de entrada até a metade
      for(int i=0 ; i<tam/2 ; i++) {
         // compara primeiro e ultimo caracteres
         if(str.charAt(i) != str.charAt(tam-i-1)) {
            resp = false; // se forem diferentes, não é palíndromo
            i = tam; // interrompe o laço for
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

      //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
      for(int i = 0; i < numEntrada; i++){
         MyIO.println(ehPalindromo(entrada[i]) ? "SIM" : "NAO");
      }
   }
}