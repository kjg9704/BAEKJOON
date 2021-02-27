import java.util.ArrayList;
import java.util.Scanner;


class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int N;
	static int M;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] matrix;
	static boolean[][] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		matrix = new int[N][M];
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int var = sc.nextInt();
				matrix[i][j] = var;
				if(var == 1) {
					max++;
				}
			}
		}
		sc.close();
		visited = new boolean[N][M];
		int result = 0;
		int cheese = 0;
		dfs(0, 0);
		while(max != 0) {
			cheese = max;
			visited = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(matrix[i][j] == 1) {
						for(int k = 0; k < 4; k++) {
							int nextX = i + dx[k];
							int nextY = j + dy[k];
							if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
								if(matrix[nextX][nextY] == -1) {
									matrix[i][j] = 0;
									max--;
									break;
								}
							}
						}
					}
				}
			}
			dfs(0, 0);
			result++;
		}

		System.out.println(result);
		System.out.println(cheese);
		
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		if(matrix[x][y] == 0) {
			matrix[x][y] = -1;
		}
		for(int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
				if(matrix[nextX][nextY] <= 0 && visited[nextX][nextY] == false) {
					dfs(nextX, nextY);
				}
			}
		}
	}

}
