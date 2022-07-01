public class Exercicios {
  
  /*
  Metodos pedidos implementados em ListaDupla.java

  // Exercicio 1
  void inverte(){
    Celula i = primeiro.prox;
    Celula j = ultimo;
    while (i != j && j.prox != i){
      int tmp = i.elemento;
      i.elemento = j.elemento;
      j.elemento = tmp;
      i = i.prox;
      j = j.ant;
    }
  }

  Exercicio 2
	void inverteSimples(){
		CelulaDupla i = primeiro.prox;
		CelulaDupla j = ultimo;
		CelulaDupla k;
		while (i != j && j.prox != i){
			int tmp = i.elemento;
			i.elemento = j.elemento;
			j.elemento = tmp;
			i = i.prox;
			for (k = primeiro; k.prox != j; k = k.prox); j
			= k;
		}
	}
  */
  
  public static void main(String[] args) {
    ListaDupla listaDupla = new ListaDupla();
    listaDupla.inserirFim(5);  
    listaDupla.inserirFim(4);  
    listaDupla.inserirFim(3);  
    listaDupla.inserirFim(2);  
    listaDupla.inserirFim(1); 
    
    listaDupla.mostrar();

    listaDupla.inverte();
    listaDupla.mostrar();
    
    listaDupla.inverteSimples();
    listaDupla.mostrar();
    
  }
}
