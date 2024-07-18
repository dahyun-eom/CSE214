import java.util.*;

public class SampleHW10 {
	public void test(Connectable c) {
		c.addEdge(0, 1, 1);
		c.addEdge(0, 2, 2);
		c.addEdge(0, 3, 4);
		c.addEdge(0, 4, 10);
		c.addEdge(3, 4, 1);
		Iterator<Integer> it = c.breadthFirstIterator(0);
		while(it.hasNext())
			System.out.println(it.next() + " "); // e.g., 0-1-2-3-4
		it = c.depthFirstIterator(0);
		while(it.hasNext())
			System.out.println(it.next() + " "); // e.g., 0-3-4-2-1
		Connectable mst = c.getMST();
		if(mst.numEdges() != 4 || mst.isEdge(0, 4)) System.err.println("MST error");		
	}

}
