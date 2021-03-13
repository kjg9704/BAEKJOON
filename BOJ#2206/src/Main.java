import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point{
	int x;
	int y;
	int wall;
	int count;
	public Point(int x, int y, int wall, int count) {
		this.x = x;
		this.y = y;
		this.wall = wall;
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
		int[][] matrix = new int[N + 1][M + 1];
		int[][] count = new int[N + 1][M + 1];
		boolean[][][]visited = new boolean[N + 1][M + 1][2];
		for(int i = 1; i <= N; i++) {
			String str = br.readLine();
			for(int j = 0; j < str.length(); j++) {
				matrix[i][j + 1] = Integer.parseInt(str.charAt(j) + "");
				count[i][j + 1] = Integer.MAX_VALUE;
			}
		}
		br.close();
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(1, 1, 0, 1));
		count[1][1] = 1;
		visited[1][1][0] = true;
		visited[1][1][1] = true;
		while(!que.isEmpty()) {
			Point now = que.poll();
			for(int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX > 0 && nextY > 0 && nextX <= N && nextY <= M) {
					if(matrix[nextX][nextY] == 1) {
						if(now.wall == 0) {
							if(!visited[nextX][nextY][0]) {
								que.add(new Point(nextX, nextY, now.wall + 1, now.count + 1));
								visited[nextX][nextY][1] = true;
								if(count[nextX][nextY] > now.count + 1) 
									count[nextX][nextY] = now.count + 1;
							}
						}
					}else {
						if(now.wall == 0) {
							if(!visited[nextX][nextY][0]) {
								que.add(new Point(nextX, nextY, now.wall, now.count + 1));
								visited[nextX][nextY][0] = true;
								if(count[nextX][nextY] > now.count + 1) 
									count[nextX][nextY] = now.count + 1;
							}
						}else {
							if(!visited[nextX][nextY][0]) {
								if(!visited[nextX][nextY][1]) {
									que.add(new Point(nextX, nextY, now.wall, now.count + 1));
									visited[nextX][nextY][1] = true;
									if(count[nextX][nextY] > now.count + 1) 
										count[nextX][nextY] = now.count + 1;
								}
							}
						}
					}
				}
			}
			System.out.println("---------------------");
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					System.out.print(count[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println(now.x + "  " + now.y + "  " + now.wall + "  " + now.count);
		}
		if(count[N][M] == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(count[N][M]);
		}
	}

}
