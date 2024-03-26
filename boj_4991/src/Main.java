import java.io.*;
import java.util.*;

public class Main {

	static class Point{
		int x, y, cnt, move;
		
		public Point(int x, int y, int cnt, int move) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.move = move;
		}
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String[] input = br.readLine().split(" ");
			
			int w = Integer.parseInt(input[0]);
			int h = Integer.parseInt(input[1]);
			
			if(w == 0 && h == 0) {
				break;
			}
			
			int[][] matrix = new int[h][w];
			
			Point start = null;
			int dirty = 0;
			for(int i = 0; i < h; i++) {
				String str = br.readLine();
				
				for(int j = 0; j < w; j++) {
					char now = str.charAt(j);
					
					if(now == 'x') {
						matrix[i][j] = -1;
						
					}else if(now == '*') {
						dirty++;
						matrix[i][j] = dirty;
					}else {
						if(now == 'o') start = new Point(i, j, 0, 0);
						matrix[i][j] = 0;
						
					}
				}
			}
			
			Queue<Point> que = new LinkedList<>();
			boolean[][][] visited = new boolean[1024][h][w];
			visited[0][start.x][start.y] = true;
			que.add(start);
			int result = -1;
			while(!que.isEmpty()) {
				Point now = que.poll();
				
				if(now.cnt == (1 << dirty) - 1) {
					result = now.move;
					break;
				}
		
				for(int z = 0; z < 4; z++) {
					int nextX = now.x + dx[z];
					int nextY = now.y + dy[z];
					
					if(nextX < 0 || nextX >= h || nextY < 0 || nextY >= w) continue;
					
					if(matrix[nextX][nextY] == -1) continue;
					
					if(visited[now.cnt][nextX][nextY]) continue;
					
					
					if(matrix[nextX][nextY] > 0) {
						int bit = now.cnt | (1 << (matrix[nextX][nextY] - 1));
						if(visited[bit][nextX][nextY]) {
							continue;
						}
						que.add(new Point(nextX, nextY, bit, now.move + 1));
						visited[bit][nextX][nextY] = true;
					}else {
						que.add(new Point(nextX, nextY, now.cnt, now.move + 1));
						visited[now.cnt][nextX][nextY] = true;
					}
					

				}
			}
			
			System.out.println(result);
			
			
		}
	}
	
	
}
