import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N + 1];
		int[][] matrix = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j= 1; j <= N; j++) {
				if(i == j) {
					matrix[i][j] = 0;
				}else {
					matrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		for(int i = 0; i < M; i++) {
			String[] temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			int cost = Integer.parseInt(temp[2]);
			matrix[start][end] = Math.min(matrix[start][end], cost);
		}
		String[] temp = br.readLine().split(" ");
		int start = Integer.parseInt(temp[0]);
		int end = Integer.parseInt(temp[1]);
		br.close();
		visited[start] = true;
		for(int i = 0; i < N- 1; i++) {
			int min=Integer.MAX_VALUE;
            int min_index=-1;

			for(int j = 1; j <= N; j++){
                if(!visited[j]){
                    if(matrix[start][j] <= min){
                        min = matrix[start][j];
                        min_index = j;
                    }
                }
            }
			visited[min_index] = true;
            for(int j = 1; j <= N; j++){
                if(!visited[j] && matrix[min_index][j] != Integer.MAX_VALUE){
                    if(matrix[start][j] > matrix[start][min_index] + matrix[min_index][j]){
                    	matrix[start][j] = matrix[start][min_index] + matrix[min_index][j];
                    }
                }
            }
		}
		System.out.println(matrix[start][end]);
	}
}
