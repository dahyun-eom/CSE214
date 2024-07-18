
public class CSE214 {

	public static void main(String[] s) {
		int[] x = new int[CSE214.IN_SIZE];
		System.out.println(CSE214.a(x));
		System.out.println(CSE214.b(x));
	}
	
	public static final int IN_SIZE = 10;// Change this value from 10 to 200000 with an appropriate step-size to clearly draw the plot.
	public static final int REP_LEN = 1000;
	
	public static long a(int[] arr) {
		long start = System.nanoTime();                          //1
		int sum = 0;                                             //2
		for(int i = 0; i < CSE214.REP_LEN; i++) {                //3
			for(int j = 0; j < arr.length; j++)                  //4
				sum += arr[j];                                   //5
		}
		long end = System.nanoTime();                            //6
		long elapsed_time = (end - start) / 1000000; // In 'ms'  //7
		return elapsed_time;                                     //8
	}
		
	public static long b(int[] arr) {
		long start = System.nanoTime();                          //1
		int dSum = 0;                                            //2
		for(int i = 0; i < arr.length; i++) {                    //3
			for(int j = 0; j < arr.length; j++)                  //4
				dSum += arr[j];                                  //5
		}
		long end = System.nanoTime();                            //6
		long elapsed_time = (end - start) / 1000000; // In 'ms'  //7
		return elapsed_time;                                     //8
	}
	


	
}
