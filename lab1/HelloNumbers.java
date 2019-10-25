public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int sum = 0;
        while (x < 10) {
			sum = sum + x;
            System.out.println(sum + " ");
            x = x + 1;
        }
    }
}

/*
 * 1. Before Java a variable can be used, it must be declared.
 * 2. Java variables must be a specific type.
 * 3. Java variable types can never be changed.
 * 4. Types are verified even the code even runs!!!
 * */
