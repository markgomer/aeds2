public class Retangulo {
  
  double  base, altura;
  
  /* construtores */
  Retangulo(double base, double altura) {
    this.base = base;
    this.altura = altura;
  }
  Retangulo(double lados) {
    this.base = lados;
    this.base = lados;
  }

  double getArea() {
    return this.altura * this.base;
  }
  double getArea(double lados) {
    return lados * lados;
  }
  double getArea(double altura, double base) {
    return altura * base;
  }
  double getPerimentro() {
    return 2 * (this.base + this.altura);
  }
  double getDiagonal() {
    return Math.sqrt(Math.pow(this.base,2) + Math.pow(this.altura, 2));
  }
}