#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


// retorna true se parametro for um palindromo
bool ehPalindromo(char s[], int i) {
  bool resp = true;
  if(i < (int)strlen(s)/2) {  // condicao de parada, metade da string
    if(s[i] != s[(int)strlen(s)-i-1]) {  // se caracter examinado for diferente de seu oposto na string
      resp = false;  // nao eh palindromo, interrrompe funcao
    } 
    else {
      // caso caracteres sejam iguais, passa para proxima chamada
      resp = ehPalindromo(s, i + 1);  
    }
  }
  return resp;
}

bool ehPalindromo(char s[]) {
  return ehPalindromo(s,0);
}

bool isFim(char s[]) {
  return (int)strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M';
}

int main(int argc, char *argv[]) {
  char entrada[1000][300]; // array 1000 entradas 300 caracteres
  int numEntrada = 0;
  // leitura de entrada padrao
  do {
    // lê entrada até quebra de linha \n
    scanf("%[^\n]%*c", entrada[numEntrada]);
  } while( ! isFim(entrada[numEntrada++] ) );
  numEntrada--;  // Desconsiderar ultima linha contendo a palavra FIM

  // Para cada linha de entrada, diz se é palíndromo ou não
  for(int i = 0; i < numEntrada; i++) {
    printf(ehPalindromo(entrada[i]) ? "SIM\n" : "NAO\n");
  }
  return 0;
}