package array;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * 
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * 
 * Example:
 * Consider the following matrix:
 * [
 * 	[1,   4,  7, 11, 15],
 *  [2,   5,  8, 12, 19],
 *  [3,   6,  9, 16, 22],
 *  [10, 13, 14, 17, 24],
 *  [18, 21, 23, 26, 30]
 * ]
 * 
 * Given target = 5, return true.
 * Given target = 20, return false.
 * 
 * @author ketav
 *
 */
public class MatrixElementSearch {
	
	public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return binarySearchRow2(matrix, 0, 0, matrix[0].length-1, target);
    }
    
	private boolean binarySearchRow(int [][] matrix, int row, int start, int end, int target) {
		boolean result = false;
		if(row < matrix.length) {
			if(start<0 || end<0 || start>=matrix[0].length) {
				return false;
			}
			if(row+1 < matrix.length) {
				int nextStart = matrix[row+1][start+1] <= target ? start+1 : matrix[row+1][start] >= target ? start : -1;
				int nextEnd = target <= matrix[row+1][end-1] ? end-1 : target <= matrix[row+1][end] ? end : -1;
				result = binarySearchRow(matrix, row+1, nextStart, nextEnd, target);
			}
			if(!result) {
				result = binarySearch(matrix[row], start, end, target);
			}
		}
		return result;
	}
	
	private boolean binarySearch(int [] matrixRow, int start, int end, int target) {
		if((end-start) <= 1 && (matrixRow[start] == target || matrixRow[end-1] == target)) {
    		return true;
    	} else if(start < end) {
    		int mid = start + (end-start)/2;
    		if(matrixRow[mid] == target) {
    			return true;
    		} else if(matrixRow[mid] > target) {
    			return binarySearch(matrixRow, start, mid-1, target);
    		} else {
    			return binarySearch(matrixRow, mid+1, end, target);
    		}
    	}
		return false;
	}
	
    private boolean binarySearchRow2(int [][] matrix, int row, int start, int end, int target) {
        if(row < matrix.length) { 
        	if(matrix[row][start] <= target && target <= matrix[row][end]) {
	        	if(start < end) {
	        		int mid = start + (end-start)/2;
	        		if(matrix[row][mid] == target) {
	        			return true;
	        		} else if(matrix[row][mid] > target) {
	        			return binarySearchRow2(matrix, row, start, mid-1, target);
	        		} else {
	        			return binarySearchRow2(matrix, row, mid+1, end, target);
	        		}
	        	} else if (matrix[row][start] == target) {
	        		return true;
	        	}
	        }
    		return binarySearchRow2(matrix, row+1, 0, matrix[0].length-1, target);
        }
        return false;
    }
    
    public static void main(String [] args) {
    	MatrixElementSearch obj = new MatrixElementSearch();
    	System.out.println(obj.searchMatrix(
    			new int [][] 
    					{
    						{1, 4, 7, 11, 15},
    						{2, 5, 8, 12, 19},
    						{3, 6, 9, 16, 22}
    					}, 22));
    }
    
}
