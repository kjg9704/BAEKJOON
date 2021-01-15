import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
        int m = Integer.parseInt(temp[0]);
        int n = Integer.parseInt(temp[1]);
		int [][] matrix = new int[n][m];
		boolean[] changeCheck = new boolean[m*n];
		Arrays.fill(changeCheck, false);
		Queue<Point> que1 = new LinkedList<Point>();
		Queue<Point> que2 = new LinkedList<Point>();
		for(int i = 0; i < n; i++) {
			String[] tomatoes = br.readLine().split(" ");
			for(int j = 0; j < m; j++) {
				int tomato = Integer.parseInt(tomatoes[j]);
				matrix[i][j] = tomato;
				if(tomato == 1) {
					que1.add(new Point(i, j));
				}
			}
		}
		boolean flag = true;
		int answer = 0;
		while(que1.size() != 0 || que2.size() != 0) {
			if(flag) {
				Point tomato = que1.poll();
				int x = tomato.x;
				int y = tomato.y;
				try {
					if(matrix[x][y + 1] == 0) {
						matrix[x][y + 1] = 1;
						que2.add(new Point(x, y + 1));
					}
				}catch(Exception e) {
					
				}
				try {
					if(matrix[x][y - 1] == 0) {
						matrix[x][y - 1] = 1;
						que2.add(new Point(x, y - 1));
					}
				}catch(Exception e) {
					
				}
				try {
					if(matrix[x + 1][y] == 0) {
						matrix[x + 1][y] = 1;
						que2.add(new Point(x + 1, y));
					}
				}catch(Exception e) {
					
				}
				try {
					if(matrix[x - 1][y] == 0) {
						matrix[x - 1][y] = 1;
						que2.add(new Point(x - 1, y));
					}
				}catch(Exception e) {
					
				}
				if(que1.isEmpty()) {
					flag = false;
					if(!que2.isEmpty()) {
						answer++;
					}
				}
			}
			else {
				Point tomato = que2.poll();
				int x = tomato.x;
				int y = tomato.y;
				try {
					if(matrix[x][y + 1] == 0) {
						matrix[x][y + 1] = 1;
						que1.add(new Point(x, y + 1));
					}
				}catch(Exception e) {
					
				}
				try {
					if(matrix[x][y - 1] == 0) {
						matrix[x][y - 1] = 1;
						que1.add(new Point(x, y - 1));
					}
				}catch(Exception e) {
					
				}
				try {
					if(matrix[x + 1][y] == 0) {
						matrix[x + 1][y] = 1;
						que1.add(new Point(x + 1, y));
					}
				}catch(Exception e) {
					
				}
				try {
					if(matrix[x - 1][y] == 0) {
						matrix[x - 1][y] = 1;
						que1.add(new Point(x - 1, y));
					}
				}catch(Exception e) {
					
				}
				if(que2.isEmpty()) {
					flag = true;
					if(!que1.isEmpty()) {
						answer++;
					}
				}
			}
			
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(matrix[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(answer);
		br.close();
	}
}