import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[][] gear;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new int[4][8];
		for(int i = 0; i < 4; i++) {
			String temp = br.readLine();
			for(int j = 0; j < 8; j++) {
				gear[i][j] = Character.getNumericValue(temp.charAt(j));
			}
		}
		int K = Integer.parseInt(br.readLine());
		visited = new boolean[4];
		for(int i = 0; i < K; i++) {
			String[] temp = br.readLine().split(" ");
			int target = Integer.parseInt(temp[0]);
			int clock = Integer.parseInt(temp[1]);
			if(clock == 1) {
				turn(target - 1, true);
			}else {
				turn(target - 1, false);
			}

		}
		int result = 0;
		for(int i = 0; i < 4; i++) {
			if(gear[i][0] == 1) {
				if(i == 0) {
					result++;
				}else if(i == 1) {
					result += 2;
				}else if(i == 2) {
					result += 4;
				}else if(i == 3) {
					result += 8;
				}
			}
		}
		System.out.println(result);
	}

	static void turn(int target, boolean clock) {
		visited[target] = true;
		if(target == 0) {
			if(!visited[1]) {
				if(gear[target][2] != gear[1][6]) {
					turn(1, !clock);
				}
			}
			if(clock) {
				int temp = gear[target][7];
				for(int i = 7; i >= 1; i--) {
					gear[target][i] = gear[target][i-1]; 
				} 
				gear[target][0] = temp;
			}else {
				int temp = gear[target][0];
				for(int i = 0; i < 7; i++) {
					gear[target][i] = gear[target][i + 1]; 
				}
				gear[target][7] = temp;
			}
			
		}else if(target == 1) {
			if(!visited[0]) {
				if(gear[target][6] != gear[0][2]) {
					turn(0, !clock);
				}
			}
			if(!visited[2]) {
				if(gear[target][2] != gear[2][6]) {
					turn(2, !clock);
				}
			}
			if(clock) {
				int temp = gear[target][7];
				for(int i = 7; i >= 1; i--) {
					gear[target][i] = gear[target][i-1]; 
				} 
				gear[target][0] = temp;
			}else {
				int temp = gear[target][0];
				for(int i = 0; i < 7; i++) {
					gear[target][i] = gear[target][i + 1]; 
				}
				gear[target][7] = temp;
			}
			
		}else if(target == 2) {
			if(!visited[1]) {
				if(gear[target][6] != gear[1][2]) {
					turn(1, !clock);
				}
			}
			if(!visited[3]) {
				if(gear[target][2] != gear[3][6]) {
					turn(3, !clock);
				}
			}
			if(clock) {
				int temp = gear[target][7];
				for(int i = 7; i >= 1; i--) {
					gear[target][i] = gear[target][i-1]; 
				} 
				gear[target][0] = temp;
			}else {
				int temp = gear[target][0];
				for(int i = 0; i < 7; i++) {
					gear[target][i] = gear[target][i + 1]; 
				}
				gear[target][7] = temp;
			}
			
		}else if(target == 3) {
			if(!visited[2]) {
				if(gear[target][6] != gear[2][2]) {
					turn(2, !clock);
				}
			}
			if(clock) {
				int temp = gear[target][7];
				for(int i = 7; i >= 1; i--) {
					gear[target][i] = gear[target][i-1]; 
				} 
				gear[target][0] = temp;
			}else {
				int temp = gear[target][0];
				for(int i = 0; i < 7; i++) {
					gear[target][i] = gear[target][i + 1]; 
				}
				gear[target][7] = temp;
			}
			
		}
		visited[target] = false;
	}

}