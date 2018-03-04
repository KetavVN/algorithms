package graph;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class RoadsAndLibraries {

	static BigInteger findTotalCost(int n, int c_lib, int c_road, int[][] cities) {
		
		//prepare adjList
		Map<Integer, List<Integer>> adjList = createCitiesAdjList(cities);
		
		if(c_lib <= c_road) {
			return new BigInteger(Integer.toString(n)).multiply(new BigInteger(Integer.toString(c_lib)));
		}
		
		BigInteger total = new BigInteger("0");
		Set<Integer> visited = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1; i<=n; i++) {
			if(!visited.contains(i)) {
				queue.add(i);
				total = traverse(adjList, queue, visited, total.add(new BigInteger(Integer.toString(c_lib))), c_road);
			}
		}
		return total;
	}

	private static Map<Integer, List<Integer>> createCitiesAdjList(int[][] cities) {
		Map<Integer, List<Integer>> adjList = new HashMap<>();
		for(int i=0; i<cities.length; i++) {
			if(!adjList.containsKey(cities[i][0])) {
				adjList.put(cities[i][0], new ArrayList<Integer>());
			}
			if(!adjList.containsKey(cities[i][1])) {
				adjList.put(cities[i][1], new ArrayList<Integer>());
			}
			adjList.get(cities[i][0]).add(cities[i][1]);
			adjList.get(cities[i][1]).add(cities[i][0]);
		}
		return adjList;
	}
	
	private static BigInteger traverse(Map<Integer, List<Integer>> adjList, Queue<Integer> queue, 
								Set<Integer> visited, BigInteger total, int cRoad) {
		if(!queue.isEmpty()) {
			int len = queue.size();
			for(int i=0; i<len; i++) {
				Integer city = queue.poll();
				if(!visited.contains(city)) {
					visited.add(city);
					List<Integer> neighbours = adjList.get(city);
					if(neighbours != null) {
						for(Integer neighbour : neighbours) {
							//avoid loops and duplicate items in queue
							if(!visited.contains(neighbour) 
							&& !queue.contains(neighbour)) {
								total = total.add(new BigInteger(Integer.toString(cRoad)));
								queue.add(neighbour);
							}
						}
					}
				}
			}
			return traverse(adjList, queue, visited, total, cRoad);
		}
		
		return total;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for(int a0 = 0; a0 < q; a0++){
			int n = in.nextInt();
			int m = in.nextInt();
			int c_lib = in.nextInt();
			int c_road = in.nextInt();
			int[][] cities = new int[m][2];
			for(int cities_i = 0; cities_i < m; cities_i++){
				for(int cities_j = 0; cities_j < 2; cities_j++){
					cities[cities_i][cities_j] = in.nextInt();
				}
			}
			BigInteger result = findTotalCost(n, c_lib, c_road, cities);
			System.out.println(result);
		}
		in.close();
	}
}
