class Matriz {
  static class Celula {
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

  private Celula inicio;
  private int linha, coluna;

  public Matriz (){
    this(3, 3);
  }

  //alocar a matriz com this.linha linhas e this.coluna colunas
  public Matriz (int linha, int coluna){
    this.linha = linha;
    this.coluna = coluna;

    inicio = new Celula();
    Celula ancoraSup = null;
    Celula ancoraLinha = inicio; 
    Celula c = inicio;
    
    // primeira linha 
    for (int i = 1; i < coluna; i++) {
      c = new Celula(0,null,null,c,null);
      c.esq.dir = c;
    }
    c = ancoraLinha;
    ancoraSup = ancoraLinha;

    for(int i = 1; i<linha; i++) { // nova linha
      c = new Celula(0,ancoraSup,null,null,null);
      ancoraLinha = c;
      ancoraSup.inf = c;
      for(int j = 1; j<coluna; j++) { // completa linha
        ancoraSup = ancoraSup.dir;
        c = new Celula(0,ancoraSup,null,c,null);
        c.esq.dir = c;
        ancoraSup.inf = c;
      }
      c = ancoraLinha;
      ancoraSup = ancoraLinha;
    }
  }


  public void ler() {
    Celula ancora = inicio;
    Celula c;

    for(c = ancora; ancora!=null; c = ancora.inf, ancora = c) {
      c.elemento = MyIO.readInt();
      for(c = ancora.dir; c!=null; c = c.dir) {
        c.elemento = MyIO.readInt();
      }
    }
  }

  public boolean isQuadrada(){
    return (this.linha == this.coluna);
  }

  public void mostrar() {
    Celula ancora = inicio;
    Celula c;
    for(c = ancora; ancora!=null; c = ancora.inf, ancora = c) {
      MyIO.print(c.elemento + " ");
      for(c = ancora.dir; c!=null; c = c.dir) {
        MyIO.print(c.elemento + " ");
      }
      MyIO.print("\n");
    }
  }


  public static void main(String[] args) {
    // numero de casos
    int numCasos = MyIO.readInt();

    Matriz m = new Matriz(4,4);
    m.ler();
    m.mostrar();


  }
}
