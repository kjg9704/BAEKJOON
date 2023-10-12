package boj_21611;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static int[][] matrix;
	static Point[] arr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static int startX;
	static int startY;
	static int SharkX;
	static int SharkY;
	static int result = 0;
	static int marble = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		matrix = new int[N + 1][N + 1];
		arr = new Point[N * N + 1];
		for(int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(input[j - 1]);
			}
		}
		
		SharkX = (N + 1) / 2;
		SharkY = (N + 1) / 2;
		startX = (N + 1) / 2;
		startY = (N + 1) / 2 - 1;
		fill();
		
		for(int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int d = Integer.parseInt(input[0]) - 1;
			int s = Integer.parseInt(input[1]);
			
			for(int j = 1; j <= s; j++) {
				int nextX = SharkX + (dx[d] * j);
				int nextY = SharkY + (dy[d] * j);
				if(nextX <= N && nextX >= 1 && nextY <= N && nextY >= 1) {

					matrix[nextX][nextY] = 0;
				}
			}
			
			move();
			while(true) {
				int dest = destroy();
				if(dest > 0) {
					move();
				}else {
					break;
				}
			}
			
			change();
			
		}
		
		System.out.println(result);
		

	}
	
	static boolean move() {
		boolean change = false;
		for(int i = 1; i < N * N; i++) {
			Point now = arr[i];
			if(matrix[now.x][now.y] == 0) {
				for(int j = i + 1; j < N * N; j++) {
					Point next = arr[j];
					if(matrix[next.x][next.y] != 0) {
						matrix[now.x][now.y] = matrix[next.x][next.y];
						matrix[next.x][next.y] = 0;
						change = true;
						break;
					}
				}
				
			}
		}
		
		return change;
	}
	
	
	static void change() {
		ArrayList<Point> list = new ArrayList<>();
		ArrayList<Point> result_list = new ArrayList<>();
		list.add(arr[1]);
		for(int i = 2; i < N * N; i++) {
			Point now = arr[i];
			Point std = list.get(0);
			if(matrix[now.x][now.y] == matrix[std.x][std.y]) {
				list.add(now);
			}else {
				Point p = list.get(0);
				int num = matrix[p.x][p.y];
				result_list.add(new Point(list.size(), num));
				list.clear();
				list.add(now);
			}
			
		}
		
		int index = 1;
		for(Point pair : result_list) {
			if(index == N * N) {
				break;
			}
			Point A = arr[index];
			Point B = arr[index + 1];
			matrix[A.x][A.y] = pair.x;
			matrix[B.x][B.y] = pair.y;
			index += 2;
		}
		;
	}
	static int destroy(){
		ArrayList<Point> list = new ArrayList<>();
		list.add(arr[1]);
		int dest_cnt = 0;
		for(int i = 2; i < N * N; i++) {
			Point now = arr[i];
			Point std = list.get(0);
			if(matrix[now.x][now.y] == matrix[std.x][std.y]) {
				list.add(now);
			}else {
				if(list.size() >= 4) {
					int num = matrix[list.get(0).x][list.get(0).y];
					result = result + (num * list.size());
					dest_cnt += list.size();
					for(Point dest : list) {
						matrix[dest.x][dest.y] = 0;
					}
				}
				list.clear();
				list.add(now);
			}
			
		}
		
		return dest_cnt;
	}
	
	
	
	static int get_next_dir(int dir) {
		if(dir == 2) {
			return 1;
		}else if(dir == 1) {
			return 3;
		}else if(dir == 3) {
			return 0;
		}else {
			return 2;
		}
	}
	
	static void fill() {
		int nowX = SharkX;
		int nowY = SharkY;
		
		int move = 1;
		int dir = 2;
		int index = 1;
		while(true) {
			for(int i = 0; i < 2; i++) {
				
				for(int j = 0; j < move; j++) {
					if(nowX == SharkX && nowY == SharkY) {
						nowX = nowX + dx[dir];
						nowY = nowY + dy[dir];
					}else {
						Point point = new Point(nowX, nowY);
						arr[index++] = point;
						if(nowX == 1 && nowY == 1) return;
						nowX = nowX + dx[dir];
						nowY = nowY + dy[dir];
					}
				}
				
				dir = get_next_dir(dir);
				
				
			}
			move++;
		}
		
	}
	
	static void print() {
		System.out.println("--------");
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
