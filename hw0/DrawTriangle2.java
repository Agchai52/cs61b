public class DrawTriangle2 {
   public static void drawTriangle(int n) {
       String star = "";
       for (int i = 0; i < n; i++) {
         star += "*";
         System.out.println(star);
       }
   }
   public static void main(String[] args){
       int N = 10;
       drawTriangle(N);
          
   }
   
}
