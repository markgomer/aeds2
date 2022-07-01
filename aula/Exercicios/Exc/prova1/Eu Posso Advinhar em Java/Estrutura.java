public class Estrutura {
  //depende do indice do elemento no qual o comando dois atua

  //stack = pilha: UEPS    com2 atua no ultimo elemento inserido
  //queue = fila: PEPS     com2 atua no primeiro elemento inserido
  //pr queue = fila de prioridade: 
  //impossivel: com2 tenta tirar elem que nao existe
  //not sure: com2 atua sobre => (primeiro = ultimo)

  public static void comando1(int array[], int x) {
    
  }
  
  
  public static void comando2(int array[], int x) {

  }

  public static void main(String[] args) {
    
    do {
      int numComandos = MyIO.readInt();
      int[] array = new int[numComandos];
      for(int i = 0; i < numComandos; i++) {
        int comando = MyIO.readInt();
        if(comando == 1) {
          int elemento = MyIO.readInt();
          comando1(array, elemento);
        }
        else if(comando == 2) {
          int elemento = MyIO.readInt();
          comando2(array, elemento);
          
        }
      }
    } while(MyIO.readInt()!=-1);
  
  }

}