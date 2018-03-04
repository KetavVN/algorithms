package graph;

import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * This algorithm can be used to find shortest path between two nodes in a graph.
 * This is widely used algorithm in Networking (OSPF, IS-IS)
 * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * 
 * @author ketav
 */
public class Dijakstra {

	private static class Node<T> {
	
		public T val;
		public Map<Node<T>, Integer> neighbours = new HashMap<>();
		public Integer distance = Integer.MAX_VALUE;

		public static Comparator<Node<?>> distanceComparator = new Comparator<Node<?>>() {
		
			@Override
			public int compare(Node<?> node1, Node<?> node2) {
				if(node1 == node2) {
					return 0;
				} else if (node1 == null && node2 != null) {
					return -1;
				} else if (node1 != null && node2 == null) {
					return 1;
				} 
				return node1.distance - node2.distance;
			}
		};

		public Node(T v) {
			val = v;
		}

		@Override
		public String toString() {
			return val.toString();
		}

	}

	public static <T> Integer findShortestPath(Dijakstra.Node<T> n1, Dijakstra.Node<T> n2) {

		n1.distance = 0;
		Queue<Node<T>> queue = new PriorityQueue<>(Dijakstra.Node.distanceComparator);
		queue.add(n1);
		while(!queue.isEmpty()) {
			Dijakstra.Node<T> node = queue.poll();
			Map<Dijakstra.Node<T>, Integer> neighbours = node.neighbours;
			for(Map.Entry<Dijakstra.Node<T>, Integer> entry : neighbours.entrySet()) {
				Dijakstra.Node<T> neighbour = entry.getKey();
				Integer dist = node.distance + entry.getValue();
				if(neighbour.distance > dist) {
					neighbour.distance = dist;
					if(neighbour.equals(n2)) {
						break;
					}
					queue.add(neighbour);
				}
				if(neighbour.equals(n2)) {
					break;
				}
			}
		}
		return n2.distance;
	}

	public static void main (String ...args) {
		Node<Integer> one = new Node<>(1);
		Node<Integer> two = new Node<>(2);
		Node<Integer> three = new Node<>(3);
		Node<Integer> four = new Node<>(4);
		Node<Integer> five = new Node<>(5);
		Node<Integer> six = new Node<>(6);
		Node<Integer> seven = new Node<>(7);
		Node<Integer> eight = new Node<>(8);
		Node<Integer> nine = new Node<>(9);	//disconnectedNode
		
		one.neighbours.put(two, 5);
		one.neighbours.put(three, 2);
		one.neighbours.put(four, 7);
		
		two.neighbours.put(one, 5);
		two.neighbours.put(five, 1);
		two.neighbours.put(six, 3);
		
		three.neighbours.put(one, 2);
		three.neighbours.put(six, 3);
		
		four.neighbours.put(one, 7);
		four.neighbours.put(seven, 8);
		
		five.neighbours.put(two, 1);
		five.neighbours.put(eight, 12);
		
		six.neighbours.put(two, 3);
		six.neighbours.put(three, 3);
		six.neighbours.put(seven, 5);
		
		seven.neighbours.put(four, 8);
		seven.neighbours.put(six, 5);
		seven.neighbours.put(eight, 3);
		
		eight.neighbours.put(five, 12);
		eight.neighbours.put(seven, 3);
		
		System.out.println(findShortestPath(one, eight));
		System.out.println(findShortestPath(one, nine));
	}

}
