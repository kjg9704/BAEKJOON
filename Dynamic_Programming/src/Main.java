
public class Main {

	static long[] dp;
	public static void main(String[] args) {

		
		dp = new long[51];
//		System.out.println(fibo(50));
		
		long result = 0;
		for(int i = 1; i <= 50; i++) {
			if(i == 1 || i == 2) {
				dp[i] = 1;
				continue;
			}
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		System.out.println(dp[50]);
	}
	
	static long fibo(int num) {
		if(num == 1 || num == 2) {
			return 1;
		}
		if(dp[num] != 0) {
			return dp[num];
		}
		dp[num] = fibo(num - 1) + fibo(num - 2);
		return dp[num];
	}

}
