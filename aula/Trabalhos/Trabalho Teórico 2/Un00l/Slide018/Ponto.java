/* 
  Um aluno desenvolveu a classe abaixo e pediu sua ajuda para compil�-la. Para ajudar, voc� deve criar uma
  classe Ponto com as seguintes regras:
  ? Quatro atributos privados: double x, double y, int id e int nextID
  ? Os atributos id e nextID ser�o alterados somente por um m�todo construtor.
  ? Implemente os m�todos get e set tanto para o atributo x como para o y
  ? Na declara��o do atributo nextID, o mesmo deve receber zero. Al�m disso, a altera��o do valor desse
  atributo por um objeto sempre ser� compartilhada com qualquer objeto da classe ponto
  ? Implemente dois construtores sendo que o primeiro n�o recebe par�metros e inicializa os valores de x e
  y com zero. O segundo recebe dois par�metros (cujos nomes s�o obrigatoriamente x e y) e devem ser
  utilizados para inicializar os valores dos atributos x e y, respectivamente
  ? Os dois construtores devem atribuir o valor corrente do atributo nextID ao atributo id e incrementar o
  valor de nextID. Observe que cada objeto ter� um ID distinto
  ? Implemente qualquer m�todo que seja necess�rio para compilar a classe LixaoPonto
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