
public class GridGameTest {

	public static void p(String s) { System.err.println(s); }

	public GridGameTest() {
		int score = 60;
		try { score -= testInsert(); } catch(Exception e) {p("Exception in testInsert()"); score -= 20;}
		try { score -= testRemoval(); } catch(Exception e) {p("Exception in testRemoval()"); score -= 20;}
		try { score -= testMove(); } catch(Exception e) {p("Exception in testMove()"); score -= 20;}
		try { score -= testRetrieval(); } catch(Exception e) {p("Exception in testRetrieval()"); score -= 20;}
		p("Correctness score: " + Math.max(score, 0) + "/60");
	}
	
	/*
	 * Test insertions
	 */
	public int testInsert() {
		GridGame g = new GridGame(2, 2); // Build a 2x2 grid
		int ded = 0;
		if(!g.spawnPlayer("Homer Simpson", 0, 1)) ded += 5;
		if(g.spawnPlayer("Marjorie Bouvier", 0, 1)) ded += 5; // error
		if(g.spawnPlayer("Homer Simpson", 1, 1)) ded += 5;    // error
		if(!g.spawnPlayer("Gerald Smith", 0, 0)) ded += 5;
		if(g.spawnPlayer("Richard Sanchez", 5, 2)) ded += 5;  // error
		p(ded + " point(s) deducted from testInsert()");
		return ded;
	}
	
	/*
	 * Test removals.
	 */
	public int testRemoval() {
		GridGame g = new GridGame(2, 5); // Build a 2x5 grid
		int ded = 0;
		g.spawnPlayer("Meeseeks", 0, 0);
		if(!g.removeAt(0, 0)) ded += 5;
		if(g.removeAt(0, 0)) ded += 5;
		g.spawnPlayer("Mario", 1, 1);
		if(!g.removePlayer("Mario")) ded += 5;
		p(ded + " point(s) deducted from testRemoval()");
		return ded;
	}
	
	public int testMove() {
		GridGame g = new GridGame(5, 5);
		int ded = 0;
		g.spawnPlayer("A", 0, 0);
		g.spawnPlayer("B", 1, 0);
		if(!"B".equals(g.moveTo("A", 1, 0))) ded += 5;
		g.spawnPlayer("C", 0, 4);
		g.spawnPlayer("D", 1, 1);
		if(g.moveTo("C", 0, 3) != null) ded += 5;
		if(g.moveTo("C", 1, 1) != null) { p("here"); ded += 5;}
		//if(g.moveTo("B", 0, 1) != null) ded += 5;
		g.spawnPlayer("E", 2, 0);
		g.spawnPlayer("F", 2, 4);
		if(!"F".equals(g.moveTo("E", 2, 4))) { p("Blah"); ded += 5;}
		
		g.spawnPlayer("G", 0, 2);
		g.spawnPlayer("H", 4, 2);
		if(!"H".equals(g.moveTo("G", 4, 2))) { p("foo"); ded += 5;}
		p(ded + " point(s) deducted from testMove()");
		return ded;
	}
	
	/*
	 * Test the rest...
	 */
	public int testRetrieval() {
		GridGame g = new GridGame(5, 5); 
		int ded = 0;
		String[] all = g.getAllPlayers();
		if(all != null) ded += 5;
		String[] names = {"Bender Rodriguez", "Nibbler", "Leela"};
		for(int i = 0; i < names.length; i++)
			g.spawnPlayer(names[i], i, i + 1);
		
		all = g.getAllPlayers();
		Location[] locs = g.getAllOccupiedGrids();
		if(all.length != names.length) ded += 5;
		if(locs.length != names.length) ded += 5;
		for(int i = 0; i < all.length; i++)
			if(!contains(names, all[i])) { p("asdf"); ded += 5;}
		
		for(Location l : locs)
			if(l.getCol() != l.getRow() + 1) { p("col: " + l.getCol() + ", row: " + l.getRow()); ded += 5;}
		
		g.spawnPlayer("Philip Fry", 0, 4);
		all = g.getAllPlayers();
		if(!contains(all, "Philip Fry")) ded += 5;
		if(!"Philip Fry".equals(g.getPlayerAt(0, 4))) ded += 5;
		
		Location l = g.whereIs("Nibbler");
		if(l.getRow() != 1 || l.getCol() != 2) ded += 5;
		g.moveTo("Philip Fry", 0, 3);
		l = g.whereIs("Philip Fry");
		if(l.getRow() != 0 || l.getCol() != 3) ded += 5;
		g.moveTo("Leela", 1, 3); // (2,3) -> (1,3)
		g.moveTo("Leela", 1, 2); // (1,3) -> (1,2) && takes Nibbler
		if(g.whereIs("Nibbler") != null) ded += 5;
		p(ded + " point(s) deducted from testRetrieval()");
		return ded;
	}
	
	private boolean contains(String[] n, String t) {
		for(String s : n)
			if(t.equals(s)) return true;
		return false;
	}
	
	public static void main(String[] args) {
		new GridGameTest();
	}

}
