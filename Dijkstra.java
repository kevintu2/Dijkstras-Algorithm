
import java.util.*;
import java.util.PriorityQueue;

class Tuple implements Comparable<Tuple> {
	public int destn;
	public int weight;
	public Tuple() {
		
	}
	public Tuple(int d, int w) {
		destn = d;
		weight = w;
	}
    public int compareTo(Tuple t1) 
    { 
        if (this.weight < t1.weight) 
            return -1; 
        if (this.weight > t1.weight) 
            return 1; 
        return 0; 
    }

}
class Main{
	public static LinkedList<Tuple>[] alist;
	public static PriorityQueue<Tuple> order;
	public static int [] temp;
	public static int [] parents;
	public static boolean [] visit;
	public static int counter = 0;


	
public static void main(String[] args) {

	
	
	Scanner sc;
        sc = new Scanner(System.in);
		int totalnodes = sc.nextInt();
		int totaledges = sc.nextInt();
		int startnode = sc.nextInt();
		alist = new LinkedList[totalnodes];
		order = new PriorityQueue<Tuple>();
		temp = new int [totaledges];
		parents = new int [totalnodes];
		visit = new boolean[totalnodes];
		
		for(int i = 0; i<alist.length;i++) {
			alist[i] = new LinkedList<Tuple>();
			
		}
		for(int i = 0; i<temp.length;i++) {
			temp[i] = Integer.MAX_VALUE; //setting all the distances to infinity 
		}
		while(sc.hasNextInt()) {
			int nodeID = sc.nextInt();
			int endNode = sc.nextInt();
			int weight = sc.nextInt();
			Tuple s = new Tuple(endNode,weight); //Tuple includes the destination node and the weight of it
			alist[nodeID].add(s); //making the linked list of tuples for each index of the array of linked list
			
		}
		sc.close();

		order.add(new Tuple(startnode,0)); //the start node has a weight of 0 to itself
		temp[startnode] = 0; // set the distance of the start node to 0
		parents[startnode] = startnode; //parent of the start node is itself
		while(!order.isEmpty()) {
			int x = order.remove().destn;//current node to check
			visit[x] = true; //set true when all neighbors are checked
			dijkstra(x); //check its neighbors
		}
		for(int i = 0; i<visit.length;i++) {
			if(visit[i] && i!=startnode) {
				System.out.println(i + " " + temp[i] + " " + parents[i]); //print out the order
			}
		}

}



public static void dijkstra(int cur) {
	int dist = -1;
	int newweight = -1;
	
	for(int i = 0; i < alist[cur].size(); i++) { //go through the linkedlist of tuples in the current node
		Tuple t = alist[cur].get(i);
		
		if(!visit[t.destn]) {
			dist = t.weight; 
			newweight = temp[cur] + dist;
			
		
		if(newweight < temp[t.destn]) { //check if the new weight is less than the array of weights
			temp[t.destn] = newweight; //change to new weight
			parents[t.destn] = cur; //change the parent node of the end node to the current node when new weight is less
		}
	
			order.add(new Tuple(t.destn,temp[t.destn])); //add the tuple to the queue to get checked
			
		}
		
		
	}
	
	
}
}


