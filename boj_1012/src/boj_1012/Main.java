package boj_1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			Stack<Point> stack = new Stack<>();
			String[] input = br.readLine().split(" ");
			int M = Integer.parseInt(input[0]);
			int N = Integer.parseInt(input[1]);
			int K = Integer.parseInt(input[2]);
			int cnt = 0;
			
			int[][] matrix = new int[M][N];
			boolean[][] visited = new boolean[M][N];
			for(int j = 0; j < K; j++) {
				input = br.readLine().split(" ");
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				matrix[x][y] = 1;
			}
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(matrix[k][j] == 1 && !visited[k][j]) {
						stack.push(new Point(k, j));
						visited[k][j] = true;
						cnt++;
						while(!stack.isEmpty()) {
							Point now = stack.pop();
							for(int d = 0; d< 4; d++) {
								int nextX = now.x + dx[d];
								int nextY = now.y + dy[d];
								if(nextX < M && nextY < N && nextX >= 0 && nextY >= 0 && matrix[nextX][nextY] == 1 && !visited[nextX][nextY]) {
									stack.push(new Point(nextX, nextY));
									visited[nextX][nextY] = true;
								}
							}
						}
					}
				}
			}
			
			System.out.println(cnt);

		}
	}

}
