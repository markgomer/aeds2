public class Fila {

  int[] lista;

  void mostraIntercalada(Fila a, Fila b, int tam) {
    int i = 0;
    mostraIntercalada(a, b, tam, i);
  }

  void mostraIntercalada(Fila a, Fila b, int tam, int i) {
    if(i < tam) {
      System.out.println(a.lista[i]);
      System.out.println(b.lista[i]);
      mostraIntercalada(a, b, ++i);
    }
  }
}

// Complexidade = O(tam)