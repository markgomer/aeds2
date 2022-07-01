class Pilha {

	void enfileirar(int x) { // inserir elemento no topo
		Celula tmp = new Celula(x);
			tmp.prox = topo;
			topo = tmp;
			tmp = null;
	}

	int desinfileirar() { // remover a base da pilha
		Celula tmp;
		for(tmp = topo; tmp.prox !=null; tmp = tmp.prox); // levar tmp ate a base da pilha
		int resp = tmp.elemento;
		for(Celula i = topo; i.prox != tmp; i = i.prox){ // levar i.prox = tmp
		i.prox = null;
		tmp = null;
		}
	}
  
}