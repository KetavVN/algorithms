package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Cracking the coding interview Ch-4 Problem-7 - BuildOrder
 * 
 * Given project and their dependent projects write an algorithm to return project buid order
 * 
 * ex:
 * 	Projects: a, b, c, d, e, f
 *  Dependencies: [a, d] , [f, b], [b, d], [f, a], [d, c] 
 *
 * Output: f, e, a, b, d, c
 */
public class BuildOrder {

	static class ProjectNode {
		private String name;
		private List<ProjectNode> neighbours = new ArrayList<>();
		private int inboundCount = 0;
		public ProjectNode(String n) {
			name = n;
		}
		public void addChild(ProjectNode child) {
			neighbours.add(child);
			child.inboundCount++;
		}
		public void decreaseNeighbourInboundCount() {
			for(ProjectNode neighbour : neighbours) {
				neighbour.inboundCount--;
			}
		}
		public boolean hasNoDependency() {
			return inboundCount == 0;
		}
		@Override
		public String toString() {
			return name;
		}
	}

	public List<String> buildOrder(String[] projs, String [][] deps) {
		List<String> buildOrder = new ArrayList<>();
		if(deps == null || deps.length == 0) {
			return buildOrder;
		}
		Set<String> ret = new LinkedHashSet<>(); 
		Map<String, ProjectNode> pMap = createProjectMap(projs);
		//System.out.println("pMap = " + pMap);
		buildGraph(deps, pMap);
		List<ProjectNode> noInboundDepNodes = null;
		while(!(noInboundDepNodes = findNoDependencyNodes(pMap, ret)).isEmpty()) {
			//System.out.println("no dep nodes for current iteration = " + noInboundDepNodes);
			for(ProjectNode node : noInboundDepNodes) {
				ret.add(node.name);
				node.decreaseNeighbourInboundCount();
			}
		}
		if(ret.size() != projs.length) {
			//System.out.println("ret = "+ret+" retLen = " + ret.size() + " inLen = "+projs.length);
			throw new RuntimeException("Build order can not be determinded due to cyclic dependencies");
		}
		buildOrder.addAll(ret);
		return buildOrder;
	}

	private Map<String, ProjectNode> createProjectMap(String [] projs) {
		Map<String, ProjectNode> pMap = new HashMap<>();
		for(String p : projs) {
			pMap.put(p, new ProjectNode(p));
		}
		return pMap;
	}

	private void buildGraph(String [][] deps, Map<String, ProjectNode> pMap) {
		for(int i=0; i<deps.length; i++) {
			pMap.get(deps[i][0]).addChild(pMap.get(deps[i][1]));
		}
	}

	private List<ProjectNode> findNoDependencyNodes(
			Map<String, ProjectNode> pMap, Set<String> visited) {
		List<ProjectNode> ret = new ArrayList<>();
		for(Map.Entry<String, ProjectNode> e : pMap.entrySet()) {
			if(e.getValue().hasNoDependency() && !visited.contains(e.getKey())) {
				ret.add(e.getValue());
			}
		}
		return ret;
	}

	public static void main(String [] args) {
		String [][] deps = new String [][] {
			{"a", "d"},
			{"f", "b"},
			{"b", "d"},
			{"f", "a"},
			{"d", "c"}
			//,{"c", "d"} //create loop
		};
		String [] projs = new String [] {"a", "b", "c", "d", "e", "f"};
		System.out.println(new BuildOrder().buildOrder(projs, deps));
	}
}
