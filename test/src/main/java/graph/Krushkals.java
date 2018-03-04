package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*
Krushkal's Algorithm - Technically this Algorithm should work on Forest (multiple sets of connected graphs)
but this algorithm is only implemented for single graph (instead of forest)
*/
public class Krushkals {

	static private class Node<T> {
		private T val;
		private Map<Node<T>, Integer> neighbours = new HashMap<>();

		public Node(T v) {
			val = v;
		}

		@Override
		public String toString() {
			return val.toString();
		}

		@Override @SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			if(o instanceof Node) {
				Node<T> other = (Node<T>) o;
				return val.equals(other.val);
			}
			return false;
		}
	}

	static private class Edge<T> implements Comparable<Edge<T>>{
		private Node<T> n1, n2;
		private Integer weight;

		public Edge(Node<T> n1, Node<T> n2) {
			this.n1 = n1;
			this.n2 = n2;
			weight = n1.neighbours.get(n2);
		}

		@Override @SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			if(o instanceof Edge) {
				Edge<T> other = (Edge<T>) o;
				return ( ( n1.equals(other.n1) && n2.equals(other.n2) ) 
							|| ( n1.equals(other.n2) && n2.equals(other.n1) ) ) 
						&& weight.equals(other.weight);
			}
			return false;
		}

		@Override
		public int compareTo(Edge<T> other) {
			if(other == null) {
				return 1;
			} else if (this == other) {
				return 0;
			}
			return weight - other.weight;
		}
	}

	public static <T> Queue<Edge<T>> findAllEdgesConnectedToNode(Node<T> n) {
		Queue<Edge<T>> edges = new PriorityQueue<>();
		Set<Node<T>> visited = new HashSet<>();
		Queue<Node<T>> queue = new LinkedList<>();
		queue.add(n);
		while(!queue.isEmpty()) {
			Node<T> node = queue.poll();
			if(!visited.contains(node)) {
				visited.add(node);
				for(Map.Entry<Node<T>, Integer> entry : node.neighbours.entrySet()) {
					edges.add(new Edge<T>(node, entry.getKey()));
					queue.add(entry.getKey());
				}
			}
		}
		return edges;
	}

	public static <T> int findMinWeightOfConnectedGraph(Node<T> node, int size) {
		Queue<Edge<T>> edges = findAllEdgesConnectedToNode(node);
		Set<Node<T>> visited = new HashSet<>();
		int total = 0;
		while(!edges.isEmpty()) {
			Edge<T> edge = edges.poll();
			if(!visited.contains(edge.n1) 
				|| !visited.contains(edge.n2)) {
				visited.add(edge.n1);
				visited.add(edge.n2);
				total += edge.weight;
			}
		}
		if(visited.size() == size) {
			return total;
		}
		return Integer.MAX_VALUE;
	}

	public static void main(String ...args) {
		Node<Integer> one = new Node<>(1);
		Node<Integer> two = new Node<>(2);
		Node<Integer> three = new Node<>(3);
		Node<Integer> four = new Node<>(4);
		Node<Integer> five = new Node<>(5);
		Node<Integer> six = new Node<>(6);
		Node<Integer> seven = new Node<>(7);
		Node<Integer> eight = new Node<>(8);
		//Node<Integer> nine = new Node<>(9);	//disconnectedNode

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

		System.out.println(findMinWeightOfConnectedGraph(one, 8));
	}

}
