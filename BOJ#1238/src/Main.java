import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		int X = Integer.parseInt(temp[2]);
		int[][] matrix = new int[N + 1][N + 1];
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			int cost = Integer.parseInt(temp[2]);
			matrix[start][end] = cost;
		}
		int[][] distance = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) {
					distance[i][j] = 0;
				}else {
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[N + 1];
			for(int j = 0; j < N - 1; j++) {
				int min = Integer.MAX_VALUE;
				int index = -1;
				for (int z = 1; z <= N; z++) {
					if (!visited[z] && min > distance[i][z]) {
						index = z;
						min = distance[i][z];
					}
				}

				for (int z = 1; z <= N; z++) {
					if (!visited[z] && matrix[index][z] != 0 && distance[i][index] != Integer.MAX_VALUE && distance[i][index] + matrix[index][z] < distance[i][z]) {
						distance[i][z] = distance[i][index] + matrix[index][z];
					}
				}
				visited[index] = true;
			}
		}
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				System.out.print(distance[i][j] + " ");
//			}
//			System.out.println();
//		}
		int result = 0;
		for(int i = 1; i <= N; i++) {
			result = Math.max(result, distance[i][X] + distance[X][i]);
		}
		System.out.println(result);
	}

}
