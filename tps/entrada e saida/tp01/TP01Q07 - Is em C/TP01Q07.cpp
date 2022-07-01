#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// converte caracter para maiusculo
char toUpper(char c) {
  return c >= 'a' && c <= 'z' ? (char)(c - 32) : c ;
}

// retorna true se caracter for letra
bool isLetra(char c){
  c = toUpper(c);
  return c >= 'A' && c <= 'Z';
}

// retorna true se caracter for vogal
bool isVogal(char c){
  c = toUpper(c);
  return c =='A' || c =='E' || c =='I' || c =='O' || c =='U';
}

// retorna true se caracter for consoante
bool isConsoante(char c) {
  c = toUpper(c);
  return isLetra(c) && !isVogal(c);
}

// retorna true se caracter for numeral
bool isNumber(char c) {
  return c >= '0' && c <= '9';
}

// retorna true se o parametro contiver apenas vogais
bool somenteVogais(char s[]) {
  bool resp = true;
  for(int i = 0; i < (int)strlen(s); i++) {
    if(!isVogal(s[i])) {
      resp = false;
    }
  }
  return resp;
}

// retorna true se o parametro contiver apenas consoantes
bool somenteConsoantes(char s[]) {
  bool resp = true;
  for(int i = 0; i < (int)strlen(s); i++) {
    if(!isConsoante(s[i])) {
      resp = false;
    }
  }
  return resp;
}

/// retorna true se o parametro contiver um numero inteiro
bool isInt(char s[]) {
  bool resp = true;
  for(int i = 0; i < (int)strlen(s); i++) {
    if(!isNumber(s[i])) {
      resp = false;
    }
  }
  return resp;
}

// retorna true se o parametro contiver um numero real
bool isDouble(char s[]) {
  bool resp = true;
  int len = (int)strlen(s);
  int count = 0;  // para contar pontos e virgulas
  for(int i = 0; i < len; i++) {  // percorre caracteres de "s"
    if(!isNumber(s[i])) { // se nao for numero
      if(s[i] == ',' || s[i] == '.') {  // se for ponto ou virgula
        count++;  // incrementa contador
        if(count > 1) {  // se tiver mais de um ponto ou virgula
          resp = false;  // nao e numero real
          i = len; // interrompe laco
        }
      } else {  // se nao for numero, nem ',' ou '.', nao e numero real
        resp = false;
      }
    }
  }
  return resp;
}

// retorna true se entrada for igual a "FIM"
bool isFim(char s[]) {
  return (int)strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M';
}

int main(int argc, char *argv[]) {
  char entrada[1024][300]; // array 1024 entradas 300 caracteres
  int numEntrada = 0;
  // leitura de entrada padrao
  do {
    // le entrada ate quebra de linha \n
    scanf("%[^\n]%*c", entrada[numEntrada]);
  } while( ! isFim(entrada[numEntrada++] ) );
  numEntrada--;  // Desconsiderar ultima linha contendo a palavra FIM

  // Para cada linha de entrada
  for(int i = 0; i < numEntrada; i++) {
    printf(somenteVogais(entrada[i])? "SIM " : "NAO ");
    printf(somenteConsoantes(entrada[i])? "SIM " : "NAO ");
    printf(isInt(entrada[i])? "SIM " : "NAO ");
    printf(isDouble(entrada[i])? "SIM\n" : "NAO\n");
  }
  return 0;
}