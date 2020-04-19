package memoization;

/*
https://leetcode.com/problems/minimum-path-sum/
Leetcode day 17 challenge

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

Success Details
5ms Beats 7% of solutions
*/

public class MinMatrixPathSum {

	 int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        return minPathSum(grid, 0, 0, map);
    }
    
	public int minPathSum(int [][] grid, int x, int y, Map<Integer, List<Integer>> map) {
        if(x==grid.length-1 && y==grid[0].length-1) {
            map.put(grid[0].length*x+y, Arrays.asList(grid[x][y]));
            return grid[x][y];
        } else if (x>=grid.length || y>=grid[0].length) {
            return Integer.MAX_VALUE;
        } else if (map.containsKey(grid[0].length*x+y)) {
            return map.get(grid[0].length*x+y).get(0);
        } else {
            
            int down  = minPathSum(grid, x+1, y, map);
            int right = minPathSum(grid, x, y+1, map);
            
            int min = down < right ? down : right;
            
            int s = min + grid[x][y];
            
            /*System.out.println(String.format("grid[%d][%d]=%d, down=%d, right=%d, min=%d, s=%d, mapIndex=%d", x, y, grid[x][y], down, right, min, s, grid[0].length*x+y));*/
            
            map.put(grid[0].length*x+y, Arrays.asList(s));
            
            return s;
        }
    }
}
