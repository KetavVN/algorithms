package tree;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class GraphNode<T> {

	@SuppressWarnings("unused")
	private T val;
	private Set<GraphNode<T>> neighbours = new HashSet<>();
	
	public GraphNode(T val) {
		this.val = val;
	}

	public boolean isPathExists(GraphNode<T> dest) {
		List<GraphNode<T>> path = findPathDFS(this, dest, new LinkedList<GraphNode<T>>(), 
				new HashSet<GraphNode<T>>());
		return path != null && !path.isEmpty();
	}

	public boolean isPathExists1(GraphNode<T> dest) {
		Queue<GraphNode<T>> nodesToVisit = new LinkedList<>();
		nodesToVisit.add(this);
		return findPathBFS(dest, nodesToVisit, new HashSet<GraphNode<T>>());
	}
	
	private List<GraphNode<T>> findPathDFS(GraphNode<T> current, GraphNode<T> dest, 
			List<GraphNode<T>> path, Set<GraphNode<T>> visitedNodes) {
		if(current.equals(dest)) {
			path.add(current);
			return path;
		} else if (visitedNodes.contains(current)) {
			return null;
		} else {
			visitedNodes.add(current);
			for(GraphNode<T> neighbour : current.neighbours) {
				List<GraphNode<T>> path1 = findPathDFS(neighbour, dest, path, visitedNodes);
				if(path1 != null) {
					path1.add(neighbour);
					return path1;
				}
			}
			return null;
		}
	}
	
	private boolean findPathBFS(GraphNode<T> dest, 
			Queue<GraphNode<T>> nodesToVisit, 
			Set<GraphNode<T>> visitedNodes) {
		
		if(nodesToVisit == null || nodesToVisit.isEmpty()) {
			return false;
		}
		
		int i = 0;
		int len = nodesToVisit.size();
		while(i < len) {
			i++;
			GraphNode<T> nodeTovisit = nodesToVisit.poll();
			if(!visitedNodes.contains(nodeTovisit)) {
				visitedNodes.add(nodeTovisit);
				if(nodeTovisit == dest) {
					return true;
				}
				nodesToVisit.addAll(nodeTovisit.neighbours);
			}
		}
		
		return findPathBFS(dest, nodesToVisit, visitedNodes);
		
	}
	
	/*@Override
	public boolean equals(Object other) {
		return this == other;
	}
	
	@Override
	public int hashCode() {
		return val.hashCode();
	}*/
	
	public static void main(String ...args) {
		GraphNode<Integer> n1 = new GraphNode<>(1);
		GraphNode<Integer> n2 = new GraphNode<>(2);
		GraphNode<Integer> n3 = new GraphNode<>(3);
		GraphNode<Integer> n4 = new GraphNode<>(4);
		GraphNode<Integer> n5 = new GraphNode<>(5);
		GraphNode<Integer> n6 = new GraphNode<>(6);
		GraphNode<Integer> n7 = new GraphNode<>(7);
		GraphNode<Integer> n8 = new GraphNode<>(8);
		//GraphNode<Integer> n9 = new GraphNode<>(9);
		GraphNode<Integer> n10 = new GraphNode<>(10);
		GraphNode<Integer> n11 = new GraphNode<>(11);
		GraphNode<Integer> n12 = new GraphNode<>(12);
		
		n1.neighbours.add(n2);
		n1.neighbours.add(n3);
		n1.neighbours.add(n5);
		
		n2.neighbours.add(n4);
		
		n3.neighbours.add(n6);
		n3.neighbours.add(n2);
		
		n4.neighbours.add(n7);
		n4.neighbours.add(n8);
		n4.neighbours.add(n10);
		
		n5.neighbours.add(n11);
		n5.neighbours.add(n8);
		
		n6.neighbours.add(n1);
		n6.neighbours.add(n5);
		
		System.out.println("n1 to n10 exists - "+n1.isPathExists1(n10));
		System.out.println("n1 to n12 exists - "+n1.isPathExists1(n12));
	}
	
}
