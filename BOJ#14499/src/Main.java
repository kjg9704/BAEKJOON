import java.util.Scanner;

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Dice{
	Point point;
	int[] side = {0, 0, 0, 0, 0, 0};
	public Dice(int x, int y) {
		this.point = new Point(x, y);
	}
}
public class Main {

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int K = sc.nextInt();
		int[][] matrix = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		Dice dice = new Dice(x, y);
		for(int i = 0; i < K; i++) {
			int move = sc.nextInt();
			int nextX = dice.point.x + dx[move - 1];
			int nextY = dice.point.y + dy[move- 1];
			if(nextX >= N || nextY >= M || nextX < 0 || nextY < 0) {
				continue;	
			}
			dice.point = new Point(nextX, nextY);
			int[] temp = new int[6];
			for(int j = 0; j < 6; j++) {
				temp[j] = dice.side[j];
			}
			switch(move) {
			case 1://µ¿
				dice.side[5] = temp[3];
				dice.side[1] = temp[5];
				dice.side[2] = temp[1];
				dice.side[3] = temp[2];
				break;
			case 2://¼­
				dice.side[5] = temp[1];
				dice.side[3] = temp[5];
				dice.side[2] = temp[3];
				dice.side[1] = temp[2];
				break;
			case 3://ºÏ
				dice.side[5] = temp[0];
				dice.side[4] = temp[5];
				dice.side[2] = temp[4];
				dice.side[0] = temp[2];
				break;
			case 4://³²
				dice.side[5] = temp[4];
				dice.side[0] = temp[5];
				dice.side[2] = temp[0];
				dice.side[4] = temp[2];
				break;
			}
			if(matrix[nextX][nextY] == 0) {
				matrix[nextX][nextY] = dice.side[5];
			}
			else {
				dice.side[5] = matrix[nextX][nextY];
				matrix[nextX][nextY] = 0;
			}
			System.out.println(dice.side[2]);
		}
		sc.close();
	}
}
