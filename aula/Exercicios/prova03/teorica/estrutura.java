
boolean pesquisarT1(int x) {
  boolean resp = false;
  // pesquisar em T1
  int pos = hashT1(x);
  if(tabelaT1[pos].elemento == x) {
    resp = true;
  } else {
    // se não estiver na posição de T1 pesquisar na T2
    resp = tabelaT1[pos].pesquisarT2(x);
  }

  return resp;
}

boolean pesquisarT2(int x) {
  boolean resp = false;
  // 
  int pos = hashT2(x);

  if(tabelaT2[pos] == x){
     resp = true;

  } else {
     pos = reh(x);

     if(tabelaT2[pos] == x){
        resp = true;
     }
     // se a resposta não for encontrada, procurar em T3
     if(!resp) {
        switch (hashT3(x)) {
          case 0: resp = listaT3.pesquisarListaT3(x); break;
          case 1: resp = arvT3.pesquisarArvT3(x); break;
        }
     }
  }
  return resp;
}



boolean pesquisarListaT3 (int x) {

}

boolean pesquisarArvT3 (int x) {

}


/*
Ordem de complexidade de pesquisa tem pior caso O(n), caso passe até T3
O melhor caso é O(1)

*/