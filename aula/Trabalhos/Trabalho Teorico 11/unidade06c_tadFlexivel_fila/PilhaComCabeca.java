public class PilhaComCabeca {
  static Celula topo;

  static class Celula {
    Celula prox;
    int elemento;

    public Celula() {
      this.prox = null;
    }

    public Celula(int elemento) {
      this.prox = null;
      this.elemento = elemento;
    }
  }

  public PilhaComCabeca() {
    topo = new Celula();
  }
  

  public void inserir(int num) {
    Celula nova = new Celula(num);
    nova.prox = topo.prox;
    topo.prox = nova;
  }


  public int remover() throws Exception {
    if (topo == null)
      throw new Exception("Erro!");
    int elemento = topo.prox.elemento;
    Celula tmp = topo.prox;
    topo.prox = tmp.prox;
    tmp.prox = null;
    tmp = null;
    return elemento;
  }


  public void mostrarOrdemDeInsercao() {
    Celula i = topo;
    System.out.print("[ ");
    mostrarOrdemDeInsercao(i);
    System.out.println("]");
  }

  public void mostrarOrdemDeInsercao(Celula i) {
    Celula c = i.prox;
    if (c != null) {
      mostrarOrdemDeInsercao(c);
      System.out.print(c.elemento + " ");
    }
  }


  public static void main(String[] args) throws Exception {
    PilhaComCabeca pilha = new PilhaComCabeca();
    pilha.inserir(3);
    pilha.inserir(5);
    pilha.inserir(7);

    pilha.mostrarOrdemDeInsercao();

    pilha.remover();

    pilha.mostrarOrdemDeInsercao();

  }
}
