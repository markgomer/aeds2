/* 
  Um aluno desenvolveu a classe abaixo e pediu sua ajuda para compilá-la. Para ajudar, você deve criar uma
  classe Ponto com as seguintes regras:
  ? Quatro atributos privados: double x, double y, int id e int nextID
  ? Os atributos id e nextID serão alterados somente por um método construtor.
  ? Implemente os métodos get e set tanto para o atributo x como para o y
  ? Na declaração do atributo nextID, o mesmo deve receber zero. Além disso, a alteração do valor desse
  atributo por um objeto sempre será compartilhada com qualquer objeto da classe ponto
  ? Implemente dois construtores sendo que o primeiro não recebe parâmetros e inicializa os valores de x e
  y com zero. O segundo recebe dois parâmetros (cujos nomes são obrigatoriamente x e y) e devem ser
  utilizados para inicializar os valores dos atributos x e y, respectivamente
  ? Os dois construtores devem atribuir o valor corrente do atributo nextID ao atributo id e incrementar o
  valor de nextID. Observe que cada objeto terá um ID distinto
  ? Implemente qualquer método que seja necessário para compilar a classe LixaoPonto
*/
public class Ponto {
  private double x, y;
  private int id;
  static int nextID = 0;
  
  /* construtor */
  Ponto() {
    setX(0);
    setY(0);
    this.id = nextID;
    nextID++;
  }
  Ponto(double x, double y) {
    setX(x);
    setY(y);
    this.id = nextID;
    nextID++;
  }
  public double getX() {
    return x;
  }
  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }
  public void setY(double y) {
    this.y = y;
  }
  
  public int getId() {
    return id;
  }
  public int getNextID() {
    return nextID;
  }
}