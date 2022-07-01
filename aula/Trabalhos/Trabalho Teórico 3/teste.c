
void main(){
   int n = 10;
   int a = 1;
   for (int i = n+4; i > 0; i >>= 1){
      a *= 2;
      printf("%d ", i);
   }
   system("pause");
}