#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool ehPalindromo(char str[]) {
  bool resp = true;
  int tam = (int)strlen(str);
  // percorre string de entrada at� a metade
  for(int i = 0; i < tam/2; i++) {
    // compara primeiro e ultimo caracteres
    if(str[i] != str[tam-i-1]) {  
      resp = false;  // se forem diferentes, n�o � pal�ndromo
      i = tam;  // interrompe o la�o for
    }
  }
  return resp;
}


bool isFim(char s[]) {
  return (int)strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M';
}


int main(int argc, char *argv[]) {
  char entrada[1000][300]; // array 1000 entradas 300 caracteres
  int numEntrada = 0;
  // leitura de entrada padrao
  do {
    // l� entrada at� quebra de linha \n
    scanf("%[^\n]%*c", entrada[numEntrada]);
  } while( ! isFim(entrada[numEntrada++] ) );
  numEntrada--;  // Desconsiderar ultima linha contendo a palavra FIM

  // Para cada linha de entrada, diz se � pal�ndromo ou n�o
  for(int i = 0; i < numEntrada; i++) {
    printf(ehPalindromo(entrada[i]) ? "SIM\n" : "NAO\n");
  }
  return 0;
}