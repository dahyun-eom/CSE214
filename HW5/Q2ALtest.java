public class Q2ALtest {
	public static void main(String[] args) {
		Q2AL<Integer> a = new Q2AL<Integer>();
		for(int i = 0; i < 10; i++)
			a.add(i);
		for(int i = 0; i < 10; i++) {
			System.out.print(a.get(i) + " "); // 0 1 2 3 4 5 6 7 8 9
		}
		System.out.println();
		
		a.add(1, 12); 
		System.out.println(a.get(1)); // 12
		for(int i = 0; i < a.size();i++) {
			System.out.print(a.get(i) + " "); // 0 12 1 2 3 4 5 6 7 8 9
		}
		System.out.println();
		
		a.remove(2);
		for(int i = 0; i < a.size();i++) {
			System.out.print(a.get(i) + " "); // 0 12 2 3 4 5 6 7 8 9
		}
		System.out.println();
		
		a.set(3, 1);
		for(int i = 0; i < a.size();i++) {
			System.out.print(a.get(i) + " "); // 0 12 2 1 4 5 6 7 8 9
		}
		System.out.println();
		
		System.out.println(a.indexOf(1)); //3
		System.out.println(a.indexOf(0)); // 0
		System.out.println(a.indexOf(1)); // 3
		System.out.println(a.indexOf(10)); // -1
		
		a.add(12);
		for(int i = 0; i < a.size();i++) {
			System.out.print(a.get(i) + " "); // 0 12 2 1 4 5 6 7 8 9 12
		}
		System.out.println();
		
		System.out.println(a.get(10)); // 12
		System.out.println(a.get(0)); // 0
		System.out.println(a.size()); // 11
		
		a.set(0, 3);
		a.add(2);
		System.out.println(a.size()); // 12
		for(int i = 0; i < a.size();i++) {
			System.out.print(a.get(i) + " "); // 3 12 2 1 4 5 6 7 8 9 12 2
		}
		System.out.println();
		
		a.remove(1);
		a.remove(2);
		for(int i = 0; i < a.size();i++) {
			System.out.print(a.get(i) + " "); // 3 2 4 5 6 7 8 9 12 2
		}
		System.out.println();
		System.out.println(a.size()); // 10
		
		a.clear();
		System.out.println(a.size()); // 0
		
//		a.set(-1, 3); // IndexOutOfBoundsException
//		a.get(-1); // IndexOutOfBoundsException
//		a.add(-1, 0); // IndexOutOfBoundsException
//		a.remove(-1); // IndexOutOfBoundsException

	}
}
