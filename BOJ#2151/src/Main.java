import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Point implements Comparable<Point>{
	int x;
	int y;
	int dir; // 0, 1, 2, 3
	int cost;
	public Point(int x, int y, int dir, int cost) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cost = cost;
	}
	@Override
	public int compareTo(Point o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dy = {0, 1, 0, -1};
	static int result = Integer.MAX_VALUE;
	static char[][] matrix;
	static boolean[][][] visited;
	static ArrayList<Point> doors;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix = new char[N][N];
		visited = new boolean[N][N][4];
		doors = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < N; j++) {
				char ch = temp.charAt(j);
				matrix[i][j] = ch;
				if(ch == '#') {
					doors.add(new Point(i, j, 0, 0));
				}
			}
		}
		bfs();
		
		System.out.println(result);
	}
	static void bfs() {
		PriorityQueue<Point> que = new PriorityQueue<>();
		Point door = doors.get(0); //시작점
		for(int i = 0; i < 4; i++) {
			int nextX = door.x + dx[i];
			int nextY = door.y + dy[i];
			if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && matrix[nextX][nextY] != '*') {
				que.add(new Point(nextX, nextY, i, 0));
			}
		}
		visited[door.x][door.y][0] = true;
		visited[door.x][door.y][1] = true;
		visited[door.x][door.y][2] = true;
		visited[door.x][door.y][3] = true;
		while(!que.isEmpty()) {
			Point now = que.poll();
			if(now.x == doors.get(1).x && now.y == doors.get(1).y) {
				result = Math.min(result, now.cost);
				continue;
			}
			if(visited[now.x][now.y][now.dir]) {
				continue;
			}
			visited[now.x][now.y][now.dir] = true;
			if(matrix[now.x][now.y] == '!') { // 만약 거울이 설치가능한 곳이면 설치하고 다음으로넘어가는 경우의수를 que에 넣음
				for(int i = 0; i < 4; i++) {
					if(Math.abs(now.dir - i) != 2) {
						int nextX = now.x + dx[i];
						int nextY = now.y + dy[i];
						if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && matrix[nextX][nextY] != '*') {
							if(i != now.dir) {
								que.add(new Point(nextX, nextY, i, now.cost + 1));
							}
						}
					}
				}
			}
			int nextX = now.x + dx[now.dir];
			int nextY = now.y + dy[now.dir];
			if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && matrix[nextX][nextY] != '*') {
				que.add(new Point(nextX, nextY, now.dir, now.cost));
			}
		}
	}
}
