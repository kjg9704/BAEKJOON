package boj_18809;

import java.io.*;
import java.util.*;

public class Main {
	
	static class Point{
		int x;
		int y;
		int color;
		int cnt;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		public Point(int x, int y, int cnt, int color) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.color = color;
		}
	}

	static int N;
	static int M;
	static int G;
	static int R;
	
	static int[][] matrix;
	static ArrayList<Point> list;
	
	static int[] greens;
	static int[] reds;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int max;
	// G == 3, R == 4, flower = 5
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		G = Integer.parseInt(input[2]);
		R = Integer.parseInt(input[3]);
		
		matrix = new int[N][M];
		list = new ArrayList<Point>();
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				int num = Integer.parseInt(input[j]);
				matrix[i][j] = num;
				if(num == 2) {
					list.add(new Point(i, j));
				}
			}
			
		}
		
		greens = new int[G];
		reds = new int[R];
		
		dfs(0, 0, 0);
		

		
		System.out.println(max);
	}
	
	static void dfs(int cnt, int green, int red) {
		if(green == G && red == R) {
			int flowers = grow();
			max = Math.max(max, flowers);
			return;
		}
		
		if(green < G) {
			for(int i = cnt; i < list.size(); i++) {
				greens[green] = i;
				dfs(i + 1, green + 1, red);
			}
			
		}
		
		if(red < R) {
			for(int i = cnt; i < list.size(); i++) {
				reds[red] = i;
				dfs(i + 1, green, red + 1);
			}
		}
		
		
	}
	
	static int grow() {

		int[][] now = new int[N][M];
		int[][] count_arr = new int[N][M];
		int flowers = 0;
		for(int i = 0; i < N; i++) {
			now[i] = Arrays.copyOf(matrix[i], matrix[i].length);
		}
		
		Queue<Point> que = new LinkedList<>();
		
		for(int i = 0; i < G; i++) {
			Point p = list.get(greens[i]);
			p.color = 3;
			now[p.x][p.y] = 3;
			que.add(p);
		}
		
		for(int i = 0; i < R; i++) {
			Point p = list.get(reds[i]);
			p.color = 4;
			now[p.x][p.y] = 4;
			que.add(p);
		}
		

		while(!que.isEmpty()) {
			Point point = que.poll();
			if(now[point.x][point.y] == 5) {
				continue;
			}

			for(int z = 0; z < 4; z++) {
				int nextX = point.x + dx[z];
				int nextY = point.y + dy[z];
				
				if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || matrix[nextX][nextY] == 0) {
					continue;
				}
				
				if(now[nextX][nextY] == 1 || now[nextX][nextY] == 2) {
					now[nextX][nextY] = point.color;
					count_arr[nextX][nextY] = point.cnt + 1;
					que.add(new Point(nextX, nextY, point.cnt + 1, point.color));
				}else if(now[nextX][nextY] == 3 || now[nextX][nextY] == 4) {
					if(now[nextX][nextY] != point.color) {
						if(count_arr[nextX][nextY] == point.cnt + 1) {
							now[nextX][nextY] = 5;
							flowers++;
						}
					}
				}
					
				
			}
		}
		
		return flowers;

	}
	
	static void print(int[][] tmp) {
		System.out.println("-------------");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(tmp[i][j] == 3) {
					System.out.print("G ");
				}else if(tmp[i][j] == 4) {
					System.out.print("R ");
				}else if(tmp[i][j] == 5){
					System.out.print("F ");
				}else {
					System.out.print(tmp[i][j] + " ");
				}
				
			}
			System.out.println();
		}
	}

}
