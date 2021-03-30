import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	static int N;
	static int result;
	static int[] queen;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		br.close();
		queen = new int[N];
		result = 0;
		for(int i = 0; i < N; i++) {
			queen[i] = -1;
		}
		for(int i = 0; i < N; i++) {
			queen[0] = i;
			dfs(1);
			queen[0] = -1;
		}
		System.out.println(result);
	}
	static void dfs(int x) {
		if(x == N) {
			result++;
			return;
		}
		for(int i = 0; i < N; i++) {
			if(check(x, i)) {
				queen[x] = i;
				dfs(x + 1);
				queen[x] = -1;
			}
		}
	}

	static boolean check(int x, int y) {
		for(int i = 0; i < N; i++) {
			if(queen[i] != -1) {
				if(x == i) {
					return false;
				}
				if(y == queen[i]) {
					return false;
				}
				if(Math.abs(x - i) == Math.abs(y - queen[i])) {
					return false;
				}
			}
		}
		return true;
	}

}
