import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		dp = new int[41][2];
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		for(int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			for(int j = 2; j <= n; j++) {
				dp[j][0] = dp[j - 1][1];
				dp[j][1] = dp[j][0] + dp[j -1][0];
			}
			bw.write(dp[n][0] + " " + dp[n][1] + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
