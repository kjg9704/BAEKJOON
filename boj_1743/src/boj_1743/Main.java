package boj_1743;

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
		String[] input = br.readLine().split(" ");

		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		
		int[][] matrix = new int[M + 1][N + 1];
		boolean[][] visited = new boolean[M + 1][N + 1];
		Stack<Point> stack = new Stack<>();
		int max = 0;
		for(int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			matrix[c][r] = 1;
		}
		
		for(int i = 1; i <= M; i++) {
			for(int j = 1; j <= N; j++) {
				if(matrix[i][j] == 1 && !visited[i][j]) {
					int cnt = 1;
					stack.push(new Point(i, j));
					visited[i][j] = true;
					while(!stack.isEmpty()) {
						Point now = stack.pop();
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							if(nextX >= 1 && nextX <= M && nextY >= 1 && nextY <= N && matrix[nextX][nextY] == 1 && !visited[nextX][nextY]) {
								stack.push(new Point(nextX, nextY));
								cnt++;
								visited[nextX][nextY] = true;
							}
						}
					}
					
					max = Math.max(max, cnt);
				}
			}
		}
		
		System.out.println(max);


	}

}
