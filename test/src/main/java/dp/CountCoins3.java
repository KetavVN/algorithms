package dp;

import java.util.*;

public class CountCoins3 {

	public static long countWays2(long n, int [] coins) {

		long [] ans = new long[(int)(n+1)];
		ans[0] = 1l;

		for(int c=0; c<coins.length; c++) {
			for(int j=coins[c]; j<=n; j++) {
				ans[j] += ans[j-coins[c]];
			}
		}

		return ans[(int)n];
	}

	public static long countWays(long n, int [] coins) {

		Map<Long, Long> ans = new HashMap<>();
		ans.put(0l, 1l);	//prefill

		for(long i=1; i<=n; i++) {
			long sum = 0;
			for(int j=0; j<coins.length; j++) {
				if((i-coins[j]) >= 0) {
					sum += ans.get(i-coins[j]);
				}
			}
			ans.put(i, sum);
		}

		for(Map.Entry<Long, Long> entry : ans.entrySet()) {
			System.out.println(entry.getKey()+"->"+entry.getValue());
		}
		
		return ans.get(n);
	}

	public static void main(String ...args) {
		long n = 5;
		int [] coins = {1, 2, 3};
		System.out.println(countWays2(n, coins));
	}
	
}

