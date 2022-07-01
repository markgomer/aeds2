public class Slide30 {
   // Leia o nome de um arquivo e uma frase e salve essa frase nesse arquivo
   public static void exercicioA(String nome, String frase) {
      Arq.openWriteClose(nome, frase);
   }


   // Leia o nome de um arquivo e mostre seu conteúdo na tela
   public static void exercicioB(String nome) {
      System.out.println(Arq.openReadClose(nome));
   }
   

   // Leia o nome de um arquivo e mostre seu conteúdo convertido para letras maiúsculas
   public static char toUpper(char c){
      return c >= 'a' && c <= 'z' ? (char)(c - 32) : c ;
   }
   public static String exercicioC(String nome) {
      Arq.openRead(nome);
      String s = "";
      String maiuscula = "";
      
      while(Arq.hasNext()) {  /* lê todo o conteudo do arquivo e atribui à String "s" */
         s += Arq.readLine() + "\n";
      }

      for(int i=0 ; i<s.length() ; i++) {  /* percorre string lida e converte cada caracter para maiúsculo */
         maiuscula += toUpper(s.charAt(i));
      }

      System.out.println(maiuscula);
      System.out.println(maiuscula.toUpperCase());
      return maiuscula.toUpperCase();
   }


   // Leia o nome de dois arquivos e copie o conteúdo do primeiro para o último
   public static void exercicioD(String arqFonte, String arqDestino) {
      String copia = Arq.openReadClose(arqFonte);
      Arq.openWriteClose(arqDestino, copia);
   }


   // Leia o nome de dois arquivos, abra o primeiro, converta seu conteúdo para letra maiúscula e salve o no segundo
   public static void exercicioE(String arqFonte, String arqDestino) {
      String conteudoMaiusculo = exercicioC(arqFonte);
      Arq.openWriteClose(arqDestino, conteudoMaiusculo);
   }


   /* Leia o nome de dois arquivos e copie o conteúdo do primeiro
      para o segundo invertendo a ordem dos caracteres. O último
      caractere no arquivo de entrada será o primeiro do de saída, o
      penúltimo caractere será o segundo, ...
   */
   public static void exercicioF(String arqFonte, String arqDestino) {
      String sourceContent = Arq.openReadClose(arqFonte); /* lê arquivo e atribui conteudo à String sourceContent */
      String resp = "";

      for(int i = sourceContent.length()-1; i >= 0; i--) { /* percorre string ao contrário e atribui cada char à String "resp" */
         resp += sourceContent.charAt(i);
      }
      Arq.openWriteClose(arqDestino, resp); /* escreve a String resp no novo arquivo */
   }


   // Leia o nome de um arquivo e mostre na tela o conteúdo desse arquivo criptografado usando o ciframento de César (k = 3)
   public static boolean isLetra(char c) {
      return c >= 'A' && c <= 'Z' ? true : false; 
   }
   public static char toCifraCesar(char c) {
      char cifra;
      c = toUpper(c);  /* converte os caracteres para maiúsculo */
      if(isLetra(c)) { 
         if(c >= 'A' || c <= 'W') {
            cifra = (char)(c + 3); 
         } else {
            cifra = (char)(c - 23); /* Para X, Y, e Z converter para A, B e C */
         }
      } else {
         cifra = c; /* Se não for letra, não converter */
      }
      return cifra;
   }
   public static String exercicioG(String nomeArq) {
      String sourceContent = Arq.openReadClose(nomeArq);  /* lê todo o conteúdo do arquivo e atribui à String sourceContent */
      String cifrado = "";
      for(int i = 0; i < sourceContent.length(); i++) {  
         cifrado += toCifraCesar(sourceContent.charAt(i));  /* converte cada caracter para a cifra de César */
      }
      MyIO.println("Cifra de César");  
      MyIO.println(cifrado);  /* mostra na tela o resultado */
      return cifrado;
   }


   /* Leia o nome de um arquivo contendo uma mensagem
      criptografada com o Ciframento de César (k = 3),
      descriptografe a mensagem e mostre-a na tela
   */
   public static char fromCifraCesar(char c) {
      char resp;
      if(isLetra(c)) {
         if(c >= 'D' || c <= 'Z') {
            resp = (char)(c - 3); 
         } else {
            resp = (char)(c + 23); /* Para X, Y, e Z converter para A, B e C */
         }
      } else {
         resp = c; /* Se não for letra, não converter */
      }
      return resp;
   }
   public static void exercicioH(String nomeArq) {
      String cript = Arq.openReadClose(nomeArq);
      String decript = "";
      for(int i = 0; i < cript.length(); i++) {
         decript += fromCifraCesar(cript.charAt(i));
      }
      MyIO.println(decript);
   }

   /*
      Seja os arquivos Pilha.java e PrincipalPilha.java
      (fonte/tadEstatica), altere o segundo de tal forma que ele
      contenha um menu com as opções (1) Inserir, (2) Remover (3)
      Listar (4) Sair, permitindo inserções, remoções e listar os
      elementos de uma pilha. Quando executamos o PrincipalPilha,
      ele lê os elementos da pilha de um arquivo pilha.dat. Quando
      o usuário seleciona a opção (4) Sair, o programa salva os
      elementos da pilha no arquivo pilha.dat. Devemos manter a
      ordem dos elementos a cada nova execução do PrincipalPilha.
   */

   public static void main(String[] args) {
      exercicioA("AAAAA.txt", "a ligeira raposa marrom\nsaltou sobre o cachorro cansado");
      exercicioB("AAAAA.txt");
      exercicioC("AAAAA.txt");
      exercicioD("AAAAA.txt", "A_copia.txt");
      exercicioE("AAAAA.txt", "ex_E_copia_Maiuscula");
      exercicioF("AAAAA.txt", "ex_F_copia_invertida");
      exercicioG("AAAAA.txt");
      exercicioH("AAAAA.txt");
   }
}