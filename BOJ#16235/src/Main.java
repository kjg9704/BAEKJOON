import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Ground{
	int nurtient;
	PriorityQue
}
public class Main {

	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int N;
	static int M;
	static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		K = Integer.parseInt(temp[2]);
		int[][] matrix = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				matrix[i][j + 1] = Integer.parseInt(temp[j]);
			}
		}
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			int X = Integer.parseInt(temp[0]);
			int Y = Integer.parseInt(temp[1]);
			int age = Integer.parseInt(temp[2]);
		}
		
		for(int i = 0; i < K; i++) {
			cycle();
		}
	}
	
	static void cycle() {
		for(int )
	}

}