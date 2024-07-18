import java.util.Iterator;
import java.util.Set;

/*
 * You will be implementing the weighted undirected graph data structure for this assignment. See
Connectable.java for the interface specification that you should implement. Part of the assignment
is understanding the behaviors of the methods given in the Connectable interface, so carefully plan
on how you would implement them. You are free to use any data structures we learned in class as
long as they’re provided by Java’s Collection framework. In case you need to build new classes
and/or declare other methods, feel free to do so.
 */
/*
 * We will use an Integer type to represent each node's ID.
 */
interface Connectable {
	public Set<Integer> nodeSet(); // return the set of nodes
	public Set<Integer> getNeighbors(int node); // return the set of neighbors connected to 'node'
	
	public Iterator<Integer> breadthFirstIterator(int src); // return the iterator on nodes in a breadth-first manner
	public Iterator<Integer> depthFirstIterator(int src); // same as above in a depth-first manner
	
	public void addNode(int node); // add a new vertex into the current graph.
	public boolean addEdge(int source, int target, double w); // add a new edge. also add non-existing nodes. return false if edge already exists. 
	public boolean removeNode(int node); // remove node. return false if node doesn't exist.
	public boolean removeEdge(int source, int target); // remove edge. return false if edge doesn't exist.
	public boolean isEdge(int source, int target); // Returns true if source-target is a valid edge. The source-target order doesn't matter in undirected graphs.
	
	public double getWeight(int source, int target); // return weight of edge (what if edge doesn't exist?).
	public boolean setWeight(int source, int target, double w); // set the weight. existing weights are overwritten. if edge doesn't exist, return false;
	public int numNodes(); // return the total number of nodes. should have O(1) time complexity.
	public int numEdges(); // return the total number of edges. should have O(1) time complexity.
	
	public Connectable getMST(); // return a minimum spanning tree using either Prim's or Kruskal's algorithm.
}
