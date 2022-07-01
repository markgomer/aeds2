class Slide184 {
  /*  
  T(0) = 1
  T(1) = 2
  T(n) = T(n-1) * T(n-2) - T(n-1) 
  */
  static int babiruba(int n) {
    int resp = n + 1;
    if(n != 0 && n != 1){
      resp = babiruba(n-1) * babiruba (n-2) - babiruba(n-1);
    }
    return resp;
  }

  /*
  T(0) = 1
  T(n) = T(n-1)^2
  */
  static int babiruba2(int n) {
    int resp = n + 1;
    if(n != 0) {
      resp = babiruba2(n-1) * babiruba2(n-1);
    }
    return resp;
  }


  public static void main(String[] args) {
    System.out.println(babiruba(2));
    System.out.println(babiruba(3));
    System.out.println(babiruba(4));
    System.out.println(babiruba(5));

    System.out.println(babiruba2(1));
    System.out.println(babiruba2(2));
    System.out.println(babiruba2(3));
    System.out.println(babiruba2(4));
  }  
}