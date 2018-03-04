package array;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class RectangleFinder {

	public static class Point implements Comparable<Point> {
		int x, y, width;
		public Point (int x, int y, int w) {
			this.x = x;
			this.y = y;
			width = w;
		}

		@Override
		public boolean equals(Object o) {
			if(o instanceof Point) {
				Point other = (Point) o;
				return x == other.x && y == other.y;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return width * x + y;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}

		@Override
		public int compareTo(Point o) {
			if(o == null) { 
				return 1;
			}
			return hashCode()-o.hashCode();
		}

	}

	public static Set<SortedSet<Point>> findRectangles(int [][] arr) {
		//npe checks
		Set<SortedSet<Point>> allRect = new HashSet<>();
		Set<Point> visited = new HashSet<>();
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				Point current = new Point(i, j, arr[i].length);
				if(arr[i][j] == 0 && !visited.contains(current)) {
					SortedSet<Point> rect = new TreeSet<>();
					findAllRect(arr, current, rect, visited);
					allRect.add(rect);
				}
			}
		}

		return allRect;
	}

	public static void findAllRect(int [][] arr, Point current, 
			SortedSet<Point> rect, Set<Point> visited) {
		if(!visited.contains(current) && arr[current.x][current.y]==0) {
			visited.add(current);
			rect.add(current);
			if(current.x+1 < arr.length) {
				Point p = new Point(current.x+1, current.y, arr[current.x+1].length);
				findAllRect(arr, p, rect, visited);
			}
			if(current.y+1 < arr[current.x].length) {
				Point p = new Point(current.x, current.y+1, arr[current.x].length);
				findAllRect(arr, p, rect, visited);
			}
		}
	}

	public static void main(String ...args) {
		int [][] arr = new int [][] {
		//   0  1  2  3  4
			{1, 0, 0, 0, 1},	//0
			{0, 1, 1, 1, 1},	//1
			{1, 1, 1, 0, 0},	//2
			{0, 0, 1, 0, 0},	//3
			{0, 0, 1, 1, 1}		//4
		};
		
		Set<SortedSet<Point>> allRect = findRectangles(arr);
		for(SortedSet<Point> rect : allRect) {
			System.out.println(String.format("upperLeft=%s lowerRight=%s", rect.first(), rect.last()));
		}
	}

}
