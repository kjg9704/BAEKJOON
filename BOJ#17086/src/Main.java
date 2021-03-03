import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


class Point{
	int x;
	int y;
	int distance;
	public Point(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
}

public class Main {

	static int N;
	static int M;
	static int[][] matrix;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		matrix = new int[N][M];
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				int var = Integer.parseInt(temp[j]);
				matrix[i][j] = var;
			}
		}
		br.close();
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 0) {
					max = Math.max(max, bfs(i, j));
				}
			}
		}
		System.out.println(max);

	}
	static int bfs(int x, int y) {
		Queue<Point> que = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		que.add(new Point(x, y, 0));
		visited[x][y] = true;
		while(!que.isEmpty()) {
			Point now = que.poll();
			for(int i = 0; i < 8; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && visited[nextX][nextY] == false) {
					if(matrix[nextX][nextY] == 1) {
						System.out.println("-------");
						System.out.println("X = " + nextX + " Y = " + nextY + " distance : " + (now.distance + 1) );
						return now.distance + 1;
					}
					visited[nextX][nextY] = true;
					que.add(new Point(nextX, nextY, now.distance + 1));
				}
			}
		}
		return 0;
	}

}
