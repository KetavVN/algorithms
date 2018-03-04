package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class ShortestDistance {

    static int[] bfs(int nodes, int totalEdges, int[][] edges, int s) {
        Map<Integer, List<Integer>> neighbours = new HashMap<>();
        for(int i=0; i<edges.length; i++) {
            if(!neighbours.containsKey(edges[i][0])) {
                neighbours.put(edges[i][0], new ArrayList<Integer>());
            }
            if(!neighbours.containsKey(edges[i][1])) {
                neighbours.put(edges[i][1], new ArrayList<Integer>());
            }
            neighbours.get(edges[i][0]).add(edges[i][1]);
            neighbours.get(edges[i][1]).add(edges[i][0]);
        }
        
        SortedMap<Integer, Integer> visitedNodes = new TreeMap<>();
        Queue<Integer> toVisit = new LinkedList<>();
        toVisit.add(s);
        findDistance(neighbours, toVisit, visitedNodes, 0, 6);
        int [] distances = new int [nodes-1];
        int j = 0;
        for(int k=1; k<=nodes; k++) {
            if(k != s) {
                distances[j] = -1;
                if(visitedNodes.containsKey(k)) {
                    distances[j] = visitedNodes.get(k);
                }
                j++;
            }
        }
        return distances;
    }
    
    private static void findDistance(Map<Integer, List<Integer>> neighbourMap, Queue<Integer> queue, 
                                     SortedMap<Integer, Integer> visitedNodes, int start, int distance) {
        if(!queue.isEmpty()) {
            int len = queue.size();
            for(int i=0; i<len; i++) {
                Integer toVisit = queue.poll();	
                if(!visitedNodes.containsKey(toVisit)) {
                    visitedNodes.put(toVisit, start);
                    List<Integer> neighbours = neighbourMap.get(toVisit);
                    if(neighbours != null) {
                    	for(Integer neighbour : neighbours) {
                            if(!visitedNodes.containsKey(neighbour)) {
                                queue.add(neighbour);
                            }
                        }
                    }
                }
            }
            findDistance(neighbourMap, queue, visitedNodes, start+distance, distance);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int nodes = in.nextInt();
            int totalEdges = in.nextInt();
            int[][] edges = new int[totalEdges][2];
            for(int edges_i = 0; edges_i < totalEdges; edges_i++){
                for(int edges_j = 0; edges_j < 2; edges_j++){
                    edges[edges_i][edges_j] = in.nextInt();
                }
            }
            int startNode = in.nextInt();
            int[] result = bfs(nodes, totalEdges, edges, startNode);
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
            }
            System.out.println("");
        }
        in.close();
    }
}
