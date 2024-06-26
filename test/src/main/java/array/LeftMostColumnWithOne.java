package array;

import java.util.Arrays;
import java.util.List;

/*
Leetcode day 20 - title: "Leftmost Column with at Least a One"

(This problem is an interactive problem.)

A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, 
this row is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. 
If such index doesn't exist, return -1.

You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:

    BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
    BinaryMatrix.dimensions() returns a list of 2 elements [m, n], which means the matrix is m * n.

Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  
Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes you're given the binary matrix mat as input in the following four examples. 
You will not have access the binary matrix directly.

Example 1:
Input: mat = [[0,0],[1,1]]
Output: 0

Example 2:
Input: mat = [[0,0],[0,1]]
Output: 1

Example 3:
Input: mat = [[0,0],[0,0]]
Output: -1

Example 4:
Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
Output: 1

Example 5:
Input: mat = [[1,1,1,1], [0,0,0,1], [0,0,1,1], [0,1,1,1]]
Output: 0

Constraints:
    m == mat.length
    n == mat[i].length
    1 <= m, n <= 100
    mat[i][j] is either 0 or 1.
    mat[i] is sorted in a non-decreasing way.

Success Details:
0ms 100% faster than others

*/
public class LeftMostColumnWithOne {

	class BinaryMatrix {
		
		int table [][];
		
		public BinaryMatrix(int x, int y) {
			table = new int[x][y];
		}
		
		public List<Integer> dimensions() {
			return Arrays.asList(table.length, table[0].length);
		}
		
		public int get(int x, int y) {
			return table[x][y];
		}
		
	}
	
	public int leftMostColumnWithOne(BinaryMatrix mat) {
        List<Integer> dim = mat.dimensions();
        return getMinLeft(mat, dim, 0, dim.get(1)-1, -1);
    }
    
    public int getMinLeft(BinaryMatrix mat, List<Integer> dim, int row, int col, int ans) {
        if(col < 0 || row >= dim.get(0)) {
            return ans;
        } else {
            if(mat.get(row, col) == 1) {
                return getMinLeft(mat, dim, row, col-1, col);
            } else {
                return getMinLeft(mat, dim, row+1, col, ans);
            }
        }
    }
	
}
