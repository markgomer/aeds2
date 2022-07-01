class _03_slide_7 {
  boolean isLetra(char c){
    return c<='A' && c<='Z' || c>='a' && c<='z';
  }
  
  char toUpper(char c){
    return c >= 'a' && c <= 'z' ? (char)(c - 32) : c ;
  }
  
  boolean isVogal(char c){
    c = toUpper(c);
    return c =='A' || c =='E' || c =='I' || c =='O' || c =='U';
  }

  boolean hasConsoante(String s, int n) { // alteração do nome do método
    boolean resp = true;
    // 
    if (n != s.length()) {
      // retorna false se for algarismo
      if (s.charAt(n) < '0' || s.charAt(n) > '9') { 
        // retorna true se for vogal
        if (isVogal(s.charAt(n))) {
          resp = false;
        } else {
          resp = hasConsoante(s, n + 1);
        }
      } else {
        resp = false;
      }
    }
    return resp;
  }
}