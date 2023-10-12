package boj_23289;

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
	
	static class Wind{
		int x;
		int y;
		int wind;
		public Wind(int x, int y, int wind) {
			this.x = x;
			this.y = y;
			this.wind = wind;
		}
	}
	
	static class Heater{
		int x;
		int y;
		int dir;
		
		public Heater(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	static int R, C, K;
	static int[][] matrix;
	static int[][] temp_board;
	static boolean[][][] is_wall;
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	static int[][] wind_dx = {
			{0, 0, 0},
			{-1, 0, 1},
			{-1, 0, 1},
			{-1, -1, -1},
			{1, 1, 1}
	};
	static int[][] wind_dy = {
			{0, 0, 0},
			{1, 1, 1},
			{-1, -1, -1},
			{-1, 0, 1},
			{-1, 0, 1}
	};
	
	static ArrayList<Heater> heaters;
	static ArrayList<Point> check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);

		matrix = new int[R + 1][C + 1];
		temp_board = new int[R + 1][C + 1];
		is_wall = new boolean[R + 1][C + 1][4];
		heaters = new ArrayList<>();
		check = new ArrayList<>();
		
		for(int i = 1; i <= R; i++) {
			input = br.readLine().split(" ");
			for(int j = 1; j <= C; j++) {
				int num = Integer.parseInt(input[j - 1]);
				matrix[i][j] = num;
				if(num > 0 && num <= 4) {
					heaters.add(new Heater(i, j, num));
				}
				if(num == 5) {
					check.add(new Point(i, j));
				}
			}
		}
		
		int W = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < W; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			int t = Integer.parseInt(input[2]);
			if(t == 1) {
				is_wall[x][y][1] = true;
				is_wall[x][y + 1][3] = true;
			}else {
				is_wall[x][y][0] = true;
				is_wall[x - 1][y][2] = true;
			}
		}
		
		int choco = 0;
		
		while(true) {
			if(choco > 100) {
				break;
			}
			
			wind();
			
			move_temp();
			
			decrease_temp();
			
			choco++;
			if(temp_check()) {
				break;
			}
		}
		
		System.out.println(choco);
		
	}
	
	static void wind() {
		for(Heater heater : heaters) {
			boolean[][] visited = new boolean[R + 1][C + 1];
			Queue<Wind> que = new LinkedList<>();
			
			int startX = heater.x + dx[heater.dir];
			int startY = heater.y + dy[heater.dir];
			visited[startX][startY] = true;
			que.add(new Wind(startX, startY, 5));
			
			while(!que.isEmpty()) {
				Wind now = que.poll();
				
				temp_board[now.x][now.y] += now.wind;
				
				if(now.wind > 1) {
					for(int i = 0; i < 3; i++) {
						int nextX = now.x + wind_dx[heater.dir][i];
						int nextY = now.y + wind_dy[heater.dir][i];
						
						if(nextX > R || nextX < 1 || nextY > C || nextY < 1 || visited[nextX][nextY]) {
							continue;
						}
						
						if(i == 1) {
							if(!wall_check(now.x, now.y, nextX, nextY)) {
								que.add(new Wind(nextX, nextY, now.wind - 1));
								visited[nextX][nextY] = true;
							}
						}else {
							if(heater.dir == 1 || heater.dir == 2) {
								if(!wall_check(now.x, now.y, nextX, now.y) && !wall_check(nextX, now.y, nextX, nextY)) {
									que.add(new Wind(nextX, nextY, now.wind - 1));
									visited[nextX][nextY] = true;
								}
							}else {
								if(!wall_check(now.x, now.y, now.x, nextY) && !wall_check(now.x, nextY, nextX, nextY)) {
									que.add(new Wind(nextX, nextY, now.wind - 1));
									visited[nextX][nextY] = true;
								}
							}
						}
						
					}
				}
				
			}
			
		}
	}
	
	static boolean wall_check(int x1, int y1, int x2, int y2) {
		if(x1 == x2) {
			if(y1 < y2) {
				if(is_wall[x1][y1][1]) {
					return true;
				}
			}else {
				if(is_wall[x1][y1][3]) {
					return true;
				}
			}
		}else if(y1 == y2){
			if(x1 < x2) {
				if(is_wall[x1][y1][2]) {
					return true;
				}
			}else {
				if(is_wall[x1][y1][0]) {
					return true;
				}
			}
		}
		
		return false;
	}
	static void move_temp() {
		int[][] temp = new int[R + 1][C + 1];
		
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				int nowX = i;
				int nowY = j;
				
				for(int z = 1; z <= 4; z++) {
					int nextX = nowX + dx[z];
					int nextY = nowY + dy[z];
					if(nextX > R || nextX < 1 || nextY > C || nextY < 1) {
						continue;
					}
					
					if(!wall_check(nowX, nowY, nextX, nextY)) {
						if(temp_board[nowX][nowY] < temp_board[nextX][nextY]) {
							temp[nowX][nowY] += (temp_board[nextX][nextY] - temp_board[nowX][nowY]) / 4;
						}else if(temp_board[nowX][nowY] > temp_board[nextX][nextY]){
							temp[nowX][nowY] -= (temp_board[nowX][nowY] - temp_board[nextX][nextY]) / 4;
						}
						
					}
				}
			}
		}
		
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				temp_board[i][j] += temp[i][j];
			}
		}

		
	}
	
	static void decrease_temp() {
		for(int i = 1; i <= C; i++) {
			if(temp_board[1][i] >= 1) {
				temp_board[1][i]--;
			}
		}
		for(int i = 1; i <= C; i++) {
			if(temp_board[R][i] >= 1) {
				temp_board[R][i]--;
			}
		}
		
		for(int i = 2; i < R; i++) {
			if(temp_board[i][1] >= 1) {
				temp_board[i][1]--;
			}
		}
		
		for(int i = 2; i < R; i++) {
			if(temp_board[i][C] >= 1) {
				temp_board[i][C]--;
			}
		}
	}
	
	static boolean temp_check() {
		boolean flag = true;
		for(Point now : check) {
			if(temp_board[now.x][now.y] < K) {
				flag = false;
				break;
			}
		}
		return flag;
	}

}
