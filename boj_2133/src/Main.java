import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		int[] dp = new int[31];
		
		dp[0] = 1;
		dp[1] = 0;
		dp[2] = 3;
		dp[3] = 0;
		if(N % 2 > 0) {
			System.out.println(0);
			return;
		}
		for(int i = 4; i <= N; i++) {
			dp[i] = dp[i - 2] * dp[2];
			for(int j = i - 4; j >= 0; j-=2) {
				dp[i] += dp[j] * 2;
			}
		}
		
		System.out.println(dp[N]);
	}

}