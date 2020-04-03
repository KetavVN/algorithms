package number;

/*
https://leetcode.com/problems/happy-number/

leet code challenge 30 days - day 2

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

Solution details:
Beats 87.88% of the solutions wrt to time taken
Runtime: 1 ms
Memory Usage: 36.5 MB


*/
public class HappyNumber {
    public boolean isHappy(int n) {
        boolean happyNumber = false;
        
        long num = n;
        //int digit = 0;
        long sqSum = 0;
        int i = 0;
        
        while(i<15 && !happyNumber) {
            while(num > 0) { //squre all digits
                sqSum += Math.pow(num%10, 2);
                num /= 10;
            }
            happyNumber = sqSum == 1;
            num = sqSum;
            sqSum = 0;
            i++;
        }
        
        return happyNumber;
    }
}
