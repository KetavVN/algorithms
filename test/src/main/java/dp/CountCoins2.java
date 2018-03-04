package dp;

public class CountCoins2 {

	public static long getWays(long n, long[] coins){
		//npe check
		long [][] arr = new long [(int)n][coins.length]; 
		return findWays(n, coins, arr);
	}
	
	public static long findWays(long n, long [] coins, long [][] ans) {
		if(n == 0) {
			return 1;
		} else {
			long sum = 0; 
			for(int i=0; i<coins.length; i++) {
				if((n-coins[i]) >= 0 && ans[(int)n-1][i] == 0) {
					if((n-1-coins[i]) >=0 && ans[(int) (n-1-coins[i])][i] != 0) {
						ans[(int)n-1][i] = ans[(int) (n-1-coins[i])][i] + 1;
						//ans[(int)n-1][i] = 1;
					} else {
						ans[(int)n-1][i] = findWays(n-coins[i], coins, ans);
					}
					System.out.println(String.format("ans[%d][%d]=%d", n-1, i, ans[(int)n-1][i]));
					sum += ans[(int)n-1][i];
				}
			}
			
			return sum;
		}
	}

	public static void main(String ...args) {
		long [] coins = {1,2,3};
		long n = 8;
		System.out.println(getWays(n, coins));
	}

}
