import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int [][] dp = new int[K + 1][N + 1];
		dp[1][N] = 1;
		
		for (int i = 0; i <= N ; i++) {
            dp[1][i] = 1;
        }
        // N != 0
        for (int i = 1; i <= K ; i++) {
            for (int j = 0; j <= N ; j++) {
                for (int k = j; k >=0 ; k--) {
                    dp[i][j] += dp[i - 1][j - k];
                    dp[i][j] %= 1000000000;
                }
            }
        }
		System.out.println(dp[K][N]);
		
		sc.close();
	}

}