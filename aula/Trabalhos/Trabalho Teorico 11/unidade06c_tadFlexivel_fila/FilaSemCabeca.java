public class FilaSemCabeca {

  static class Celula {
    int elemento;
    Celula prox;

    public Celula() {
      this.prox = null;
    }

    public Celula(int elemento) {
      this.prox = null;
      this.elemento = elemento;
    }
  }

  private Celula primeiro, ultimo;

  public FilaSemCabeca() {
    primeiro = null;
    ultimo = null;
  }

  public void inserir(int x) {
    Celula nova = new Celula(x);
    if(primeiro == null) {
      primeiro = ultimo = nova;
      nova = null;
    } else {
      ultimo.prox = new Celula(x);
      ultimo = ultimo.prox;
    }
  }

  public int remover() throws Exception {
    int elemento;
    if(primeiro == null) {
      throw new Exception("Erro!");
    } else if(primeiro.prox == null) {
      elemento = primeiro.elemento;
      primeiro = ultimo = null;
    } else {
      Celula tmp = primeiro;
      elemento = primeiro.elemento;
      primeiro = primeiro.prox;
      tmp.prox = null;
      tmp = null;
    }
    return elemento;
  }

  public void mostrar() {
    System.out.print("[ ");
    for (Celula i = primeiro; i != null; i = i.prox) {
      System.out.print(i.elemento + " ");
    }
    System.out.println("]");
  }

  public static void main(String[] args) {
    FilaSemCabeca fila = new FilaSemCabeca();

    fila.inserir(3);
    fila.inserir(5);
    fila.inserir(7);

    fila.mostrar();
  }
}