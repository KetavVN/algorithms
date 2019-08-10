package array;

//https://leetcode.com/problems/remove-duplicates-from-sorted-array
public class RemoveDuplicate {

    //Runtime: 1 ms, faster than 97.48% of Java online submissions for Remove Duplicates from Sorted Array.
    //Memory Usage: 38.6 MB, less than 99.47% of Java online submissions for Remove Duplicates from Sorted Array.
    public static int removeDuplicates(int[] nums) {
        int count = 0;
        if(nums != null && nums.length != 0) {
            count = 1;
            int prev = 1;
            for(int i=1; i<nums.length; i++) {
                if(nums[i-1]!=nums[i]) {
                    count++;
                    nums[prev] = nums[i];
                    prev++;
                }
            }
        }
        return count;
    }
}
