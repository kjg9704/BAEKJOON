package boj_2583;

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

		String[] input = br.readLine().split(" ");
		
		int M = Integer.parseInt(input[0]);
		int N = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		
		int[][] matrix = new int[N][M];
		ArrayList<Integer> list = new ArrayList<>();
		
		boolean[][] visited = new boolean[N][N];
		Stack<Point> stack = new Stack<>();
		
		for(int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			int minX = Integer.parseInt(input[0]);
			int minY = Integer.parseInt(input[1]);
			int maxX = Integer.parseInt(input[2]);
			int maxY = Integer.parseInt(input[3]);
			
			for(int j = minX; j < maxX; j++) {
				for(int z = minY; z < maxY; z++) {
					matrix[j][z] = 1;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 0 && !visited[i][j]) {
					int cnt = 1;
					stack.push(new Point(i, j));
					visited[i][j] = true;
					
					while(!stack.isEmpty()) {
						Point now = stack.pop();
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							
							if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && matrix[nextX][nextY] == 0 && !visited[nextX][nextY]) {
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
			bw.write(num + " ");
		}
		bw.flush();
		bw.close();
	}

}
