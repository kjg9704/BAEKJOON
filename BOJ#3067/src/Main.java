import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			int N = sc.nextInt();
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int j = 0; j < N; j++) {
				list.add(sc.nextInt());
			}
			int M = sc.nextInt();
			dp = new int[M + 1];
			dp[0] = 1;
			for (int k = 1; k <= N; k++) {
				for (int z = list.get(k - 1); z <= M; z++) {
					dp[z] += dp[z - list.get(k - 1)];
				}
			}
			System.out.println(dp[M]);
		}
		sc.close();
	}
}
