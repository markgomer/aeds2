/**
 * Algoritmo de ordenacao Shellsort
 * @author Max do Val Machado
 * @version 3 08/2020
 */

class Shellsort extends Geracao {

   /**
    * Construtor.
    */
   public Shellsort(){
      super();
   }


   /**
    * Construtor.
    * @param int tamanho do array de numeros inteiros.
    */
   public Shellsort(int tamanho){
      super(tamanho);
   }


   /**
    * Algoritmo de ordenacao Shellsort.
    */
   @Override
   public void sort() {
      int h = 1;

      do { h = (h * 3) + 1; } while (h < n);

      do {
         h /= 3;
         for(int cor = 0; cor < h; cor++){
            insercaoPorCor(cor, h);
         }
      } while (h != 1);
   }


   /**
    * Metodo que efetua a insercao nos pseudo-arrays do Shellsort.
    * @param int cor cor do pseudo array.
    * @param int h passo do shelsort
    */
   static int movimentos = 0;
   static int comparacoes = 0;
   public void insercaoPorCor(int cor, int h){
      for (int i = (h + cor); i < n; i+=h) {
         
         int tmp = array[i]; // movimentacao
         movimentos++;
         System.out.println("Movimentacao no." + movimentos + ": " + tmp + " <--> " + array[i]);
         
         int j = i - h;
         while ((j >= 0) && (array[j] > tmp)) { // comparacao++, se j>=0
            
            if (j >= 0) {
               comparacoes++;
               System.out.println("Comparacao no." + comparacoes + ": " + array[j] + " > " + tmp);
            } 
            
            movimentos++;
            System.out.println("Movimentacao no." + movimentos + ": " + array[j + h] + " <--> " + array[j]);
            
            array[j + h] = array[j]; // movimentacao
            j-=h;

         }
         movimentos++;
         System.out.println("Movimentacao no." + movimentos + ": " + array[j+h] + " <--> " + tmp);
         
         array[j + h] = tmp; // movimentacao

      }
   }
}
