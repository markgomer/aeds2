public class Fila {
  public boolean pesquisar(int x) {
    boolean resp = false;
    for (Celula i = primeiro.prox; i != null; i = i.prox) {
      if (i.elemento == x) {
        resp = true;
        i = ultimo;
      }
    }
    return resp;
  }

  public int removerFim() throws Exception {
    if (primeiro == ultimo) {
      throw new Exception("Erro ao remover (vazia)!");
    }

    // Caminhar ate a penultima celula:
    Celula i;
    for (i = primeiro; i.prox != ultimo; i = i.prox);

    int resp = ultimo.elemento;
    ultimo = i;
    i = ultimo.prox = null;

    return resp;
  }

  public int extrairDaFila(int x) {
    int resp = -1;
    if (pesquisar(x)) {
      int resp = x;
      if (primeiro.elemento == x) {
        resp = remover();
      } else if (ultimo.elemento == x) {
        resp = removerFim();
      } else {
        Celula i = primeiro;
        for (Celula j = primeiro; j.elemento != x; j = j.prox, i = i.prox);

        Celula tmp = i.prox;
        resp = tmp.elemento;
        i.prox = tmp.prox;
        tmp.prox = null;
        i = tmp = null;
      }
    }
    return resp;
  }

}
