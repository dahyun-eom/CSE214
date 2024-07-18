/**
 * Implement your Queue-based array list. 
 * It doesn't matter how you use a queue, as long as the required functionalities are properly implemented.
 * You should use the 'q' field in the Q2AL class to implement the required methods.
 * Do not import anything else other than the Queue provided. 
 * @author <Dahyun Eom, 115943034>
 */
import java.util.*;

public class Q2AL<E>{
	
	private Queue<E> q;
	
	/*
	 * O(1)
	 * It is just initiallizing "q" by instantiatig a a class LinkedList.
	 * This requires constant time time complexity.
	 */
	public Q2AL() {
		q = new LinkedList<E>();
	}
	
	/*
	 * TODO: Implement the following methods using 'q' and provide the time complexity analysis.
	 * Use only enqueue, dequeue, and peek operations. 
	 * Calling any other methods of Queue will result in a 0 for whatever method that contains it.
	 */
	/*
	 * O(n) where n represents the number of elements in queue(size of the queue "q"), in other words the number of elements in LinkedList.
	 * 
	 * The method firstly create a new queue, "q2" by instantiating LinkedList.
	 * Then, declare a local integer variable name "size" initializing it by calling the size() method which requires linear time complexity.
	 * It will go through a for-loop which will search the "obj" that is given through parameter inside the queue.
	 * 
	 * Inside the for-loop, there is an if statement to check if the current head of the queue "q" is equal to "obj" that is given through the parameter. 
	 * If the condition meets the if statement, it will go through another for-loop inside the if statement. 
	 * The role of this inner for-loop is to remove the leftover elements in "q" and add that removed elements back in to the newly made queue "q2" in order 
	 * so that the newly made queue "q2" became same to the former original "q". Then, will assign "q" to "q2" "so that "q" is not modified after running this method.
	 * 
	 * If it did not meet the condition of if statement, then the method will add removed head of queue "q" to newly made queue "q2".
	 * so that when it loop again, the if statement can check the next element.
	 * 
	 * This big for-loop block requires O(n) time complexity where n is the size of the original queue "q". 
	 * Because in both case whether found the "obj" in the queue "q" or not, 
	 * It will add removed head of queue "q" to newly made queue "q2" for the whole elements in the queue.
	 */
	public int indexOf(E obj) {
		Queue<E> q2 = new LinkedList<E>();
		int size = q.size();

		for(int i=0; i<size; i++){
			if(q.peek().equals(obj)){
				for(int j=i; j<size; j++){
					q2.add(q.remove());
				}
				q = q2;
				return i;
			}
			q2.add(q.remove());
		}
		q = q2;
		return -1;
	}

	/*
	 * O(n) where n represents the number of elements in queue(size of the queue "q"), in other words the number of elements in LinkedList.
	 * The method firstly create a new queue, "q2" by instantiating LinkedList and initiallize variable "count" to 0.
	 * 
	 * Using while-loop, whenever it loops, the method is trying to remove the head of original queue "q" and add that removed element into this newly made queue "q2" with adding 1 to "count".
	 * 
	 * Then, will assign "q" to "q2" so that "q" is not modified after running this method.
	 * The process of adding all the elements to new queue "q2" which was removed from the original queue "q" requires linear time complexity o(n) where n is the size of the original queue "q". 
	 */
	public int size() { 
		Queue<E> q2 = new LinkedList<E>();
		int count =0; 
		while (q.peek() != null){
			q2.add(q.remove());
			count++;
		}
		q = q2;   
		return count;
	}

	/*
	 * O(n) where n represents the number of elements in queue(size of the queue "q"), in other words the number of elements in LinkedList.
	 * The method firstly create a new queue, instantiating LinkedList.
	 * Then, declare a local integer variable name "size" initializing it by calling the size() method which requires linear time complexity.
	 * Also, the method will check if the index which given through the parameter is valid or not.
	 * 
	 * The method has 2 for-loops. 
	 * The first for-loop will repeat removing the head element in the "q" and add that removed element in to the newly made queue "q2" in order 
	 * until before it reach the index that the method wants to modify. 
	 * 
	 * When it terminates the first for-loop, it will remove the head element in "q". 
	 * When it remove the elemenet, it will store the value into new variable "oldVal" for later return.
	 * Then, it will add the element(obj) of a value that is given through the parameter into the newly made queue "q2".
	 * 
	 * After that, it will enter the second for-loop which role is to remove the leftover elements in "q" and add that removed elements back in to the newly made queue "q2" in order 
	 * so that the newly made queue "q2" becomes same to the former original "q"(except the index "index" element in "q"). 
	 * 
	 * In addition through 2 for-loops, it will move all the elements(except "index" element in "q") from "q" to the new queue "q2". 
	 * So, the time complexity of this method is O(n) where n represents the size of the original queue "q".
	 * 
	 * Finally, the method will assign "q" to "q2".
	 * And return the value of the index "index"element of original former "q" before it was modified.
	 */
	public E set(int index, E obj) {
		Queue<E> q2 = new LinkedList<E>();
		int size = q.size();
		if(index>=size || index<0){
			throw new IndexOutOfBoundsException();
		}
		for(int i=0; i<index; i++){
			q2.add(q.remove());
		}
		E oldVal = q.remove();
		q2.add(obj);
		for(int i=index+1; i<size; i++){
			q2.add(q.remove());
		}
		q = q2;
		return oldVal;
	}

	/*
	 * O(n) where n represents the number of elements in queue(size of the queue "q"), in other words the number of elements in LinkedList.
	 * The method firstly create a new queue, instantiating LinkedList.
	 * Then, declare a local integer variable name "size" initializing it by calling the size() method which requires linear time complexity.
	 * Also, the method will check if the index which given through the parameter is valid or not.
	 * 
	 * The method has 2 for-loops. 
	 * The first for-loop will repeat removing the head element in the "q" and add that removed element in to the newly made queue "q2" in order 
	 * until before it reach the index that the method wants to retrieve. 
	 * 
	 * When it terminates the first for-loop, The method will save the value of the head of "q" for later return.
	 * 
	 * After that, it will enter the second for-loop which role is to remove the leftover elements in "q" and add that removed elements back in to the newly made queue(q2) in order 
	 * so that the newly made queue(q2) becomes same to the former original "q".
	 * 
	 * In addition through 2 for-loops, it will move all the elements from "q" to the new queue "q2". 
	 * So, the time complexity of this method is O(n) where n represents the size of the original queue "q".
	 * 
	 * Finally, the method will assign "q" to "q2".
	 * And return the value of the index "index"element of original former "q" that we have saved in to "value" variable.
	 */
	
	public E get(int index) {
		Queue<E> q2 = new LinkedList<E>();
		int size = q.size();
		if(index>=size || index<0){
			throw new IndexOutOfBoundsException();
		}
		for(int i=0; i<index; i++){
			q2.add(q.remove());
		}
		E value = q.peek();
		for(int i=index; i<size; i++){
			q2.add(q.remove());
		}
		q = q2;
		return value;
	}

	/*
	 * O(n) where n represents the number of elements in queue(size of the queue "q"), in other words the number of elements in LinkedList.
	 * The method firstly create a new queue, instantiating LinkedList.
	 * Then, declare a local integer variable name "size" initializing it by calling the size() method which requires linear time complexity.
	 * Also, the method will check if the index which is passed through the parameter is valid or not.
	 * 
	 * The method has 2 for-loops. 
	 * The first for-loop will repeat removing the head element in the "q" and add that removed element in to the newly made queue "q2" in order 
	 * until before it reach the index that the method wants to add "obj" that is given through the parameter.
	 * Then, it will add the element "obj" that is given through the parameter into the newly made queue "q2".
	 * After that, it will enter the second for-loop which role is to remove the leftover elements in "q" and add that removed elements back in to the newly made queue "q2" in order. 
	 * In addition through 2 for-loops, it will move all the elements from "q" to the new queue "q2". 
	 * So, the time complexity of this method is O(n) where n represents the size of the original queue"q".
	 * Finally, the method will assign "q" to "q2" and return true.
	 */
	
	public boolean add(int index, E obj) {
		Queue<E> q2 = new LinkedList<E>();
		int size = q.size();
		if(index>=size || index<0){
			return false;
		}
		for(int i=0; i<index; i++){
			q2.add(q.remove());
		}
		q2.add(obj);
		for(int i=index; i<size; i++){
			q2.add(q.remove());
		}
		q = q2;
		return true;	
	}

	/*
	 * O(1)
	 * In this method, it is going to add element "obj" that is given through the parameter into the tail of the queue "q". 
	 * In other words, since adding new node to the last in LinkedList is O(1) in time complexity, This method has the constant time complexity.
	 */
	public boolean add(E obj) {
		q.add(obj);	
		return true;
	}

	/*
	 * O(n) where n represents the number of elements in queue(size of the queue "q"), in other words the number of elements in LinkedList.
	 * The method firstly create a new queue, instantiating LinkedList.
	 * Then, declare a local integer variable name "size" initializing it by calling the size() method which requires linear time complexity.
	 * Also, the method will check if the index which is passed through the parameter is valid or not.
	 * 
	 * The method has 2 for-loops. 
	 * The first for-loop will repeat removing the head element in the "q" and add that removed element in to the newly made queue"q2" in order 
	 * until before it reach the index that the method wants to remove.
	 * 
	 * Then, it will remove the head element in "q". 
	 * 
	 * After that, it will enter the second for-loop which role is to remove the leftover elements in "q" and add that removed elements back in to the newly made queue "q2" in order. 
	 * 
	 * In addition through 2 for-loops, it will move all the elements (except index"index" element in original "q") from "q" to the new queue "q2". 
	 * So, the time complexity of this method is O(n) where n represents the size of the original queue "q".
	 * 
	 * Finally, the method will assign "q" to "q2" and return true.
	 */
	public boolean remove(int index) {
		Queue<E> q2 = new LinkedList<E>();
		int size = q.size();
		if(index>=size || index<0){
			return false;
		}
		for(int i=0; i<index; i++){
			q2.add(q.remove());
		}
		q.remove();
		for(int i=index+1; i<size; i++){
			q2.add(q.remove());
		}
		q = q2;
		return true;
	}

	/*
	 * O(n) where n represents the number of elements in queue(size of the queue "q"), in other words the number of elements in LinkedList.
	 * It will declare a local integer variable name "size" initializing it by calling the size() method which requires linear time complexity.
	 * Using for-loop, it will remove the head element in "q" for "size" times which requires linear time complexity. 
	 */
	
	public void clear() {
		int size = q.size();
		for(int i=0; i<size; i++){
			q.remove();
		}
	}
	
	public static void main(String[] args) {
		// // Q2AL<Integer> a = new Q2AL<Integer>();
		// // for(int i = 0; i < 10; i++)
		// // 	a.add(i);
		// // System.out.println(a.get(0)); //0
		// // System.out.println(a.size()); //10
		// // System.out.println(a.get(2)); //2
		// // System.out.println(a.set(3, 4)); //3
		// // System.out.println(a.size()); //10
		// // System.out.println(a.get(3)); //4
		// // System.out.println(a.add(3, 5)); //true
		// // System.out.println(a.size()); //11
		// // System.out.println(a.get(3)); //5
		// // System.out.println(a.get(4)); //4
		// // System.out.println(a.remove(3)); //true
		// // System.out.println(a.size()); //10
		// // System.out.println(a.get(3)); //4
		// // a.clear();
		// // System.out.println(a.size()); //0
		// Q2AL<Integer> a = new Q2AL<Integer>();
		// for(int i = 0; i < 10; i++)
		// 	a.add(i);
		// for(int i = 0; i < 10; i++) {
		// 	System.out.print(a.get(i) + " "); // 0 1 2 3 4 5 6 7 8 9
		// }
		// System.out.println();
		
		// a.add(1, 12); 
		// System.out.println(a.get(1)); // 12
		// for(int i = 0; i < a.size();i++) {
		// 	System.out.print(a.get(i) + " "); // 0 12 1 2 3 4 5 6 7 8 9
		// }
		// System.out.println();
		
		// a.remove(2);
		// for(int i = 0; i < a.size();i++) {
		// 	System.out.print(a.get(i) + " "); // 0 12 2 3 4 5 6 7 8 9
		// }
		// System.out.println();
		
		// a.set(3, 1);
		// for(int i = 0; i < a.size();i++) {
		// 	System.out.print(a.get(i) + " "); // 0 12 2 1 4 5 6 7 8 9
		// }
		// System.out.println();
		
		// System.out.println(a.indexOf(1)); //3
		// System.out.println(a.indexOf( 0)); // 0
		// System.out.println(a.indexOf(1)); // 3
		// System.out.println(a.size()); //10
		// System.out.println(a.indexOf(10)); //-1
		// System.out.println("here1"); 
		// System.out.println(a.size()); //10
		// a.add(12);
		// System.out.println("here2");
		// System.out.println(a.size()); //11

		// for(int i = 0; i < a.size();i++) {
		// 	System.out.print(a.get(i) + " "); // 0 12 2 1 4 5 6 7 8 9 12
		// }
		// System.out.println();
		// System.out.println("here3");
		// System.out.println();
		
		// System.out.println(a.get(10)); // 12
		// System.out.println(a.get(0)); // 0
		// System.out.println(a.size()); // 11
		
		// a.set(0, 3);
		// a.add(2);
		// System.out.println(a.size()); // 12
		// for(int i = 0; i < a.size();i++) {
		// 	System.out.print(a.get(i) + " "); // 3 12 2 1 4 5 6 7 8 9 12 2
		// }
		// System.out.println();
		
		// a.remove(1);
		// a.remove(2);
		// for(int i = 0; i < a.size();i++) {
		// 	System.out.print(a.get(i) + " "); // 3 2 4 5 6 7 8 9 12 2
		// }
		// System.out.println();
		// System.out.println(a.size()); // 10
		
		// a.clear();
		// System.out.println(a.size()); // 0

		// System.out.println(a.add(-1, 0)); //false
		// System.out.println(a.remove(-1)); //false
		
		// //a.set(-1, 3); // IndexOutOfBoundsException
		// //a.get(-1); // IndexOutOfBoundsException

		int[]a = {1, 2, 3, 4};
		int[] b=a;
		b[0] = 2;
		System.out.println(b[0]);
		System.out.println(a[0]);


	}
}
