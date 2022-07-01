//método para INSERIR palavras na árvore
public void inserir(String s) throws Exception {
  inserir(s, raiz, 0);
}

private void inserir(String s, No no, int i) throws Exception {
  System.out.print("\nEM NO(" + no.elemento + ") (" + i + ")");
  
  if( no.prox[s.charAt(i)] == null ) {  // + 1 comparacao
      System.out.print("--> criando filho(" + s.charAt(i) + ")");
      no.prox[s.charAt(i)] = new No(s.charAt(i));
      // quando criar filho, inserir seu endereço na segunda arvore
      outroNo.inserir(no.prox);
      
      if(i == s.length() - 1) {  // + 1 comparacao
        System.out.print("(folha)");
        no.prox[s.charAt(i)].folha = true;
        outroNo.inserir(no.prox);  
    
      } else {
        inserir(s, no.prox[s.charAt(i)], i + 1);
        outroNo.inserir(no.prox);
      }
      
    } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1) { // + 1 comparacao
      inserir(s, no.prox[s.charAt(i)], i + 1);
      outroNo.inserir(no.prox);

  } else {
      throw new Exception("Erro ao inserir!");
  } 
}

/*
As ordens de complexidade para a inserção são as seguintes:

Pior caso: O(n) (inserção na primeira árvore) 
         + O(n) (inserção na segunda árvore)
         = O(n)

Melhor caso: O(1) (inserção na primeira árvore) 
           + O(1) (inserção na segunda árvore)
           = O(1)

*/           
