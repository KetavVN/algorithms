package array;

/*
 * https://leetcode.com/problems/median-of-two-sorted-arrays/submissions/
 * Success Details 
 * Runtime: 2 ms, faster than 99.97% of Java online submissions for Median of Two Sorted Arrays.
 * Memory Usage: 41.9 MB, less than 98.61% of Java online submissions for Median of Two Sorted Arrays.
 */
public class SortedListMedian {

	/**
	 * if arr.length % 2 == 1
	 * 	 median = arr[arr.length/2];
	 * else
	 *   median = ( arr[arr.length/2-1] + arr[arr.length/2] ) /2;
	 *    
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getMedian(int [] a, int [] b) {
		
		if((a == null || a.length == 0) && (b == null || b.length == 0))
			return 0;
		
		int len = a.length + b.length;
		int start = len % 2 == 1 ? len/2 : len/2-1;
		int end = len/2;
		
		int ai=0, bi=0, cnt=0;
		double sum = 0;
		int divisor = 0;
		
		//System.out.println(String.format("len=%d start=%d end=%d", len, start, end));
		
		//skip though unwanted part from both arrays
		for(; cnt<start && ai<a.length && bi<b.length; cnt++) {
			if(a[ai] < b[bi])
				ai++;
			else
				bi++;
		}
		
		//skip from b further
		if(cnt<start && ai==a.length) {
			bi += (start-cnt);
			
			//cnt = start;
			while(start <= end) {
				sum += b[bi++];
				start++;
				divisor++;
			}
			return sum/divisor;
		}
		
		//skip from a further
		if(cnt<start && bi==b.length) {
			ai += (start-cnt);
			
			//cnt = start;
			while(start <= end) {
				sum += a[ai++];
				start++;
				divisor++;
			}
			return sum/divisor;
		}
		
		for(; cnt<=end && ai<a.length && bi<b.length; cnt++, divisor++) {
			if(a[ai] < b[bi])
				sum += a[ai++];
			else
				sum += b[bi++];
		}
		
		for(; cnt<=end && ai<a.length; cnt++, divisor++)
			sum += a[ai++];
		
		for(; cnt<=end && bi<b.length; cnt++, divisor++)
			sum += b[bi++];
		
		return sum / divisor;
	}
	
	public static void main (String ... strings ) {
		System.out.println(getMedian(new int [] {1, 2}, new int [] {3, 4}));
		System.out.println(getMedian(new int [] {1, 3, 5}, new int [] {2, 4}));
		System.out.println(getMedian(new int [] {1, 3, 5}, new int [] {2, 4, 6}));
		System.out.println(getMedian(new int [] {1, 3, 5}, new int [] {}));
		System.out.println(getMedian(new int [] {}, new int [] {2, 4, 6}));
		System.out.println(getMedian(new int [] {}, new int [] {}));
		System.out.println(getMedian(new int [] {1,1,1,2,2,3,3}, new int [] {2, 4, 6}));
	}
	
}
