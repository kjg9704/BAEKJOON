import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point{
	int x;
	int y;
	int count;
	public Point(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
	}
}

public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		boolean[][]visited = new boolean[N + 1][M + 1];
		int[][] matrix = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			String str = br.readLine();
			for(int j = 0; j < str.length(); j++) {
				matrix[i][j + 1] = Integer.parseInt(str.charAt(j) + "");
			}
		}
		br.close();
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(1, 1, 1));
		visited[1][1] = true;
		int result = Integer.MAX_VALUE;
		while(!que.isEmpty()) {
			Point now = que.poll();
			for(int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX > 0 && nextY > 0 && nextX <= N && nextY <= M && matrix[nextX][nextY] == 1 && visited[nextX][nextY] == false) {
					que.add(new Point(nextX, nextY, now.count + 1));
					visited[nextX][nextY] = true;
					if(nextX == N && nextY == M)
						result = Math.min(result, now.count + 1);
				}
			}
		}
		System.out.println(result);
	}

}
