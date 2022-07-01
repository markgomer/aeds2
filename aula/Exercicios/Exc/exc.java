class Matriz {
  
  static class Celula {
    int elemento;
    Celula esq, dir, sup, inf;

    public Celula() {
      this(0, null, null, null, null);
    }
    public Celula(int elemento, Celula esq, Celula dir, Celula inf, Celula sup) {
      this.elemento = elemento;
      this.esq = esq;
      this.dir = dir;
      this.inf = inf;
      this.sup = sup;
    }
  }

  public Matriz() {
    
    for(Celula i=primeiro; i!=i.dir.dir; i = i.dir){
      i.dir = new Celula(0, i, i.dir.dir, null, null);
    }
    i.dir = new Celula(0, i, i.prox, );
    
    for(int i = 0 ; i < linha; i++) {
      Celula x = new Celula(0,primeiro,primeiro.dir.dir,null,null);
      
    }
  }

  public static void main (String[] args){
    
  }
}
