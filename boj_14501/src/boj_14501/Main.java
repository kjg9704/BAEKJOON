package boj_14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static class Node{
		int time;
		int pay;
		public Node(int time, int pay) {
			this.time = time;
			this.pay = pay;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Node[] consults = new Node[N + 1];
		int[] dp = new int[N + 7];
		for(int i = 1; i <= N; i++) {
			String[] temp = br.readLine().split(" ");
			consults[i] = new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
		}
		
		for(int i = 1; i <= N; i++) {
			if(consults[i].time > 1) {
				int temp = i + consults[i].time - 1;
				dp[temp] = Math.max(dp[temp], dp[i - 1] + consults[i].pay);
				dp[i] = Math.max(dp[i], dp[i - 1]);
			}else {
				dp[i] = Math.max(dp[i], dp[i - 1] + consults[i].pay);
			}
		}
		
		System.out.println(dp[N]);
	}

}
