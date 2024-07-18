
public class RecNSTest {

	boolean rec; // test only isValidSnake, toString(), and getMaxValidSnake()
	
	public RecNSTest(boolean rec) {
		this.rec = rec;
		int score = rec ? 70 : 60;
		int maxScore = score;
		if(!rec) try { score -= testLL(); } catch(Exception e) { p("Exception in testGetSet()"); score -= 30; } // 10
		try { score -= testMerge(rec); } catch(Exception e) { p("Exception in testAddRowCol()"); score -= 35; } // 20 (10 for rec)
		try { score -= testToString(); } catch(Exception e) { p("Exception in testToString()");score -= 35; } // 10
		if(!rec) try { score -= testRemove(); } catch(Exception e) { p("Exception in testAL()");score -= 30; } // 20
		if(rec) try { score -= testMaxLength(); } catch(Exception e) { p("Exception in testMaxLength()");score -= 35; } // 50
		p("Correctness score: " + Math.max(0, score) + "/" + maxScore);
	}
	
	private int testMaxLength() {
		int ded = 0;
		Body[] b = {new Body('T', '1', null), new Body('T', '2', null), new Body('1', '2', null), new Body('2', '3', null), 
				new Body('2', 'H', null), new Body('3', '4', null), new Body('4', '5', null), new Body('5', 'H', null), 
				new Body('2', '5', null), new Body('5', '6', null), new Body('6', '7', null), new Body('7', '8', null),
				new Body('1', '9', null), new Body('8', '9', null), new Body('9', 'H', null), new Body('0', '1', null)};
		Body[] b2 = {new Body('H', '1', null), new Body('1', 'T', null), new Body('1', '2', null), new Body('2', '3', null), 
				new Body('3', '4', null), new Body('4', '5', null), new Body('5', '6', null), new Body('6', '7', null), new Body('7', '8', null),
				new Body('8', '9', null), new Body('9', 'H', null)};
		NumberSnake ns = new NumberSnake();
		Body head = ns.getMaxValidSnake(b); // Should be T-1-2-3-4-5-6-7-8-9-H
		if(!getStr(head).toUpperCase().equals("T112233445566778899H")) ded += 25;
		head = ns.getMaxValidSnake(b2); // Should be H-1-T
		if(!getStr(head).toUpperCase().equals("H11T")) ded += 25;
		return ded;
	}
	
	private int len(Body b) {
		int l = 0;
		while(b != null) {
			l++;
			b = b.next;
		}
		return l;
	}
	
	private String getStr(Body b) {
		String s = "";
		while(b != null) {
			s += (b.headNum + "" + b.tailNum);
			b = b.next;
		}
		return s;
	}
	
	private int testLL() { // Test the usual LL functionalities
		NumberSnake ns = new NumberSnake();
		int ded = 0;
		ns.addFirst('4', '1');
		ns.addFirst('5', '2');
		ns.addLast('0', '1');
		if(len(ns.getMain()) != 3) ded += 5;
		if(!getStr(ns.getMain()).equals("524101")) ded += 5;
		ns = new NumberSnake();
		try {
			ns.removeFirst();
			ded += 5;
		} catch(Exception e) {
		}
		ns.addFirst('1', '1');
		ns.removeLast();
		if(len(ns.getMain()) != 0) ded += 5;
		
		ded = Math.min(ded, 10);
		p(ded + " point(s) deducted from testLL()");
		return ded; //Math.min(10, ded);
	}
	
	private int testToString() { // .toString() 
		int ded = 0;
		NumberSnake ns = new NumberSnake();
		ns.addFirst('T', '2');
		ns.addLast('2', 'H');
		ns.addFirst('2', '5');
		if(!(ns + "").equals("(2,5)-(T,2)-(2,H)")) ded += 5;
		ns = new NumberSnake();
		ns.addLast('3', '5');
		if(!(ns + "").equals("(3,5)")) ded += 5;
		
		ded = Math.min(ded, 10);
		p(ded + " point(s) deducted from testToString()");
		return ded;
	}
	
	private int testMerge(boolean rec) { // mergeSnakes() and isValidSnake() 
		int ded = 0;
		NumberSnake ns = new NumberSnake();
		ns.addFirst('H', '1');
		ns.addLast('1', 'T');
		if(!ns.isValidSnake()) ded += 10;
		if(!rec) {
			// The following represents a body sequence (1,2)-(2,3)-(3,4)-(4,1)
			//char[] chs = {'1', '2', '2', '3', '3', '4', '4', '1'};
			char[] chs = {'1', '2'};
			if(ns.mergeSnakes(chs)) ded += 10;
			if(!getStr(ns.getMain()).equals("H11T")){p(ns+""); ded += 10;}
			
			ns = new NumberSnake();
			ns.addFirst('H', '5');
			ns.addLast('5', '4');
			ns.addLast('4', '3');
			ns.addLast('3', 'T');
			char[] chs2 = {'4','0','0','5','5','4'};
			if(!ns.mergeSnakes(chs2)) ded += 10; // (H,5)-(5,4)-(4,0)-(0,5)-(5,4)-(4,3)-(3,T)
			if(!getStr(ns.getMain()).equals("H554400554433T")) ded += 10;
			if(!ns.isValidSnake()) ded += 10;
		}
		ded = Math.min(ded, 20);
		p(ded + " point(s) deducted from testMerge()");
		return ded;
	}
	
	private int testRemove() { // removeChunk()
		int ded = 0;
		NumberSnake ns = new NumberSnake();
		ns.addFirst('0', '1');
		ns.addLast('1', '2'); // Remove
		ns.addLast('2', '3'); // Remove
		ns.addLast('3', '1'); // Remove
		ns.addLast('1', '5');
		ns.removeChunk();
		//if(len(ns.getMain()) != 2) ded += 10;
		if(!getStr(ns.getMain()).equals("0115")) ded += 10;
		
		ns = new NumberSnake();
		ns.addFirst('0', '1');
		ns.addLast('1', '2');
		ns.addLast('2', '3');
		ns.removeChunk();
		//if(len(ns.getMain()) != 2) ded += 10;
		if(!getStr(ns.getMain()).equals("011223")) ded += 10;
		
		ded = Math.min(ded, 20);
		p(ded + " point(s) deducted from testRemove()");
		return ded;
	}
	
	public static void p(String msg) { System.out.println(msg); }
	
	public static void main(String[] args) {
		new RecNSTest(true);
	}

}
