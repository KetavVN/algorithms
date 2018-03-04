package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class AstonautSelection {
	
	/**
	 * @param n = number of countries
	 * @param astronaut = 2D array indicating both astronauts [i][0] and [i][1] belonging to same country
	 * @return
	 */
	static long journeyToMoon(int n, int[][] astronaut) {
		
		Map<Integer, Set<Integer>> astronautAdjMap = prepareAstronautMap(astronaut);
		
		Set<Set<Integer>> sets = prepareCountryAstronautSets(astronautAdjMap);
		
		int astronautsInGroups = 0;
		int countries = sets.size();
		for(Set<Integer> set : sets) {
			//System.out.println(set);
			astronautsInGroups += set.size();
		}
		int astronautsAlone = n - astronautsInGroups;
		countries = sets.size() + astronautsAlone;
		
		/*System.out.println(String.format(
			"n=%d | astronaut in group=%d | astronaut group countries=%d | single astronaut countries=%d | total countries=%d", 
			n, astronautsInGroups, sets.size(), astronautsAlone, countries));*/
		
		int [] sizes = new int [countries];
		
		//fill up sizes
		Iterator<Set<Integer>> ite = sets.iterator();
		for(int i=0; i<countries; i++) {
			if(ite.hasNext()) {
				sizes[i] = ite.next().size();
			} else {
				sizes[i] = 1;
			}
		}
		
		//find total combinations
		long total = 0;
		for(int i=0; i<sizes.length; i++) {
			for(int j=i+1; j<sizes.length; j++) {
				total += sizes[i] * sizes[j];
			}
		}
		return total;
	}

	private static Map<Integer, Set<Integer>> prepareAstronautMap(int[][] astronaut) {
		//prepare adj list
		Map<Integer, Set<Integer>>  map = new HashMap<>();
		for(int i=0; i<astronaut.length; i++) {
			if(!map.containsKey(astronaut[i][0])) {
				map.put(astronaut[i][0], new HashSet<Integer>());
				map.get(astronaut[i][0]).add(astronaut[i][0]);
			}
			if(!map.containsKey(astronaut[i][1])) {
				map.put(astronaut[i][1], new HashSet<Integer>());
				map.get(astronaut[i][1]).add(astronaut[i][1]);
			}
			map.get(astronaut[i][0]).add(astronaut[i][1]);
			map.get(astronaut[i][1]).add(astronaut[i][0]);
		}
		return map;
	}

	private static Set<Set<Integer>> prepareCountryAstronautSets(Map<Integer, Set<Integer>> map) {
		Set<Set<Integer>> sets = new HashSet<>();
		Set<Integer> visited = new HashSet<>();
		for(Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
			if(!visited.contains(entry.getKey())) {
				Queue<Integer> queue = new LinkedList<>();
				queue.add(entry.getKey());
				Set<Integer> set = new HashSet<>();
				traverse(map, queue, visited, set);
				sets.add(set);
			}
		}
		return sets;
	}
	
	private static void traverse(Map<Integer, Set<Integer>> map, Queue<Integer> queue, 
								Set<Integer> visited, Set<Integer> currentSet) {
		if(!queue.isEmpty()) {
			int len = queue.size();
			for(int i=0; i<len; i++) {
				Integer item = queue.poll();
				if(!visited.contains(item)) {
					visited.add(item);
					currentSet.add(item);
					Set<Integer> children = map.get(item);
					for(Integer child : children) {
						if(!visited.contains(child)) {
							queue.add(child);
						}
					}
				}
			}
			traverse(map, queue, visited, currentSet);
		}
	}
	
	/**
	 * input:
		100000 2
		1 2
		3 4
		output:
		4999949998
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int p = in.nextInt();
		int[][] astronaut = new int[p][2];
		for(int astronaut_i = 0; astronaut_i < p; astronaut_i++){
			for(int astronaut_j = 0; astronaut_j < 2; astronaut_j++){
				astronaut[astronaut_i][astronaut_j] = in.nextInt();
			}
		}
		long result = journeyToMoon(n, astronaut);
		System.out.println(result);
		in.close();
	}
}

/*

https://www.hackerrank.com/challenges/journey-to-the-moon/problem

The member states of the UN are planning to send people to the Moon. But there is a problem. In line with their principles of global unity, they want to pair astronauts of different countries.

There are trained astronauts numbered from to . But those in charge of the mission did not receive information about the citizenship of each astronaut. The only information they have is that some particular pairs of astronauts belong to the same country.

Your task is to compute in how many ways they can pick a pair of astronauts belonging to different countries. Assume that you are provided enough pairs to let you identify the groups of astronauts even though you might not know their country directly. For instance, if are astronauts from the same country; it is sufficient to mention that and are pairs of astronauts from the same country without providing information about a third pair .

Input Format

The first line contains two integers, and , separated by a single space. lines follow. Each line contains integers separated by a single space and such that

and and are astronauts from the same country.

Constraints

Output Format

An integer that denotes the number of permissible ways to choose a pair of astronauts.

Sample Input 0

5 3
0 1
2 3
0 4

Sample Output 0

6

Explanation 0

Persons numbered , and belong to the same country, and those numbered and belong to the same country, but different from the previous one. All in all, the UN has ways of choosing a pair:	

Sample Input 1

4 1
0 2

Sample Output 1

5

Explanation 1

Persons numbered and belong to the same country, and persons and don't share countries with anyone else, so they belong to unique countries on their own. All in all, the UN has ways of choosing a pair:

*/
