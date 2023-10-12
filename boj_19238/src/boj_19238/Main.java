package boj_19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Passenger implements Comparable<Passenger>{
		
		int num;
		Point start;
		Point end;
		
		public Passenger(int num, Point start, Point end) {
			this.num = num;
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Passenger o) {
			if(this.start.x == o.start.x) {
				return this.start.y - o.start.y;
			}
			return this.start.x - o.start.x;
		}
		
	}
	
	static class Point{
		int x;
		int y;
		int dist;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	static int N, M, fuel;
	static int[][] matrix;
	static Passenger[] passenger_arr;
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	static boolean[][] is_passenger;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		fuel = Integer.parseInt(input[2]);
		
		matrix = new int[N + 1][N + 1];
		passenger_arr = new Passenger[M];
		for(int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(input[j - 1]);
			}
		}
		input = br.readLine().split(" ");
		Point taxi = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		
		is_passenger = new boolean[N + 1][N + 1]; 
		for(int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int startX = Integer.parseInt(input[0]);
			int startY = Integer.parseInt(input[1]);
			int endX = Integer.parseInt(input[2]);
			int endY = Integer.parseInt(input[3]);
			is_passenger[startX][startY] = true;
			passenger_arr[i] = new Passenger(i + 1, new Point(startX, startY), new Point(endX, endY));
		}
		int cnt = M;
		
		while(cnt != 0) {
			Passenger next_passenger = get_next_passenger(taxi);
			if(next_passenger == null) {
				System.out.println(-1);
				return;
			}
			taxi.x = next_passenger.start.x;
			taxi.y = next_passenger.start.y;
			int dist = get_distance(taxi, next_passenger);
			if(fuel < dist) {
				System.out.println(-1);
				return;
			}
			if(dist == -1) {
				System.out.println(-1);
				return;
			}
			fuel -= dist;
			fuel = fuel + (dist * 2);
			taxi.x = next_passenger.end.x;
			taxi.y = next_passenger.end.y;
			is_passenger[next_passenger.start.x][next_passenger.start.y] = false;
			cnt--;
		}
		
		System.out.println(fuel);
		
	}
	
	static Passenger get_next_passenger(Point taxi) {
		Queue<Point> que = new LinkedList<>();
		PriorityQueue<Passenger> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		que.add(new Point(taxi.x, taxi.y, 0));
		visited[taxi.x][taxi.y] = true;
		if(is_passenger[taxi.x][taxi.y]) {
			return get_passenger(new Point(taxi.x, taxi.y));
		}
		int shortest = Integer.MAX_VALUE;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			for(int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX > N || nextX < 1 || nextY > N || nextY < 1) {
					continue;
				}
				
				if(!visited[nextX][nextY] && matrix[nextX][nextY] == 0) {
					if(is_passenger[nextX][nextY] && shortest >= now.dist + 1) {
						shortest = Math.min(shortest, now.dist + 1);
						pq.add(get_passenger(new Point(nextX, nextY)));
					}
					que.add(new Point(nextX, nextY, now.dist + 1));
					visited[nextX][nextY] = true;
				}
			}
		}
		
		if(fuel < shortest) {
			return null;
		}
		fuel = fuel - shortest;
		
		
		return pq.poll();
		
		
	}
	
	static Passenger get_passenger(Point point) {
		for(int i = 0; i < M; i++) {
			if(passenger_arr[i].start.x == point.x && passenger_arr[i].start.y == point.y) {
				return passenger_arr[i];
			}
		}
		return null;
	}
	
	static int get_distance(Point taxi, Passenger passenger) {
		int[][] distance = new int[N + 1][N + 1];
		Queue<Point> que = new LinkedList<>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		que.add(new Point(taxi.x, taxi.y, 0));
		visited[taxi.x][taxi.y] = true;
		distance[taxi.x][taxi.y] = 0;
		while(!que.isEmpty()) {
			Point now = que.poll();
			for(int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX > N || nextX < 1 || nextY > N || nextY < 1) {
					continue;
				}
				
				if(!visited[nextX][nextY] && matrix[nextX][nextY] == 0) {
					if(passenger.end.x == nextX && passenger.end.y == nextY) {
						return now.dist + 1;
					}
					que.add(new Point(nextX, nextY, now.dist + 1));
					visited[nextX][nextY] = true;
					distance[nextX][nextY] = now.dist + 1;
				}
			}
		}
		
		return -1;
		
	}

}
