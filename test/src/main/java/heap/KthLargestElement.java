/**
https://leetcode.com/problems/kth-largest-element-in-an-array/submissions/

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

Success Details
Runtime: 3 ms, faster than 75.69% of Java online submissions for Kth Largest Element in an Array.
Memory Usage: 40.9 MB, less than 5.18% of Java online submissions for Kth Largest Element in an Array.
 */
public class KthLargestElement {

	public int findKthLargest(int[] nums, int k) {
		if(nums == null || nums.length == 0) {
			return 0;
		}

		PriorityQueue<Integer> queue = new PriorityQueue<>(/*new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1);
			}
		}*/);

		for(int i=0; i<k && i<nums.length; i++) {
			queue.add(nums[i]);
		}

		for(int i=k; i<nums.length; i++) {
			if(nums[i] > queue.peek()) {
				queue.poll();
				queue.add(nums[i]);
			}
		}

		return queue.poll();

	}
}
