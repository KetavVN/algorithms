package array;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * 
 * Note that you cannot sell a stock before you buy one.
 * 
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * 
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * 
 * Example 2:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * 
 * following solution beats 75.75% solutions
 * 
 * @author Ketav
 *
 */
public class BuySellStock1 {
	
	public int maxProfit(int[] prices) {

		if(prices == null || prices.length == 0) {
			return 0;
		}

		int min=0, max=0;

		int cmin = prices[0];
		int cmax = prices[0];

		for(int i=0; i<prices.length; i++) {
			//System.out.println(String.format("min=%-3d max=%-3d cmin=%-3d cmax=%-3d",min, max, cmin, cmax));
			if(prices[i] < cmin) {    
				cmin = prices[i];
				cmax = prices[i];
			} else if(prices[i] >= cmax) {
				cmax = prices[i];
			}
			if((max-min) < (cmax-cmin)) {
				min = cmin;
				max = cmax;
			}
		}

		return (max-min);

	}
	
	public static void main (String ... args) {
		System.out.println(new BuySellStock1().maxProfit(new int [] {7,1,5,3,6,4}));
		System.out.println(new BuySellStock1().maxProfit(new int [] {7,6,4,3,1}));
	}
	
}
