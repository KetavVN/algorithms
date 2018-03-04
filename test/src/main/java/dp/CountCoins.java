package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountCoins {

	public static long getWays(long n, long[] coins){
		//npe check
		//Arrays.sort(coins);
		long [] arr = new long [(int)n]; 
		Map<List<Long>, Long> visited = new HashMap<>();
		long ret = findWays(n, coins, arr, new ArrayList<Long>(), visited);
		for(List<Long> lst : visited.keySet()) {
			System.out.println(lst);
		}
		return ret;
	}

	public static long findWays(long n, long [] coins, long [] ans, List<Long> currentCoins, Map<List<Long>, Long> visited) {
		if(n == 0) {
			System.out.println("add current:" + currentCoins);
			visited.put(currentCoins, 1l);
			return 1;
		} /*else if (visited.containsKey(currentCoins)) {
			System.out.println("found existing:"+currentCoins);
			return 0;//visited.get(currentCoins);
		} else if(ans[(int)n-1] != 0) {
			return ans[(int)n-1]+1;
		}*/ else {
			long sum = 0; 
			for(int i=0; i<coins.length; i++) {
				if((n-coins[i]) >= 0) {
					List<Long> newCurrent = new ArrayList<>(currentCoins);
					newCurrent.add(coins[i]);
					Collections.sort(newCurrent);
					if(!visited.containsKey(newCurrent)) {
						//System.out.println("newCurrent:" + newCurrent);
						sum += findWays(n-coins[i], coins, ans, newCurrent, visited);
					} /*else {
						System.out.println("found existing1:"+newCurrent);
					}*/
				} /*else {
					System.out.println(String.format("n=%d coins[%d]=%d", n, i, coins[i]));
				}*/
			}
			ans[(int)n-1] = sum;
			return ans[(int)n-1];
		}
	}

	public static void main(String ...args) {
		long [] coins = {1,2,3};
		long n = 4;
		System.out.println(getWays(n, coins));
	}

}
