import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			int n = sc.nextInt();
			int[][] matrix = new int[2][n];
			int[][] dp = new int[2][n];
			for(int j = 0; j < 2; j++) {
				for(int z = 0; z < n; z++) {
					matrix[j][z] = sc.nextInt();
				}
			}
			dp[0][0] = matrix[0][0];
			dp[1][0] = matrix[1][0];
			for(int j = 1; j < n; j++) {
				if(j == 1) {
					dp[0][j] = Math.max(dp[1][j - 1], 0) + matrix[0][j];
					dp[1][j] = Math.max(dp[0][j - 1], 0) + matrix[1][j];
				}
				else {
					dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + matrix[0][j];
					dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + matrix[1][j];
				}
				
			}
			System.out.println(Math.max(dp[0][n - 1], dp[1][n - 1]));
		}
		sc.close();
	}

}
