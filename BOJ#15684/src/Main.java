import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int result;
	static int N;
	static int M;
	static int H;
	static int[][] matrix;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		H = Integer.parseInt(temp[2]);
		matrix = new int[H + 1][N + 1];
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			int a = Integer.parseInt(temp[0]);
			int b = Integer.parseInt(temp[1]);
			matrix[a][b] = 1;
			matrix[a][b + 1] = -1;
		}
		
		if(check()) {
			System.out.println(0);
			return;
		}
		for(int i = 1; i <= 3; i++) {
			backtracking(0, i);
		}
		System.out.println(-1);
		
		br.close();
	}
	
	static void backtracking(int now, int count) {
		if(now == count) {
			if(check()) {
				System.out.println(count);
				System.exit(0);
			}
			return;
		}
		for(int i = 1; i < N; i++) {
			for(int j = 1; j <= H; j++) {
				if(matrix[j][i] == 0 && matrix[j][i + 1] == 0) {
					matrix[j][i] = 1;
					matrix[j][i + 1] = -1;
					backtracking(now + 1, count);
					matrix[j][i] = 0;
					matrix[j][i + 1] = 0;
				}
			}
		}
	}
	
	static boolean check() {
		for(int i = 1; i <= N; i++) {
			int start = i;
			for(int j = 1; j <= H; j++) {
				if(matrix[j][start] == 1) {
					start++;
				}else if(matrix[j][start] == -1) {
					start--;
				}
			}
			if(start != i) {
				return false;
			}
		}
		return true;
	}
	
	

}
