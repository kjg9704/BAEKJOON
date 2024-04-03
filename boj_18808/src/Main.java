import java.io.*;
import java.util.*;

public class Main {
	
	static class Sticker{
		int x, y, size;
		int[][] matrix;
		public Sticker(int x, int y, int[][] matrix, int size){
			this.x = x;
			this.y = y;
			this.matrix = matrix;
			this.size = size;
		}
	}

	static int N, M, K;
	static int[][] note;
	static Sticker[] stickers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		
		stickers = new Sticker[K];
		note = new int[N][M];
		for(int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			int[][] matrix = new int[r][c];
			int size = 0;
			for(int j = 0; j < r; j++) {
				input = br.readLine().split(" ");
				for(int z = 0; z < c; z++) {
					int now = Integer.parseInt(input[z]);
					matrix[j][z] = now;
					if(now == 1) {
						size++;
					}
				}
			}
			
			stickers[i] = new Sticker(r, c, matrix, size);
		}
		
		int cnt = 0;
		for(Sticker now : stickers) {
			
			boolean flag = false;
			for(int z = 0; z < 4; z++) {
				if(flag) break;
				int[][] new_matrix = rotate(now.matrix, z);
				for(int i = 0; i < N; i++) {
					if(flag) break;
					for(int j = 0; j < M; j++) {
						if(check(new_matrix, i, j)) {
							flag = true;
							cnt += now.size;
							break;
						}
					}
				}
				
			}
			
		}
		
		System.out.println(cnt);

	}
	
	static boolean check(int[][] sticker, int x, int y) {
		int note_x = note.length;
		int note_y = note[0].length;
		
		int sticker_x = sticker.length;
		int sticker_y = sticker[0].length;
		
		if(note_x - x < sticker_x || note_y - y < sticker_y) return false;
		
		for(int i = 0; i < sticker_x; i++) {
			for(int j = 0; j < sticker_y; j++) {
				if(sticker[i][j] == 1 && note[x + i][y + j] == 1) {
					return false;
				}
			}
		}
		
		for(int i = 0; i < sticker_x; i++) {
			for(int j = 0; j < sticker_y; j++) {
				if(sticker[i][j] == 1) {
					note[x + i][y + j] = 1;
				}
			}
		}
		
		return true;
	}
	
	static int[][] rotate(int[][] matrix, int degree){
		int pre_x = matrix.length;
		int pre_y = matrix[0].length;
		int[][] new_matrix = null;
		if(degree == 0) {
			return matrix;
		}
		switch(degree) {
		
		case 1: // 90
			new_matrix = new int[pre_y][pre_x];
			break;
			
		case 2: // 180
			new_matrix = new int[pre_x][pre_y];
			break;
			
		case 3: // 270
			new_matrix = new int[pre_y][pre_x];
			break;
		}
		
		for(int i = 0; i < new_matrix.length; i++) {
			for(int j = 0; j < new_matrix[i].length; j++) {
				switch(degree) {
				
				case 1: // 90
					new_matrix[i][j] = matrix[pre_x - 1 - j][i];
					break;
					
				case 2: // 180
					new_matrix[i][j] = matrix[pre_x - 1 - i][pre_y - 1 - j];
					break;
					
				case 3: // 270
					new_matrix[i][j] = matrix[j][pre_y - 1 - i];
					break;
				}
			}
		}
		
		return new_matrix;
		
	}

}
