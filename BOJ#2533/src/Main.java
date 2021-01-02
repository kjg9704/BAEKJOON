import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int dp[][];
	static void solution(ArrayList<ArrayList<Integer>> arr, int start, int parent){
		dp[start][0] = 0;
		dp[start][1] = 1;
		for(int i = 0; i < arr.get(start).size(); i++) {
			int next = arr.get(start).get(i);
			if(next != parent) {
				solution(arr, next, start);
				dp[start][0] += dp[next][1];
				dp[start][1] += Math.min(dp[next][0], dp[next][1]);
			}	
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
		dp = new int[num + 1][2];
		for(int i = 0; i < num + 1; i++) {
			arr.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < num - 1; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			arr.get(start).add(end);
			arr.get(end).add(start);
		}
		solution(arr, 1, -1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
		sc.close();
	}

}
