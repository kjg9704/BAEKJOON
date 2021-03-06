import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] matrix = new int[N][N];
		int maxH = 0;
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				int high = Integer.parseInt(temp[j]);
				matrix[i][j] = high;
				maxH = Math.max(maxH, high);
			}
		}
		int result = 0;
		for(int i = 0; i < maxH; i++) {
			boolean[][] visited = new boolean[N][N];
			Queue<Point> que = new LinkedList<>();
			int count = 0;
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(matrix[j][k] > i && visited[j][k] == false) {
						count++;
						visited[j][k] = true;
						que.add(new Point(j, k));
						while(!que.isEmpty()) {
							Point now = que.poll();
							for(int z = 0; z < 4; z++) {
								int nextX = now.x + dx[z];
								int nextY = now.y + dy[z];
								if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < N && visited[nextX][nextY] == false && matrix[nextX][nextY] > i) {
									visited[nextX][nextY] = true;
									que.add(new Point(nextX, nextY));
								}
							}
						}
					}
				}
			}
			result = Math.max(result, count);
		}
		System.out.println(result);
		br.close();
	}
}

