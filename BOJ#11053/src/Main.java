import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] dp;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		String[] temp = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			arr[i + 1] = Integer.parseInt(temp[i]);
		}
		dp = new int[N + 1];
		dp[1] = 1;
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		int max = -1;
		for(int i = 1; i <= N; i++) {
			if(dp[i] > max) {
				max = dp[i];
			}
		}
		System.out.println(max);
	}
}
