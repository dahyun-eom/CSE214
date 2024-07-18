import java.util.*;
public class HW10 {
    public static void main(String[] args) {
        // UndirectedGraph graph = new UndirectedGraph();
        
        // // Add nodes
        // graph.addNode(0);
        // graph.addNode(1);
        // graph.addNode(2);
        // graph.addNode(3);
        
        // // Add edges
        // graph.addEdge(0, 1, 4.0);
        // graph.addEdge(0, 2, 3.0);
        // graph.addEdge(1, 2, 2.0);
        // graph.addEdge(2, 3, 5.0);
        
        // // Test number of nodes and edges
        // System.out.println("Number of nodes: " + graph.numNodes()); // Expected: 4
        // System.out.println("Number of edges: " + graph.numEdges()); // Expected: 4
        
        // // Test getNeighbors
        // System.out.println("Neighbors of node 0: " + graph.getNeighbors(0)); // Expected: [1, 2]
        // System.out.println("Neighbors of node 2: " + graph.getNeighbors(2)); // Expected: [0, 1, 3]
        
        // // Test breadthFirstIterator
        // Iterator<Integer> bfsIterator = graph.breadthFirstIterator(0);
        // System.out.print("Breadth-first search starting from node 0: ");
        // while (bfsIterator.hasNext()) {
        //     System.out.print(bfsIterator.next() + " "); // Expected: 0 1 2 3
        // }
        // System.out.println();
        
        // // Test depthFirstIterator
        // Iterator<Integer> dfsIterator = graph.depthFirstIterator(0);
        // System.out.print("Depth-first search starting from node 0: ");
        // while (dfsIterator.hasNext()) {
        //     System.out.print(dfsIterator.next() + " "); // Expected: 0 2 3 1 (order may vary)
        // }
        // System.out.println();
        
        // // Test getWeight
        // System.out.println("Weight of edge 0-1: " + graph.getWeight(0, 1)); // Expected: 4.0
        // System.out.println("Weight of edge 2-3: " + graph.getWeight(2, 3)); // Expected: 5.0
        
        // // Test setWeight
        // graph.setWeight(0, 1, 10.0);
        // System.out.println("New weight of edge 0-1: " + graph.getWeight(0, 1)); // Expected: 10.0
        
        // // Test removeEdge
        // graph.removeEdge(0, 1);
        // System.out.println("Number of edges after removing edge 0-1: " + graph.numEdges()); // Expected: 3
        
        // // Test removeNode
        // graph.removeNode(2);
        // System.out.println("Number of nodes after removing node 2: " + graph.numNodes()); // Expected: 3
        // System.out.println("Number of edges after removing node 2: " + graph.numEdges()); // Expected: 1
        
        // // Test isEdge
        // System.out.println("Is there an edge between 0 and 1? " + graph.isEdge(0, 1)); // Expected: false
        // System.out.println("Is there an edge between 1 and 2? " + graph.isEdge(1, 2)); // Expected: false
    }
    
}

class UndirectedGraph implements Connectable {

    private Map<Integer, Map<Integer, Double>> graph;
    private Set<Integer> nodes;
    private int edgeCount;

    /* <constructor of class UndirectedGraph>
     * Time complexity: O(1) 
     * Initializing HashMap, HashSet and int is a O(1) operation.
     * 
     * Space complexity: O(1) 
     */
    public UndirectedGraph(){
        graph = new HashMap<>();
        nodes = new HashSet<>();
        edgeCount = 0;
    }
   
    /* <return the set of nodes>
     * Time complexity: O(1) 
     * Space complexity: O(1) 
     */
    public Set<Integer> nodeSet(){ 
        return nodes;
    }

    
    /* <return the set of neighbors connected to 'node'>
     * Time complexity: O(1) accessing HashMap 'graph' with key  and calling the method keySet() is constant time.
     * Space complexity: O(1) space used for variable 'res' is just a refernce to the set.
     */
	public Set<Integer> getNeighbors(int node){
        Set<Integer> res = graph.get(node).keySet();
        return res;
    }

    /* < return the iterator on nodes in a breadth-first manner >
     * Time complexity: O(n+e) where n is the number of nodes in the graph and e is the number of edges in the graph.
     * Because each nodes is added into the 'queue' and dequeued exactly once and each edge is checked at most twice.
     * 
     * Space complexity: O(n) where n is the number of nodes in the graph
     * In worst case when there are no edges, the 'queue' might need to store all the nodes 
     * + ArrayList 'visited' also stores all the nodes. 
     */
    public Iterator<Integer> breadthFirstIterator(int src){
        LinkedList<Integer> queue = new LinkedList();
        ArrayList<Integer> visited = new ArrayList<>();

        queue.addLast(src);
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            if(!visited.contains(node)){
                visited.add(node);
                Iterator<Integer> it = graph.get(node).keySet().iterator();
                while(it.hasNext()){
                    Integer neighbor = it.next();
                    if(!visited.contains(neighbor)){
                        queue.addLast(neighbor);
                    }
                }
            }
        }

        Iterator<Integer> it = visited.iterator();
        return it;
    }

    /* <same as above in a depth-first manner>
     * Time complexity:Time complexity: O(n+e) where n is the number of nodes in the graph and e is the number of edges in the graph.
     * Because each nodes is pushed into the stack and pop exactly once and each edge is checked at most twice.
     * 
     * 
     * Space complexity: O(n) where n is the number of nodes in the graph
     * In worst case when there are no edges, the stack might need to store all the nodes 
     * + ArrayList 'visited' also stores all the nodes. 
     */
	public Iterator<Integer> depthFirstIterator(int src){
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> visited = new ArrayList<>();

        stack.push(src);
        while (!stack.isEmpty()) {
            Integer node = stack.pop();
            if(!visited.contains(node)){
                visited.add(node);
                Iterator<Integer> it = graph.get(node).keySet().iterator();
                while(it.hasNext()){
                    Integer neighbor = it.next();
                    if(!visited.contains(neighbor)){
                        stack.push(neighbor);
                    }
                }
            }
        }
        Iterator<Integer> it = visited.iterator();
        return it;
    }
    
   
    /* <add a new vertex into the current graph.>
     * Time complexity: O(1) 
     * put() method which to add a key into a hashMap 
     * and add() method to add 'node' to HashSet is all constant time.
     * 
     * Space complexity: O(1) adding a single 'node' into HashMap, HashSet needs constant space.
     */
	public void addNode(int node){ 
        graph.put(node, new HashMap<>()); //value is a new empty HashMap. This empty HashMap will later hold the neighbors of this node and the corresponding edge weights.
        nodes.add(node);
    }

    
    /* < add a new edge. also add non-existing nodes. return false if edge already exists.>
     * Time complexity: O(1)
     * containsKey() method for checking, addNode() method, get() to retrieve, and method put() these are all 
     * constant time operations. 
     * 
     * Space complexity: O(1)
     * constant space is required to add a single edge.
     */
	public boolean addEdge(int source, int target, double w){
        if(graph.containsKey(source)||graph.containsKey(target)){ // graph에 source나 target 하나 이상 있을 때

            if(graph.containsKey(source)&&graph.containsKey(target)){ //graph에 source나 target 둘 다 있을 때
                if(graph.get(source).containsKey(target) == false){ // source랑 target이 연결이 안 되어 있다면 (no edge)
                    graph.get(source).put(target, w);
                    graph.get(target).put(source, w);
                    edgeCount++;
                    return true;
                }
                else{ // source랑 target이 연결 되어 있다면 (edge exist)
                    return false;
                }
            }

            else if(graph.containsKey(source) == true && graph.containsKey(target) == false){ //graph에 source만 있을 때
                this.addNode(target);
                graph.get(source).put(target, w);
                graph.get(target).put(source, w);
                edgeCount++;
                return true;
            }

            else{
                this.addNode(source);
                graph.get(source).put(target, w);
                graph.get(target).put(source, w);
                edgeCount++;
                return true;
            }

        }

        else{ //source target 둘 다 없을 때
            this.addNode(source);
            this.addNode(target);

            graph.get(source).put(target, w);
            graph.get(target).put(source, w);

            edgeCount++;
            return true;
        }
    }

    /* <remove node. return false if node doesn't exist.>
     * Time complexity: O(n) where n represents number of neighbors of 'node' which is passed through the parameter.
     * Because when 'node' has neighbors, then have to iterate number of neighbors of 'node' times 
     * to access each neighbor's HashMap (value) to delete 'node' from that HashMap. 
     * 
     * Space complexity: O(1)
     * for temporary variables and references such as 'neighbors' or 'it' requires constant space.
     */
	public boolean removeNode(int node){
        if(!graph.containsKey(node)){
            return false;
        }
        else{
            Set<Integer> neighbors = graph.get(node).keySet();
            Iterator<Integer> it = neighbors.iterator();
            while (it.hasNext()) {
                Integer key = it.next();
                graph.get(key).remove(node);
                edgeCount--;
            }
            graph.remove(node);
            nodes.remove(node);
            return true;
        }
    }
    
    /* <remove edge. return false if edge doesn't exist.>
     * Time complexity: O(1)
     * isEdge() method to check if edge exists, get() and remove() from HashMap() are all O(1)
     * 
     * Space complexity: O(1)
     */
	public boolean removeEdge(int source, int target){
        if(isEdge(source, target)){ //edge exist
            graph.get(source).remove(target);
            graph.get(target).remove(source);
            edgeCount--;
            return true;
        }
        else{ //edge doesn't exist.
            return false;
        }
    }

    /* <Returns true if source-target is a valid edge. The source-target order doesn't matter in undirected graphs.>
     * Time complexity: O(1)
     * containsKey() method to check if a key exists in the HashMap and get() is constant time.
     * 
     * Space complexity: O(1)
     */
	public boolean isEdge(int source, int target){
        if(graph.get(source).containsKey(target)){ //edge exist
            return true;
        }
        else{
            return false;
        }
    }

    /* <return weight of edge (what if edge doesn't exist?).>
     * Time complexity: O(1) 
     * isEdge() method to check if edge exists and get() to retrieve values are all O(1).
     * 
     * Space complexity:O(1)
     */
	public double getWeight(int source, int target){
        if(!isEdge(source, target)){
            throw new NoSuchElementException();
        }
        else{
            return graph.get(source).get(target);
        } 
    }

    
    /* <set the weight. existing weights are overwritten. if edge doesn't exist, return false;>
     * Time complexity: O(1)
     * isEdge() method to check if edge exists and replace() to replace the value are all O(1) operations.
     * 
     * Space complexity: O(1)
     */
	public boolean setWeight(int source, int target, double w){
        if(!isEdge(source, target)){
            return false;
        }
        else{
            graph.get(source).replace(target, w);
            graph.get(target).replace(source, w);
            return true;
        }
    }

    
    /* <return the total number of nodes. should have O(1) time complexity.>
     * Time complexity: O(1) retrieving size of HashSet is constant operation
     * Space complexity: O(1) 
     */
	public int numNodes(){ 
        return nodes.size();
    }

    
    /* <return the total number of edges. should have O(1) time complexity.>
     * Time complexity: O(1) 
     * Space complexity: O(1)
     */
	public int numEdges(){
        return edgeCount;
    } 

    /* <return a minimum spanning tree using either Prim's or Kruskal's algorithm.>
     * Time complexity: O(1) checking if tbe HashSet is empty or nor is constant operation.
     * Space complexity: O(1)
     */
    
	public Connectable getMST(){
        UndirectedGraph mst = new UndirectedGraph();
        if(nodes.isEmpty()){
            return mst;
        }
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        

        int startNode = nodes.iterator().next();
        mst.addNode(startNode);
        visited.add(startNode);
        Set<Integer> neighbors = getNeighbors(startNode);

        for(int i=0; neighbors.size(); i++){
            priorityQueue.add(new Edge(startNode, graph.get(startNode), i))

        }
        
        return mst;
    }

    private class Edge implements Comparable<Edge>{
        int source;
        int target;
        double weight;

        public Edge(int source, int target, double weight){
            this.source = source;
            this.target = target;
            this.weight = weight;
        }

        public int compareTo(Edge o){
            if(weight>o.weight){
                return 1;
            }
            if(weight<o.weight){
                return -1;
            }
            else{
                return 0;
            }
        }
    }

}

