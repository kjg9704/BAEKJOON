import java.io.*;
import java.util.*;

public class Main {

	static class Point{
		int x, y, dist;
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	static int N, M, D;
	static int[][] matrix;
	static int result;
	static int enemy;
	static int[] dx = {0, -1, 0};
	static int[] dy = {-1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		D = Integer.parseInt(input[2]);
		matrix = new int[N][M];
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				int now = Integer.parseInt(input[j]);
				matrix[i][j] = now;
				if(now == 1) {
					enemy++;
				}
			}
		}
		
		if(enemy == 0) {
			System.out.println(0);
			return;
		}
		boolean[] visited = new boolean[M];
		int[] archer = new int[3];
		for(int i = 0; i < M; i++) {
			dfs(visited, archer, i, 0);
		}
		
		System.out.println(result);
		

	}
	
	static void dfs(boolean[] visited, int[] archer, int start, int depth) {
		if(depth == 3) {
			int kill = 0;
			int clear = 0;
			
			int[][] copy_matrix = copy();
			
			while(kill + clear < enemy) {
				for(int arc : archer) {
					boolean[][] visited_arrow = new boolean[N][M];
					Queue<Point> que = new LinkedList<>();
					Point start_point = new Point(N - 1, arc, 1);
					que.add(start_point);
					visited_arrow[start_point.x][start_point.y] = true;
					
					while(!que.isEmpty()) {
						Point now = que.poll();
						
						if(copy_matrix[now.x][now.y] > 0) {
							copy_matrix[now.x][now.y] += 1;
							break;
						}
						
						for(int z = 0; z < 3; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							
							if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
							
							if(now.dist + 1 <= D) {
								visited_arrow[nextX][nextY] = true;
								que.add(new Point(nextX, nextY, now.dist + 1));
							}
							
						}
					}
				}
				
				for(int i = N - 1; i >= 0; i--) {
					for(int j = 0; j < M; j++) {
						if(copy_matrix[i][j] > 1) {
							copy_matrix[i][j] = 0;
							kill++;
						}else if(copy_matrix[i][j] == 1) {
							copy_matrix[i][j] = 0;
							if(i + 1 < N) {
								copy_matrix[i + 1][j] = 1;
							}else {
								clear++;
							}
						}
					}
				}
			}
			
			
			result = Math.max(result, kill);
			return;
		}
		
		for(int i = start; i < M; i++) {
			if(!visited[i]) {
				visited[i] = true;
				archer[depth] = i;
				dfs(visited, archer, i, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static int[][] copy(){
		int[][] new_matrix = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				new_matrix[i][j] = matrix[i][j];
			}
		}
		
		return new_matrix;
	}
	

}
