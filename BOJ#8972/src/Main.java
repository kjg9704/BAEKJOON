import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] size = sc.nextLine().split(" ");
		int R = Integer.parseInt(size[0]);
		int C = Integer.parseInt(size[1]);
		String[][] matrix = new String[R][C];
		Point me = new Point(0, 0);
		Queue<Point> que = new LinkedList<>();
		for(int i = 0; i < R; i++) {
			String column = sc.nextLine();
			for(int j = 0; j < C; j++) {
				String str = column.charAt(j) + "";
				matrix[i][j] = str;
				if(str.equals("I")){
					me.x = i;
					me.y = j;
				}
				if(str.equals("R")) {
					que.add(new Point(i, j));
				}
			}
		}
		String move = sc.nextLine();
		sc.close();
		for(int i = 0; i < move.length(); i++) {
			int moveDir = Integer.parseInt(move.charAt(i) + "");
			String[][] matrix2 = new String[R][C];
			if(moveDir != 5) {
				int nextX = me.x + dx[moveDir];
				int nextY = me.y + dy[moveDir];
				if(matrix[nextX][nextY].equals("R")) {
					System.out.println("kraj " + (i + 1));
					return;
				}else {
					matrix2[nextX][nextY] = "I";
					me.x = nextX;
					me.y = nextY;
				}
			}else {
				matrix2[me.x][me.y] = "I";
			}
			while(!que.isEmpty()) {
				Point point = que.poll();
				int nextX = point.x + dx[0];
				int nextY = point.y + dy[0];
				for(int z = 1; z < 10; z++) {
					if(z != 5) {
						int x = point.x + dx[z];
						int y = point.y + dy[z];
						int compare1 = Math.abs(me.x - nextX) + Math.abs(me.y - nextY);
						int compare2 = Math.abs(me.x - x) + Math.abs(me.y - y);
						if(compare1 > compare2) {
							nextX = x;
							nextY = y;
						}
					}
				}
				if(matrix2[nextX][nextY] != null) {
					if(matrix2[nextX][nextY].equals("I")) {
						System.out.println("kraj " + (i + 1));
						return;
					}
					matrix2[nextX][nextY] = "x";
				}else if(matrix2[nextX][nextY] == null) {
					matrix2[nextX][nextY] = "R";
				}
			}
			for(int q = 0; q < R; q++) {
				for(int j = 0; j < C; j++) {
					if(matrix2[q][j] == null || matrix2[q][j].equals("x")) {
						matrix[q][j] = ".";
					}
					else if(matrix2[q][j].equals("R")) {
						matrix[q][j] = "R";
						que.add(new Point(q, j));
					}
					else if(matrix2[q][j].equals("I")) {
						matrix[q][j] = "I";
					}
				}
			}
		}
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(matrix[i][j]);
			}
			if(i != R - 1)
			System.out.println();
		}
	}
}
