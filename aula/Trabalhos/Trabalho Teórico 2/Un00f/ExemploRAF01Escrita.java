//Arquivo ExemploRAF01Escrita.java
import java.io.*;

class ExemploRAF01Escrita {
  public static void main(String[] args) throws Exception {
    RandomAccessFile raf = new RandomAccessFile("exemplo.txt", "rw"); // cria nova instância de RandomAccessFile, cria exemplo.txt em leitura e escrita
    raf.writeInt(1); // 
    raf.writeDouble(5.3); // escreve "5.3" em exemplo.txt
    raf.writeChar('X'); // escreve "X" em exemplo.txt
    raf.writeBoolean(true); 
    raf.writeBytes("Algoritmos");
    raf.close();
  }
}