public class Time {
  int ordem;  // ordem alfabética
  String nome;

  void compareNome(Time time1, Time time2) {
    compareNome(time1, time2, 0);
  }
  void compareNome(Time time1, Time time2, int i) {
    String nome1 = time1.nome;
    String nome2 = time2.nome;
    int l1 = nome1.length();
    int l2 = nome2.length();

    if(i < l1 && i < l2) {
      if(nome1.charAt(i) != nome2.charAt(i)) {
        if(nome1.charAt(i) > nome2.charAt(i)) {
          time2.ordem = time1.ordem - 1;
          i = l1 + l2;
        } 
        else {
          time1.ordem = time2.ordem - 1;
          i = l1 + l2;
        }
      }
      compareNome(time1, time2, i+1);
    }
  }
}
