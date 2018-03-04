package dp;

//algorithm is explain by MIT professor on youtube.
//https://www.youtube.com/watch?v=V5hZoJ6uK-s
public class LongestCommonSubsequence {

    public static int[] longestCommonSubsequence(int[] a, int[] b) {

        //b = vertical/rows, a=horizontal/columns
        int [][] table = new int [b.length+1][a.length+1];

        //create array
        int i,j;
        for(i=1; i<table.length; i++) {
            for(j=1; j<table[0].length; j++) {
                if(b[i-1] == a[j-1]) {
                    table[i][j] = table[i-1][j-1] + 1;
                } else {
                    if(table[i-1][j] > table[i][j-1]) {
                        table[i][j] = table[i-1][j];
                    } else {
                        table[i][j] = table[i][j-1];
                    }
                }
            }
        }

        return backtrack(a, b, table);

    }

	private static int[] backtrack(int[] a, int[] b, int[][] table) {
		int i;
		int j;
		//backtrack
        i = b.length;
        j = a.length;
        int k=table[i][j];
        int [] ret = new int [k];
        while(table[i][j]!=0 && i>0 && j>0) {
            if(a[j-1] == b[i-1]) {
                ret[k-1] = a[j-1];
                i--;
                j--;
                k--;
            } else {
                if(table[i-1][j] > table[i][j-1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
		return ret;
	}

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 1};
		int b[] = {3, 4, 1, 2, 1, 3};
        int[] result = longestCommonSubsequence(a, b);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        System.out.println("");
    }

}
