package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
  https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix
  
  Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

  Note that it is the kth smallest element in the sorted order, not the kth distinct element.

  Example:

  matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
  ],
  k = 8,

  return 13.
  
  Note:
  You may assume k is always valid, 1 ≤ k ≤ n2.

  Success Details
  Runtime: 17 ms, faster than 42.29% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
  Memory Usage: 45.3 MB, less than 24.32% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
 */
public class KthSmallestElementInSortedMatrix {
    //K way merge sort
    public static class Point1 implements Comparable<Point1> {
        int val;
        int row;
        public Point1(int v, int r) {
            val = v; row = r;
        }
        @Override
        public int compareTo(Point1 p2) {
            if(!(p2 instanceof Point1)) return 1;
            return Integer.compare(val, p2.val);
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        //input validation not required
        int [] indArr = new int[matrix.length];
        List<Integer> sortedList = new ArrayList<>(k);
        PriorityQueue<Point1> minQueue = new PriorityQueue<>(matrix.length);
        
        for(int i=0; i<k && i<matrix.length; i++)
            minQueue.add(new Point1(matrix[i][0], i));
        
        for(int i=0; i<k; i++) {
            Point1 p = minQueue.poll();
            sortedList.add(p.val);
            indArr[p.row]++;
            if(indArr[p.row] < matrix.length) {
                minQueue.add(new Point1(matrix[p.row][indArr[p.row]], p.row));
            }
        }
        
        return sortedList.get(sortedList.size()-1);
    }
}
