import java.io.*;
import java.util.*;

public class Main {

	static class Point{
		int x, y, time;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.time = 0;
		}
		
		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	static int N, M;
	static int[][] matrix;
	static ArrayList<Point> virus;
	
	static int empty;
	static int result = Integer.MAX_VALUE;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		virus = new ArrayList<Point>();
		matrix = new int[N][N];
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				int now = Integer.parseInt(input[j]);
				if(now != 1) empty++;
				if(now == 2) {
					virus.add(new Point(i, j));
				}
				if(now == 1) {
					matrix[i][j] = -1;
				}else {
					matrix[i][j] = now;
				}
			}
		}
		boolean[] visited = new boolean[virus.size()];
		dfs(visited, 0, 0);
		
		if(result == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(result);
		}
		
	}
	
	static void dfs(boolean[] visited, int index, int depth) {
		if(depth == M) {
			Queue<Point> que = new LinkedList<>();
			
			boolean[][] virus_visited = new boolean[N][N];
			int cnt = 0;
			int time = 0;
			for(int i = 0; i < virus.size(); i++) {
				if(visited[i]) {
					Point tmp = virus.get(i);
					
					que.add(tmp);
					virus_visited[tmp.x][tmp.y] = true;
				}
			}
			
			while(!que.isEmpty()) {
				Point now = que.poll();
				cnt++;
				time = now.time;
				for(int z = 0; z < 4; z++) {
					int nextX = now.x + dx[z];
					int nextY = now.y + dy[z];
					
					if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
					if(virus_visited[nextX][nextY] || matrix[nextX][nextY] == -1) continue;
					
					virus_visited[nextX][nextY] = true;
					
					que.add(new Point(nextX, nextY, now.time + 1));
				}
			}
			
			if(cnt == empty) {
				result = Math.min(result, time);
			}
			
			return;
		}
		
		for(int i = index; i < virus.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(visited, i, depth + 1);
				visited[i] = false;
			}
		}
	}

}
