
public class NSTest {

	public NSTest() {
		int score = 60;
		try { score -= testLL(); } catch(Exception e) { p("Exception in testGetSet()"); score -= 30; } // 10
		try { score -= testMerge(); } catch(Exception e) { p("Exception in testAddRowCol()"); score -= 30; } // 20
		try { score -= testToString(); } catch(Exception e) { p("Exception in testToString()");score -= 30; } // 10
		try { score -= testRemove(); } catch(Exception e) { p("Exception in testAL()");score -= 30; } // 20
		p("Correctness score: " + Math.max(0, score) + "/60");
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
			s += (b.headNum + "," + b.tailNum);
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
		if(!getStr(ns.getMain()).equals("5,24,10,1")) ded += 5;
		ns = new NumberSnake();
		try {
			ns.removeFirst();
			ded += 5;
		} catch(Exception e) {
		}
		ns.addFirst('1', '1');
		ns.removeLast();
		if(len(ns.getMain()) != 0) ded += 5; //여기다
		
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
	
	private int testMerge() { // mergeSnakes() and isValidSnake() 
		int ded = 0;
		NumberSnake ns = new NumberSnake();
		ns.addFirst('H', '1');
		ns.addLast('1', 'T');
		// The following represents a body sequence (1,2)-(2,3)-(3,4)-(4,1)
		//char[] chs = {'1', '2', '2', '3', '3', '4', '4', '1'};
		char[] chs = {'1', '2'};
		if(ns.mergeSnakes(chs)) ded += 10;
		if(!getStr(ns.getMain()).equals("H,11,T")){p(ns+""); ded += 10;}
		
		ns = new NumberSnake();
		ns.addFirst('H', '5');
		ns.addLast('5', '4');
		ns.addLast('4', '3');
		ns.addLast('3', 'T');
		char[] chs2 = {'4','0','0','5','5','4'};
		if(!ns.mergeSnakes(chs2)) ded += 10; // (H,5)-(5,4)-(4,0)-(0,5)-(5,4)-(4,3)-(3,T)
		if(!getStr(ns.getMain()).equals("H,55,44,00,55,44,33,T")) ded += 10;
		if(!ns.isValidSnake()) ded += 10;
				
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
		if(len(ns.getMain()) != 2) ded += 10;
		if(!getStr(ns.getMain()).equals("0,11,5")) ded += 10;
		
		ns = new NumberSnake();
		ns.addFirst('0', '1');
		ns.addLast('1', '2');
		ns.addLast('2', '3');
		ns.removeChunk();
		if(len(ns.getMain()) != 2) ded += 10;
		if(!getStr(ns.getMain()).equals("0,11,22,3")) ded += 10;
		
		ded = Math.min(ded, 20);
		p(ded + " point(s) deducted from testRemove()");
		return ded;
	}
	
	public static void p(String msg) { System.out.println(msg); }
	
	public static void main(String[] args) {
		new NSTest();
	}

}
