package boj_18430;

import java.io.*;
import java.util.*;

public class Main {

	static int[][] matrix;
	static boolean[][] visited;
	
	static int[][][] move = {
			{{0, -1}, {1, 0}},
			{{-1, 0}, {0, -1}},
			{{-1, 0}, {0, 1}},
			{{0, 1}, {1, 0}}
	};
	
	
	static int N;
	static int M;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		matrix = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			
			for(int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		dfs(0, 0, 0);
		
		System.out.println(max);
		
		
	}
	
	static void dfs(int x, int y, int sum) {
		
		if(y == M) {
			y = 0;
			x++;
		}
		
		if(y == 0 && x == N) {
			max = Math.max(max, sum);
			return;
		}
		
		if(!visited[x][y]) {
			visited[x][y] = true;
			for(int z = 0; z < 4; z++) {
				
				int next1X = x + move[z][0][0];
				int next1Y = y + move[z][0][1];
				
				int next2X = x + move[z][1][0];
				int next2Y = y + move[z][1][1];
				
				if(oob(next1X, next1Y) || oob(next2X, next2Y) || visited[next1X][next1Y] || visited[next2X][next2Y]) {
					continue;
				}
				
				visited[next1X][next1Y] = true;
				visited[next2X][next2Y] = true;
				
				dfs(x, y + 1, sum + (matrix[x][y] * 2) + (matrix[next1X][next1Y]) + (matrix[next2X][next2Y]));
				
				visited[next1X][next1Y] = false;
				visited[next2X][next2Y] = false;
				
				
			}
			visited[x][y] = false;
		}
		
		dfs(x, y + 1, sum);
		
		
		
	}
	
	static boolean oob(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) return true;
		return false;
	}

}
