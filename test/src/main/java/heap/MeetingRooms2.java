package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * http://www.learn4master.com/interview-questions/leetcode/leetcode-meeting-rooms-ii-java
 * 
 * Meeting Rooms II
 * Given an array of meeting time intervals consisting of start and end times 
 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 * 
 * This is a follow up question for the Meeting Rooms one. 
 * 
 * @author ketav
 *
 */
public class MeetingRooms2 {

	public static class Interval {
		public int start, end;
		public Interval(int s, int e) {
			start = s; end = e;
		}
	}
	
	Comparator<Interval> startTimeCom = (i1, i2)->Integer.compare(i1.start, i2.start);
	Comparator<Interval> endTimeCom = (i1, i2)->Integer.compare(i1.end, i2.end);
	
	public int minMeetingRooms(int[][] intervals) {
		//guard block
		if(intervals == null || intervals.length == 0)
			return 0;
		
		//build Interval list
		List<Interval> lst = Arrays.stream(intervals)
				.map((interval)->new Interval(interval[0], interval[1]))
				.collect(Collectors.toList());
		
		//sort by start time
		Collections.sort(lst, startTimeCom);
		
		//put it into queue by end time
		PriorityQueue<Interval> queue = new PriorityQueue<>(endTimeCom);
		
		int minRooms = 0;
		
		for(Interval interval : lst) {
			while (!queue.isEmpty() && queue.peek().end < interval.start)
				queue.poll();
			queue.add(interval);
			minRooms = (queue.size()>minRooms) ? queue.size() : minRooms; 
		}
		
		return minRooms;
	}
	
	public static void main (String [] args) {
		System.out.println(new MeetingRooms2().minMeetingRooms(new int [][] {{0,30},{5,10},{15,20}}));
	}
	
}
