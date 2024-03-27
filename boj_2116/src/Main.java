import java.io.*;
import java.util.*;

public class Main {

	static int result;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		int[][] dices = new int[N][6];
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < 6; j++) {
				dices[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		int[] choice_idx = new int[N];
		
		for(int i = 0; i < 6; i++) {
			choice_idx[0] = i;
			dfs(dices, choice_idx, 1);
		}
		
		System.out.println(result);

	}
	
	static void dfs(int[][] dices, int[] choice_idx, int depth) {
		if(depth == N) {
			
			int sum = 0;
			
			for(int i = 0; i < N; i++) {
				int max = 0;
				for(int j = 0; j < 6; j++) {
					if(j == choice_idx[i] || j == get_side_idx(choice_idx[i])) {
						continue;
					}
					max = Math.max(max, dices[i][j]);
				}
				sum += max;
			}
			
			result = Math.max(result, sum);
			return;
		}
		for(int i = 0; i < 6; i++) {
			if(dices[depth][i] == dices[depth - 1][choice_idx[depth - 1]]) {
				int top = get_side_idx(i);
				choice_idx[depth] = top;
				dfs(dices, choice_idx, depth + 1);
				break;
			}
		}
	}
	
	static int get_side_idx(int num) {
		if(num == 0) {
			return 5;
		}else if(num == 1) {
			return 3;
		}else if(num == 2) {
			return 4;
		}else if(num == 3) {
			return 1;
		}else if(num == 4) {
			return 2;
		}else {
			return 0;
		}
	}

}
