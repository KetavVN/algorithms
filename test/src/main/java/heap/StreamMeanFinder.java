package heap;

/**
  https://leetcode.com/problems/find-median-from-data-stream/
  
  Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
  For example,

  [2,3,4], the median is 3

  [2,3], the median is (2 + 3) / 2 = 2.5

  Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.

  Example:

  addNum(1)
  addNum(2)
  findMedian() -> 1.5
  addNum(3) 
  findMedian() -> 2
 
  Follow up:

    If all integer numbers from the stream are between 0 and 100, how would you optimize it?
    If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

  Success Details
  Runtime: 48 ms, faster than 66.01% of Java online submissions for Find Median from Data Stream.
  Memory Usage: 55 MB, less than 100.00% of Java online submissions for Find Median from Data Stream.
 */
public class StreamMeanFinder {

    PriorityQueue<Integer> lowerMaxQueue = null;
    PriorityQueue<Integer> higherMinQueue = null;
    
    /** initialize your data structure here. */
    public MedianFinder() {
        lowerMaxQueue = new PriorityQueue<>((x,y)->Integer.compare(y,x));
        higherMinQueue = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(lowerMaxQueue.size() <= higherMinQueue.size()) {
            lowerMaxQueue.offer(num);
        } else {
            higherMinQueue.offer(num);
        }
        
        while(!higherMinQueue.isEmpty() 
              && !lowerMaxQueue.isEmpty() 
              && higherMinQueue.peek() < lowerMaxQueue.peek()) {
            int temp1 = higherMinQueue.poll();
            int temp2 = lowerMaxQueue.poll();
            higherMinQueue.offer(temp2);
            lowerMaxQueue.offer(temp1);
        }
        
    }
    
    public double findMedian() {
        if(higherMinQueue.size() > lowerMaxQueue.size()) {
            return higherMinQueue.peek();
        } else if (higherMinQueue.size() < lowerMaxQueue.size()) {
            return lowerMaxQueue.peek();
        } else {
            return ((double)higherMinQueue.peek() + (double)lowerMaxQueue.peek() ) / 2d;
        }
    }

}
