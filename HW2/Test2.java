public class Test2 {
    public static void main(String[] args) {
        int[] arr4 = {4, 4, 4, 6, 8, 9, 10};
		int[] arr5 = {7, 7, 7, 7, 7, 7, 7, 7};
		int[] arr6 = {7, 7, 5, 4, 3, 2};
		
		System.out.println(count(arr4, 2)); //0
		System.out.println(count(arr4, 4)); //3
		System.out.println(count(arr4, 5)); //3
		System.out.println(count(arr4, 7));//4
		System.out.println();

		System.out.println(count(arr5, 9)); //8
		System.out.println(count(arr5, 2)); //0
		System.out.println(count(arr5, 7)); //8
		System.out.println();

		System.out.println(count(arr6,9)); //6
		System.out.println(count(arr6, 7)); //6
		System.out.println(count(arr6, 3)); //2
    }
    
}





