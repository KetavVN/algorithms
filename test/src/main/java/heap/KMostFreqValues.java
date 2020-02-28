package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 * 
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * 
 * Input: nums = [1], k = 1
 * Output: [1]
 * 
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * 
 * Success Details
 * Runtime: 11 ms, faster than 75.11% of Java online submissions for Top K Frequent Elements.
 * Memory Usage: 42.6 MB, less than 7.76% of Java online submissions for Top K Frequent Elements.
 * 
 * @author a521492
 *
 */
public class KMostFreqValues {

	public static class ValCount implements Comparable<ValCount> {

		public int val, count;

		public ValCount(int v, int c) {
			val = v;
			count=c;
		}

		@Override
		public int hashCode() {
			return val;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof ValCount)) return false;
			ValCount o2 = (ValCount) o;
			return val == o2.val;
		}

		@Override
		public int compareTo(ValCount o2) {
			if(o2 == null) return 1;
			return Integer.compare(o2.count, count);
		}
	}

	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> valCountMap = new HashMap<>();
		for(int v : nums) {
			if(!valCountMap.containsKey(v)) {
				valCountMap.put(v, 1);
			} else {
				valCountMap.put(v, valCountMap.get(v)+1);
			}
		}
		
		PriorityQueue<ValCount> valCount = new PriorityQueue<>(); 
		for(Map.Entry<Integer, Integer> entry : valCountMap.entrySet()) {
			valCount.add(new ValCount(entry.getKey(), entry.getValue()));
		}
		
		List<Integer> ret = new ArrayList<>();
		for (int i = 0; i < k /* && i<valCount.size() */; i++) {
			ret.add(valCount.poll().val);
		}
		
		return ret;
	}
	
	public static void main(String [] args) {
		System.out.println(new KMostFreqValues().topKFrequent(new int [] {2, 2, 1, 1, 1, 1, 0, 0, 0, 0}, 2));
		System.out.println(new KMostFreqValues().topKFrequent(new int [] {2, 2}, 2));
		System.out.println(new KMostFreqValues().topKFrequent(new int [] {2, 2}, 0));
		System.out.println(new KMostFreqValues().topKFrequent(new int [] {2, 1}, 1));
		System.out.println(new KMostFreqValues().topKFrequent(new int [] {2, 1}, 2));
	}

}
