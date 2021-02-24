import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] grapes = new int[N + 1];
		int[] dp = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			grapes[i] = sc.nextInt();
		}
		dp[1] = grapes[1];
		if(N > 1) {
			dp[2] = grapes[1] + grapes[2];
		}
		for(int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 3] + grapes[i - 1] + grapes[i], dp[i - 2] + grapes[i]);
			if(i >= 4) {
				dp[i] = Math.max(dp[i], dp[i - 4] + grapes[i - 1] + grapes[i]);
			}
		}
		System.out.println(Math.max(dp[N - 1], dp[N]));
		sc.close();
	}
}