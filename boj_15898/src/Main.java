import java.io.*;
import java.util.*;

public class Main {

	static class Stuff{
		int[][] num;
		char[][] color;
		public Stuff(int[][] num, char[][] color){
			this.num = num;
			this.color = color;
		}
		
		public Stuff(Stuff base){
			this.num = new int[4][4];
			this.color = new char[4][4];
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					this.num[i][j] = base.num[i][j];
					this.color[i][j] = base.color[i][j];
				}
			}
		}
		
		public void rotate() {
			int[][] new_num = new int[4][4];
			char[][] new_color = new char[4][4];
			
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					new_num[j][4 - 1 - i] = this.num[i][j];
				}
			}
			
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					new_color[j][4 - 1 - i] = this.color[i][j];
				}
			}
			
			this.num = new_num;
			this.color = new_color;
		}
	}
	static int N;
	static Stuff[] stuff_arr;
	
	static int max = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		stuff_arr = new Stuff[N];
		
		for(int i = 0; i < N; i++) {
			int[][] num = new int[4][4];
			for(int j = 0; j < 4; j++) {
				String[] input = br.readLine().split(" ");
				for(int z = 0; z < 4; z++) {
					num[j][z] = Integer.parseInt(input[z]);
				}
			}
			
			char[][] color = new char[4][4];
			for(int j = 0; j < 4; j++) {
				String[] input = br.readLine().split(" ");
				for(int z = 0; z < 4; z++) {
					color[j][z] = input[z].charAt(0);
				}
			}
			stuff_arr[i] = new Stuff(num, color);
		}
		
		boolean[] visited = new boolean[N];
		int[] arr = new int[3];
		dfs(visited, arr, 0);
		
		System.out.println(max);

	}
	
	static void copy(int[][] base_num, char[][] base_color, int[][] tmp_num, char[][] tmp_color) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				tmp_num[i][j] = base_num[i][j];
				tmp_color[i][j] = base_color[i][j];
			}
		}
	}
	
	static void make(int[][] num, char[][] color, int[] arr, int index) {
		if(index == arr.length) {
			int res = getScore(num, color);
			max = Math.max(max, res);
			return;
		}
		
		Stuff rotated = new Stuff(stuff_arr[arr[index]]);
		for(int i = 0; i < 4; i++) {

			if(i > 0) {
				rotated.rotate();
			}
			
			for(int r = 0; r < 2; r++) {
				for(int c = 0; c < 2; c++) {
					
					int[][] next_num = new int[5][5];
					char[][] next_color = new char[5][5];
					copy(num, color, next_num, next_color);
					for(int j = 0; j < 4; j++) {
						for(int z = 0; z < 4; z++) {
							int point = num[r + j][c + z] + rotated.num[j][z];
							if(point > 9) {
								next_num[r + j][c + z] = 9;
							}else if(point < 0) {
								next_num[r + j][c + z] = 0;
							}else {
								next_num[r + j][c + z] = point;
							}
							
							if(rotated.color[j][z] != 'W') {
								next_color[r + j][c + z] = rotated.color[j][z];
							}
						}
					}
					
					make(next_num, next_color, arr, index + 1);
				}
			}
			
		}
	}
	
	static void dfs(boolean[] visited, int[] arr, int depth) {
		if(depth == 3) {
			int[][] base_num = new int[5][5];
			char[][] base_color = new char[5][5];
			
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					base_num[i][j] = 0;
					base_color[i][j] = 'W';
				}
			}
			make(base_num, base_color, arr, 0);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				dfs(visited, arr, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static int getScore(int[][] num, char[][] color) {
		int R = 0;
		int B = 0;
		int G = 0;
		int Y = 0;
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++) {
				switch(color[i][j]) {
				case 'R':
					R += num[i][j];
					break;
					
				case 'B':
					B += num[i][j];
					break;
					
				case 'G':
					G += num[i][j];
					break;
				
				case 'Y':
					Y += num[i][j];
					break;
				}
			}
		}
		//7R + 5B + 3G + 2Y
		return (7 * R) + (5 * B) + (3 * G) + (2 * Y);
	}

}
