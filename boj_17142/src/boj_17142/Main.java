package boj_17142;

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
	static int[][] matrix;
	static boolean[][]isVirus;
	static int result = Integer.MAX_VALUE;
	static int target = 0;
	static int start_count = 0;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		ArrayList<Point> virus = new ArrayList<>();
		
		matrix = new int[N][N];
		isVirus = new boolean[N][N];
		target = N * N;
		
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				int now = Integer.parseInt(input[j]);
				if(now == 2) {
					virus.add(new Point(i, j));
					start_count++;
					isVirus[i][j] = true;
				}else if(now == 1) {
					target--;
				}
				matrix[i][j] = now;
			}
		}
		boolean[] visited = new boolean[virus.size()];
		combination(matrix, virus, visited, 0, virus.size(), M);
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
		
	}
	
	static void combination(int[][] matrix, ArrayList<Point> virus, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
			int virus_count = start_count;
			if(virus_count == target) {
				result = 0;
				return;
			}
			Queue<Point> que = new LinkedList<>();
			boolean[][] visit_check = new boolean[N][N];
			for(int i = 0; i < n; i++) {
				if(visited[i]) {
					Point tmp = virus.get(i);
					System.out.print(tmp.x + " " + tmp.y + " // ");
					visit_check[tmp.x][tmp.y] = true;
					que.add(tmp);
				}
			}
			System.out.println();
			int stage = 0;
			while(!que.isEmpty()) {
				int size = que.size();
				for(int i = 0; i < size; i++) {
					Point now = que.poll();
					for(int j = 0; j < 4; j++) {
						int nextX = now.x + dx[j];
						int nextY = now.y + dy[j];
						if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && matrix[nextX][nextY] != 1 && !visit_check[nextX][nextY]) {
							visit_check[nextX][nextY] = true;
							que.add(new Point(nextX, nextY));
							if(matrix[nextX][nextY] == 0) {
								virus_count++;
							}
						}
					}
				}
				
				
				stage++;
				
				if(virus_count == target) {
					result = Math.min(result, stage);
					return;
				}
				
				if(result < stage) {
					break;
				}
				
			}
			if(virus_count == target) {
				result = Math.min(result, stage);
				System.out.println(result);
			}
			
		}
		
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(matrix, virus, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}

}
