package array;

/**
 * Inplace N * N matrix rotate by 90 degree
 * 
 * Cracking the coding interview rotate matrix Ch 1 Problem 7
 *  
 * @author ketav
 *
 */
public class MatrixRotate {

	public void rotateMatrix(int [][] mat) {
		if(mat == null || mat.length == 0 || mat.length != mat[0].length) {
			return;
		}
		
		for(int i=0; i<mat.length/2; i++) {
			fourWaySwap(mat, i);
		}
	}
	
	public void fourWaySwap(int [][] mat, int row) {

		int firstRow = row;
		int lastRow = mat.length-1-row;
		int firstCol = row;
		int lastCol = mat.length-1-row;

		for(int i=row; i<lastRow; i++) {

			//temp copy
			int temp = mat[firstRow][i]; //correct

			//fill firstRow left to right = copy firstCol bot up
			mat[firstRow][i] = mat[mat.length-1-i][firstCol];

			//fill first col bot up = copy last-row'th row right to left
			mat[mat.length-1-i][firstRow] = mat[lastRow][mat.length-1-i];

			//fill last row right to left = copy last col top to bottom
			mat[lastRow][mat.length-1-i] = mat[i][lastCol];

			//temp copy back
			mat[i][lastCol] = temp;

		}
	}
	
	public static void main(String [] args) {
		MatrixRotate mr = new MatrixRotate();
		int [][] arr = {
				{ 1, 2, 3, 4},
				{ 5, 6, 7, 8}, 
				{ 9,10,11,12},
				{13,14,15,16}
			};
		mr.rotateMatrix(arr);
		for(int [] a : arr) {
			for(int b : a) {
				System.out.print(String.format("%3d", b));
			}
			System.out.println();
		}
	}
	
}
