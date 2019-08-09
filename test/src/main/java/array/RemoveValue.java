//https://leetcode.com/problems/remove-element/submissions/
public class Solution {

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Element.
    //Memory Usage: 36.3 MB, less than 100.00% of Java online submissions for Remove Element.
    public int removeElement(int[] nums, int val) {
        
        int count = 0;
        
        if(nums == null || nums.length == 0) {
            return count;
        }
        
        for(int i=0; i<nums.length; i++) {
            if(nums[i] != val) {
                nums[count++] = nums[i];
            }
        }
        
        return count;
    }
}
