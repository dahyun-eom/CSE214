/**
 * Do *NOT* import anything except the ArrayList class. 
 * In addition to the code, you should also write header comments that describe the 
 * time complexities of the following methods:
 * Constructor, get(), set(), addRows(), addColumns(), toString().
 * You *must* justify your answers. Don't just say "It's linear because there's a loop".
 * Remember to identify the variable of the time complexity.
 * @author: <Dahyun Eom>
 *
 */

import java.util.ArrayList;

public class ALMatrix {
	private ArrayList<Integer> mtx; // You must use this variable to internally represent a matrix.
	private int numRows, numCols;
	
	/*
	 * You should create a matrix having 'numRows' rows and 'numCols' columns.
	 * The matrix should be initially contain 'initVal' for every location.
	 */
	/*
	 * O(m*n)
	 * The constructor is called when an instance of the ALMatrix class is created.
	 * Firstly, it will initialize field mtx with new arraylist
	 * and then, initalize other fields such as numRows and numCols with the own values given through the parameter.
	 * All these operation is O(1) as it is just a simple assignment.
	 * Lastly, it will use for-loop to loop numRows*numCols times to add the element(initVal) which is given through the parameter
	 * since we want to make a size of numRow*numCols matrix. 
	 * The time complexity of method add(obj) used inside the loop runs in amortized constant time.
	 * Thus, time complexity of this constructor method is O(m*n) where each m represents "numCols" and n represents "numRows".
	 */
	public ALMatrix(int numRows, int numCols, int initVal) {
		// TODO: Fill the rest here
		this.mtx = new ArrayList<Integer>();
		this.numRows = numRows;
		this.numCols = numCols;
		for(int i=0; i<numRows*numCols; i++){
			mtx.add(initVal);
		}
	}
	
	/*
	 * TODO: Implement get() and set() which retrieves and sets, respectively,
	 * the value at the given location. The indexes are all zero-based.
	 * Consider what the code should do if the given location is invalid.
	 */
	/*
	 * O(1)
	 * The method will firstly check if the given location is valid or not.
	 * So that the element we want to get is inside the matrix, preventing IndexOutOfBound Exception.
	 * The complexity of this operation is O(1), since it is just an assignment with comparison.
	 * The method will caculate the index which corresponds to the given row and col 
	 * which time complexity is also O(1) since it is just an simple arithmetic operation.
	 * the element from arraylist mtx should be return, and it calls method get(index).
	 * However, the operation is also constant time O(1) since random excess is availabl in arraylist.
	 * Thus, overall time complexity of this method is O(1) since the operations that are done inside the method are all constant time O(1).
	 */
	public int get(int row, int col) {
		if (row<0 || row>=numRows || col<0 || col>=numCols){
			throw new IndexOutOfBoundsException("The location doesn't exist in the matrix");
		}
		int index = (row)*numCols+(col);
		return mtx.get(index);
	}
	/*
	 * O(1)
	 * The method will firstly check if the given location is valid or not.
	 * So that the element we want to get is inside the matrix, preventing IndexOutOfBound Exception.
	 * The complexity of this operation is O(1), since it is just an assignment with comparison.
	 * This setter method will declare and initialize a new integer variable "oldVal" which stores the old value before it change to the new data value.
	 * It calls get(row, col) method which has 2 parameters that we made which the time complexity was O(1).
	 * And then, it caculates the index which corresponds to the given row and col same as above getter method.
	 * This operation is simple arithmetic operation. So, the time complexity is also constant time O(1).
	 * Lastly, it calls original set(index, obj) method to change the old value to the new data. The called set(index, obj) method's time complexity is O(1) since random excess is available in arraylist.
	 * it will return the old value that we have saved before changing it to new data. 
	 * Thus, overall time complexity of this method is O(1) since implementations inside the method are all constant time O(1).
	 */
	
	public int set(int row, int col, int data) {
		if (row<0 || row>=numRows || col<0 || col>=numCols){
			throw new IndexOutOfBoundsException("The location doesn't exist in the matrix");
		}
		int oldVal = get(row, col);
		int index = (row)*numCols+(col);
		mtx.set(index, data);
		return oldVal;   
	}

	/*
	 * TODO: Implement addRows() which adds the given number of rows,
	 * whose values are initialized to initVal.
	 * The parameter 'addToBottom' is a flag that indicates whether the
	 * new rows should be appended to the bottom of the matrix. If this
	 * value is false, then the rows are added to the top of the matrix.
	 * See the test cases in main() to gain a better understanding
	 * of the behavior of this variable.
	 */
	/*
	 * O(m*n*f)
	 * The method will be divided into 2cases.
	 * First case is when one of the passed parameters, addToBottom is true which we should add row(s) to the bottom of the matrix.
	 * Second case is the opposite case that the rows should be added to top of the matrix.
	 * whenever the addRows() method is called, it should be one of these 2 cases.
	 * 
	 * In the first case(add row(s) to the bottom), The method will use for-loop to add the new element data at the end of the arraylist mtx for numRows*this.numCols times.
	 * we multiply numRows and this.numCols because that is the amount of elements need to be added. 
	 * The called add(obj) method inside the for-loop runs in amortized constant time O(1) when each adding is happening.
	 * It is because just adding to the end does not need any shifting process.
	 * So, the the time complexity of this first case  depends on the new "numRows" which is given from the parameter meaning how many rows want to be added and "this.numCols" which we already know. 
	 * And it can be representes as O(m*f) where each m represents new "numRows" which is given from the parameter and frepresents "this.numCols"
	 * 
	 * In the Second case(add row(s) to the top), The method will use for-loop to add the element data at the front(index 0) of the arraylist mtx for numRows*this.numCols times.
	 * we multiply numRows and this.numCols because that is the amount of elements need to be added. 
	 * Unlike the first case, this case will use add(index, obj) method inside the for-loop which have 2 parameters, index and initVal passed in.
	 * This add(index, obj) method has the time complexity of O(n) where n represents size of the arraylist because the subarray has to be shift to the right. 
	 * So, this for-loop block has the time complexity of O(m*n*f) where m represents new "numRows" which is given from the parameter, f represents "this.numCols", and n represents"the size of the arraylist"
	 * 
	 * Whatever case it has gone through, in the last, both case have to add number of rows that had been added to the original numRows(this.numRows). 
	 * This operation is just an simmple arithmetic operation which is O(1).
	 * 
	 * Overall, the time complexity should be the worst case, and the worst case is the 2nd case which the time complexity is 
	 * O(m*n*f) where m represents new "numRows" which is given from the parameter, f represents "this.numCols", and n represents "the size of the arraylist"

	 */
	public void addRows(int numRows, int initVal, boolean addToBottom) {
		//add to bottom
		if (addToBottom == true){
			for(int i=0; i<numRows*this.numCols; i++){
				mtx.add(initVal);
			}
		}
		//add to top
		else{
			for(int i=0; i<numRows*this.numCols; i++){
				mtx.add(0, initVal);
			}
		}
		this.numRows += numRows;
	}
	
	/*
	 * TODO: Implement addColumns() which adds the given number of columns,
	 * whose values are initialized to initVal.
	 * The parameter 'addToRight' indicates whether the new columns should
	 * be added to the right side of this matrix. The new columns are added
	 * to the left of this matrix if this value is false.
	 */
	/*
	 * O(m*n*f)
	 * The method will be divided into 2cases.
	 * First case is when one of the passed parameters, addToRight is true which we should add column(s) to the right of the matrix.
	 * Second case is the opposite case that the columns should be added to left of the matrix.
	 * whenever the addRows() method is called, it should be one of these 2 cases.
	 * 
	 * In the first case(add column(s) to the right), The method will firstly use for-loop to add the new element data at the matrix 1st row's right-end for "numCols" times. 
	 * The time complexity of add(index, obj) is O(n) and the amount of time the loop runs depends on the given parameter "numCols".
	 * So, this block of for-loop's time complexity is O(n*m) where n represents "numCols" and m represents "the size of the arraylist".
	 * when the column is added in first row, this.numCols have to be updated by adding "numCols" which the time complexity of this operation is just O(1).
	 * The addition of columns from the second row is to be done with the nested for-loop.
	 * This nested for-loop represents the element should be added "numCols" times whenever the row changes.
	 * Inside the inner for-loop, there is simple arithmetic operation to find out the index where to add which the time complexity is O(1).
	 * And actual adding by add(index, obj) method has the time complexity is O(n).
	 * So, the time complexity of this whole nested for-loop block is O(n*m*f) where f represents "numRows", n represents"numCols" which is given through parameter, and m represents "the size of the arraylist". 
	 * Thus, the time complexity of this first case is O(n*m*f).
	 * 
	 * In the second case(add column(s) to the left), The method will firstly use for-loop to add the new element data at the matrix 1st row's left-end for "numCols" times. 
	 * The time complexity of add(index, obj) is O(n) and the amount of time the loop runs depends on the parameter "numCols".
	 * So, this block of for-loop's time complexity is O(n*m) where n represents "numCols" and m represents "the size of the arraylist".
	 * when the column is added in first row, this.numCols have to be updated by adding "numCols" which the time complexity of this operation is just O(1).
	 * The addition of columns from the second row is to be done with the nested for-loop.
	 * This nested for-loop represents the element should be added "numCols" times whenever the row changes.
	 * Inside the inner for-loop, there is simple arithmetic operation to find out the index where to add which the time complexity is O(1).
	 * And actual adding by add(index, obj) method has the time complexity is O(n).
	 * So, the time complexity of this whole nested for-loop block is O(m*n*f) where f represents "numRows", n represents "numCols" which is given through the parameter, and m represents "the size of the arraylist". 
	 * Thus, the time complexity of this second case is also O(m*n*f).
	 * 
	 * Overall, the time complexity should be the worst case. 
	 * However both cases have the same complexity of O(m*n*f) where f represents "numRows", n represents"numCols" which is given through parameter, and m represents "the size of the arraylist".
	 */
	public void addColumns(int numCols, int initVal, boolean addToRight) {
		//add to right
		if(addToRight == true){

			for(int i=0; i<numCols; i++){
				mtx.add(this.numCols, initVal);
			}
			this.numCols += numCols;
			for(int i=1; i<numRows; i++){
				for(int j=0; j<numCols; j++){
					int addIdx = i*this.numCols + this.numCols-numCols;
					mtx.add(addIdx, initVal);
				}
			}
		}
		//add to left
		else{
			for(int i=0; i<numCols; i++){
				mtx.add(0, initVal);
			}
			this.numCols += numCols;
			for(int i=1; i<numRows; i++){
				for(int j=0; j<numCols; j++){
					int addIdx = i*this.numCols;
					mtx.add(addIdx, initVal);
				}
			}
		}
	}

	/*
	 * TODO: Implement toString() that builds a String representation of this
	 * matrix. The matrix is represented as a sequence of numbers laid out in
	 * a numRows-by-numCols grid. See the examples given in main().
	 */
	/*
	 * O(m*f*n)
	 * This method is to represent the element in the arraylist to String with matrix format.
	 * So firstly, declare a String variable "str" and initialize it with "" which represents nothing.
	 * The method will gradually add the elements into this String variable "str" as it goes through the whole arraylist.
	 * 
	 * It will use the nested for-loop to add the elements to "str" until it reach the last row.
	 * At this point, "str" will also change the row whenever the row of the matrix changes(outer for-loop).
	 * At each row, it will go though every column(inner for-loop) so that all the elements in that row could be added to the "str".
	 * it will use get(index) method which the time complexity is O(1) to retrieve the element.
	 * However, the retrieved element is integer so that it needs to be changed to type String.
	 * It can be done with using Integer.toString() method. The time complexity of this method is O(n) where n represents "the number of digits in the string representation of the integer".
	 * So, the time complexity of this nested for-loop block is O(m*f*n) where m represents "numRows", f represents "numCols", and n represents "the number of digits in the string representation of the integer".
	 * 
	 * for the last row, it will use single for-loop so that it can add the elemetnts of every column in the last row. 
	 * Inside the loop, same as above, it will use get() method which is O(1) and Integer.toString() method which is O(n) where n represents "the number of digits in the string representation of the integer".
	 * So, the time complexity of this for-loop block is O(f*n) where f represents "numCols" and  n represents "the number of digits in the string representation of the integer".
	 * 
	 * Lastly, it will return "str" where all the element data of the arraylist is added after each of them is converted to String type. 
	 * 
	 * Therefore, the time complexity of this whole method is O(m*f*n) where m represents "numRows", f represents "numCols", and n represents "the number of digits in the string representation of the integer".
	 */
	public String toString() {
		String str = "";

		for(int i=0; i<numRows-1; i++){
			for(int j=0; j<numCols; j++){  
				str += Integer.toString(mtx.get(i*numCols+j)); 
			}
			str += "\n";
		}
		for(int k=0; k<numCols; k++){
			str += Integer.toString(mtx.get((numRows-1)*numCols+k));
		}
		return str;
	}
	
	/*
	 * Don't modify or remove the following three methods.
	 * These are for testing purposes, and hence will be used by my grading script.
	 * You're free to use these in your code, however.
	 */
	public int getNumRows() { return numRows; }
	public int getNumCols() { return numCols; }
	public ArrayList<Integer> getAL() { return mtx; }
	
	public static void main(String[] s) {
	
		ALMatrix m = new ALMatrix(3, 4, 0);
		System.out.println(m); // Should print out zeros of 3 rows and 4 columns.
		System.out.println(m.set(0, 0, 1)); // 0
		System.out.println(m.get(0, 0));    // 1
		m.addRows(2, 4, false); // Add two rows of 4s to the top of the matrix.
		System.out.println(m);		// Should print the following
		/*
		 * 4 4 4 4
		 * 4 4 4 4
		 * 1 0 0 0
		 * 0 0 0 0
		 * 0 0 0 0
		 */
		m.addColumns(1, -4, true);  // Add one column of 5s to the right of the matrix.
		System.out.println(m);		// Should print the following
		/*
		 * 4 4 4 4 5
		 * 4 4 4 4 5
		 * 1 0 0 0 5
		 * 0 0 0 0 5
		 * 0 0 0 0 5
		 */
		System.out.println(m.get(1, 4)); // 5
		System.out.println(m.set(1, 1, 3)); //4
		//System.out.println(m.get(-1, 1)); //error message
	}
}