public class Exercicio extends Lista{
  
  // EXERCICIO //
  // Esta implementado em Lista.java!
  
  /*
	public int removeSegundaPosicao() {
    Celula p = primeiro.prox;
		Celula q = p.prox;
		int elemento = q.elemento;
		p.prox = q.prox;
		q.prox = q = null;
		return elemento;
	}
  */


  public static void main(String[] args) {
    Lista lista = new Lista();

    lista.inserirFim(25);
    lista.inserirFim(32);
    lista.inserirFim(12);
    lista.mostrar();

    lista.removeSegundaPosicao();

    lista.mostrar();

  }
}
