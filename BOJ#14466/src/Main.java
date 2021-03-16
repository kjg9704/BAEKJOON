import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		int R = Integer.parseInt(temp[2]);
		int[][] matrix = new int[N + 1][N + 1];
		ArrayList<ArrayList<Point>> roadList = new ArrayList<ArrayList<Point>>();
		ArrayList<Point> cows = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			for(int j = 0; j <= N; j++) {
				roadList.add(new ArrayList<Point>());
			}
		}
		for(int i = 0; i < R; i++) {
			temp = br.readLine().split(" ");
			int startX = Integer.parseInt(temp[0]);
			int startY = Integer.parseInt(temp[1]);
			int endX = Integer.parseInt(temp[2]);
			int endY = Integer.parseInt(temp[3]);
			roadList.get(startX * (N + 1) + startY).add(new Point(endX, endY));
			roadList.get(endX * (N + 1) + endY).add(new Point(startX, startY));
		}
		for(int i = 1; i <= K; i++) {
			temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0]);
			int y = Integer.parseInt(temp[1]);
			matrix[x][y] = i;
			cows.add(new Point(x, y));
		}
		
		int result = 0;
		for(Point cow : cows) {
			Queue<Point> que = new LinkedList<>();
			que.add(cow);
			boolean[][] visited = new boolean[N + 1][N + 1];
			visited[cow.x][cow.y] = true;
			int count = 0;
			while(!que.isEmpty()) {
				Point now = que.poll();
				for(int i = 0; i < 4; i++) {
					int nextX = now.x + dx[i];
					int nextY = now.y + dy[i];
					if(nextX > 0 && nextY > 0 && nextX <= N && nextY <= N && visited[nextX][nextY] == false) {
						if(containsRoad(roadList.get(now.x * (N + 1) + now.y), nextX, nextY)) {
							continue;
						}
						if(matrix[nextX][nextY] > 0) {
							count++;
						}
						que.add(new Point(nextX, nextY));
						visited[nextX][nextY] = true;
					}
				}
			}
			result = result + K - 1 - count;
		}
		System.out.println(result/2);
	}
	static boolean containsRoad(ArrayList<Point> list, int x, int y) {
		for(Point point : list) {
			if(point.x == x && point.y == y) {
				return true;
			}
		}
		return false;
	}

}
