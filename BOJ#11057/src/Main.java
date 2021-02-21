import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		int[][] dp = new int[N + 1][10];
		dp[1][0] = 1;
		Arrays.fill(dp[1], 1);
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j < 10; j++) {
				if(j == 0) {
					dp[i][j] = dp[i - 1][j];
				}
				else {
					dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 10007;
				}
			}
		}
		int result = 0;
		for(int i = 0; i < 10; i++) {
			result += dp[N][i] % 10007;
		}
		System.out.println(result % 10007);
	}
}
