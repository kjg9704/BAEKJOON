import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] weights = new int[N];
		int[] values = new int[N];
		int[] dp = new int[K + 1];
		for(int i = 0; i < N; i++) {
			weights[i] = sc.nextInt();
			values[i] = sc.nextInt();
		}
		for(int i = 0; i < N; i++) {
			int now = weights[i];
			for(int j = K; j >= now; j--)
			dp[j] = Math.max(dp[j], dp[j - now] + values[i]);
		}
		System.out.println(dp[K]);
		sc.close();
	}
}
