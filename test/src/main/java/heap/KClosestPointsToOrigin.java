package heap;

import java.util.PriorityQueue;

/**
  https://leetcode.com/problems/k-closest-points-to-origin/
  
  We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

  (Here, the distance between two points on a plane is the Euclidean distance.)

  You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 
  Example 1:

  Input: points = [[1,3],[-2,2]], K = 1
  Output: [[-2,2]]
  Explanation: 
  The distance between (1, 3) and the origin is sqrt(10).
  The distance between (-2, 2) and the origin is sqrt(8).
  Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
  We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

  Example 2:

  Input: points = [[3,3],[5,-1],[-2,4]], K = 2
  Output: [[3,3],[-2,4]]
  (The answer [[-2,4],[3,3]] would also be accepted.)

  Note:

    1 <= K <= points.length <= 10000
    -10000 < points[i][0] < 10000
    -10000 < points[i][1] < 10000

  Success Details
  Runtime: 29 ms, faster than 45.10% of Java online submissions for K Closest Points to Origin.
  Memory Usage: 47.4 MB, less than 100.00% of Java online submissions for K Closest Points to Origin.
  
 */
 class KClosestPointsToOrigin {
    
    public static class Point1 implements Comparable<Point1> {
        public int x, y;
        double distance;
        
        public Point1(int a, int b) {
            x=a; y=b;
            distance=Math.sqrt(x*x + y*y);
        }
        
        @Override
        public int compareTo(Point1 o2) {
            if(!(o2 instanceof Point1)) return -1;
            return Double.compare(o2.distance, distance);
        }
    }
    
    public int[][] kClosest(int[][] points, int k) {
        
        //if(points == null || points.length==0) {
        //    return null;
        //}
        
        PriorityQueue<Point1> maxQueue = new PriorityQueue<>();
        for(int i=0; i<points.length && i<k; i++) {
            maxQueue.add(new Point1(points[i][0], points[i][1]));
        }
        
        for(int i=k; i<points.length; i++) {
            Point1 p = new Point1(points[i][0], points[i][1]);
            if(maxQueue.peek().distance > p.distance) {
                maxQueue.remove();
                maxQueue.add(p);
            }
        }
        
        int len = maxQueue.size();
        int [][] ret = new int[len][2];
        
        for(int i=0; i<len; i++) {
            Point1 p = maxQueue.remove();
            ret[i][0] = p.x;
            ret[i][1] = p.y;
        }
        
        return ret;
    }
    
}
