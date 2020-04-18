package array;

/*
https://leetcode.com/problems/number-of-islands/
Medium
Leetcode challenge day 17

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3

Success Details:
1ms 42.2mb 99.98% faster than other solutions

*/
public class NumberOfIslands {

	public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        
        int count = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    traverseIsland(grid, i, j);
                }
            }
        }
        return count;
    }
    
    private void traverseIsland(char [][] grid, int x, int y) {
        if(x>=0 && x<grid.length && y>=0 && y<grid[0].length) {
            if(grid[x][y] == '1') {
                grid[x][y] = '0';
                traverseIsland(grid, x+1, y);
                traverseIsland(grid, x-1, y);
                traverseIsland(grid, x, y+1);
                traverseIsland(grid, x, y-1);
            }
        }
    }

}
