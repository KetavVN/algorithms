package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/sliding-window-median/
 * 
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * Examples:
 * 
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. 
 * Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
 * 
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7      -1
 *  1  3 [-1  -3  5] 3  6  7      -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 *  
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 * Note:
 * You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 * 
 * Success Details
 * Runtime: 26 ms, faster than 89.33% of Java online submissions for Sliding Window Median.
 * Memory Usage: 53.8 MB, less than 20.00% of Java online submissions for Sliding Window Median.
 * 
 * Next challenges:
 * Longest Repeating Character Replacement --med
 * Minimum Number of K Consecutive Bit Flips -- hard
 * Max Consecutive Ones III	--med
 * 
 * @author ketav
 */
public class ArrayWindowMedians {

	public double[] medianSlidingWindow(int[] nums, int k) {
		
		//NavigableLinkedHashMap -- order and sorting both are required
        //order is required to remove/add elements
        //Navigable is due to sorting requirement within the window
        
        if(nums == null || nums.length == 0) {
            return new double[0];
        }
        
        List<Integer> sortedList = new ArrayList<>();
        for(int i=0; i<k-1; i++) {
            sortedList.add(nums[i]);
        }
        Collections.sort(sortedList);
        
        double [] ans = new double[nums.length-k+1];
        
        int i = k-1;
        do {
            addNum(sortedList, nums[i]);    //array has k elements now
            //System.out.println(sortedList);
            ans[i-k+1] = (k%2 == 1) ? sortedList.get(k/2) 
                : ((double)sortedList.get(k/2-1) + (double)sortedList.get(k/2))/2d;
            findAndRemove(sortedList, nums[i-k+1]);   //array has k-1 elements now
            i++;
        } while(i<nums.length);
        
        return ans;
    }
    
    public void addNum(List<Integer> sortedList, int num) {
        int ind = getInd(sortedList,0, sortedList.size()-1, num);
        sortedList.add(ind, num);
    }
    
    public int getInd(List<Integer> sortedList, int start, int end, int num) {
        if(start<end) {
            int mid = (start+end)/2;
            if(sortedList.get(mid) == num) {
                return mid;
            } else if (sortedList.get(mid) < num) {
                return getInd(sortedList, mid+1, end, num);
            } else {
                return getInd(sortedList, start, mid-1, num);
            }
        }
        return sortedList.isEmpty() ? start : sortedList.get(start) < num ? start+1 : start;
        
    }
    
    
    public void findAndRemove(List<Integer> sortedList, int num) {
        int ind = getInd(sortedList, 0, sortedList.size()-1, num);
        sortedList.remove(ind);
    }
	
	public static void main(String[] args) {
		ArrayWindowMedians obj = new ArrayWindowMedians();
		//double [] ans = obj.medianSlidingWindow(new int [] {1,3,-1,-3,5,3,6,7}, 3);
		double [] ans = obj.medianSlidingWindow(new int [] {1,2,3}, 0);
		System.out.print("\nans=");
		for(double a : ans) {
			System.out.print(a+" ");
		}
	}

}
