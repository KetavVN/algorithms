package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

There are some spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter, and hence the x-coordinates of start and end of the diameter suffice. The start is always smaller than the end.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps traveling up infinitely.

Given an array points where points[i] = [xstart, xend], return the minimum number of arrows that must be shot to burst all balloons.

Example 1:

Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).

Example 2:

Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4

Example 3:

Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2

Example 4:

Input: points = [[1,2]]
Output: 1

Example 5:

Input: points = [[2,3],[2,3]]
Output: 1

Constraints:

    0 <= points.length <= 104
    points.length == 2
    -231 <= xstart < xend <= 231 - 1

submission:
32.64% faster than other solutions

*/
public class MinArrows {
    
    private static class Baloon {
        int start, end;
        public Baloon(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", start, end);
        }
    }

    private Comparator<Baloon> ascComp = new Comparator<Baloon>() {
        @Override
        public int compare(Baloon b1, Baloon b2) {
            if(b1 == b2) return 0;
            //if(b1 ==null && b2 != null) return -1;
            //if(b1!=null && b2 == null) return 1;
            return Integer.compare(b1.start, b2.start);
        }
    };

    @SuppressWarnings("unused")
	private Comparator<Baloon> descComp = new Comparator<Baloon>() {
        @Override
        public int compare(Baloon b1, Baloon b2) {
            if(b1 == b2) return 0;
            if(b1 ==null && b2 != null) return -1;
            if(b1!=null && b2 == null) return 1;
            return Integer.compare((b1.end - b1.start), (b2.end - b2.start));
        }
    };
                                   
    /**
        Below solution may not work all the time
        After sorting by start, 
            Need to traverse both branches - shoot baloon or not shoot [recursive]
        ex: B[1,10], B[1,2], B[3,5], B[4,6], B[4,8]
        Think of DP solution [even better than recursive one]
    **/
    public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0) {
            return 0;
        }

        PriorityQueue<Baloon> q = new PriorityQueue<>(ascComp);
        for(int i=0; i<points.length; i++) {
            q.add(new Baloon(points[i][0], points[i][1]));
        }

        int arrows = 0;
        while(!q.isEmpty()) {
            Baloon b = q.remove();
            int e = b.end;
            while(!q.isEmpty() && q.peek().start <= e) {
                Baloon b1 = q.remove();
                e = b1.end < e ? b1.end : e;
            }
            arrows++;
        }
        return arrows;
    }
}
