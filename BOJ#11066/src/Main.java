import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			int K = Integer.parseInt(br.readLine());
			String[] temp = br.readLine().split(" ");
			int[] sum = new int[K];
			sum[0] = Integer.parseInt(temp[0]);
			for(int j = 1; j < K; j++) {
				sum[j] = Integer.parseInt(temp[j]) + sum[j - 1];
			}
			int[][] dp = new int[K][K];
			for(int j = 1; j < K; j++) {
				for(int z = 0; z < K - j; z++) {
					dp[z][z + j] = Integer.MAX_VALUE;
					for(int k = 0; k < j; k++) {
						int cost = dp[z][z + k] + dp[z + k + 1][z + j] + sum[j + z];
						if(z - 1 >= 0) {
							cost -= sum[z - 1];
						}
						dp[z][z + j] = Math.min(dp[z][z + j], cost);
					}
				}
			}
			System.out.println(dp[0][K - 1]);
		}
	}
}