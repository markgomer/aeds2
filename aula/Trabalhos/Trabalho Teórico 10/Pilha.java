public class Pilha {
  
  public int somar() {
    int resp = 0;
    for (Celula i = topo; i != null; i = i.prox) {
      resp += i.elemento;
    }
    return resp;
  }
  

  // Exercicio 01
  // Seja nossa Pilha, faça um método RECURSIVO que soma o conteúdo dos
  // elementos contidos na mesma
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
  // Seja nossa Pilha, faça um método que retorna o maior elemento contido na pilha
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
  // Seja nossa Pilha, faça um método RECURSIVO que retorna o maior
  // elemento contido na pilha
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
  // Seja nossa Pilha, faça um método RECURSIVO para mostrar os elementos
  // da pilha na ordem em que os mesmos serão removidos
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
  // Seja nossa Pilha, faça um método RECURSIVO para mostrar os elementos
  // da pilha na ordem em que os mesmos foram inseridos
  public void mostrarOrdemDeInsercao() {
    Celula i = topo;
    mostrarOrdemDeInsercao(i);
  }
  public void mostrarOrdemDeInsercao(Celula i) {
    Celula c = i;
    if(i!=null) {
      mostrarOrdemDeInsercao(c.prox);
      System.out.println(i.elemento);
    }
  }


  // Exercicio 06
  // Seja nossa Pilha, faça um método ITERATIVO para mostrar os elementos
  // da pilha na ordem em que os mesmos foram inseridos
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
    for(int j = vet.length; j >= 0; j--) {
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
      this.prox = maiorCelula();
      this.elemento = elemento;
    }
  }

  
  public Pilha() {
    Celula c = new Celula();
    topo = c;
  }
  

  public void inserir(int num) {
    Celula tmp = new Celula(num);
    tmp.prox = topo.prox;
    topo.prox = tmp;
    tmp = tmp.prox;
    tmp = null;
  }
    

  //***********************/
  // Fim Exercicio 07
  //***********************/
 


}