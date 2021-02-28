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
	//x - 1 * 3 + y
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int result;
	static boolean[][] matrix;
	static boolean[][] turnCheck;
	static boolean[][] visitCheck;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		matrix = new boolean[N + 1][N + 1];
		turnCheck = new boolean[N + 1][N + 1];
		visitCheck = new boolean[N + 1][N + 1];
		result = 1;
		ArrayList<ArrayList<Point>> arr = new ArrayList<>();
		for(int i = 0; i <= N * N; i++) {
			arr.add(new ArrayList<Point>());
		}
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			arr.get((Integer.parseInt(temp[0]) -1) * N + Integer.parseInt(temp[1])).add(new Point(Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
		}
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(1, 1));
		matrix[1][1] = true;
		turnCheck[1][1] = true;
		while(!que.isEmpty()) {
			Point now = que.poll();
			for(Point turn : arr.get((now.x - 1) * N + now.y)){
				if(matrix[turn.x][turn.y] == false) {
					matrix[turn.x][turn.y] = true;
					result++;
				}
			}
			for(int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX > 0 && nextY > 0 && nextX <= N && nextY <= N && turnCheck[nextX][nextY] == false) {
					visitCheck[nextX][nextY] = true;

				}
			}
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(turnCheck[i][j] == false && visitCheck[i][j] == true && matrix[i][j] == true) {
						turnCheck[i][j] = true;
						que.add(new Point(i, j)); 
					}
				}
			}
			System.out.println("-------------  " + now.x + " " + now.y );
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
		}
		System.out.println(result);
		br.close();
	}
}
