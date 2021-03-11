import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


class Point{
	int x;
	int y;
	int dir;
	int cnt;
	public Point(int x, int y, int dir, int cnt) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cnt = cnt;
	}
}
public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int W = Integer.parseInt(temp[0]);
		int H = Integer.parseInt(temp[1]);
		String[][] matrix = new String[H][W];
		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;
		Queue<Point> que = new LinkedList<>();
		for(int i = 0; i < H; i++) {
			String str = br.readLine();
			for(int j = 0; j < str.length(); j++) {
				String var = str.charAt(j) + "";
				matrix[i][j] = var;
				if(var.equals("C")) {
					if(startX == -1) {
						startX = i;
						startY = j;
					}
					else if(startX != -1 && endX == -1) {
						endX = i;
						endY = j;
					}
				}
			}
		}
		int[][] matrixCnt = new int[H][W];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				matrixCnt[i][j] = -1;
			}
		}
		for(int i = 0; i < 4; i++) {
			int nextX = startX + dx[i];
			int nextY = startY + dy[i];
			if(nextX >= 0 && nextY >= 0 && nextX < H && nextY < W && !matrix[nextX][nextY].equals("*")) {
				matrixCnt[nextX][nextY] = 0;
				que.add(new Point(nextX, nextY, i, 0));
			}
		}
		while(!que.isEmpty()) {
			Point now = que.poll();
			for(int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX >= 0 && nextY >= 0 && nextX < H && nextY < W && !(nextX == startX && nextY ==  startY) && !matrix[nextX][nextY].equals("*")) {
					if(i == now.dir) {
						if(matrixCnt[nextX][nextY] == -1) {
							que.add(new Point(nextX, nextY, i, now.cnt));
							matrixCnt[nextX][nextY] = now.cnt;
						}else if(matrixCnt[nextX][nextY] >= now.cnt) {
							que.add(new Point(nextX, nextY, i, now.cnt));
							matrixCnt[nextX][nextY] = now.cnt;
						}
					}else {
						if(matrixCnt[nextX][nextY] == -1) {
							que.add(new Point(nextX, nextY, i, now.cnt + 1));
							matrixCnt[nextX][nextY] = now.cnt + 1;
						}else if(matrixCnt[nextX][nextY] >= now.cnt + 1) {
							que.add(new Point(nextX, nextY, i, now.cnt + 1));
							matrixCnt[nextX][nextY] = now.cnt + 1;
						}
						
					}
				}
			}
//			System.out.println("------------------");
//			for(int i = 0; i < H; i++) {
//				for(int j = 0; j < W; j++) {
//					System.out.print(matrixCnt[i][j] + " ");
//				}
//				System.out.println();
//			}
		}
//		for(int i = 0; i < H; i++) {
//			for(int j = 0; j < W; j++) {
//				System.out.print(matrixCnt[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(matrixCnt[endX][endY]);
	}

}
