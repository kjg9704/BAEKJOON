import java.util.Scanner;

public class Main {

	static int N;
	static int[][] matrix;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		long B = sc.nextLong();
		matrix = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		int[][] result = pow(B);
		for(int i = 0; i < N; i++) {
			for(int j= 0; j < N; j++) {
				System.out.print(result[i][j] % 1000 + " ");
			}
			System.out.println();
		}
		sc.close();
	}
	static int[][] pow(long B) {
		if(B == 1) {
			return matrix;
		}
		if(B % 2 == 0) {
			int[][] temp = pow(B / 2);
			return multi(temp, temp);
		}
		else {
			int[][] temp = pow(B - 1);
			return multi(temp, matrix);
		}
	}

	static int[][] multi(int[][] first, int[][] second) {
		int[][] result = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = 0;
				for (int k = 0; k < N; k++) {
					num += first[i][k] * second[k][j];
				}
				result[i][j] = num % 1000;
			}
		}
		return result;
	}

}
