// /**
//  * Name: Dahyun Eom, 115943034
//  * 
//  * Do not import anything else.
//  * You may add other methods and classes as needed, but do not alter what's given.
//  */
public class Huffman {

	private Queueable<Node> pq; // Implement a heap-based priority queue
	private Node root; // TODO: Create your own Node class --> it is in the bottom side

    /*
     * This is the constuctor of the class Huffman.
     * Time complexity: O(1). 
     * Space complexity: O(1). Because it only involves the creation of the priority queue without adding any elements yet.
     */
	public Huffman() {
		pq = new MinHeap();
	}

	/*
	 * Perform a Huffman encoding of 'msg', and return a String containing 0s and 1s that
	 * encodes 'msg'. You MUST use a priority queue-based algorithm for this assignment.
	 * 'msg' is guaranteed to consist only of ASCII values (0 - 255). See https://www.asciitable.com/
	 */
    /* 
     * Time complexity: O(n) where n is the length of 'msg' that is given through the parameter.
     * The time complexity of asciiArr method that is called inside is O(n) where n is length of 'msg' that is given through the parameter.
     * Eventhough buildPQ buildHuffmanTree method has high timecomplexity, when encode method calls them, the input size is always fixed to 256 (-->buildPQ) and equal or less than 256(-->buildHuffmanTree).
     * So, the time complexity of method buildPQ and buildHuffmanTree becomes O(1) inside the encode method.
     * asciiAndCode method is also same, it is guaranteed that the the variable size that affect the time complexity is upperbounded to 256(and perhaps it will never be 256 because it should be the number of leaf nodes after the Huffman tree is built).
     * So we can consider as O(1) here inside the encode method too.
     * getNode method is O(1) which does not affect the time complexity.
     * Hence, we just need to consider the outer for-loop which has the time complexity of O(n) where n is length of 'msg' that is given through the parameter.
     * Overall, the timecomplexity of this method is O(n) where n is the length of 'msg' that is given through the parameter.
     * 
     * Space complexity: O(n) where n is the length of 'msg' that is given through the parameter.
     * Since in the method, size of the array 'asciiArray' is fixed to 256. 
     * So, buildPQ, buildHuffmanTree, asciiAndCode method is guaranteed to be O(1).
     * Thus, only have to consider the string concatenation that will be perform by the for-loop.
     * And it will be O(n) where  n is the length of 'msg' that is given through the parameter.
     */
	public String encode(String msg) {
		int[] asciiArr = asciiArr(msg);     // O(n)/O(1) n is length of 'msg'
		buildPQ(asciiArr);                  //O(1) / O(1)
		//int numberOfChar = ((MinHeap)pq).size;
		//System.out.println(numberOfChar);
		buildHuffmanTree();                  //O(1)/ O(1)   

		ArrayList usedChars = asciiAndCode();      //O(1) / O(1)
		//System.out.println(usedChars.length());

		String res = "";
		for(int i=0; i<msg.length(); i++){
			for(int j=0; j<usedChars.length(); j++){
				if(usedChars.getNode(j).data == (int)(msg.charAt(i))){
					res += usedChars.getNode(j).code;
				}
			}
		}
		return res;
	}
    /*
     * asciiArr method is to return a 256 size array which its(the returning array) index will represents the ascii code and actual int value in each index represents the frequency of the every Char from the 'msg' which is given through the parameter.
     * 
     * Time complexity: O(n) where n is the length of the 'msg' given through the parameter.
     * Since it needs to go through every character in the msg. 
     * 
     * Space complexity: O(1). Creating an array of fixed size is independent of the input size.
     * Thus, it is a constant amount of memory, regardless of the array size. 
     */
	private int[] asciiArr(String msg){ 
		int [] asciArr = new int[256];
		for(int i=0; i<msg.length(); i++){
			int ascii = (int)(msg.charAt(i));
			asciArr[ascii]++;
		}
		return asciArr;
	}
    /*
     * buildPQ method is to build pq which will be keep using during building the Huffman tree.
     * 
     * Time complexity: O(nlogn) where n is the length of the array 'asciiArr' which is given through the parameter.
     * In worst case, enqueuing up to n nodes, each operation taking O(logk), where k is the current number of nodes in the pq.
     * Therefore, the overall complexity is O(nlogn).
     * 
     * Space complexity: O(n^2) where n is the length of the array 'asciiArr'.
     * In the worst case, it has to create Node(space complexity of instantiating Node is O(1)) n times, which will be O(n) where n is the length of the array 'asciiArr', 
     * and enqueue n times (space complexity of enqueuing is O(k) where k is the current number of nodes in the pq) which will be O(n^2) where n is the length of the array 'asciiArr'. ---> O(1)+O(2)+....+O(n) is upperbounded to n*O(n) which equals to O(n^2).
     * O(n^2) dominates O(n). So, the overall space complexity is O(n^2) where n is the length of the array 'asciiArr'.
     *  
     * ++ However, this method will be used only passing 'asciiArr' that the size is 256. 
     * So, even though the method itself has bad time and space complexity,it will run as the time complexity of O(1) and space complexity of O(1). 
     */
	private void buildPQ(int[] asciiArr){ //No problem
		for(int i=0; i<asciiArr.length; i++){
			if(asciiArr[i]>=1){
				Node node = new Node(i, asciiArr[i]);
				//System.out.println(asciiArr[i]);
				pq.enqueue(node);
			}
		}
		// System.out.println(((MinHeap)pq).size);
		// int a = ((MinHeap)pq).size;
		// for(int i=0; i<a; i++){
		// 	System.out.println(pq.dequeue().frequency);
		// }
	}
    /*
     * buildHuffmanTree method is to build a binary tree using the MinHeap instance variable 'pq'.
     * It will dequeue 2 Nodes from pq and build a tree and enqueue the root of that tree into pq again until the size of the pq is 1.
     * 
     * Recursive case: The method will call the method itself after the dequeuing 2 Nodes from pq and build a tree and enqueuing the root of that tree into pq again until the size of the pq is 1.
     * Base case(1): It is when the size of the pq becomes 1 but that node does not have child.
     * Base case(2): It is when the size of the pq becomes 1 but that node has child. 
     * 
     * Time complexity: O(n^2) where n is the number of elements in 'pq'(instance variable).
     * Building Huffman tree involves repeated dequeue and enqueue operations which they have both O(k) time complexity where k is the size of the 'pq'(instance variable).
     * Since it is repeated, overall it is O(n^2).
     * 
     * Space complexity: O(n^2) where n is the number of elements in 'pq'(instance variable).
     * Because it will dequeue 2 nodes from'pq' and create a new internal node. The number of creation of new node will be 2n-1 where n is the number of elements in 'pq'(instance variable).
     * And space complexity of dequeue and enqueue operations are both O(k) where k is the size of the 'pq'(instance variable).
     * So overall it is O(n^2).
     */
	private void buildHuffmanTree(){
		if(((MinHeap)pq).size ==1 && ((MinHeap)pq).first.left == null && ((MinHeap)pq).first.right == null ){
			addCode(((MinHeap)pq).first, "0");
			root = ((MinHeap)pq).first;
			return;
		}
		Node left = pq.dequeue();
		if(((MinHeap)pq).isEmpty() == true){ 
			pq.enqueue(left);
			return;
		}
		Node right = pq.dequeue();
		root = new Node(-1, left.frequency+right.frequency);

		root.left = left;
		addCode(left, "0");
		
		root.right = right;
		addCode(right, "1");

		pq.enqueue(root);
		buildHuffmanTree();
	}

    /*
     * addCode method is to add String 'a'(which is given through the parameter) into the leaf Node's code of the subtree which the root Node is 'node' that is also given through the parameter.
     * 
     * Recursive case(1): calling the method itself but passing the left child of node into the parameter.
     * Recursive case(2): calling the method itself but passing the right child of node into the parameter.
     * Base case: when the node is a leaf Node, that node.code is reassignied by adding the String "a" in front of the original node.code.
     * 
     * Time complexity: O(n) where n is the number of node of the binary tree which the root is node that given trhough the parameter.
     * The recursive calls will be made for each node in the tree exactly once. 
     * Thus, for a binary tree with n nodes, each node will be visited once.
     * 
     * Space complexity: O(n) where n is the number of nodes in tree. 
     * The space is used by the recursive calls. 
     * In the worst case, the depth of the recursion will be equal to the height of the tree which is O(n) where n is the number of nodes in tree. 
     */
	private void addCode(Node node, String a){
		String nodeCode = node.code;
		if(node.left == null && node.right == null){
			node.code = a + nodeCode.substring(0);
			return;
		}
		addCode(node.left, a);
		addCode(node.right, a);
	}
    /*
     * asciiAndCode method is to return a ArrayList that contains all the leaf Nodes of the Huffman tree which the root Node is in pq(instance variable).
     * to collect all the leaf Nodes, the method will call the collectLeafNodes passing tempNode(which is the root) and ArrayList 'asciiAndCode' which is created to the parameter.
     * 
     * asciiAndCode method's time and space complexity is same with collectLeafNodes method's time and space complexity.
     * Time complexity: O(n). where n is the number of nodes in the tree. Root Node of the tree is 'root'(instance variable).
     * Space complexity: O(n). where n is the number of nodes in the tree. Root Node of the tree is 'root'(instance variable).
     */

	private ArrayList asciiAndCode(){
		ArrayList asciiAndCode = new ArrayList();
		Node tempNode = root;
		collectLeafNodes(tempNode, asciiAndCode);
		return asciiAndCode;
	}
    /*
     * collectLeafNodes method is to collect all the leaf Nodes in the tree by the root Node of the tree('tempNode') which is given through the parameter 
     * and store it to the ArrayList 'asciiAndCode' which is also given through the parameter.
     * 
     * Recursive case(1): calling the method itself but passing the left child of 'tempNode' into the parameter.
     * Recursive case(2): calling the method itself but passing the left child of 'tempNode' into the parameter.
     * Base case: when the tempNode is null, the recursion will stop.
     * 
     * Time complexity: O(n) where n is the number of nodes in the tree which the root of the tree is given through the parameter.
     * Since each node in the tree is visited once. 
     * 
     * Space complexity: O(n) where n is the number of nodes in the tree which the root of the tree is given through the parameter. 
     * Because in the worst case, the maximum depth of the recursion is determined by the height of the tree. Enqueing has the space complexity of  O(n)
     */
	private void collectLeafNodes(Node tempNode, ArrayList asciiAndCode){
		if(tempNode == null){
			return;
		}
		if(tempNode.left == null && tempNode.right == null){ //when it meets the condition of the if statement, it will eventually trigger the base case since the current tempNode is a leaf Node.
			asciiAndCode.enqueue(tempNode);
		}
		collectLeafNodes(tempNode.left, asciiAndCode);
		collectLeafNodes(tempNode.right, asciiAndCode);
	}
	/*
	 * Perform decoding of the binary string 'code' using the Huffman tree represented by 'this.root'.
	 * This method should return a null in case the given code cannot be decoded.
	 * (e.g., error in code, or Huffman tree doesn't exist)
	 */
    /*
     * Time complexity: O(n) where n is the length of the 'code' given through the parameter.
     * Since it will loop length of the parameter 'code' times and implementation inside the for-loop is constant time since it has simple assignments.
     * 
     * Space complexity: O(n) where n is the length of the 'code' string given through the parameter.
     * Because the local variable 'originalWord' which will be returned in the end can be up to the length of n characters in the worst case.
     */
	public String decode(String code) {
		// System.out.println(code.length()); //37
		Node tempRoot = root;
		String originalWord= "";
		for(int i=0; i<code.length(); i++){
			if(tempRoot.left == null && tempRoot.right == null){
				originalWord += (char)(tempRoot.data);
				continue;
			}
			if(i == code.length()-1 && (tempRoot.left.data == -1||tempRoot.left.data == -1)){
				return null;
			}
			if(code.charAt(i)=='0'){
				tempRoot = tempRoot.left;
				if(tempRoot.data != -1){
					originalWord += (char)(tempRoot.data);
					tempRoot = root;
				}
			}
			else if (code.charAt(i)=='1'){
				tempRoot = tempRoot.right;
				if(tempRoot.data != -1){
					originalWord += (char)(tempRoot.data);
					tempRoot = root;
				}
			}
		}
		return originalWord;
	}
	
	/*
	 * The following two methods are just for testing purposes, and you do not have to use them in your implementation.
	 * You can use these to see what the binary representation of the original string looks like.
	 * It's probably useless in this assignment, but just in case you're curious....
	 */
	public String toBinary(String s) {
		String ret = "";
		for(int i = 0; i < s.length(); i++) 
			ret = toBinary(s.charAt(i)) + ret;
		return ret;
	}
	
	private String toBinary(int ch) {
		ch = 0xFFFF & ch; // Just want to deal with char's
		String ret = "";
		for(int i = 0; i < 16; i++) {
			ret = (ch & 1) + ret;
			ch = ch >> 1;
		}
		return ret;
	}
	//////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		Huffman h = new Huffman();
		//String msg = "Hello ";
		//String msg = "Hello world!";
		//String msg = "There is a pleasure in philosophy, and a lure even in the mirages of metaphysics, which every student feels until the coarse necessities of physical existence drag him from the heights of thought into the mart of economic strife and gain.";
		String msg = "She sells sea shells by the sea shore.\nThe shells she sells are seashells, I\'m sure.\nAnd if she sells seashells on the seashore\nThen I\'m sure she sells seashells.";
		//String msg = "And I shall have some peace there, for peace comes dropping slow, Dropping from the veils of the morning to where the cricket sings; There midnight\'s all a glimmer, and noon a purple glow, And evening full of the linnet\'s wings.";
		//String msg = "Paying anything to roll the dice, just one more time. Some will win, some will lose, some are born to sing the blues. Oh the movie never ends it goes on and on and on and on.";
		//String msg = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		//String msg = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		//String msg = "azazazazazazazazazazazazazazazazazazazazazazazazazazazazazazazazaz";
		// System.out.println("Original binary:\n" + h.toBinary(msg));
		// String code = h.encode(msg);
		// System.out.println("Compressed binary:\n" + code);
		// assert(h.decode(code).equals(msg)); // Original message should be reconstructed.
		// assert(code.length() < msg.length() * 16); // Code should be compressed.
		String code = h.encode(msg);
		System.out.println(code);

		String origin = h.decode(code);
		System.out.println(origin);
	}
}

class Node implements Comparable<Node>{
	Node left, right;
	int data;
	int frequency;
	String code;

    /*
     * The space complexity of the constructor of Node class is O(1).
     * Because the space taken by references, integers, empty String can be considered O(1).
     * Constrcuctor assigns values to the instance variables, and this process takes constant time.
     * So, the time complexity is also O(1).
     */
	public Node(int data, int frequency){
		left= null;
		right = null;
		this.data = data;
		this.frequency = frequency;
		code = "";
	}
    /*
     * This method is to compare the frequency of each node.
     * The compareTo method has the constant space complexity since it does not allocate 
     * any additional memory for new objects or data structures.
     * The time complexity is O(1) since the implementation of the methods is just comparison assignments(3 comparisons at most). 
     */
	public int compareTo(Node o) {
		if(frequency>o.frequency){
			return 1;
		}
		if(frequency<o.frequency){
			return -1;
		}
		else{
			return 0;
		}
    }
}

/*
 * Provide your priority queue implementation here.
 */
/////////////MinHeap is a heap based priority queue///////////////////////
class MinHeap implements Queueable<Node>{
    Node[] minHeap;
    Node first;
	int size;

    /*
     * This class is made to be a min heap based priority queue.
     * 
     * Time complexity: O(1). Assigning the variable 'size' to 0 takes constant time.
     * Space complexity: O(1). The space taken by integer ('size' variable) is constant.
     */
	public MinHeap(){
		size = 0;
	}
    /* 
     * getParent method is to retreive the parent Node by the index(idx) of the child Node(itself).
     * 
     * Time complexity: O(1). simple comparison and accessing an element in an array by index is constant time.
     * Space complexity: O(1). No additional space is allocated in this method.
     */
	public Node getParent(int idx){
		if(idx == 0){
			return null;
		}
		return minHeap[(idx-1)/2];
	}
    /* 
     * getLeftChild method is to retreive the left child Node by the index(idx) of the parent Node(itself).
     * 
     * Time complexity: O(1). simple comparison and accessing an element in an array by index is constant time.
     * Space complexity: O(1). No additional space is allocated in this method.
     */
	public Node getLeftChild(int idx){
		if((2*idx)+1 >= minHeap.length){
			return null;
		}
		return minHeap[(2*idx)+1];
	}
    /* 
     * getRightChild method is to retreive the right child Node by the index(idx) of the parent Node(itself).
     * 
     * Time complexity: O(1). simple comparison and accessing an element in an array by index is constant time.
     * Space complexity: O(1). No additional space is allocated in this method.
     */
	public Node getRightChild(int idx){
		if((2*idx)+2 >= minHeap.length){
			return null;
		}
		return minHeap[(2*idx)+2];
	}
    /*
     * enqueue method is to add new Node that is given through the parameter to the minHeap array which is the instance variable.
     * It will add new Node to the last of the minHeap after growing. 
     * Since minHeap(instance variable) has to maintain the form of min-heap, it will call percolateUp method.
     * 
     * Time compexity: O(n) where n is the number of elements(Nodes) in minHeap(instance variable) before it adds the new Node 'node'.
     * Because copying the elements from the old array('temp') to the new array('minHeap') takes O(n) time where n is the number of elements in the heap.
     * The percolateUp method that enqueue method calls takes O(logn) time in the worst case where n represents the number of elements in the heap.
     * However, the array copying dominates the percolateUp operation, which the overall time complexity of enqueue is O(n). 
     * 
     * Space compexity: O(n) where n is the number of elements(Nodes) in minHeap(instance variable). 
     * Because array('minHeap') of size n+1 is created to add one more Node. 
     */
	public void enqueue(Node node){
		if(first == null){
			minHeap = new Node[1];
			minHeap[0] = node;
			first = minHeap[0];
			size++;
			return;
		}
		Node[] temp = minHeap;
		int beforeLength = minHeap.length;
		minHeap = new Node[beforeLength+1];
		
		for(int i=0; i<temp.length; i++){
			minHeap[i] = temp[i];
		}
		minHeap[minHeap.length-1] = node;
		percolateUp(minHeap, beforeLength);
		first = minHeap[0];
		size++;
	}
    /*
     * percolateUp method is to maintain the min heap form when the new Node is add to the min heap.
     *
     * Time complexity: O(logn) where n is the number of elements(Nodes) in minHeap. 
     * In the worst case, it traverses the height of the heap, which is logn where n is the number of elements(Nodes) in minHeap
     * so that the time complexity is uppperbounded to logn.
     * It calls the swapWithParent, compareTo, getParent method. 
     * However, they all have the constant time complexity which does not affect the time complexity of pecolateUp method.
     *  
     * Space complexity: O(1). No additional space is allocated in this method.  
     */
	public void percolateUp(Node[] minHeap, int idx){
		while (minHeap[idx].compareTo(getParent(idx)) == -1){
			swapWithParent(minHeap, idx);
			idx = (idx-1)/2;
			if(idx<=0){
				return;
			}
		}
	}
    /*
     * swapWithParent method is to swap parent Nodes with the Node(itself) inside the heap.
     * 
     * Time complexity: O(1). The implemetation is just simple assignments which takes constant time.
     * Space complexity: O(1). No additional space is allocated in this method.
     */
	public void swapWithParent(Node[] heap, int idx){
		if(idx == 0){
			return;
		}
		Node temp = heap[(idx-1)/2];
		heap[(idx-1)/2] = heap[idx];
		heap[idx] = temp;
	}
    /*
     * swap method is to swap two Nodes in the heap.
     * 
     * Time complexity: O(1). The implemetation is just simple assignments which takes constant time.
     * Space complexity: O(1). No additional space is allocated in this method.
     */
	public void swap(Node[] heap, int idx1, int idx2){
		Node temp = heap[idx2];
		heap[idx2] = heap[idx1];
		heap[idx1] = temp;
	}
     /*
     * dequeue method is to remove the first element(Node) from the minHeap array which is the instance variable.
     * Since minHeap(instance variable) has to maintain the form of min-heap, it will call percolateDown method.
     * 
     * Time compexity: O(n) where n is the number of elements(Nodes) in minHeap(instance variable). 
     * Because copying the elements from the old array('temp') to the new array('minHeap') takes O(n) time where n is the number of elements in the heap.
     * The percolateDown method that dequeue method calls takes O(logn) time in the worst case where n represents the number of elements in the heap.
     * And the swap method that dequeue method calls takes constant time.
     * However, the array copying dominates the percolateDown and swap operation, which the overall time complexity of dequeue is O(n). 
     * 
     * Space compexity: O(n) where n is the number of elements(Nodes) in minHeap(instance variable). 
     * Because array('minHeap') of size n-1 is created. 
     */
	public Node dequeue(){
		if(first == null){
			throw new IndexOutOfBoundsException();
		}
		Node[] temp = minHeap;
		Node removed = minHeap[0];
		int beforeLength = minHeap.length;

		if(beforeLength == 1){
			minHeap = null;
			first = null;
			size--;
			return removed;
		}
		else if(beforeLength == 2){
			minHeap = new Node[1];
			minHeap[0] = temp[1];
			first = minHeap[0];
			size--;
			return removed;
		}
		swap(minHeap, 0, beforeLength-1);
		minHeap = new Node[beforeLength-1];
		for(int i=0; i<minHeap.length; i++){
			minHeap[i] = temp[i];
		}
		percolateDown(minHeap, 0);
		first = minHeap[0];
		size--;
		return removed;
		
	}
    /*
     * percolateDown method is to maintain the min heap form when dequeue happens.
     * Since when dequeue happens, it will swap the first and the last Node in minHeap and remove the last Node.
     * However, then the first Node of minHeap has the low priority. 
     * Thus, the process of placing the swapped element (first Node) into the right place is needed. 
     *
     * Time complexity: O(logn) where n is the number of elements(Nodes) in minHeap. 
     * In the worst case, it traverses the height of the heap, which is logn where n is the number of elements(Nodes) in minHeap 
     * so that the time complexity is uppperbounded to logn.
     * It calls the swap, compareTo, getLeftChild, getRightChild method. 
     * However, they all have the constant time complexity which does not affect the time complexity of pecolateDown method.
     *  
     * Space complexity: O(1). No additional space is allocated in this method.  
     */

	public void percolateDown(Node[] minHeap, int idx){ 
		if((2*idx)+1>=minHeap.length && (2*idx)+2>=minHeap.length){
			return; //left and right child 둘 다 없을 때
		}
		else if((2*idx)+2>=minHeap.length && (2*idx)+1<minHeap.length ){ //only has left child 일 때
			if(minHeap[idx].compareTo(getLeftChild(idx))==1){
				swap(minHeap, idx, (2*idx)+1);
				return; 
			}
		}
		else{
			while (minHeap[idx].compareTo(getLeftChild(idx))==1 ){ //minHeap[idx].compareTo(getRightChild(idx))==1 
				if(getLeftChild(idx).compareTo(getRightChild(idx)) == 1 || getLeftChild(idx).compareTo(getRightChild(idx)) == 0){
					swap(minHeap, idx, (2*idx)+2);
					idx = (2*idx)+2;
					if((2*idx)+1>=minHeap.length && (2*idx)+2>=minHeap.length){
						return;
					}
					else if((2*idx)+2>=minHeap.length && (2*idx)+1<minHeap.length ){
						if(minHeap[idx].compareTo(getLeftChild(idx))==1){
							swap(minHeap, idx, (2*idx)+1);
							return;
						}
					}
				}
				else{
					swap(minHeap, idx, (2*idx)+1);
					idx = (2*idx)+1;
					if((2*idx)+1>=minHeap.length && (2*idx)+2>=minHeap.length){
						return;
					}
					else if((2*idx)+2>=minHeap.length && (2*idx)+1<minHeap.length ){
						if(minHeap[idx].compareTo(getLeftChild(idx))==1){
							swap(minHeap, idx, (2*idx)+1);
							return;
						}
					}
	
				}
			}

		}
		
	}
    /*
     * isEmpty method is to check whether the minHeap is empty or not.
     * 
     * Time complexity: O(1). simple comparison takes constant time.
     * Space complexity: O(1). No additional space is allocated in this method. 
     */
	public boolean isEmpty(){
		if(first == null){
			return true;
		}
		return false;
	}
}


class ArrayList {
	Node[] nodeArr;
	Node first;

    /*
     * class ArrayList can use array like arraylist. 
     * However, in this assignment, I will only use when adding element to last. so I only implement that.
     * 
     * This is the constructor of the class ArrayList.
     * 
     * Time complexity: O(1). The constructor doesn't perform any operations.
     * Space complexity: O(1). No additional space is allocated in this constructor method.
     */
	public ArrayList(){
	}
    /*
     * enqueue method in class ArrayList is to add new Node that is given through the parameter to the nodeArr array which is the instance variable.
     * It will add new Node to the last of the nodeArr after growing. 
     * 
     * Time complexity: O(n) where n is the number of elements in instance variable 'nodeArr'.
     * Because it has to copy the existing elements to the new array.
     * Space complexity: O(n) where n is the current number of elements in instance variable 'nodeArr'.
     * Because array('nodeArr') of size n+1 is created to add one more Node. 
     */
	public void enqueue(Node node){
		if(first == null){
			nodeArr = new Node[1];
			nodeArr[0]= node;
			first = nodeArr[0];
			return;
		}
		
		Node[] temp = nodeArr;
		int beforeLength = nodeArr.length;
		nodeArr = new Node[beforeLength+1];

        ///////revised
		for(int i=0; i<temp.length; i++){
			nodeArr[i] = temp[i];
		}
        nodeArr[nodeArr.length-1] = node;


		// int idx = 0;
		// for(int i=0; i<temp.length; i++){ //find the index to add
		// 	if(node.compareTo(temp[i]) ==1 || node.compareTo(temp[i]) ==0){
		// 		if(i==temp.length-1){
		// 			idx = i+1;
		// 			break;
		// 		}
		// 		continue;
		// 	}
		// 	idx = i;
		// 	break;
		// }

		// for(int i=0, j=0; i<nodeArr.length; i++){
		// 	if(i == idx){
		// 		nodeArr[i] = node;
		// 		continue;
		// 	}
		// 	nodeArr[i] = temp[j++];
		// }
	}
    /*
     * length method is retrieving the length of the array 'nodeArr' which is the instance variable.
     *  
     * Time complexity: O(1). 
     * Space complexity: O(1). No additional space is allocated in this method.
     */
	public int length(){
		//System.out.println(nodeArr.length);
		return nodeArr.length;
	}
    /*
     * getNode method is to retrieve the Node which is the Node that is in index 'index'(given through the parameter) in array 'nodeArr'(instance variable).
     * 
     * Time complexity: O(1). Random access is constant time.
     * Space complexity: O(1). No additional space is allocated in this method.
     */
	public Node getNode(int index){
		return nodeArr[index];
	}
}

interface Queueable<E extends Comparable<E> > {
	public void enqueue(E obj);
	public E dequeue();
}


