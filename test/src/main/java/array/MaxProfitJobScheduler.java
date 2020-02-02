package array;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 * 
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * 
 * You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.
 * 
 * If you choose a job that ends at time X you will be able to start another job that starts at time X. 
 * 
 * Example 1:
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * 
 * Explanation: The subset chosen is the first and fourth job. 
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

 * @author Ketav
 *
 */
public class MaxProfitJobScheduler {
	
	public static class Key implements Comparable<Key>{
		public int start, profit;

		public Key(int s, int p) {
			start =  s;
			profit = p;
		}
		
		@Override
		public int compareTo(Key o) {
			if(o == null) return 1;
			if (this == o) return 0;
			if(start > o.start) return 1;
			if(start == o.start && profit < o.profit) return 1;
			if(start == o.start && profit == o.profit) return 0;
			return -1;
		}
	}
	
	public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		sortInput(startTime, endTime, profit);
		return jobScheduling2(startTime, endTime, profit, 0, 0, 0);
	}
	
	private static void sortInput(int[] startTime, int[] endTime, int[] profit) {
		Map<Key, int []> sortedInput = new TreeMap<>();
		int i=0;
		for(Integer time : startTime) {
			sortedInput.put(new Key(time, profit[i]), new int [] {startTime[i], endTime[i], profit[i]});
			i++;
		}
		i=0;
		for(Map.Entry<Key, int[]> entry : sortedInput.entrySet()) {
			startTime[i] = entry.getValue()[0];
			endTime[i] = entry.getValue()[1];
			profit[i] = entry.getValue()[2];
			System.out.println(String.format("startTime=%d endTime=%d profit=%d", startTime[i], endTime[i], profit[i]));
			i++;
		}
	}

	public static int jobScheduling(int [] stimes, int [] etimes, int [] profits, int ind, int total, List<Integer> list) {
		
		if(ind >= stimes.length)
			return total;
		
		int maxProfit = 0;
		for(int i=ind; i<stimes.length; i++) {
			if(!list.contains(i)) {
				List<Integer> overlapingJobs = getConflictingIndexes(stimes, etimes, i);
				System.out.println(String.format("currentIndex=%-2d overlap=%-10s prev=%-5d profit=%-5d total=%-5d", 
						i, overlapingJobs, total, profits[i], total+profits[i]));
				for(Integer j : overlapingJobs) {
					//int totalProfit = jobScheduling(stimes, etimes, profits, j+1, total+profits[j], overlapingJobs);
					int totalProfit = jobScheduling(stimes, etimes, profits, overlapingJobs.get(overlapingJobs.size()-1)+1, total+profits[j], overlapingJobs);
					if(totalProfit > maxProfit)
						maxProfit = totalProfit;
				}
			} else {
				System.out.println(String.format("skip-index=%d, total=%d", i, total));
			}
		}
		
		return maxProfit;
	}

	public static int jobScheduling2(int [] stimes, int [] etimes, int [] profits, int ind, int total, int endTime) {
		
		if(ind >= stimes.length) {
			System.out.println(String.format("return=%d", total));
			return total;
		}
		
		int maxProfit = 0;
		for(int i=ind; i<stimes.length; i++) {
			int totalProfit = 0;
			if(stimes[i] >= endTime) {
				System.out.println(String.format("currentIndex=%-2d prev=%-5d profit=%-5d total=%-5d", 
						i, total, profits[i], total+profits[i]));
				//int totalProfit = jobScheduling(stimes, etimes, profits, j+1, total+profits[j], overlapingJobs);
				totalProfit = jobScheduling2(stimes, etimes, profits, i+1, total+profits[i], endTime>etimes[i]?endTime:etimes[i]);
			}
			if(totalProfit > maxProfit) {
				maxProfit = totalProfit;
				System.out.println(String.format("maxProfit=%d", maxProfit));
			}
		}
		
		return total>maxProfit?total:maxProfit;
	}

	/**
	 * Calling index will be part of return array
	 */
	private static List<Integer> getConflictingIndexes(int[] stimes, int[] etimes, int ind) {
		List<Integer> overlapingJobs = new ArrayList<>();
		for(int i = ind; i< stimes.length; i++) {
			if (/* stimes[ind] <= stimes[i] && */ stimes[i] < etimes[ind])
				overlapingJobs.add(i);
		}
		return overlapingJobs;
	}
	
	public static void main(String [] args) {
		//System.out.println(jobScheduling(new int [] {1,2,3,3}, new int [] {3,4,5,6}, new int [] {50,10,40,70}));	//works
		//System.out.println(jobScheduling(new int [] {1,2,3,4,6}, new int [] {3,5,10,6,9}, new int [] {20,20,100,70,60}));	//works
		//System.out.println(jobScheduling(new int [] {1,1,1}, new int [] {2,3,4}, new int [] {5,6,4}));	//works
		//System.out.println(jobScheduling(new int [] {6,15,7,11,1,3,16,2}, new int [] {19,18,19,16,10,8,19,8}, new int [] {2,9,1,19,5,7,3,19}));	//works
		//System.out.println(jobScheduling(new int [] {1,2,2,3}, new int [] {2,5,3,4}, new int [] {3,4,1,2}));	//works
		System.out.println(jobScheduling(new int [] {15,44,15,47,11,18,5,41,38,25,19,25}, 
				new int [] {33,48,20,49,37,22,32,48,39,37,38,40}, new int [] {18,19,16,1,5,12,17,7,19,9,18,9}));	//does not works - expected 61 actual 56
	}
	
}
