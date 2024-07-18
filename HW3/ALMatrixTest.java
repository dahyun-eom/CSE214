import java.util.ArrayList;

public class ALMatrixTest {

	public static void main(String[] args) {
		new ALMatrixTest();
	}
	
	public ALMatrixTest() {
		int score = 60;
		try { score -= testGetSet(); } catch(Exception e) { p("Exception in testGetSet()"); score -= 30; }
		try { score -= testAddRowCol(); } catch(Exception e) { p("Exception in testAddRowCol()"); score -= 30; }
		try { score -= testToString(); } catch(Exception e) { p("Exception in testToString()");score -= 30; }
		try { score -= testAL(); } catch(Exception e) { p("Exception in testAL()");score -= 30; }
		p("Correctness score: " + Math.max(0, score) + "/60");
	}
	
	private int testGetSet() { // 10 points 
		int ded = 0;
		ALMatrix m = new ALMatrix(5, 5, 1);
		if(m.getNumCols() != 5 || m.getNumRows() != 5) ded += 4;
		if(m.get(0, 0) != 1) ded += 4;
		if(m.set(0, 0, 0) != 1) ded += 4;
		if(m.set(0, 0, 4) != 0) ded += 4;
		try {
			m.get(5, 0);
			ded += 4;
		} catch(Exception e) {}
		try {
			m.set(0, 5, 3);
			ded += 4;
		} catch(Exception e) {}
		m.set(1, 2, 3);
		if(m.get(1, 2) != 3) ded += 4;
		
		ded = Math.min(ded, 10);
		p(ded + " point(s) deducted from testGetSet()");
		return ded;
	}
	
	private int testAddRowCol() { // 20 points
		int ded = 0;
		ALMatrix m = new ALMatrix (5, 5, 1);
		m.addColumns(2, 4, false);
		if(m.getNumCols() != 7 || m.getNumRows() != 5) ded += 4;
		try {
			if(m.get(0, 6) != 1) ded += 4;
			if(m.get(0, 0) != 4) ded += 4;
		} catch(Exception e) { ded += 4; }
		
		m = new ALMatrix(5, 5, 1);
		m.addColumns(2, 3, true);
		if(m.getNumCols() != 7 || m.getNumRows() != 5) ded += 4;
		try {
			if(m.get(0, 6) != 3) ded += 4;
			if(m.get(0, 0) != 1) ded += 4;
		} catch(Exception e) { ded += 4; }
		
		m = new ALMatrix(5, 5, 1);
		m.addRows(2, 3, false);
		if(m.getNumRows() != 7 || m.getNumCols() != 5) ded += 4;
		try {
			if(m.get(0, 0) != 3) ded += 4;
			if(m.get(6, 0) != 1) ded += 4;
		} catch(Exception e) { ded += 4; }
		
		m = new ALMatrix(5, 5, 1);
		m.addRows(2, 3, true);
		if(m.getNumRows() != 7 || m.getNumCols() != 5) ded += 4;
		try {
			if(m.get(0, 0) != 1) ded += 4;
			if(m.get(6, 0) != 3) ded += 4;
		} catch(Exception e) { ded += 4; }
		
		ded = Math.min(ded, 20);
		p(ded + " point(s) deducted from testAddRowCol()");
		return ded;
	}
	
	private int testToString() { // 10 points
		int ded = 0;
		ALMatrix m = new ALMatrix(2, 2, 1);
		m.set(0, 0, 0);
		m.set(1, 1, 0);
		String[] rows = (m + "").strip().split("\\n"); // {"0 1", "1 0"}
		String[][] gt = {{"0", "1"}, {"1", "0"}};
		for(int i = 0; i < rows.length; i++) {
			String[] toks = rows[i].split("\\s");
			if(toks.length != gt[i].length) { ded = 10; break; }
			for(int j = 0; j < toks.length; j++) {
				if(!toks[j].equals(gt[i][j])) ded += 2; // 2 points are free
			}
		}
		ded = Math.min(ded, 10);
		p(ded + " point(s) deducted from testToString()");
		return ded;
	}
	
	private int testAL() { // 20 points	
		int ded = 0;
		ALMatrix m = new ALMatrix(2, 3, 1);
		m.set(1, 2, 5);
		m.set(0, 1, 2);
		/*
		 * 1 2 1
		 * 1 1 5
		 */
		ArrayList<Integer> arr = m.getAL();
		int[] gt = {1, 2, 1, 1, 1, 5};
		if(arr.size() != 6) ded += 5;
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i) != gt[i]) ded += 5;
		}
		ded = Math.min(ded, 20);
		p(ded + " point(s) deducted from testAL()");
		return ded;
	}
	
	public static void p(String msg) { System.out.println(msg); }
}
