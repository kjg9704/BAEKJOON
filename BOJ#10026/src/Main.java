import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int n;
	static int zone;
	static int disorderZone;
	static String [][] matrix;
	static boolean [][] visited1;
	static boolean [][] visited2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = Integer.parseInt(sc.nextLine());
		matrix = new String[n][n];
		visited1 = new boolean[n][n];
		visited2 = new boolean[n][n];
		zone = 0;
		disorderZone = 0;
		for(int i = 0; i < n; i++) {
			String str = sc.nextLine();
			for(int j = 0; j < n; j++) {
				matrix[i][j] = str.charAt(j) + "";
			}
			
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited1[i][j]) {
					bfs(i,j);
				}
				if(!visited2[i][j]) {
					disorderBFS(i, j);
				}
			}
		}
		sc.close();
		System.out.print(zone + " " + disorderZone);
		
	}
	static void bfs(int x, int y) {
		Queue<Point> que = new LinkedList<Point>();
		Point nowPoint = new Point(x, y);
		que.add(nowPoint);
		visited1[x][y] = true;
		while(!que.isEmpty()) {
			int nowX = que.peek().x;
			int nowY = que.poll().y;
			for(int i = 0; i < 4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				if(nextX >= 0 && nextY >= 0 && nextX < n && nextY < n) {
					if(matrix[nowX][nowY].equals(matrix[nextX][nextY])) {
						if(!visited1[nextX][nextY]) {
							que.add(new Point(nextX, nextY));
							visited1[nextX][nextY] = true;
						}
					}
				}
			}
		}
		zone++;
	}
	static void disorderBFS(int x, int y) {
		Queue<Point> que = new LinkedList<Point>();
		Point nowPoint = new Point(x, y);
		que.add(nowPoint);
		visited2[x][y] = true;
		while(!que.isEmpty()) {
			int nowX = que.peek().x;
			int nowY = que.poll().y;
			for(int i = 0; i < 4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				if(nextX >= 0 && nextY >= 0 && nextX < n && nextY < n) {
					if(matrix[nowX][nowY].equals(matrix[nextX][nextY])) {
						if(!visited2[nextX][nextY]) {
							que.add(new Point(nextX, nextY));
							visited2[nextX][nextY] = true;
						}
					}
					else if((matrix[nowX][nowY].equals("R") && matrix[nextX][nextY].equals("G"))|| (matrix[nowX][nowY].equals("G") && matrix[nextX][nextY].equals("R"))) {
						if(!visited2[nextX][nextY]) {
							que.add(new Point(nextX, nextY));
							visited2[nextX][nextY] = true;
						}
					}
				}
			}
		}
		disorderZone++;
	}
	
}
