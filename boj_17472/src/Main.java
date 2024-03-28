import java.io.*;
import java.util.*;

public class Main {

	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int node, cost;
		
		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Main.Edge o) {
			return this.cost - o.cost;
		}
		
	}
	static int result;
	static int N, M;
	static int[][] matrix;
	static int[][] edge;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		matrix = new int[N][M];
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		int island = 1;
		
		boolean[][] visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 1 && !visited[i][j]) {
					
					Queue<Point> que = new LinkedList<>();
					
					que.add(new Point(i, j));
					visited[i][j] = true;
					
					while(!que.isEmpty()) {
						Point now = que.poll();
						
						matrix[now.x][now.y] = island;
						
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							
							if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
							if(visited[nextX][nextY]) continue;
							if(matrix[nextX][nextY] == 1) {
								visited[nextX][nextY] = true;
								que.add(new Point(nextX, nextY));
							}
						}
					}
					
					island++;
				}
			}
		}
		
		edge = new int[island][island];
		
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] > 0 && !visited[i][j]) {
					
					Queue<Point> que = new LinkedList<>();
					
					que.add(new Point(i, j));
					visited[i][j] = true;
					
					while(!que.isEmpty()) {
						Point now = que.poll();
						
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							
							if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
							if(visited[nextX][nextY]) continue;
							if(matrix[nextX][nextY] == 1) {
								visited[nextX][nextY] = true;
								que.add(new Point(nextX, nextY));
							}else if(matrix[nextX][nextY] == 0) {
								go(matrix[i][j], now, z);
							}
						}
					}
				}
			}
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited_island = new boolean[island];
		
		pq.add(new Edge(1, 0));
		
		int result = 0;
		int land_cnt = 0;
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if(visited_island[now.node]) {
				continue;
			}
			visited_island[now.node] = true;
			result += now.cost;
			land_cnt++;
			
			for(int i = 1; i < island; i++) {
				if(edge[now.node][i] > 0) {
					pq.add(new Edge(i, edge[now.node][i]));
				}
			}
		}
		
		if(land_cnt == island - 1) {
			System.out.println(result);
		}else {
			System.out.println("-1");

		}
		
	}
	
	static void go(int start_island, Point start_node, int dir) {
		int nextX = start_node.x + dx[dir];
		int nextY = start_node.y + dy[dir];
		int dist_cnt = 0;
		while(true) {
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
				return;
			}
			
			if(matrix[nextX][nextY] == 0) {
				dist_cnt++;
				nextX += dx[dir];
				nextY += dy[dir];
				continue;
			}
			
			if(matrix[nextX][nextY] != start_island) {
				if(dist_cnt == 1) return;
				int dep_island = matrix[nextX][nextY];
				if(edge[start_island][dep_island] > 0) {
					int min = Math.min(edge[start_island][dep_island], dist_cnt);
					edge[start_island][dep_island] = min;
				}else {
					edge[start_island][dep_island] = dist_cnt;
				}
				
				return;
			}else {
				return;
			}
		}
	}

}
