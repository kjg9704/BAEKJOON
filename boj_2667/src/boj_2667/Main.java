package boj_2667;

import java.io.*;
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		
		int[][] matrix = new int[N][N];
		ArrayList<Integer> list = new ArrayList<>();
		
		boolean[][] visited = new boolean[N][N];
		Stack<Point> stack = new Stack<>();
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				matrix[i][j] = input.charAt(j) - '0';		
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(matrix[i][j] == 1 && !visited[i][j]) {
					int cnt = 1;
					stack.push(new Point(i, j));
					visited[i][j] = true;
					
					while(!stack.isEmpty()) {
						Point now = stack.pop();
						for(int d = 0; d< 4; d++) {
							int nextX = now.x + dx[d];
							int nextY = now.y + dy[d];
							if(nextX < N && nextY < N && nextX >= 0 && nextY >= 0 && matrix[nextX][nextY] == 1 && !visited[nextX][nextY]) {
								stack.push(new Point(nextX, nextY));
								visited[nextX][nextY] = true;
								cnt++;
							}
						}
					}
					
					list.add(cnt);
				}
			}
		}
		
		Collections.sort(list);
		bw.write(list.size() + "\n");
		for(int num : list) {
			bw.write(num + "\n");
		}
		
		bw.flush();
		bw.close();
	}

}
