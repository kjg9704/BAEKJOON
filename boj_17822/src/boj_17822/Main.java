package boj_17822;

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
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int T = Integer.parseInt(input[2]);
		
		int[][] matrix = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for(int j = 1; j <= M; j++) {
				matrix[i][j] = Integer.parseInt(input[j - 1]);
			}
		}
		
		for(int loop = 0; loop < T; loop++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int d = Integer.parseInt(input[1]);
			int k = Integer.parseInt(input[2]);
			
			for(int i = 1; i <= N; i++) {
				if(i % x == 0) {
					if(d == 0) {
						int[] temp = new int[M + 1];
						for(int j = 1; j <= M; j++) {
							int nextY = j + k;
							if(nextY > M) {
								nextY = nextY % M;
							}
							temp[nextY] = matrix[i][j];
						}
						
						for(int j = 1; j <= M; j++) {
							matrix[i][j] = temp[j]; 
						}
						
					}else {
						int[] temp = new int[M + 1];
						for(int j = 1; j <= M; j++) {
							int nextY = j - k;
							if(nextY < 1) {
								nextY = M + nextY;
							}
							temp[nextY] = matrix[i][j];
						}
						
						for(int j = 1; j <= M; j++) {
							matrix[i][j] = temp[j]; 
						}
					}	
				}
			}
			print(matrix);
			int sum = 0;
			int cnt = 0;
			boolean delete_check = false;
			boolean[][] visited = new boolean[N + 1][M + 1];
			Queue<Point> que = new LinkedList<>();
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					if(matrix[i][j] > 0 && !visited[i][j]) {
						boolean check = false;
						int now_num = matrix[i][j];
						que.add(new Point(i, j));
						visited[i][j] = true;
						sum += matrix[i][j];
						cnt++;
						while(!que.isEmpty()) {
							Point now = que.poll();
							matrix[now.x][now.y] = 0;
							for(int z = 0; z < 4; z++) {
								int nextX = now.x + dx[z];
								int nextY = now.y + dy[z];
								if(nextX > N || nextX < 1) {
									continue;
								}
								
								if(nextY > M) {
									nextY = 1;
								}else if(nextY < 1) {
									nextY = M;
								}
								
								if(matrix[nextX][nextY] == now_num && !visited[nextX][nextY]) {
									sum += matrix[nextX][nextY];
									cnt++;
									delete_check = true;
									check = true;
									visited[nextX][nextY] = true;
									que.add(new Point(nextX, nextY));
								}
							}
						}
						if(!check) {
							matrix[i][j] = now_num;
						}
					}
				}
			}
			
			print(matrix);
			if(!delete_check) {
				double avg = (double)sum / (double)cnt;
				for(int i = 1; i <= N; i++) {
					for(int j = 1; j <= M; j++) {
						if(matrix[i][j] > 0) {
							if(matrix[i][j] > avg) {
								matrix[i][j]--;
							}else if(matrix[i][j] < avg) {
								matrix[i][j]++;
							}
						}
					}
				}
			}
						
		}
		
		System.out.println("-----result-----");
		print(matrix);
		int result = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				result += matrix[i][j];
			}
		}
		System.out.println(result);
	}
	
	static void print(int[][] matrix) {
		System.out.println("----------------------------------");
		for(int i = 1; i < matrix.length;i++) {
			for(int j = 1; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
