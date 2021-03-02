import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static int M;
	static int N;
	static int[][] matrix;
	static int[][] matrix2;
	static int[] dx = {0, -1, -1};
	static int[] dy = {-1, -1, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] temp = br.readLine().split(" ");
		M = Integer.parseInt(temp[0]);
		N = Integer.parseInt(temp[1]);
		StringBuilder sb = new StringBuilder();
		matrix = new int[M][M];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++) {
				matrix[i][j] = 1;
			}
		}
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			int zero = Integer.parseInt(temp[0]);
			int one = Integer.parseInt(temp[1]);
			int two = Integer.parseInt(temp[2]);
			Point point = new Point(M - 1, 0);
			while(zero != 0) {
				if(point.y > 0) {
					point.y = point.y + 1;
				}
				else {
					point.x = point.x - 1;
				}
				if(point.x < 0) {
					point.x = 0;
					point.y = point.y + 1;
				}
				zero--;
			}
			while(one != 0) {
				matrix[point.x][point.y] = matrix[point.x][point.y] + 1;
				if(point.y > 0) {
					point.y = point.y + 1;
				}
				else {
					point.x = point.x - 1;
				}
				if(point.x < 0) {
					point.x = 0;
					point.y = point.y + 1;
				}
				one--;
			}
			while(two != 0) {
				matrix[point.x][point.y] = matrix[point.x][point.y] + 2;
				if(point.y > 0) {
					point.y = point.y + 1;
				}
				else {
					point.x = point.x - 1;
				}
				if(point.x < 0) {
					point.x = 0;
					point.y = point.y + 1;
				}
				two--;
			}
			
		}
		for(int j = 1; j < M; j++) {
			for(int k = 1; k < M; k++) {
				int max = 0;
				for(int z = 0; z < 3; z++) {
					int nextX = j + dx[z];
					int nextY = k + dy[z];
					max = Math.max(max, matrix[nextX][nextY]);
				}
				matrix[j][k] = max;
			}
		}
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(matrix[i][j] + " ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
}
