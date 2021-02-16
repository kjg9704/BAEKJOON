import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[][] visited;
	static int[] dx = {0, 1, -1, 0};
	static int[] dy = {1, 0, 0, -1};
	static int N;
	static int M;
	static int[][] matrix;
	static int result;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		matrix = new int[N][M];
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(temp[j]);
			}
		}
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, matrix[i][j]);
				visited[i][j] = false;
			}
		}
		System.out.println(result);
		br.close();
	}

	static void dfs(int x, int y, int depth, int sum) {
		if(depth == 4) {
			result = Math.max(result, sum);
			return;
		}
		if(depth == 2) {
			for(int i = 0; i < 4; i++) {
				int ssum = 0;
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if(nextX >= N || nextY >= M || nextX < 0 || nextY < 0 || visited[nextX][nextY]) continue;
				visited[nextX][nextY] = true;
				ssum = sum + matrix[nextX][nextY];
				for(int j = i + 1; j < 4; j++) {
					int nextX2 = x + dx[j];
					int nextY2 = y + dy[j];
					if(nextX2 >= N || nextY2 >= M || nextX2 < 0 || nextY2 < 0 || visited[nextX2][nextY2]) continue;
					visited[nextX2][nextY2] = true;
					ssum += matrix[nextX2][nextY2];
					result = Math.max(result, ssum);
					visited[nextX2][nextY2] = false;
					break;
				}
				visited[nextX][nextY] = false;
			}
		}
		for(int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if(nextX >= N || nextY >= M || nextX < 0 || nextY < 0 || visited[nextX][nextY]) continue;
			visited[nextX][nextY] = true;
			dfs(nextX, nextY, depth + 1, sum + matrix[nextX][nextY]);
			visited[nextX][nextY] = false;
		}
	
	}
}
