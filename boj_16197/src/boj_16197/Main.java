package boj_16197;

import java.io.*;
import java.util.*;

public class Main {

	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Status{
		Point coin1;
		Point coin2;
		int cnt;
		public Status(Point coin1, Point coin2, int cnt) {
			this.coin1 = coin1;
			this.coin2 = coin2;
			this.cnt = cnt;
		}
	}
	
	static char[][] matrix;
	static int N, M;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		int min = Integer.MAX_VALUE;
		matrix = new char[N][M];
		
		Point[] coins = new Point[2];
		int idx = 0;
		for(int i = 0; i < N; i++) {
			String row = br.readLine();
			
			for(int j = 0; j < M; j++) {
				char now = row.charAt(j);
				if(now == 'o') {
					coins[idx++] = new Point(i, j);
				}
				matrix[i][j] = now;
				
			}
		}
		
		Queue<Status> que = new LinkedList<>();
		
		que.add(new Status(coins[0], coins[1], 0));
		
		while(!que.isEmpty()) {
			Status now = que.poll();
			
			int out = out_check(now.coin1, now.coin2);
			if(out == 1) {
				min = Math.min(min, now.cnt);
				break;
			}else if(out == 2) {
				continue;
			}
			
			if(now.cnt == 10) {
				continue;
			}
			for(int z = 0; z < 4; z++) {
				Point coin1 = now.coin1;
				Point coin2 = now.coin2;
				
				int nextX1 = coin1.x + dx[z];
				int nextY1 = coin1.y + dy[z];
				
				if(nextX1 >= 0 && nextX1 < N && nextY1 >= 0 && nextY1 < M && matrix[nextX1][nextY1] == '#') {
					nextX1 = coin1.x;
					nextY1 = coin1.y;
				}
				
				int nextX2 = coin2.x + dx[z];
				int nextY2 = coin2.y + dy[z];
				
				if(nextX2 >= 0 && nextX2 < N && nextY2 >= 0 && nextY2 < M && matrix[nextX2][nextY2] == '#') {
					nextX2 = coin2.x;
					nextY2 = coin2.y;
				}
				
				que.add(new Status(new Point(nextX1, nextY1), new Point(nextX2, nextY2), now.cnt + 1));
				
				
			}
			
		}
		
		if(min == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(min);
		}
		
		
	}
	
	static int out_check(Point one, Point two) {
		int cnt = 0;
		if(one.x < 0 || one.x >= N || one.y < 0 || one.y >= M) {
			cnt++;
		}
		if(two.x < 0 || two.x >= N || two.y < 0 || two.y >= M) {
			cnt++;
		}
		
		return cnt;
	}

}
