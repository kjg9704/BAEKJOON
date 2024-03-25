import java.io.*;
import java.util.*;

public class Main {

	// key == 1, 2, 4, 8, 16, 32
	static class Point{
		int x, y, cnt, key;
		
		public Point(int x, int y, int cnt, int key) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.key = key;
		}
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		char[][] matrix = new char[N][M];
		Queue<Point> que = new LinkedList<Point>();
		boolean[][][] visited = new boolean[64][N][M];
		Point start;
		int result = -1;
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				char now = str.charAt(j);
				matrix[i][j] = now;
				
				if(now == '0') {
					start = new Point(i, j, 0, 0);
					que.add(start);
					visited[0][start.x][start.y] = true;
				}
			}
		}
		
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('a', 1);
		map.put('b', 2);
		map.put('c', 4);
		map.put('d', 8);
		map.put('e', 16);
		map.put('f', 32);
		
		map.put('A', 1);
		map.put('B', 2);
		map.put('C', 4);
		map.put('D', 8);
		map.put('E', 16);
		map.put('F', 32);
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			
			//print(matrix, now);
			
			if(matrix[now.x][now.y] == '1') {
				result = now.cnt;
				break;
			}
			
			for(int z = 0; z < 4; z++) {
				int nextX = now.x + dx[z];
				int nextY = now.y + dy[z];
				
				if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || visited[now.key][nextX][nextY] || matrix[nextX][nextY] == '#') {
					continue;
				}
				
				if(matrix[nextX][nextY] == '.' || matrix[nextX][nextY] == '1' || matrix[nextX][nextY] == '0') {
					visited[now.key][nextX][nextY] = true;
					que.add(new Point(nextX, nextY, now.cnt + 1, now.key));
				}else {
					char alpha = matrix[nextX][nextY];
					int key_val = map.get(alpha);
					
					if(Character.isLowerCase(alpha)) {
						if((now.key & key_val) == 0) {
							visited[now.key][nextX][nextY] = true;
							que.add(new Point(nextX, nextY, now.cnt + 1, now.key + key_val));
						}else {
							visited[now.key][nextX][nextY] = true;
							que.add(new Point(nextX, nextY, now.cnt + 1, now.key));
						}
					}else {
						if((now.key & key_val) > 0) {
							visited[now.key][nextX][nextY] = true;
							que.add(new Point(nextX, nextY, now.cnt + 1, now.key));
						}
					}
				}
				
			}
		}
		
		System.out.println(result);
		
	}
	
	static void print(char[][] matrix, Point now) {
		System.out.println("------------" + now.cnt + "---------");
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(i == now.x && j == now.y) {
					System.out.print("x");
				}else {
					System.out.print(matrix[i][j]);
				}
			}
			System.out.println();
		}
	}

}
