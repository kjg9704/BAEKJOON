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
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		int[][] matrix = new int[N][M];
		
		int[][] empty_group = new int[N][M];
		
		int[] group_cnt = new int[N * M + 1];
		
		Point[] group_start = new Point[N * M + 1];
		
		int[][] result = new int[N][M];
		
		int group = 1;
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				matrix[i][j] = str.charAt(j) - 0x30;
			}
		}
		br.close();
		StringBuilder sb = new StringBuilder();
		
		Queue<Point> que = new LinkedList<>();
		
		boolean[][] visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 0 && !visited[i][j]) {
					
					int cnt = 0;
					que.add(new Point(i, j));
					visited[i][j] = true;
					ArrayList<Point> walls = new ArrayList<>();
					
					group_start[group] = new Point(i, j);
					
					while(!que.isEmpty()) {
						Point now = que.poll();
						
						empty_group[now.x][now.y] = group;
						
						cnt++;
						
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							
							if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
							
							if(visited[nextX][nextY]) continue;
							
							if(matrix[nextX][nextY] == 1 && !visited[nextX][nextY]) {
								visited[nextX][nextY] = true;
								walls.add(new Point(nextX, nextY));
								continue;
							}
							
							visited[nextX][nextY] = true;
							que.add(new Point(nextX, nextY));
						}
						
					}
					
					for(Point now : walls) {
						result[now.x][now.y] += cnt;
						visited[now.x][now.y] = false;
					}
					group_cnt[group] = cnt;
					group++;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 1) {
					result[i][j] += 1;
					result[i][j] = result[i][j] % 10;
					sb.append(result[i][j]);
				}else {
					sb.append("0");
				}
			}
			sb.append("\n");
		}

		
		System.out.println(sb);
	}

}
