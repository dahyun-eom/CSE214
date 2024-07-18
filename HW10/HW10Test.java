import java.util.Set;
import java.util.Iterator;

public class HW10Test {
	HW10 hw;
	
	public HW10Test() {
		hw = new HW10();
		double total = 60;
        try{ 
            hw.addEdge(0, 1, 1);
            hw.addEdge(0, 2, 2);
            if(hw.addEdge(0, 1, 1)) {
                System.err.println("addEdge admits duplicate edge.");
                total -= 10;
            }
            hw.addEdge(2, 3, 3);
            hw.addEdge(4, 5, 4);
            hw.addEdge(2, 4, 5);
            hw.addEdge(1, 5, 6);
            hw.addEdge(3, 5, 7);
            hw.addEdge(2, 5, 1);
	    } catch(Exception e) {
            System.out.println("addEdge exception");
            total -= 20;
        }
        try{
            total -= testSets();
        } catch(Exception e) {
            System.out.println("Set exception");
            total -= 30;
        }

        try{        
            displayIterators();
        } catch(Exception e) {
            System.out.println("Iterator exception");
            total -= 30;
        }
        try{
            Connectable c = hw.getMST();
            if(c == null || !testMST(c)) {
                System.out.println("MST error: -10");
                total -= 10;
            }
            else{ System.out.println("OK"); }
        } catch(Exception e) {
            System.out.println("MST exception");
            total -= 30;
        }

        total = Math.max(0, total);
        System.out.println("Correctness: " + total);
		
	}
	
	/*
	 * Test nodeSet() and getNeighbors()
	 */
	public double testSets() {
        double ded = 0;
		Set<Integer> nodes = hw.nodeSet();
		Iterator<Integer> it = nodes.iterator();
		int[] neighbors = {3, 5, 12, 7, 7, 10};
		int sum = 15; // 0+1+2+3+4+5=15
		while(it.hasNext()) {
			int n = it.next();
			System.out.println(n);
			sum -= n;
			Set<Integer> s = hw.getNeighbors(n);
			Iterator<Integer> it2 = s.iterator();
			while(it2.hasNext()) {
				int n2 = it2.next();
				neighbors[n] -= n2; // not the best way to test nodes, but I'll be lenient 
			}
			if(neighbors[n] != 0) {
				System.err.println("Error in getNeighbors()");
                ded += 10;
			}
		}
		if(sum != 0) {
			System.err.println("Error in nodeSet()");
			ded += 10;
		}
		
        return ded;
	}
	
	public void displayIterators() {
		System.out.println("\tPrinting out DFS");
		Iterator<Integer> it = hw.depthFirstIterator(5);
		while(it.hasNext()) 
			System.out.print(it.next() + " ");
		
		System.out.println("\n\tPrinting out BFS");
		it = hw.breadthFirstIterator(5);
		while(it.hasNext())
			System.out.print(it.next() + " ");
        System.out.println();
	}
	
	public boolean testMST(Connectable mst) {
		try{
			if(mst.getWeight(0, 1) != 1) return false;
			if(mst.getWeight(0, 2) != 2) return false;
			if(mst.getWeight(2, 3) != 3) return false;
			if(mst.getWeight(4, 5) != 4) return false;
			if(mst.getWeight(2, 5) != 1) return false;
		} catch(Exception e) {
    		Iterator<Integer> it = mst.depthFirstIterator(0);
            System.err.println("\tDisplaying MST:");
            while(it.hasNext()) 
                System.out.print(it.next() + " ");
	        
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		new HW10Test();

	}

}
