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
	static char[][] field = new char[12][6];
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 12; i++) {
			String str = br.readLine();
			for(int j = 0; j < 6; j++) {
				field[i][j] = str.charAt(j);
			}
		}
		
		int cnt = 0;
		while(pop()) {
			cnt++;
			fall();
		}
		
		System.out.println(cnt);

	}
	
	static boolean pop() {
		
		boolean flag = false;
		boolean[][] visited = new boolean[12][6];
		for(int i = 11; i >= 0; i--) {
			for(int j = 0; j < 6; j++) {
				if(!visited[i][j] && field[i][j] != '.') {
					Queue<Point> que = new LinkedList<>();
					ArrayList<Point> list = new ArrayList<>();
					char color = field[i][j];
					visited[i][j] = true;
					que.add(new Point(i, j));
					int cnt = 0;
					while(!que.isEmpty()) {
						Point now = que.poll();
						list.add(now);
						cnt++;
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							
							if(nextX < 0 || nextX >= 12 || nextY < 0 || nextY >= 6) continue;
							if(visited[nextX][nextY]) continue;
							if(field[nextX][nextY] == color) {
								visited[nextX][nextY] = true;
								que.add(new Point(nextX, nextY));
							}
						}
					}
					
					if(cnt >= 4) {
						flag = true;
						for(Point now : list) {
							field[now.x][now.y] = '.';
						}
					}
				}
			}
		}
		
		return flag;
	}
	
	static void fall() {
		for(int i = 11; i >= 0; i--) {
			for(int j = 0; j < 6; j++) {
				if(field[i][j] == '.') {
					int x = i - 1;
					while(x >= 0) {
						if(field[x][j] != '.') {
							char cor = field[x][j];
							field[i][j] = cor;
							field[x][j] = '.';
							break;
						}
						x--;
					}
				}
			}
		}
	}

}
