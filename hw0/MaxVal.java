public class MaxVal {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
		if (m.length == 0) {
	        return 0;
	    }
	    int max_val = 0;
	    int i;
	    for (i = 0; i < m.length; i++) {
		    if (m[i] > max_val) {
			    max_val = m[i];
			}
		}
		return max_val; 
	}
	
	public static int max2(int[] m) {
	    
	}
    public static void main(String[] args) {
	    int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
	    System.out.println(max(numbers));
	}
}
