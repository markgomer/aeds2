public class Pilha {
  
  public int somar() {
    int resp = 0;
    for (Celula i = topo; i != null; i = i.prox) {
      resp += i.elemento;
    }
    return resp;
  }
  

  // Exercicio 01
  public int somarRec() {
    Celula i = topo;
    return somarRec(i);
  }
  public int somarRec(Celula i) {
    int resp = 0;
    if(i!=null) {
      resp += i.elemento + somarRec(i = i.prox);  
    }
    return resp;
  }


  // Exercicio 02
  public int maiorElemento() {
    int maior = topo.elemento;
    for (Celula i = topo; i != null; i = i.prox) {
      if(i.elemento > maior) {
        maior = i.elemento; 
      }
    }
    return maior;
  }

  public static Celula maiorCelula() {
    Celula maior = topo;
    for (Celula i = topo; i != null; i = i.prox) {
      if(i.elemento > maior.elemento) {
        maior.elemento = i.elemento; 
        maior.prox = i.prox;
      }
    }
    return maior;
  }


  // Exercicio 03
  public int maiorElementoRec() {
    Celula i = topo;
    int maior = topo.elemento;
    return maiorElementoRec(i, maior);
  }
  public int maiorElementoRec(Celula i, int x) {
    int maior = x;
    if(i!=null) {
      if(i.elemento > maior) {
        maior = i.elemento; 
      }
      maior = maiorElementoRec(i = i.prox, maior);
    }
    return maior;
  }


  // Exercicio 04
  public void mostrarFilaDeRemocao() {
    Celula i = topo;
    mostrarFilaDeRemocao(i);
  }
  public void mostrarFilaDeRemocao(Celula i) {
    if(i!=null) {
      System.out.println(i.elemento);
      mostrarFilaDeRemocao(i = i.prox);
    }
  }


  // Exercicio 05
  public void mostrarOrdemDeInsercao() {
    Celula i = topo;
    System.out.print("[ ");
    mostrarOrdemDeInsercao(i);
    System.out.println("]");
  }
  public void mostrarOrdemDeInsercao(Celula i) {
    Celula c = i.prox;
    if(c!=null) {
      mostrarOrdemDeInsercao(c);
      System.out.print(c.elemento + " ");
    }
  }


  // Exercicio 06
  public void ordemInsercao() {
    final int TAM = 10;
    Celula c = topo;
    int vet[] = new int[TAM];
    int i = 0;
    while(c!=null) {
      vet[i] = c.elemento;
      c = c.prox;
      i++;
    }
    for(int j = vet.length-1; j >= 0; j--) {
      System.out.println(vet[j]);
    }
  }


  //***********************/
  // Exercicio 07
  //***********************/
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

  
  public Pilha() {
    topo = new Celula();
  }
  

  public void inserir(int num) {
    Celula nova = new Celula(num);
    nova.prox = topo.prox;
    topo.prox = nova;
  }
    

  //***********************/
  // Fim Exercicio 07
  //***********************/
 


}
