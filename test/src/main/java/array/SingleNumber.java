package array;

/*
https://leetcode.com/problems/single-number/

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1

Example 2:

Input: [4,1,2,1,2]
Output: 4

Solution Details:
Beats 36.95% of solutions wrt execution time
Execution time 5ms
Memory: 40mb

*/
public class SingleNumber {

  public int singleNumber(int[] nums) {
        //input validation not required
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            if(set.contains(n)) {
                set.remove(n);
            } else {
                set.add(n);
            }
        }
        return set.iterator().next();
    }
}
