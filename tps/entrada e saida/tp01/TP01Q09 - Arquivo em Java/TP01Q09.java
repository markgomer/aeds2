import java.io.*;
import java.text.DecimalFormat;

public class TP01Q09 {
  public static void main(String[] args) throws Exception {
    DecimalFormat df = new DecimalFormat("###.###");
    
    RandomAccessFile raf = new RandomAccessFile("arq.txt", "rw");

    int numEntradas = MyIO.readInt();  // numero de entradas
    
    // escreve todos os doubles da entrada em arq.txt
    for(int i = 0; i < numEntradas; i++) {
      double d = MyIO.readDouble();
      raf.writeDouble(d);
    }
    long ultPos = raf.getFilePointer(); // ultima posicao do cursor
    
    raf.close();  // fecha arquivo
    
    // abre arquivo de novo
    RandomAccessFile rafEncore = new RandomAccessFile("arq.txt", "rw");
    
    // calcular length dos double
    long numLength = 0;
    long pointer1 = 0;
    long pointer2 = 0;

    pointer1 = rafEncore.getFilePointer();
    rafEncore.readDouble();
    pointer2 = rafEncore.getFilePointer();
    numLength = pointer2 - pointer1;  // numero de casas que cada double ocupa

    // percorre arquivo
    // long i = ultPos-numLength => comecar pela ultima linha
    // i-=numLength => volta 8 bytes para ler o double 
    for(long i = ultPos-numLength; i >= 0; i-=numLength) {
      rafEncore.seek(i);  // coloca o cursor no inicio de cada linha
      System.out.println(df.format(rafEncore.readDouble()));  // exibe na tela o double
    }

    rafEncore.close();  // fecha arquivo
  }
}