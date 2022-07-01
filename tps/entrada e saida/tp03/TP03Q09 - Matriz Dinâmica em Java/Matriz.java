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
    for(c = inicio; ancora!=null; c = ancora.inf, ancora = c) {
      c.elemento = MyIO.readInt();
      for(c = ancora.dir; c!=null; c = c.dir) {
        c.elemento = MyIO.readInt();
      }
    }
  }


  public Matriz soma (Matriz m) {
    Matriz resp = null;
    
    if(this.linha == m.linha && this.coluna == m.coluna) {
      resp = new Matriz(this.linha, this.coluna);
      Celula ancoraA = this.inicio, ancoraB = m.inicio, ancoraC = resp.inicio;
      Celula a = this.inicio,
             b = m.inicio,
             c = resp.inicio;

      while(ancoraA!=null) {
        while(a!=null) {
          c.elemento = a.elemento + b.elemento;
          a = a.dir; b = b.dir; c = c.dir;
        }
        a = ancoraA.inf; ancoraA = a;
        b = ancoraB.inf; ancoraB = b;
        c = ancoraC.inf; ancoraC = c;
      }
    } else {
      MyIO.println("Nao eh possivel somar estas matrizes");
    }

    return resp;
  }


  public Matriz multiplicacao (Matriz m) {
    Matriz resp = null;

    if(this.coluna == m.linha){
      resp = new Matriz(this.coluna, m.linha);
      Celula ancoraA = this.inicio, ancoraB = m.inicio, ancoraC = resp.inicio;
      Celula a = this.inicio,
             b = m.inicio,
             c = resp.inicio;
      int somaDeProdutos = 0;

      // percorrer matrizes
      while(ancoraC != null) {
        
        while(ancoraB != null) {
          
          while(b != null) {
            somaDeProdutos += a.elemento * b.elemento;
            // caminhar...
            a = a.dir; b = b.inf;
          }
          c.elemento = somaDeProdutos; // atribui res da mult a matriz resposta
          somaDeProdutos = 0;
          // caminhar...
          a = ancoraA;
          b = ancoraB.dir; ancoraB = b;
          c = c.dir;
        }
        a = ancoraA.inf; ancoraA = a;
        b = inicio; ancoraB = b;
        c = ancoraC.inf; ancoraC = c;
      }
      b = ancoraB = null;
    } else {
      MyIO.println("Nao eh possivel multiplicar estas matrizes");
    }

    return resp;
  }

  public boolean isQuadrada(){
    return (this.linha == this.coluna);
  }

  public void mostrarDiagonalPrincipal (){
    if(isQuadrada() == true){
      Celula c = inicio;
      for(int i = 0; i < linha; i++) {
        MyIO.print(c.elemento + " ");
        c = c.dir;
        if(c != null) {
          c = c.inf;
        }
      }
      MyIO.println("");
    }
  }

  public void mostrarDiagonalSecundaria (){
    if(isQuadrada() == true){
      Celula c = inicio;
      while(c.dir != null) {
        c = c.dir;
      }
      
      for(int i = 0; i < linha; i++) {
        MyIO.print(c.elemento + " ");
        c = c.esq;
        if(c != null) {
          c = c.inf;
        }
      }
      MyIO.println("");
    }
  }

  public void mostrar() {
    Celula ancora = inicio;
    Celula c;
    for(c = inicio; ancora!=null; c = ancora.inf, ancora = c) {
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
    

    for(int i = 0; i < numCasos; i++) {
      int numLinhas  = MyIO.readInt();
      int numColunas = MyIO.readInt();
      Matriz m1 = new Matriz(numLinhas, numColunas);
      //MyIO.println("digita a matriz");
      m1.ler();
      //m1.mostrar();
      m1.mostrarDiagonalPrincipal();
      m1.mostrarDiagonalSecundaria();

      numLinhas = MyIO.readInt();
      numColunas = MyIO.readInt();
      Matriz m2 = new Matriz(numLinhas, numColunas);
      m2.ler();
      //m2.mostrar();
      (m1.soma(m2)).mostrar();
      (m1.multiplicacao(m2)).mostrar();

    }


  }
}
