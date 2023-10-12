package boj_20058;

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
	static int[] dx = {-1, 0 , 1, 0};
	static int[] dy = {0, 1, 0, -1};

	static int N;
	static int Q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		Q = Integer.parseInt(input[1]);
		
		int size = (int) Math.pow(2, N);
		int[][] matrix = new int[size][size];

		for(int i = 0; i < size; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < size; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		

		input = br.readLine().split(" ");
		for(int loop = 0; loop < Q; loop++) {
			int L = Integer.parseInt(input[loop]);
			int divide_size = (int)Math.pow(2, L);
			
			for(int i = 0; i < size; i += divide_size) {
				for(int j = 0; j < size; j += divide_size) {

					//상하반전
					for(int z = 0; z < divide_size / 2; z++) {

						for(int k = 0; k < divide_size; k++) {
							int temp = matrix[z + i][k + j];
							matrix[z + i][k + j] = matrix[divide_size - z - 1 + i][k + j];
							matrix[divide_size - z - 1 + i][k + j] = temp;
						}

					}

					//대각선 반전
					for(int z = 0; z < divide_size ; z++) {
						for(int k = z; k < divide_size; k++) {
							int temp = matrix[z + i][k + j];
							matrix[z + i][k + j] = matrix[k + i][z + j];
							matrix[k + i][z + j] = temp;
						}
					}

				}
			}

			
			boolean[][] check = new boolean[size][size];
			
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					int cnt = 0;
					if(matrix[i][j] == 0) continue;
					for(int z = 0; z < 4; z++) {
						int nextX = i + dx[z];
						int nextY = j + dy[z];
						
						if(nextX >= 0 && nextX < size && nextY >= 0 && nextY < size) {
							if(matrix[nextX][nextY] > 0) {
								cnt++;
							}
						}
						
					}
					
					if(cnt < 3) {
						check[i][j] = true;
					}
				}
			}
			
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(check[i][j]) {
						matrix[i][j]--;
					}
				}
			}
		}
		
		
		
		int ice = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				ice += matrix[i][j];
			}
		}
		
		Queue<Point> que = new LinkedList<>();
		boolean[][] visited = new boolean[size][size];
		int max = 0;
		for(int i = 0; i < size; i++) {
			
			for(int j = 0; j < size; j++) {
				if(!visited[i][j] && matrix[i][j] > 0) {
					int count = 1;
					que.add(new Point(i, j));
					visited[i][j] = true;
					while(!que.isEmpty()) {
						Point now = que.poll();
						
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							
							if(nextX >= 0 && nextX < size && nextY >= 0 && nextY < size && !visited[nextX][nextY] && matrix[nextX][nextY] > 0) {
								visited[nextX][nextY] = true;
								que.add(new Point(nextX, nextY));
								count++;
							}
							
						}
					}
					
					max = Math.max(max, count);
				}
			}
		}
		
		System.out.println(ice);
		System.out.println(max);
		
	}
	
	static void print(int[][] area) {
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				System.out.print(area[i][j] + " ");
			}
			System.out.println();
		}
	}

}
