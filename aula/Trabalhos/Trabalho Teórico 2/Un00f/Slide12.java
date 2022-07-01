// Faça um programa que abre um arquivo e cria uma cópia

public class Slide12 {
  public static void main (String[] args) {
    Arq.openRead("exemplo.txt");
    String texto = "";
    texto = Arq.readAll();
    Arq.close();
    
    Arq.openWrite("exmplo(copia).txt");
    Arq.print(texto);
    Arq.close();
  }
}

