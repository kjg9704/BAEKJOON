import java.io.*;
import java.util.*;

public class Main {

	static int N;
	
	static int[][] green_matrix = new int[6][4];
	static int[][] blue_matrix = new int[4][6];
	static int score = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		for(int i = 1; i <= N; i++) {
			String[] input = br.readLine().split(" ");
			
			int t = Integer.parseInt(input[0]);
			int x = Integer.parseInt(input[1]);
			int y = Integer.parseInt(input[2]);

			green_input(t, x, y, i);
			blue_input(t, x, y, i);
			green_check();
			blue_check();
		}
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(green_matrix[i + 2][j] > 0) {
					cnt++;
				}
				
				if(blue_matrix[i][j + 2] > 0) {
					cnt++;
				}
			}
		}
		
		System.out.println(score);
		System.out.println(cnt);
	}
	
	static void green_check() {
		
		while(true) {
			boolean fill = false;
			for(int i =  5; i >= 2; i--) {
				boolean flag = true;
				for(int j = 0; j < 4; j++) {
					if(green_matrix[i][j] == 0) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					fill =  true;
					for(int j = 0; j < 4; j++) {
						green_matrix[i][j] = 0;
					}
					score++;
				
				}
			}
			
			if(fill) {
				for(int i = 4; i >= 0; i--) {
					for(int j = 0; j < 4; j++) {
						if(green_matrix[i][j] == 0) continue;
						if(j < 3 && green_matrix[i][j] == green_matrix[i][j + 1]) {
							int startX = i;
							while(green_matrix[startX + 1][j] == 0 && green_matrix[startX + 1][j + 1] == 0) {
								green_matrix[startX + 1][j] = green_matrix[startX][j];
								green_matrix[startX + 1][j + 1] = green_matrix[startX][j + 1];
								green_matrix[startX][j] = 0;
								green_matrix[startX][j + 1] = 0;
								startX++;
								if(startX + 1 == 6) break;
							}

							j++;
						}else if(i >= 1 && green_matrix[i][j] == green_matrix[i - 1][j]) {
							int startX = i;
							while(green_matrix[startX + 1][j] == 0) {
								green_matrix[startX + 1][j] = green_matrix[startX][j];
								green_matrix[startX][j] = green_matrix[startX - 1][j];
								green_matrix[startX - 1][j] = 0;
								startX++;
								if(startX + 1 == 6) break;
							}

						}else {
							int startX = i;
							while(green_matrix[startX + 1][j] == 0) {
								green_matrix[startX + 1][j] = green_matrix[startX][j];
								green_matrix[startX][j] = 0;
								startX++;
								if(startX + 1 == 6) break;
							}

						}
					}
				}
			}else {
				break;
			}
		}
		
		
		int dupl_cnt = 0;
		for(int i = 1; i >= 0; i--) {
			for(int j = 0; j < 4; j++) {
				if(green_matrix[i][j] > 0) {
					dupl_cnt++;
					break;
				}
			}
		}
		
		if(dupl_cnt > 0) {
			for(int i = 5 - dupl_cnt; i >= 0; i--) {
				for(int j = 0; j < 4; j++) {
					green_matrix[i + dupl_cnt][j] = green_matrix[i][j];
					green_matrix[i][j] = 0;
				}
			}
		}
	}
	
	static void blue_check() {
		while(true) {
			boolean fill = false;
			for(int i =  5; i >= 2; i--) {
				boolean flag = true;
				for(int j = 0; j < 4; j++) {
					if(blue_matrix[j][i] == 0) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					fill =  true;
					for(int j = 0; j < 4; j++) {
						blue_matrix[j][i] = 0;
					}
					score++;
				
				}
			}
			
			if(fill) {
				for(int i = 4; i >= 0; i--) {
					for(int j = 0; j < 4; j++) {
						if(blue_matrix[j][i] == 0) continue;
						if(j < 3 && blue_matrix[j][i] == blue_matrix[j + 1][i]) {
							int startY = i;
							while(blue_matrix[j][startY + 1] == 0 && blue_matrix[j + 1][startY + 1] == 0) {
								blue_matrix[j][startY + 1] = blue_matrix[j][startY];
								blue_matrix[j + 1][startY + 1] = blue_matrix[j + 1][startY];
								blue_matrix[j][startY] = 0;
								blue_matrix[j + 1][startY] = 0;
								startY++;
								if(startY + 1 == 6) break;
							}

							j++;
						}else if(i >= 1 && blue_matrix[j][i] == blue_matrix[j][i - 1]) {
							int startY = i;
							while(blue_matrix[j][startY + 1] == 0) {
								blue_matrix[j][startY + 1] = blue_matrix[j][startY];
								blue_matrix[j][startY] = blue_matrix[j][startY - 1];
								blue_matrix[j][startY - 1] = 0;
								startY++;
								if(startY + 1 == 6) break;
							}

						}else {
							int startY = i;
							while(blue_matrix[j][startY + 1] == 0) {
								blue_matrix[j][startY + 1] = blue_matrix[j][startY];
								blue_matrix[j][startY] = 0;
								startY++;
								if(startY + 1 == 6) break;
							}

						}
					}
				}
			}else {
				break;
			}
		}
		
		int dupl_cnt = 0;
		for(int i = 1; i >= 0; i--) {
			for(int j = 0; j < 4; j++) {
				if(blue_matrix[j][i] > 0) {
					dupl_cnt++;
					break;
				}
			}
		}
		
		if(dupl_cnt > 0) {
			for(int i = 5 - dupl_cnt; i >= 0; i--) {
				for(int j = 0; j < 4; j++) {
					blue_matrix[j][i + dupl_cnt] = blue_matrix[j][i];
					blue_matrix[j][i] = 0;
				}
			}
		}
	}
	
	static void green_input(int t, int x, int y, int stage) {
		int startX = 0;
		int startY = y;
		int idx = 0;
		for(idx = 0; idx < 6; idx++) {
			if(green_matrix[startX + idx][startY] > 0) {
				break;
			}
			if(t == 2) {
				if(green_matrix[startX + idx][startY + 1] > 0) {
					break;
				}
			}else if(t == 3) {
				if(idx >= 5) break;
				if(green_matrix[startX + 1 + idx][startY] > 0) {
					break;
				}
			}
		}
		idx--;
		if(t == 2) {
			green_matrix[startX + idx][startY] = stage;
			green_matrix[startX + idx][startY + 1] = stage;
		}else if(t == 3) {
			green_matrix[startX + idx][startY] = stage;
			green_matrix[startX + idx + 1][startY] = stage;
		}else {
			green_matrix[startX + idx][startY] = stage;
		}
		
	}
	
	static void blue_input(int t, int x, int y, int stage) {
		int startX = x;
		int startY = 0;
		int idx = 0;
		for(idx = 0; idx < 6; idx++) {
			if(blue_matrix[startX][startY + idx] > 0) {
				break;
			}
			if(t == 2) {
				if(idx >= 5) break;
				if(blue_matrix[startX][startY + idx + 1] > 0) {
					break;
				}
			}else if(t == 3) {
				if(blue_matrix[startX + 1][startY + idx] > 0) {
					break;
				}
			}
		}
		idx--;
		if(t == 2) {
			blue_matrix[startX][startY + idx] = stage;
			blue_matrix[startX][startY + idx + 1] = stage;
		}else if(t == 3) {
			blue_matrix[startX][startY + idx] = stage;
			blue_matrix[startX + 1][startY + idx] = stage;
		}else {
			blue_matrix[startX][startY + idx] = stage;
		}
	}
	
	static void print() {
		System.out.println("----------green-----------");
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(green_matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("----------blue-----------");
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(blue_matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	

}
