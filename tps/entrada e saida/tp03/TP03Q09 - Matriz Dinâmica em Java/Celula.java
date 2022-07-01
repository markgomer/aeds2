class Celula {
   public int elemento;
   public Celula inf, sup, esq, dir;

   public Celula(){
      this(0, null, null, null, null);
   }

   public Celula(int elemento){
      this(elemento, null, null, null, null);
   }

   public Celula(int elemento, Celula sup, Celula inf, Celula esq, Celula dir){
      this.elemento = elemento;
      this.sup = sup;
      this.inf = inf;
      this.esq = esq;
      this.dir = dir;
   }
}
