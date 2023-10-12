package boj_5373;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static char[][][] cube;
	static char[] cube_arr = {'w', 'g', 'r', 'b', 'o', 'y'};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(br.readLine());
		cube = new char[6][3][3];
		

		for(int test = 0; test < T; test++) {
			init();
			int N = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			for(int i = 0; i < N; i++) {
				turn(input[i]);
				
				print();
			}
			
			
			
		}

	}
	
	static void print() {
		System.out.println("-------");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				sb.append(cube[0][i][j]);
			}
			if(i != 2) {
				sb.append('\n');
			}
			
		}
		
		System.out.println(sb.toString());
	}
	static void turn(String color) {
		switch(color.charAt(0)) {
		case 'U':
			cube[0] = rotate_one_side(0, color.charAt(1));
			rotate_U(color.charAt(1));
			break;
		case 'L':
			cube[1] = rotate_one_side(1, color.charAt(1));
			rotate_L(color.charAt(1));
			break;
		case 'F':
			cube[2] = rotate_one_side(2, color.charAt(1));
			rotate_F(color.charAt(1));
			break;
		case 'R':
			cube[3] = rotate_one_side(3, color.charAt(1));
			rotate_R(color.charAt(1));
			break;
		case 'B':
			cube[4] = rotate_one_side(4, color.charAt(1));
			rotate_B(color.charAt(1));
			break;
		case 'D':
			cube[5] = rotate_one_side(5, color.charAt(1));
			rotate_D(color.charAt(1));
			break;
			
		}
	}
	
	static void rotate_U(char dir) {
		if(dir == '-') {
			int[] arr = {4, 3, 2, 1};
			char[] tmp = { cube[4][0][0], cube[4][0][1], cube[4][0][2]};
			for(int i = 1; i < 4; i++) {
				int side = arr[i];
				int target = arr[i - 1];
				cube[target][0][0] = cube[side][0][0];
				cube[target][0][1] = cube[side][0][1];
				cube[target][0][2] = cube[side][0][2];
			}
			
			cube[1][0][0] = tmp[0];
			cube[1][0][1] = tmp[1];
			cube[1][0][2] = tmp[2];
			
		}else {
			int[] arr = {4, 1, 2, 3};
			char[] tmp = { cube[4][0][0], cube[4][0][1], cube[4][0][2]};
			for(int i = 1; i < 4; i++) {
				int side = arr[i];
				int target = arr[i - 1];
				cube[target][0][0] = cube[side][0][0];
				cube[target][0][1] = cube[side][0][1];
				cube[target][0][2] = cube[side][0][2];
			}
			
			cube[3][0][0] = tmp[0];
			cube[3][0][1] = tmp[1];
			cube[3][0][2] = tmp[2];
		}
	}
	
	static void rotate_L(char dir) {
		if(dir == '-') {
			int[] arr = {0, 2, 5, 4};
			char[] tmp = { cube[0][0][0], cube[0][1][0], cube[0][2][0] };
			
			cube[0][0][0] = cube[2][0][0];
			cube[0][1][0] = cube[2][1][0];
			cube[0][2][0] = cube[2][2][0];
			
			cube[2][0][0] = cube[5][0][0];
			cube[2][1][0] = cube[5][1][0];
			cube[2][2][0] = cube[5][2][0];
			
			cube[5][2][0] = cube[4][0][2];
			cube[5][1][0] = cube[4][1][2];
			cube[5][0][0] = cube[4][2][2];
			
			cube[4][2][2] = tmp[0];
			cube[4][1][2] = tmp[1];
			cube[4][0][2] = tmp[2];
			
		}else {
			int[] arr = {0, 4, 5, 2};
			char[] tmp = { cube[0][0][0], cube[0][1][0], cube[0][2][0] };
			
			cube[0][2][0] = cube[4][0][2];
			cube[0][1][0] = cube[4][1][2];
			cube[0][0][0] = cube[4][2][2];
			
			cube[4][2][2] = cube[5][0][0];
			cube[4][1][2] = cube[5][1][0];
			cube[4][0][2] = cube[5][2][0];
			
			cube[5][0][0] = cube[2][0][0];
			cube[5][1][0] = cube[2][1][0];
			cube[5][2][0] = cube[2][2][0];
			
			cube[2][0][0] = tmp[0];
			cube[2][1][0] = tmp[1];
			cube[2][2][0] = tmp[2];
		}
	}
	
	static void rotate_F(char dir) {
		if(dir == '-') {
			int[] arr = {0, 3, 5, 1};
			char[] tmp = { cube[0][2][0], cube[0][2][1], cube[0][2][2] };
			
			cube[0][2][0] = cube[3][0][0];
			cube[0][2][1] = cube[3][1][0];
			cube[0][2][2] = cube[3][2][0];
			
			cube[3][2][0] = cube[5][0][0];
			cube[3][1][0] = cube[5][0][1];
			cube[3][0][0] = cube[5][0][2];
			
			cube[5][0][0] = cube[1][0][2];
			cube[5][0][1] = cube[1][1][2];
			cube[5][0][2] = cube[1][2][2];
			
			cube[1][2][2] = tmp[0];
			cube[1][1][2] = tmp[1];
			cube[1][0][2] = tmp[2];
			
		}else {
			int[] arr = {0, 1, 5, 3};
			char[] tmp = { cube[0][2][0], cube[0][2][1], cube[0][2][2] };
			
			cube[0][2][2] = cube[1][0][2];
			cube[0][2][1] = cube[1][1][2];
			cube[0][2][0] = cube[1][2][2];
			
			cube[1][0][2] = cube[5][0][0];
			cube[1][1][2] = cube[5][0][1];
			cube[1][2][2] = cube[5][0][2];
			
			cube[5][0][2] = cube[3][0][0];
			cube[5][0][1] = cube[3][1][0];
			cube[5][0][0] = cube[3][2][0];
			
			cube[3][0][0] = tmp[0];
			cube[3][1][0] = tmp[1];
			cube[3][2][0] = tmp[2];
		}
	}
	
	static void rotate_R(char dir) {
		if(dir == '-') {
			int[] arr = {0, 4, 5, 2};
			char[] tmp = { cube[0][0][2], cube[0][1][2], cube[0][2][2] };
			
			cube[0][2][2] = cube[4][0][0];
			cube[0][1][2] = cube[4][1][0];
			cube[0][0][2] = cube[4][2][0];
			
			cube[4][2][0] = cube[5][0][2];
			cube[4][1][0] = cube[5][1][2];
			cube[4][0][0] = cube[5][2][2];
			
			cube[5][0][2] = cube[2][0][2];
			cube[5][1][2] = cube[2][1][2];
			cube[5][2][2] = cube[2][2][2];
			
			cube[2][0][2] = tmp[0];
			cube[2][1][2] = tmp[1];
			cube[2][2][2] = tmp[2];
			
		}else {
			int[] arr = {0, 2, 5, 4};
			char[] tmp = { cube[0][0][2], cube[0][1][2], cube[0][2][2] };
			
			cube[0][0][2] = cube[2][0][2];
			cube[0][1][2] = cube[2][1][2];
			cube[0][2][2] = cube[2][2][2];
			
			cube[2][0][2] = cube[5][0][2];
			cube[2][1][2] = cube[5][1][2];
			cube[2][2][2] = cube[5][2][2];
			
			cube[5][2][2] = cube[4][0][0];
			cube[5][1][2] = cube[4][1][0];
			cube[5][0][2] = cube[4][2][0];
			
			cube[4][2][0] = tmp[0];
			cube[4][1][0] = tmp[1];
			cube[4][0][0] = tmp[2];
		}
	}
	
	static void rotate_B(char dir) {
		if(dir == '-') {
			int[] arr = {0, 1, 5, 3};
			char[] tmp = { cube[0][0][2], cube[0][0][1], cube[0][0][0] };
			
			cube[0][0][2] = cube[1][0][0];
			cube[0][0][1] = cube[1][1][0];
			cube[0][0][0] = cube[1][2][0];
			
			cube[1][0][0] = cube[5][2][0];
			cube[1][1][0] = cube[5][2][1];
			cube[1][2][0] = cube[5][2][2];
			
			cube[5][2][2] = cube[3][0][2];
			cube[5][2][1] = cube[3][1][2];
			cube[5][2][0] = cube[3][2][2];
			
			cube[3][2][2] = tmp[0];
			cube[3][1][2] = tmp[1];
			cube[3][0][2] = tmp[2];
			
		}else {
			int[] arr = {0, 3, 5, 1};
			char[] tmp = { cube[0][0][2], cube[0][0][1], cube[0][0][0] };
			
			cube[0][0][2] = cube[3][2][2];
			cube[0][0][1] = cube[3][1][2];
			cube[0][0][0] = cube[3][0][2];
			
			cube[3][0][2] = cube[5][2][2];
			cube[3][1][2] = cube[5][2][1];
			cube[3][2][2] = cube[5][2][0];
			
			cube[5][2][0] = cube[1][0][0];
			cube[5][2][1] = cube[1][1][0];
			cube[5][2][2] = cube[1][2][0];
			
			cube[1][0][0] = tmp[0];
			cube[1][1][0] = tmp[1];
			cube[1][2][0] = tmp[2];
		}
	}
	
	static void rotate_D(char dir) {
		if(dir == '-') {
			int[] arr = {2, 3, 4, 1};
			char[] tmp = { cube[2][2][0], cube[2][2][1], cube[2][2][2]};
			for(int i = 1; i < 4; i++) {
				int side = arr[i];
				int target = arr[i - 1];
				cube[target][2][0] = cube[side][2][0];
				cube[target][2][1] = cube[side][2][1];
				cube[target][2][2] = cube[side][2][2];
			}
			
			cube[1][2][0] = tmp[0];
			cube[1][2][1] = tmp[1];
			cube[1][2][2] = tmp[2];
			
		}else {
			int[] arr = {2, 1, 4, 3};
			char[] tmp = { cube[2][2][0], cube[2][2][1], cube[2][2][2]};
			for(int i = 1; i < 4; i++) {
				int side = arr[i];
				int target = arr[i - 1];
				cube[target][2][0] = cube[side][2][0];
				cube[target][2][1] = cube[side][2][1];
				cube[target][2][2] = cube[side][2][2];
			}
			
			cube[3][2][0] = tmp[0];
			cube[3][2][1] = tmp[1];
			cube[3][2][2] = tmp[2];
		}
	}
	
	static char[][] rotate_one_side(int side, char dir) {
		char[][] temp = new char[3][3];
		
		if(dir == '-') {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					temp[i][j] = cube[side][j][3 - i - 1];
				}
			}
		}else {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					temp[j][3 - i - 1] = cube[side][i][j];
				}
			}
		}
		
		return temp;

	}
	
	
	
	
	static void init() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 3; j++) {
				for(int z = 0; z < 3; z++) {
					cube[i][j][z] = cube_arr[i];
				}
			}
		}
	}

}
