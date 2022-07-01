public class Fila {
  
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

  
  public Fila() {
    primeiro = new Celula();
    ultimo = primeiro;
  }

  public void inserir(int x) {
    ultimo.prox = new Celula(x);
    ultimo = ultimo.prox;
  }

  public int remover() throws Exception {
    if (primeiro == ultimo)
      throw new Exception("Erro!");
    Celula tmp = primeiro;
    primeiro = primeiro.prox;
    int elemento = primeiro.elemento;
    tmp.prox = null;
    tmp = null;
    return elemento;
  }

  public void mostrar() {
    System.out.print("[ ");
    for(Celula i = primeiro.prox; i!=null; i = i.prox) {
      System.out.print(i.elemento + " ");
    }
    System.out.println("]");
  }

  // Exercicio slide 24
  public int remover_s24() throws Exception{
    if(primeiro == ultimo)
      throw new Exception("Erro!");
    Celula tmp = primeiro.prox;
    primeiro.prox = primeiro.prox.prox;
    int elemento = tmp.elemento;
    tmp.prox = null;
    tmp = null;
    return elemento;
  }

  //Exercicio slide 27
  public int maior() throws Exception {
    int maior = - 1;
    if (primeiro == ultimo) {
      throw new Exception("Erro!");
    } 
    else {
      maior = primeiro.prox.elemento;
      Celula i = primeiro.prox.prox;
      while(i != null) {
        if(i.elemento > maior) {
          maior = i.elemento;
        }
        i = i.prox;
      } 
    }
    return maior;
  }

  // Exercicio slide 29
  public int mostrarTerceiroElemento() {
    return primeiro.prox.prox.prox.elemento;
  } 
  
  // Exercicio slide 31
  public int somar() {
    int resp = 0;
    for(Celula i = primeiro.prox; i != null; i = i.prox) {
      resp += i.elemento;
    }
    return resp;
  }
  
  // Exercicio slide 33
  public void inverter () {
    Celula fim = ultimo;
    while(primeiro != fim) {
      Celula nova = new Celula(primeiro.prox.elemento);
      nova.prox = fim.prox;
      fim.prox = nova;
      Celula tmp = primeiro.prox;
      primeiro.prox = tmp.prox;
      nova = tmp = tmp.prox = null;
      if(ultimo == fim){
        ultimo = ultimo.prox; 
      }
    }
    fim = null;
  }
  
  // Exercicio slide 35
  public int contar() { 
    return contar(primeiro.prox); 
  }
  public int contar(Celula i) {
    int resp;
    if(i == null){
      resp = 0;
    } else if(i.elemento % 2 == 0 && i.elemento % 5 == 0) {
      resp = 1 + contar(i.prox);
    } 
    else {
      resp = contar(i.prox);
    }
    return resp;
  }
  

  // Exercicio slide 38
  public Celula pilhaToFila(Pilha.Celula topo) {
    Pilha.Celula tmp = topo.prox;
    Celula nova;
    this.ultimo = new Celula(tmp.elemento);
    this.primeiro = this.ultimo;

    while(tmp.prox!=null) {
      tmp = tmp.prox;
      nova = new Celula(tmp.elemento);
      nova.prox = this.primeiro;
      this.primeiro = nova;
    }

    //finalizando
    nova = new Celula();
    nova.prox = this.primeiro;
    this.primeiro = nova;
    nova = null;
    tmp = null;

    return primeiro;
  }  


  public static void main(String[] args) {
    Fila fila = new Fila();
    Pilha pilha = new Pilha();

    pilha.inserir(3);
    pilha.inserir(5);
    pilha.inserir(7);
    pilha.mostrarOrdemDeInsercao();

    fila.primeiro = fila.pilhaToFila(Pilha.topo);
    fila.mostrar();
  }
}