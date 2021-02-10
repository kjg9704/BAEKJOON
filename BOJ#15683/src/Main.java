import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point{
	int x;
	int y;
	int camera;
	public Point(int x, int y, int camera) {
		this.x = x;
		this.y = y;
		this.camera = camera;
	}
}

public class Main {

	static int[][] matrix;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int result = Integer.MAX_VALUE;
	static int N;
	static int M;
	static Queue<Point> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		matrix = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		list = new LinkedList<Point>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] != 0 && matrix[i][j] != 6)
					list.add(new Point(i, j, matrix[i][j]));
			}
		}
		bruteForce(list);
		System.out.println(result);
		sc.close();
	}
	static void bruteForce(Queue<Point> list) {
		if(!list.isEmpty()) {
			Point now = list.poll();
			switch(now.camera) {
			case 1:
				for(int j = 0; j < 4; j++) {
					set(now, j);
					bruteForce(list);
					reset(now, j);
				}
				break;
			case 2:
				for(int j = 0; j < 2; j++) {
					set(now, j);
					set(now, j + 2);
					bruteForce(list);
					reset(now, j);
					reset(now, j + 2);
				}
				break;
			case 3:
				for(int j = 0; j < 4; j++) {
					set(now, j);
					set(now, (j + 1) % 4);
					bruteForce(list);
					reset(now, j);
					reset(now, (j + 1) % 4);
				}
				break;
			case 4:
				for(int j = 0; j < 4; j++) {
					set(now, j);
					set(now, (j + 1) % 4);
					set(now, (j + 2) % 4);
					bruteForce(list);
					reset(now, j);
					reset(now, (j + 1) % 4);
					reset(now, (j + 2) % 4);
				}
				break;
			case 5:
				for(int j = 0; j < 4; j++) {
					set(now, j);
				}
				bruteForce(list);
				break;
			}
		}
		else {
			int cnt = 0;
			for(int[] i : matrix) {
				for(int j : i) {
					if(j == 0) cnt++;
				}
			}
			for(int[] i : matrix) {
				for(int j : i) {
					System.out.print(j + " ");
				}
				System.out.println();
			}
			System.out.println(cnt);
			result = Math.min(result, cnt);
		}
	}
	static void set(Point point, int direction) {
		int nextX = point.x + dx[direction];
		int nextY = point.y + dy[direction];
		while(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && matrix[nextX][nextY] != 6) {
			if(matrix[nextX][nextY] == 0) {
				matrix[nextX][nextY] = -1;
			}
			else if(matrix[nextX][nextY] < 0) {
				matrix[nextX][nextY] -= 1;
			}
			nextX += dx[direction];
			nextY += dy[direction];
		}
	}
	static void reset(Point point, int direction) {
		int nextX = point.x + dx[direction];
		int nextY = point.y + dy[direction];
		while(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && matrix[nextX][nextY] != 6) {
			if(matrix[nextX][nextY] == -1) {
				matrix[nextX][nextY] = 0;
			}
			else if(matrix[nextX][nextY] < 0) {
				matrix[nextX][nextY] += 1;
			}
			nextX += dx[direction];
			nextY += dy[direction];
		}
	}

}
