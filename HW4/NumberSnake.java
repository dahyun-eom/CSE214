/**
 * Do not import or declare any packages.
 * Make sure to include your run-time analysis for each required method.
 * @author <Dahyun Eom, 115943034>
 */

public class NumberSnake {
	
	Body first; // You must use this head pointer to manage your snake. Make sure to maintain a singly-linked snake.


	/*
	 * O(1)
	 * The time-complexity of the constructior is O(1).
	 * The constructor make new "Body" instance and initailize the field "first".
	 * So, now the first will point at the first body of the snake.
	 * This process requires the constant time.
	 */
	public NumberSnake() {
		first = new Body(' ',' ', null);
	}

	static class Container{
        Body item;
        Body[] remainder;
		int bodyLen = 0;
		char lastBodytailNum;
		/*
		 * This is the constructor of class Container.
		 * The constructor initialize the field item and remainder to bd and bdarr which are given through the parameters.
		 * Also, it will assign the field bodyLen by counting the number of bodies that are linked to the item. 
		 */
        public Container(Body bd, Body[] bdarr){
            item = bd;
            remainder = bdarr;
			lastBodytailNum = bd.tailNum;

			Body curr = item;
            while (curr != null){
                curr = curr.next;
                bodyLen += 1;
            }
        }
		/*
		 * This method is to return the boolean checking
		 * whether the 2 Bodies that are given through the parameters have the same headNum and tailNum. 
		 */
        static boolean equals(Body bd1, Body bd2){
            return (bd1.headNum == bd2.headNum) && (bd1.tailNum == bd2.tailNum);
        }
		/*
		 * This method is to return a array of Bodies that has eliminated the Body "bd" from Body[] "bdarr" that want to remove.
		 * It will shift all the Bodies to left so that there is no null in the middle of the array.
		 * The null will be in the end of the array.
		 */
        static Body[] removeDup(Body bd, Body[] bdarr){
            Body[] res = new Body[bdarr.length];
            int idx = 0;
            for(int i = 0; (i < bdarr.length) && (bdarr[i] != null); i++) {
                if (Container.equals(bd, bdarr[i])){
                    continue;
				}
                res[idx++] = bdarr[i];
            }
            return res;
        }
		// static Body[] removeDup(Body bd, Body[] bdarr){
        //     Body[] res = new Body[bdarr.length];
        //     int idx = 0;
		// 	int count = 0;
        //     for(int i = 0; (i < bdarr.length) && (bdarr[i] != null); i++) {
        //         if (Container.equals(bd, bdarr[i])){
		// 			if(count == 0){
		// 				count++;
		// 				continue;
		// 			}
		// 		}
        //         res[idx++] = bdarr[i];
        //     }
        //     return res;
        // }
    }
	/*
	 * This method is to return an array of Body that has removed bod(y)ies that its tailNum is 'T'
	 */
	public static Body[] removeBodyTailT(Body[] b){
		int count=0;
		for(int i=0; i<b.length; i++){
			if(b[i].tailNum == 'T'){
				count++;
			}
		}
		Body[] res = new Body[b.length-count];
		int idx=0;
		for(int i=0; i<b.length; i++){
			if(b[i].tailNum != 'T'){
				res[idx] = b[i];
				idx++;
			}
		}
		return res;
	}
	/*
	 * This method is to return an array of Body that has removed bod(y)ies that its tailNum is 'H'
	 */
	public static Body[] removeBodyTailH(Body[] b){
		int count=0;
		for(int i=0; i<b.length; i++){
			if(b[i].tailNum == 'H'){
				count++;
			}
		}
		Body[] res = new Body[b.length-count];
		int idx=0;
		for(int i=0; i<b.length; i++){
			if(b[i].tailNum != 'H'){
				res[idx] = b[i];
				idx++;
			}
		}
		return res;
	}


	/* TODO:
	 * The last method is a new one, which returns the longest among all new valid snakes that can be
	 * composed from the set of bodies ‘b’. 
	 * For example, if b is {(H, 3), (2, 4), (5, 2), (3, 1), (3, T), (2, 1), (1,T)} (with abuse of notation), 
	 * then it should return a new snake containing (H,3)-(3,1)-(1,T). 
	 * Notice that (H,3)-(3,T) is also a possible valid snake, but is ignored since the former snake is the longest.
	 * If there are multiple longest snakes, you may return any one of them. If no valid snakes can be formed, 
	 * then return a null.
	 */
	/*
	 * The method is to make a max length of valid snake from the given Body array "b" that is passed into the parameter.
	 * First, it will make a Body array "startNode" where it stores the Body that is the form (H, ) or (T, )
	 * 
	 * If the first index of the  array "startNode" is  the form of (H, ) then we will assign the Container "maxLen" by calling the 
	 * auxiliary method auxGetMaxValidSnake but passing Container.removeDup(startNode[0], removeBodyTailH(b)) in the second parameter.
	 * 
	 * If the first index of the  array "startNode" is  the form of (T, ) then we will assign the Container "maxLen" by calling the 
	 * auxiliary method auxGetMaxValidSnake but passing Container.removeDup(startNode[0], removeBodyTailT(b)) in the second parameter.
	 * 
	 * These division of the condition will delete the possibility of (H, )-( , )-....-( ,H) or (T, )-( , )-....-( ,T) to happen.
	 * 
	 * After, we will do the similar step for later indexes of startNode but will assign it to the Container "temp" 
	 * However it will compare the length of the linked body's so that when "temp"'s length is bigger, then it will substitute "maxLen"
	 * 
	 * Lastly, it will return the maxLen.item which will be the first of the longest valid snake.
	 * 
	 */

    public static Body getMaxValidSnake(Body[] b){
        Body[] startNode = new Body[b.length];
		int idx = 0;
        for(int i = 0; i < b.length; i++){
            if(b[i].headNum == 'H'|| b[i].headNum == 'T'){
                startNode[idx] = b[i];
				idx++;
			}
		}
		Container maxLen = null;
		if(startNode[0].headNum == 'H'){
			maxLen = auxGetMaxValidSnake(startNode[0], Container.removeDup(startNode[0], removeBodyTailH(b)));
		}
		else if (startNode[0].headNum == 'T'){
			maxLen = auxGetMaxValidSnake(startNode[0], Container.removeDup(startNode[0], removeBodyTailT(b)));
		
		}
		Container temp = null;
		for(int i = 1; i < startNode.length && startNode[i] != null; i++){
			if(startNode[i].headNum=='H'){
				temp = auxGetMaxValidSnake(startNode[i], Container.removeDup(startNode[i], removeBodyTailH(b)));
			}
			else if (startNode[i].headNum == 'T'){
				temp = auxGetMaxValidSnake(startNode[i], Container.removeDup(startNode[i], removeBodyTailT(b)));
			}
			if(maxLen.bodyLen <= temp.bodyLen){
				maxLen = temp;	
			}
		}
		NumberSnake maxValidSnake = new NumberSnake();
		maxValidSnake.first = maxLen.item;
		return maxValidSnake.first;
    }
	// public static Body getMaxValidSnake(Body[] b){
    //     Body[] startNode = new Body[b.length];
	// 	int idx = 0;
    //     for(int i = 0; i < b.length; i++){
    //         if(b[i].headNum == 'H'|| b[i].headNum == 'T'){
    //             startNode[idx] = b[i];
	// 			idx++;
	// 		}
	// 	}
	// 	Container maxLen = auxGetMaxValidSnake(startNode[0], Container.removeDup(startNode[0], b));
	// 	Container temp = null;
	// 	for(int i = 1; i < startNode.length && startNode[i] != null; i++){
	// 		temp = auxGetMaxValidSnake(startNode[i], Container.removeDup(startNode[i], b));
	// 		if(maxLen.bodyLen <= temp.bodyLen){
	// 			maxLen = temp;
	// 		}
	// 	}
	// 	return maxLen.item;
    // }
	/*
	 * This is the auxiliary method of method getMaxValidSnake().
	 * It will take 2 parameter which are the Body that will seek other bodies that are able to link it to itself 
	 * and an array of Body that used bod(y)ies are eliminated.
	 * The method will recursively search for the body that can be linked, actual linking process, and also getting the maxLength linkage of the bodies by comparison.
	 * 
	 * The method make new Body array name "concatArr" that will store the array that are able to link to Body bd that is ginven through the parameter.
	 * 
	 * The base case is when the concatArr[0] becomes null meaning there are no more bodies able to link.
	 * In this case, it will return a new Container which the item is "bd" and Body[] is "remainder" which are given through the parameters. 
	 * 
	 * The recursive case is when it calls the method itself.
	 * Firstly, it is when calling the method itself to initialize Container "maxLen" by passing the first index of concatArr "concatArr[0]" and Container.removeDup(concatArr[0], remainder) into the parameter.
	 * 
	 * Secondly, it is when assigning Container "temp" by passing the concatArr[i] (which i will change through iteration of the for-loop
	 * and Container.removeDup(concatArr[i], remainder) into the parameter. 
	 * 
	 * After recursion in each iteration of the indexes in concaatArr, it will also compare the bodyLen of the 2 Container "maxLen" and "temp" to update "maxLen" (ultimately to get the longest linkage of bodies.)
	 * 
	 * Overall, in normal case, it will return a new Container with the parameter Body "bd" and Body[] maxLen.remainder passed in. 
	 * This is actually returning the linkage which the first body is "bd" (However we just points the first body "bd") and the Body[] which the used bodies to make the linkage are eliminated(literally the remainder Bodies after making the maxLen linkage).
	 */
    private static Container auxGetMaxValidSnake(Body bd, Body[] remainder){
        Body[] concatArr = new Body[remainder.length];
        int idx = 0;
        for(int i = 0; i < remainder.length && remainder[i] != null; i++){
            if(bd.tailNum == remainder[i].headNum && bd.tailNum != 'H' &&  bd.tailNum != 'T' ){ 
                concatArr[idx] = remainder[i];
				idx++;
			}
        }
        if(concatArr[0] == null){ //base case
            return new Container(bd, remainder);
		}
        Container maxLen = auxGetMaxValidSnake(concatArr[0], Container.removeDup(concatArr[0], remainder)); //recursive case
        Container temp = null;
        for(int i = 1; i < concatArr.length && concatArr[i] != null; i++){
            temp = auxGetMaxValidSnake(concatArr[i], Container.removeDup(concatArr[i], remainder));  //recursive case
            if(maxLen.bodyLen <= temp.bodyLen){
                maxLen = temp;
			}
        }
        bd.next = maxLen.item;
        return new Container(bd,maxLen.remainder);
    }
	
	
	
	public static void main(String[] s) {
		Body[] a = new Body[11];
        a[0] = new Body('2', '4', null);
        a[1] = new Body('H', '3', null);
        a[2] = new Body('5', '2', null);
        a[3] = new Body('3', '1', null);
        a[4] = new Body('3', 'T', null);
        a[5] = new Body('4', '3', null);
        a[6] = new Body('3', '3', null);
        a[7] = new Body('3', '4', null);
        a[8] = new Body('1', 'T', null);
		a[9] = new Body('T', '2', null);
		a[10] = new Body('4', 'H', null);
        System.out.println("Test 1");
        Body curr = getMaxValidSnake(a);
        while(curr!=null){
            System.out.println("(" + curr.headNum + ", "+ curr.tailNum + ")");
            curr = curr.next;
        }

        System.out.println("\nCase 2");
        Body[] b = new Body[7];
        b[0] = new Body('H', '3', null);
        b[1] = new Body('2', '4', null);
        b[2] = new Body('5', '2', null);
        b[3] = new Body('3', '1', null);
        b[4] = new Body('3', 'T', null);
        b[5] = new Body('2', '1', null);
        b[6] = new Body('1', 'T', null);
        Body curr2 = getMaxValidSnake(b);
        while(curr2!=null){
            System.out.println("(" + curr2.headNum + ", "+ curr2.tailNum + ")");
            curr2 = curr2.next;
        }
		

		System.out.println("\nCase 3");
        Body[] c = new Body[10];
        c[0] = new Body('7', '4', null);
        //c[1] = new Body('T', '3', null);
        c[1] = new Body('4', '1', null);
        c[2] = new Body('3', '1', null);
        c[3] = new Body('1', 'H', null);
        c[4] = new Body('2', 'H', null);
        c[5] = new Body('H', '3', null);
		c[6] = new Body('T', '2', null);
		c[7] = new Body('H', '2', null);
		c[8] = new Body('1', '1', null);
		c[9] = new Body('1', '1', null);

		//c[7] = new Body('4', 'T', null);
		// Body[] curr4 = firstArr(c);
		// for(int i=0; i<curr4.length; i++){
		// 	System.out.println(curr4[i].headNum);
		// 	System.out.println(curr4[i].tailNum);
		// 	System.out.println();
		// }
        Body curr3 = getMaxValidSnake(c);
        while(curr3!=null){
            System.out.println("(" + curr3.headNum + ", "+ curr3.tailNum + ")");
            curr3 = curr3.next;
        }

		// NumberSnake ns = new NumberSnake();
		
		// // Body a = new Body('H', '3', null);

		// // ns.addBody(a);


		// // // ns.first = a;
		// // System.out.println(ns.toString());

		// ns.addFirst('H', '1');
		// ns.addLast('1', 'T');
		// ns.addFirst('2', 'H');
		// System.out.println(ns.isValidSnake());
		// System.out.println(ns.toString());
	
		// System.out.println(ns.isValidSnake()); // true
		// // The following represents a body sequence (1,2)-(2,3)-(3,4)-(4,1)
		// char[] chs = {'1', '2', '2', '3', '3', '4', '4', '1'};
		// ns.mergeSnakes(chs);
		// System.out.println(ns.toString());

		// String gt = "(H,1)-(1,2)-(2,3)-(3,4)-(4,1)-(1,T)";
		// System.out.println(ns.toString().equals(gt)); // true

		// ns.removeChunk();
		// System.out.println(ns.toString());

		// gt = "(H,1)-(1,T)";
		// System.out.println(ns.toString().equals(gt)); // true

		// Body[] a = new Body[8];
		// a[0] = new Body('H', '3', null);
		// a[1] = new Body('2', '4', null);
		// a[2] = new Body('5', '2', null);
		// a[3] = new Body('3', '1', null);
		// a[4] = new Body('3', 'T', null);
		// a[5] = new Body('2', '1', null);
		// a[6] = new Body('1', 'T', null);
		// a[7] = new Body('T', '5', null);
		// // Body[] b = withoutFirstBodies(a);
		// // for(int i=0; i<b.length; i++){
		// // 	System.out.println(b[i].headNum);
		// // 	System.out.println(b[i].tailNum);
		// // 	System.out.println();
		// // }
		// Body curr = getMaxValidSnake(a);
		// while(curr!=null){
		// 	System.out.println(curr.headNum);
		// 	System.out.println(curr.tailNum);
		// 	curr = curr.next;
		// }

	}

	/*
	 * TODO: Should return true if this snake is a full, valid snake.
	 * A valid snake is one that starts with a head/tail and ends with a tail/head (respectively),
	 * and all adjacent numbers in the middle match.
	 * e.g., (H,1)-(1,2)-(2,T)   OR   (T,4)-(4,H)
	 */
	/*
	 * O(n) where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * 
	 * The method will firstly check if the first body's headNum is 'H' or 'T' or something else.
	 * If it starts with 'H', it will goes to the first big if-block(case1), 
	 * and if it starts with 'T', then it will goes through big else-if block(case2).
	 * If the case is neither above 2 cases, then it will goes  through big else block, where it returns false.
	 * 
	 * Case1 and Case2 is exactly the same process except the position of 'H' and 'T' is the opposite.
	 * The method already initialized local variable "curr" to "first". 
	 * In each case(case1, case2) it will goes through a while-loop where it checks 
	 * if curr's tailNum and the headNum of the next boody of curr("currNext") is same or not.
	 * "curr" and "currNext" will keep shifting to the next until the "curr" points at the end Body of the snake.
	 * This process takes linear time O(n) where n represents the number of the body forming NumberSnake.
	 * when the checking of the middle part of the snake is successfully done, then it will check the tailNum of the last Body if it has the right 'H' or 'T'.
	 * 
	 * Thus, the time complexity of the method is O(n) where n represents the number of bodies in NumberSnake (size of the linked list).
	 * 
	 */
	// public boolean isValidSnake() { //iterative solution
	// 	Body curr = first;
	// 	if(first.headNum == 'H'){
	// 		while(curr.next != null){
	// 			Body currNext = curr.next;
	// 			if(curr.tailNum != currNext.headNum){ 
	// 				return false;
	// 			}
	// 			curr = curr.next;
	// 		}
	// 		if(curr.tailNum == 'T'){
	// 			return true;
	// 		}
	// 		else{
	// 			return false;
	// 		}
	// 	}
	// 	else if(first.headNum == 'T'){
	// 		while(curr.next != null){
	// 			Body currNext = curr.next;
	// 			if(curr.tailNum != currNext.headNum){
	// 				return false;
	// 			}
	// 			curr = curr.next;
	// 		}
	// 		if(curr.tailNum == 'H'){
	// 			return true;
	// 		}
	// 		else{
	// 			return false;
	// 		}
	// 	}
	// 	else{
	// 		return false;
	// 	}
	// }

	
	/*
	 * Firstly, the method will check if the first of the snake's headNum is 'H' or not.
	 * If it is, then it will return a boolean by calling auxiliary method isValidSnakeStartH(first) putting the Body first into the parameter.
	 * 
	 * If it is not the case, then the method will also check if the first of tthe snkae;s headNum is 'T' or not.
	 * If it is, then it will return a boolean by calling auxiliary method isValidSnakeStartT(first) putting the Body first into the parameter.
	 * 
	 * if the snake is none of the above cases, then it will return false since it means the snake's first headNum doesn't start with 'H' nor 'T'.
	 */
	public boolean isValidSnake(){
		if(first.headNum == 'H'){
			return isValidSnakeStartH(first);
		}
		else if(first.headNum == 'T'){
			return isValidSnakeStartT(first);
		}
		return false;
	}
	/*
	 * This method is the auxiliary method of isValidSnake() to identify is the snake is valid or not
	 * for the case especially when the first headNum of the snake is 'H'. 
	 * The method will check if the tailNum of Body that is given through the parameter is equals to the next Body's headNum.
	 * Overall checking if the snake is (H, )-( , )-.......-( ,T) format or not.
	 * 
	 * The base case of the method is when the next of the Body "curr" that is given through the parameter is null meaning the "curr" is the last Body of the snake.
	 * It will return a boolean by comparing is tailNum of "curr" is 'T' or not.
	 * 
	 * The recursive case of the method is caliing the method itself by passing the next of "curr" (nextBody) for parameter which will conduct the same assignment with the next Body.
	 */
	private boolean isValidSnakeStartH(Body curr){  //auxiliary method
		if(curr == null){
			return false;
		}
		if(curr.next == null){    //base case
			return curr.tailNum == 'T';
		}
		Body nextBody = curr.next;
		if(curr.tailNum != nextBody.headNum){
			return false;
		}
		return isValidSnakeStartH(nextBody);  //recursive case
	}
	/*
	 * This method is the auxiliary method of isValidSnake() to identify is the snake is valid or not
	 * for the case especially when the first headNum of the snake is 'T'. 
	 * The method will check if the tailNum of Body that is given through the parameter is equals to the next Body's headNum.
	 * Overall checking if the snake is (T, )-( , )-.......-( ,H) format or not.
	 * 
	 * The base case of the method is when the next of the Body "curr" that is given through the parameter is null meaning the "curr" is the last Body of the snake.
	 * It will return a boolean by comparing is tailNum of "curr" is 'H' or not.
	 * 
	 * The recursive case of the method is caliing the method itself by passing the next of "curr" (nextBody) for parameter which will conduct the same assignment with the next Body.
	 */

	private boolean isValidSnakeStartT(Body curr){  //auxiliary method
		if(curr == null){
			return false;
		}
		if(curr.next == null){     //base case
			return curr.tailNum == 'H';
		}
		Body nextBody = curr.next;
		if(curr.tailNum != nextBody.headNum){
			return false;
		}
		return isValidSnakeStartH(nextBody);   //recursive case
	}
	
	/*
	 * TODO: Given a body sequence 's', try to insert it into the current snake. 
	 * The resulting snake should also be a valid snake.
	 * e.g., s = (3,5)-(5,2)-(2,3), main = (H,4)-(4,3)-(3,T), result = (H,4)-(4,3)-(3,5)-(5,2)-(2,3)-(3,T)
	 * 's' will be formatted as follows: {c1, c2, c3, c4, c5, c6, ..., c(N-1), cN}. That is,
	 * it is a sequence of head-tail pairs. See the example in main() for a clearer idea.
	 * Return false if it's not possible to make the insertion and true otherwise.
	 * 'main' is guaranteed to be a valid snake, and 's' has length at least 2.
	 */
	/*
	 * O(n+m) where n represents the number of bodies forming NumberSnake (size of the linked list) and m represents the half of the given array's length.
	 * 
	 * The method will checkk if the length of the array is even or odd. If it is odd, it is invalid to merge. This comparsion requires O(1).
	 * Also, it will check if when the given array has only 2 elements,the 2 elements in the array is identical or not. 
	 * Because if the 2 elements are not equal, then it is unavailable to merge.
	 * This checking comparison also takes constant time.
	 * 
	 * If the case passes the checking processes, then the method will initialize local Body variable "curr" to first which takes constant time.
	 * It will use while-loop to find the postion of the main snake where to merge the new chunk(snake).
	 * while-loop shifts "curr" to the next body if curr's tailNum doesn't match the first index of the array until curr's tailNum match the first index of the array
	 * It will return false and end the method when "curr" points the end body of the snake representing there is no place to merge.
	 * So, in the worst case, this while-loop block requires time complexity of O(n) where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * 
	 * If the merge position is found by the while-loop, the method initializes local Body variable "subBodies" to the next Body of the "curr".
	 * So that after adding the new chunk, it can stitch the left over bodies.
	 * In the merging process the method will use for loop to make the elements in array that is given through the parameter to the form of Bodies. 
	 * Since 2 elements in the array form 1 body of snake, the for-loop will run "the half of the given array's length" times. 
	 * Whenver the for-loop loops, the new Body is made by the 2 elements of the array, it will also be linked to the former part of the snake.
	 *  
	 * Lastly, it will link the last newly made body("curr") to the latter part of the main snake("subBodies") and return true.
	 * 
	 * Thus, the time complexity of this method is O(n+m) where n represents the number of bodies forming NumberSnake (size of the linked list) and m represents the half of the given array's length.
	 * 
	 */
	public boolean mergeSnakes(char[] s) {
		if(s.length%2 ==1){ // if array s has less than 2 or odd number elements it cannot merge properly.
			return false;
		}
		else if(s.length == 2 && s[0]!=s[1]){ // if array s has only 2 elements, than the 2 elements in the array should be identical
			return false;    
		}
		else{
			Body curr = first;
			while (curr.tailNum != s[0]){
				if(curr.next == null){
					return false; 
				}
				curr = curr.next;
			}
			Body subBodies = curr.next;
			int j=0;
			for(int i=0; i<(s.length/2); i++){
				Body newBody = new Body(s[j], s[j+1], null);
				curr.next = newBody;
				curr = curr.next;
				j+=2;
			}
			curr.next = subBodies;
			return true;
		}
	}
		 
	
	/*
	 * TODO: Remove a body sequence of the current snake that will result in a 
	 * shorter valid snake.
	 * e.g., main = (H,4)-(4,3)-(3,5)-(5,2)-(2,3)-(3,T), result = (H,4)-(4,3)-(3,T)
	 * Remove the first chunk if there are multiple removable chunks.  
	 */
	/*
	 * O(n^2) where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * Firstly, the method initialize local Body "curr" to first which requires constant time.
	 * Also, it initializes integer variable "size" by calling the method countBody()
	 * which the time complexity is O(n) where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * 
	 * Then, it will enter the for loop which will initialize Body variable "compareBody" to the next body of the current "curr".
	 * If the method doesn't end by the inner for-loop, then this outer for-loop's role is to shift "curr" to the next body.
	 * Whenever outer-loop loops, it will goes through the inner for-loop. 
	 * this will check if the "compareBody.tailNum" equals to "curr.tailNum" which compareBody.tailNum==curr.tailNum means there exist a chunk that can be eliminated.
	 * If it meets this condition, then it will cut out the chunk, stitch the remaining bodies and end the method.
	 * If it is not, then this inner for-loop will shift "compareBody" to the next body and compare again until this compareBody checks the end body(until the inner-loop's looping ends).
	 * 
	 * Considering the outer loop, in the worst case, overall, the inner for-loop will run for n(n-1)/2 times where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * Thus, the time complexity of this method is O(n^2) where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * 
	 */

	public void removeChunk() {
		Body curr = first;
		int size = countBody(); 
		for(int i=0; i<size-1; i++){
			Body compareBody = curr.next;
			for(int j=0; j<size-1-i; j++){
				if(curr.tailNum == compareBody.tailNum){
					curr.next = compareBody.next;
					compareBody.next = null;
					return;
				}
				compareBody = compareBody.next;
			}
			curr = curr.next;
		}
		System.out.println("no chunk to remove");
	}
	
	/*
	 * TODO: Return a string representation following *exactly* this format:
	 * (c1,c2)-(c3,c4)-... and so on. The ellipsis is replaced by the rest of the sequence.
	 * There should be no white spaces (' ', '\n', '\t', etc.) anywhere in the string.
	 */
	/*
	 * O(n), where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * Firstly, if NumberSnake is an empty snake, it will return nothing. This is just an comparison which requires constant time. 
	 * 
	 * If it is not the above case, the method will initialize local Body variable "curr" to first and string variable as "".
	 * Then it will goes through the while loop which will loop "the number of bodies in NumberSnake (size of the linked list)-1" times.
	 * Whenver it loops, it will add the curr's headNum and tailNum in the format of (curr.headNum,curr.tailNum)- in String type to "str" and shift "curr" to the next body.
	 * String.valueOf(char) method that is called is O(1) since the size of input(whiich is just a single character) doesn't affect the time complexity.  
	 * So, this While-loop block's time complexity is O(n) where n represents the number of bodies in NumberSnake (size of the linked list).
	 * 
	 * When the looping process in While-loop is done, it will lastly add the last Body's headNum and tailNum in the format of (curr.headNum,curr.tailNum) in String type to "str"
	 * Finally the method will return "str". These assigniments requires constant time.
	 * 
	 * Thus, the overall time complexity is O(n) where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * 
	 */
	// public String toString() { //iterative solution
	// 	if(first.headNum==' ' && first.headNum==' '){
	// 		return "";
	// 	}
	// 	Body curr = first;
	// 	String str = "";
	// 	while(curr.next!=null){
	// 		str+="("+String.valueOf(curr.headNum)+","+String.valueOf(curr.tailNum)+")-";
	// 		curr = curr.next;
	// 	}
	// 	str+="("+String.valueOf(curr.headNum)+","+String.valueOf(curr.tailNum)+")";
	// 	return str;
	// }

	/*
	 * The method is to printout the NumberSnake to type String. ( , )-( , )-.....-( , ) in this format.
	 * The method will initiallize Body "curr" to the first of the snake and it will also instantiate new NumberSnake "subNs".
	 * "subNs" will be the snake that contains the all the bod(y)ies of the original snake with the same order
	 * except the first Body of the original snake.
	 * 
	 * The base case is when the the next body of first Body of the snake ("curr") is null meaning the snake is constitute of only one Body.
	 * Then, it will then return "("+String.valueOf(curr.headNum)+","+String.valueOf(curr.tailNum)+")" 
	 * 
	 * The recursive case is that it will return "("+String.valueOf(curr.headNum)+","+String.valueOf(curr.tailNum)+")-" and additionally calling the method itself for the NumberSnake "subNs".
	 * This will  as a result return with type String for the whole Bodies of the snake.
	 */
	public String toString(){ 
		Body curr = first;
		if (curr.next == null){ //base case
			return "("+String.valueOf(curr.headNum)+","+String.valueOf(curr.tailNum)+")";
		}
		NumberSnake subNS = new NumberSnake();
		Body curr2 = first.next;
		while (curr2 != null) {
			subNS.addLast(curr2.headNum, curr2.tailNum);
			curr2 = curr2.next;
		}
		return "("+String.valueOf(curr.headNum)+","+String.valueOf(curr.tailNum)+")-" + subNS.toString(); //reursive case
	}
	
	// public NumberSnake subSnake(NumberSnake s){
	// 	NumberSnake subNumberSnake = new NumberSnake();
	// 	Body curr = first.next;
	// 	while (curr != null) {
	// 		subNumberSnake.addLast(curr.headNum, curr.tailNum);
	// 		curr = curr.next;
	// 	}
	// 	return subNumberSnake;
	// }
	/*
	 * TODO: The usual add/remove methods. 
	 * Addition/removal should happen regardless of the numbers matching. 
	 */
	/*
	 * O(n), where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * 
	 * The method will firstly check if it is trying to addLast to the empty snake.
	 * Then, it will assign the first's headNum and tailNum with the given parameters.
	 * (These comparing operation and assigning process take constant time to do it)
	 * 
	 * In the general case, we firstly initialize local variable "curr" to first.
	 * And use while-loop to reach the end Body as it keeps shifting "curr" to the next body.
	 * This process takes linear time O(n) where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * After curr is placed on the end body, it will instantiate new Body initializing this newly made "newBody" with the given parameters.
	 * And then, it will link our last body("curr") with this new made Body "newBody".
	 * (These instantiaing, initializing, and linking processes take constant time)
	 * 
	 * Thus, the while-loop has the O(n) time complexity, the time complexity of this method is O(n) where n represents the number of bodies forming NumberSnake (size of the linked list).
	 * 
	 */
	public void addLast(char headNum, char tailNum) { 
		if(first.headNum == ' '){ //this is when the first time adding the body
			first.headNum = headNum;
			first.tailNum = tailNum;
		}
		else{
			Body curr = first;
			while(curr.next != null){
				curr = curr.next;
			}
			Body newBody = new Body(headNum, tailNum, null);
			curr.next = newBody;
		}
	}
	/*
	 * This is the helper method that I addionally made.
	 * 
	 * O(n), where n represents the number of bodies in NumberSnake (size of the linked list).
	 * The method will check if the NumberSnake is a empty snake. 
	 * If it is, it will return 0. The time complexity of this if-block is constant time 
	 * since it is just a simple assignment.
	 * 
	 * If it is not an empty snake, it will initialize local Body variable "curr" to first and local variable "count" to 1.
	 * Then, it will use the while-loop to shift curr until it points the last body of the snake.
	 * Whenevr the while-loop loops, and shifts "curr" to the next body, it will add 1 to the variable "count".
	 * This while-loop block requires the time complexity of O(n) where n represents the number of bodies in NumberSnake (size of the linked list).
	 * Lastly, it will return the count which will represent the number of the bodies which are forming NumberSnake.
	 * 
	 * Thus, the time complexity of this method is O(n), where n represents the number of bodies in NumberSnake (size of the linked list).
	 */
	public int countBody(){
		if(first.headNum == ' '){
			return 0;
		}
		Body curr = first;
		int count =1;
		while (curr.next!=null){
			curr = curr.next;
			count++;
		}
		return count;
	}
	/*
	 * O(n^2), where n represents the number of bodies in NumberSnake (size of the linked list).
	 * The method will firstly check if there is something to remove, if there is nothing to remove, it will throw an exception(this assignment requires constant time).
	 * 
	 * The method will check if the Snake has only one body.
	 * This else if statement calls countBody() method which the time complexity is O(n) where n represents the number of bodies in NumberSnake (size of the linked list).
	 * If the snake has only one body, then the method will just erase the information(headNum, tailNum) of that body by assigning ' '.
	 * The implementation inside this else-if block is just assigning the field of the body which take constant time.
	 * 
	 * If the snake has more than one body(none of the above cases) and wants to remove the last body,
	 * the method will initialize local Body variable "curr" to first.
	 * And then, it will use the for-loop to reach the body(by shifting "curr")which is the previous body of the snake's last body.
	 * And then it will unlinlk which was connecting the previous body of the snake's last body and the last body.
	 * 
	 * This whole else-block will have time complexity of O(n^2) where n represents the number of bodies in NumberSnake (size of the linked list).
	 * Because whenever it iterate in the for-loop, it will have to test the condition statement 
	 * which countBody() method is called in the condition statement that the time complexity is O(n) where n represents the number of bodies in NumberSnake (size of the linked list).
	 * Also, the number of how much it will loop depends on the number of bodies in NumberSnake (size of the linked list).
	 * Since the method's time complexity should be the worst case, it will be O(n^2) where n represents the number of bodies in NumberSnake (size of the linked list).
	 */
	public void removeLast() {
		if(first.headNum == ' ' && first.tailNum == ' '){ //when there is nothing to remove.
			throw new IndexOutOfBoundsException("cannot remove because there is nothing to remove.");
		}
		else if(countBody() ==1){
			first.headNum = ' ';
			first.tailNum = ' ';
		}
		else{
			Body curr = first;
			for(int i=0; i<countBody()-2; i++){
				curr = curr.next;
			}
			curr.next = null;
		}
	}
	/*
	 * O(1)
	 * The method will firstly check if the snake is adding the first body in the empty snake.
	 * If it is, then the method will assign first's headNum and tailNum to the value that are given through the method's parameters.
	 * 
	 * In the general case of addFirst, it will instantiate new Body instance "newBody" and initialize it with the value that is given through the Body constructor's parameters.
	 * When this initializing process happens, it will automatically link the newly made body to the front of the "first".
	 * So, the method only have to move the "first" to newly made "newBody"
	 * All these processes takes constant time.
	 */
	public void addFirst(char headNum, char tailNum) { 
		if(first.headNum == ' '){ //this is when the first time adding the body 
			first.headNum = headNum;
			first.tailNum = tailNum;
		}
		else{
			Body newBody = new Body(headNum, tailNum, first);
			first = newBody;
		}
	}
	/*
	 * O(n), where n represents the number of bodies in NumberSnake (size of the linked list).
	 * The method will firstly check if there is something to remove, if the NumberSnake is an empty snake, 
	 * it will throw an exception(this assignment requires constant time).
	 * 
	 * The method will check if the Snake has only one body.
	 * This else if statement calls countBody() method which the time complexity is O(n) where n represents the number of bodies in NumberSnake (size of the linked list).
	 * If the snake has only one body, then the method will just erase the information(headNum, tailNum) of that body by assigning ' '.
	 * The implementation inside this else-if block is just assigning the field of the Body "first" which take constant time.
	 * 
	 * If the snake has more than one body(none of the above cases) and wants to remove the first body,
	 * the method will initialize local Body variable "originalFirst" to first and move "first" to the next body of the original first("originalFirst").
	 * And then, unlink the originalFirst and the next body of the originalFirst(current "first").
	 * These implementations inside the else block requires constant time.
	 * 
	 * Since if the method doesn't throw the exception, it will always test the else if statement where it calls the countBody() method.
	 * So, the time complexity of this method is O(n) where n represents the number of bodies in NumberSnake (size of the linked list).
	 */
	
	public void removeFirst() {
		
		if(first.headNum == ' ' && first.tailNum == ' '){ //when there is nothing to remove.
			throw new IndexOutOfBoundsException("cannot remove because there is nothing to remove.");
		}
		else if(countBody() ==1){
			first.headNum = ' ';
			first.tailNum = ' ';
		}
		else{
			Body originalFirst = first;
			first = first.next;
			originalFirst.next = null;
		}
	}
	/* Do not modify or use this method. This will only be used for grading your code. */
	public Body getMain() { return first; }
}
	
	// Below is a test code given for your reference.
// 	public static void main(String[] s) {

// 		NumberSnake ns = new NumberSnake();
// 		// ns.addFirst('H', '1');
// 		// ns.addLast('1', 'T');
// 		// System.out.println(ns.toString());
	
// 		// System.out.println(ns.isValidSnake()); // true
// 		// // The following represents a body sequence (1,2)-(2,3)-(3,4)-(4,1)
// 		// char[] chs = {'1', '2', '2', '3', '3', '4', '4', '1'};
// 		// ns.mergeSnakes(chs);
// 		// System.out.println(ns.toString());

// 		// String gt = "(H,1)-(1,2)-(2,3)-(3,4)-(4,1)-(1,T)";
// 		// System.out.println(ns.toString().equals(gt)); // true

// 		// ns.removeChunk();
// 		// System.out.println(ns.toString());

// 		// gt = "(H,1)-(1,T)";
// 		// System.out.println(ns.toString().equals(gt)); // true

// 		Body[] a = new Body[8];
// 		a[0] = new Body('H', '3', null);
// 		a[1] = new Body('2', '4', null);
// 		a[2] = new Body('5', '2', null);
// 		a[3] = new Body('3', '1', null);
// 		a[4] = new Body('3', 'T', null);
// 		a[5] = new Body('2', '1', null);
// 		a[6] = new Body('1', 'T', null);
// 		a[7] = new Body('T', '5', null);
// 		Body curr = getMaxValidSnake(a);
//         while(curr!=null){
//             System.out.println("(" + curr.headNum + ", "+ curr.tailNum + ")");
//             curr = curr.next;
//         }
// 		// Body[] b = withoutFirstBodies(a);
// 		// for(int i=0; i<b.length; i++){
// 		// 	System.out.println(b[i].headNum);
// 		// 	System.out.println(b[i].tailNum);
// 		// 	System.out.println();
// 		// }



// 		// Body curr = getMaxValidSnake(a);
// 		// while(curr!=null){
// 		// 	System.out.println(curr.headNum);
// 		// 	System.out.println(curr.tailNum);
// 		// 	curr = curr.next;
// 		// }

		

	
// 	}
// }

/*
 * You may add more methods as necessary but don't change the existing code.
 * In particular, you must maintain a singly-linked representation of the snake.
 */
class Body {
	char headNum, tailNum;
	Body next;
	/*
	 * O(1)
	 * The constructor is just initializing the fields with the own values given through the parameter.
	 * All the operations are O(1) as they are just simple assignments.
	 */
	public Body(char head, char tail, Body next) {
		headNum = head;
		tailNum = tail;
		this.next = next;
		// Feel free to add more code here...
	}
}
