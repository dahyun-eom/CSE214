/**
 * Name: Dahyun Eom
 * SBUID: 115943034
 * Do not use any unauthorized packages. 
 * The only place where you're allowed to use data structures is in the iterativeEval() method.
 * 
 * For all recursive methods, feel free to use helper methods.
 * However, your recursive implementations must not use any global variables other than 'root'.
 */
import java.util.*;
public class ParseTree {
	
	Node root; // You MUST use this tree to manage your expression.
	
	public ParseTree() {
	}
	
	private class Node {
		Node parent, left, right;
		// Add more necessary fields and methods
		String exp;

		/*
		 * This is the constructor of class Node.
		 * The constructor initialize the field exp to String exp which is given through the parameter.
		 * Also, it will assign the field parent, left, right set to null. 
		 */
		public Node(String exp) { // Modify the constructor as you see fit.
			this.exp = exp;
			parent = left = right = null;
		}
	}
	
	/*
	 * Build a parse tree, to be pointed by root, that represents 'expression'.
	 * Implement recursively.
	 */
	/*
	 * This method will firstly, instantiate root node of the tree with the help of findLatestOperation method.
	 * It will then instantiate leftNode and rightNode and link each node.
	 * After, it will call the aux method buildTreeAux by passing the root of the tree into the parameter. 
	 */
	public void buildTree(String expression) {
		int i = findLatestOperation(expression);
		root = new Node(String.valueOf(expression.charAt(i)));
		//root.exp = String.valueOf(expression.charAt(i));
		Node leftNode = new Node(expression.substring(0, i));
		Node rightNode = new Node(expression.substring(i+1));
		root.left = leftNode;
		leftNode.parent = root;
		root.right = rightNode;
		rightNode.parent = root;
		buildTreeAux(leftNode.exp, leftNode);
		buildTreeAux(rightNode.exp, rightNode);
	}

	/*
	 * The mehtod gets the last operator(which will be the parent) by the help of method findLatestOperation and 
	 * instantiate and assigning leftNode which exp is the lefthand of the operator and rightode which exp is the righthand of the operator.
	 * And then link node given through the parameter with rightNode and leftNode.
	 * 
	 * The base case is when the expression that is given through the parameter only contains number. 
	 * If it's the case, it will just return doing nothing. Because there are no more things to do.
	 * 
	 * The recursive case is when in all cases except when is the base case, after assigning exp of leftNode and rightNode with appropriate expression and 
	 * link the nodes appropriately, it will call the method itself by passing in the exp of lefNode and leftNode 
	 * and calling the method itself by passing in the exp of rightNode and rightNode.
	 */
	public void buildTreeAux(String expression, Node node){
		if(isOnlyNum(expression)){ 
			return;
		}
		int i = findLatestOperation(expression);

		node.exp = String.valueOf(expression.charAt(i));

		Node leftNode = new Node("");
		Node rightNode = new Node("");

		if(isInOneParentheses(expression)){
			int count =0;
			for(int k=0; k<expression.length(); k++){
				if(expression.charAt(k)=='(' || expression.charAt(k)==')' ){
					count++;
				}
			}
			if(count == 0){
				leftNode.exp = expression.substring(0, i);
				rightNode.exp = expression.substring(i+1);
			}
			else if(count == 2){
				leftNode.exp = expression.substring(1, i);
				rightNode.exp = expression.substring(i+1, expression.length()-1);
			}
		}
		
		else if(expression.charAt(0) == '(' && expression.charAt(i-1) == ')'){
			if(isInOneParentheses(expression.substring(0, i))==true &&isInOneParentheses(expression.substring(i+1))==true){
				leftNode.exp = expression.substring(1, i-1);
				rightNode.exp = expression.substring(i+2, expression.length()-1);
			}
			else{
				leftNode.exp = expression.substring(0, i);
				rightNode.exp = expression.substring(i+1);

			}
		}
		else{
			leftNode.exp = expression.substring(0, i);
			rightNode.exp = expression.substring(i+1);
		}
		
		node.left = leftNode;
		leftNode.parent = node;
		node.right = rightNode;
		rightNode.parent = node;
		//buildTreeAux(rightNode.exp, rightNode);
		buildTreeAux(leftNode.exp, leftNode);    
		buildTreeAux(rightNode.exp, rightNode);
		
	}

	/*
	 * This method is to check whether the given expression is only constitute of number such as ex. 2 or 12.
	 * If the given expression contains operators or parenthese, then it should return false. 
	 * Otherwise, return true.
	 */
	public boolean isOnlyNum(String expression){
		for(int i=0; i<expression.length(); i++){
			if(! (expression.charAt(i) == '1' || expression.charAt(i) == '2'|| expression.charAt(i) == '3'||  expression.charAt(i) == '4'|| expression.charAt(i) == '5'|| expression.charAt(i) == '6'||
			expression.charAt(i) == '7'|| expression.charAt(i) == '8'|| expression.charAt(i) == '9'|| expression.charAt(i) == '0')){
				return false;
			}
		}
		return true;
	}

	

	/*
	 * This method is to check whether is the expression is an expression of one parentheses such as ex. (1+2)
	 * or 1+2. If the expression is  like ex. 1+(2+3) (1+2(3+2)) it should return false.
	 */
	public boolean isInOneParentheses(String expression){
		if(expression.indexOf("(")>0 ){
			return false;
		}
		if(expression.indexOf(")")<expression.length()-1){
			return false;
		}
		return true;
	}

	/*
	 * This is the important helper method for the buildTree method. It will retrieve the index of the operator which will be the root.
	 * In other woeds, it will find out the last operation in the given expression.
	 * The recursive case is only used in the case when there is  /, * operator  after the the former *, / operator and the latter part of the expression also has other operation.
	 * ex. (2+3)*(2+3)/1+3 after * it has / and after /, also has + operator. 
	 * In this case, it will call itself but passing in the expression after the second * or / operator. 
	 * In above example, it will call the method itself passing in "1+3". 
	 * The base cases are the 
	 * 1. when the when the given index is one parenthese ex. (2+3)
	 * 2. when the operator is + or - that are not in the parentheses. 
	 * 3. the last * or / operator(it should not have other operator behind). ex. 3*4  or 2*(3+4)--> In this case, the + in the parenthese will not count. 
	 */

	public int findLatestOperation(String expression){ //it gives the index which will be the parent
		for(int i=0; i<expression.length(); i++){

			if(expression.charAt(i) == '('){
				int j=0;
				while (expression.charAt(i) != ')'){
					if(expression.charAt(i) == '+' || expression.charAt(i) == '-'){
						j = i;
					}
					i++;
				}
				if(i == expression.length()-1){
					return j;
				}
			}
		
			if(expression.charAt(i) == '+' || expression.charAt(i) == '-'){
				return i;
			}

			if(expression.charAt(i) == '*' || expression.charAt(i) == '/'){
				int res = i;
				i++;
				if(isOnlyNum(expression.substring(i))==true){
					return res;
				}
				if(expression.charAt(i) == '('){
					while(expression.charAt(i) != ')'){
						i++;
					}
					if(i == expression.length()-1){
						return res;
					}
					i++;
				}
				if(expression.charAt(i)== '*' || expression.charAt(i)== '/' || expression.charAt(i)== '+' || expression.charAt(i)== '-' ){
					
					if(isOnlyNum(expression.substring(i+1))==true){
						return i;
					}
					return i + 1 + findLatestOperation(expression.substring(i+1));
				}
			}
		}
		return -1; 
	}

	/*
	 * Evaluate the expression represented by 'root'.
	 */
	/*
	 * This eval() method will return the aux method, auxEval by passing the root of the tree into the parameter.
	 * Overall, this method will return the overall evaluation of the expression that is represented into parse tree. 
	 */
	public double eval() {
		return auxEval(root);
	}
	/*
	 * This method is to evaluate the String type expression that is represented into parse tree to the double type value.
	 * The recursion case is when the exp of the node is the operator, 
	 * then it will call the method itself by passing in the left childnode and do the actual operation with calling the method itself by passing in the right childnode .
	 * The base case is when the node is the leaf node where there are no children.
	 * In this case, it will return the exp of the node but coverted into a type double. 
	 */
	private double auxEval(Node node){
		if(node.left == null && node.right == null){
			return Double.parseDouble(node.exp);
		}
		if(node.exp.equals("+")){
			return auxEval(node.left) + auxEval(node.right);
		}
		if(node.exp.equals("/")){
			return auxEval(node.left) / auxEval(node.right);
		}
		if(node.exp.equals("*")){
			return auxEval(node.left) * auxEval(node.right);
		}
		if(node.exp.equals("-")){
			return auxEval(node.left) - auxEval(node.right);
		}
		return -1;
	}

	/*
	 * Return the original infix notation. You shouldn't just return the stored input string.
	 * Furthermore, the returned string shouldn't contain any superfluous parentheses.
	 * E.g., "((2+2))" is not allowed, although it's technically the same as "2+2".
	 */
	/*
	 * This toString() method will return the aux method, auxToString passing the root of the tree into the parameter. 
	 * Overall, this method will return the original infix expression from the parse tree.
	 */
	public String toString() {
		return auxToString(root);
	}
	/*
	 * This method is to print out infix expression from the tree. 
	 * The recursive case is when the exp of the node is the operation +, -, *, or /
	 * Then, it will call the method itself passing the left child node and add the current exp (+, -, * ,or /) and add the method itself by passing
	 * the right child node.
	 * Also, when the exp is + and -, if the node is not the root, it should add the parentheses too.
	 * 
	 * The base case of this method is when the node is the leaf node where there are no children. If it is the case, then it will return the exp of the node.
	 */
	private String auxToString(Node node){

		if(node.left == null && node.right == null){
			return node.exp;
		}

		if(node.exp.equals("-")){
			if(node == root){
				return auxToString(node.left)+"-"+auxToString(node.right);
			}
			return "("+auxToString(node.left)+"-"+auxToString(node.right)+")";
		}
		if(node.exp.equals("+")){
			if(node == root){
				return auxToString(node.left)+"+"+auxToString(node.right);
			}
			return "("+auxToString(node.left)+"+"+auxToString(node.right)+")";
		}
		if(node.exp.equals("*")){
			return auxToString(node.left)+"*"+auxToString(node.right);
		}
		if(node.exp.equals("/")){
			return auxToString(node.left)+"/"+auxToString(node.right);
		}
		return "";

	
	}
	
	/*
	 * Return the postfix version of the expression.
	 */
	/*
	 * The method will return the aux method, auxtoPostfixString passing the root of the tree into the parameter.
	 * Overall, it will return the postfix version of the expression said in the above todo.
	 */
	public String toPostfixString() { 
		return auxtoPostfixtring(root);
	}
	/* 
	 * This method is to print out the exp of the each node in tree with post-order.
	 * The recursive case is that it will call the method itself passing the left child node 
	 * and adding the call of itself passing the right child node and lastly adding the expression of the given node itself.
	 * The base case is that when the node is the leaf node, it will just return the expression of the node (node.exp). 
	 */
	private String auxtoPostfixtring(Node node){
		if(node.left == null && node.right == null){
			return node.exp;
		}
		return auxtoPostfixtring(node.left) + auxtoPostfixtring(node.right) + node.exp;
	}
	/*
	 * The main() method is provided only as a reference. 
	 * No need to fill this in.
	 */
	public static void main(String[] args) {
		//String exp = "(2-5)/(6+4)+1"; // Try many more expressions of your own
		//String exp = "((((4-5)/(5-3)+1)*6-1)/5+1)*1";
		//String exp = "1+1+1+1+1*2";
		//String exp = "(2-1)*(4-3)*(5-2)*(6-4)/2";
		//String exp = "(1-1-2-6)/2+2*1*2";
		//String exp = "1+1";
		String exp = "(1*2-3-5)/2-5";




		//String exp = "1*(2-4)/7+3";
		//String exp = "(2-4)*(6+9)";
		//String exp = "(1+(2-4))/7"; 

		ParseTree pt = new ParseTree();
		pt.buildTree(exp);
		//pt.printInorder(pt.root);
		//System.out.println();
		//System.out.println(pt.root.exp);

		System.out.println(pt.toString()); // Should print the same string.
		System.out.println(pt.toPostfixString()); // "25-64+/1+"
		System.out.println(pt.eval()); // 0.7, or 0.6999999999999, or something like that
	}

}
