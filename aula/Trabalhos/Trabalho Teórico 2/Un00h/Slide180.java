public class Slide180 {
  static int contMaiusculo (String s){
    return contMaiusculo (s, 0); /* inicializa contador da recurs�o */
  }

  static boolean isUpper(char c) {
    return c >= 'A' && c <= 'Z' ? true : false; /* retorna true se caracter for maiusculo */
  }
    
  static int contMaiusculo (String s, int i){
    int resp = 0;  
    if(i < s.length()) {  /* condicao de parada */
      /* 
      * verifica se caracter na String eh maiusculo
      * se verdadeiro, acrescenta 1 e faz chamada recursiva 
      * se falso, faz chamada recursiva 
      */
      resp = isUpper(s.charAt(i)) ? 1 + contMaiusculo(s, i+1): contMaiusculo(s, i+1);
    }
    return resp;
  }
  
  
  public static void main(String[] args) {
    /* testes */
    System.out.println(contMaiusculo("1Aaaaa")==1?true:false);
    System.out.println(contMaiusculo("1Aa*aAa")==2?true:false);
    System.out.println(contMaiusculo("1AaBaC")==3?true:false);
    System.out.println(contMaiusculo("1AB C  +aD")==4?true:false);
    System.out.println(contMaiusculo("1ABCDE")==5?true:false);
    System.out.println(contMaiusculo("1_aaaa__")==0?true:false);
  }
}