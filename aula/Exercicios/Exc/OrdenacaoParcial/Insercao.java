/**
 * Algoritmo de ordenacao por insercao
 * @author Max do Val Machado
 * @version 3 01/2020
 */

class Insercao extends Geracao {

	/**
	 * Construtor.
	 */
   public Insercao(){
      super();
   }


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
   public Insercao(int tamanho){
      super(tamanho);
   }


	/**
	 * Algoritmo de ordenacao por insercao.
	 */
  public void sort(int k) {
    for (int i = 1; i < n; i++) {
			int tmp = array[i];
      int j = (i < k) ? i - 1 : k - 1;
      while ((j >= 0) && (array[j] > tmp)) {
        array[j + 1] = array[j];
        j--;
      }
      array[j + 1] = tmp;
    }
	}
}
