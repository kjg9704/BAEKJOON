import java.util.Scanner;

class Robot{
	int x;
	int y;
	public Robot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[] backX = {1, 0, -1, 0};
	static int[] backY = {0, 1, 0, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int N = sc.nextInt();
		final int M = sc.nextInt();
		int[][] matrix = new int[N][M];
		boolean[][] clean = new boolean[N][M];
		int nextDirection = 0;
		int result = 1;
		Robot robot = new Robot(sc.nextInt(), sc.nextInt());
		int dire = sc.nextInt();
		if(dire == 1) {
			nextDirection = 3;
		}
		else if(dire == 2) {
			nextDirection = 2;
		}
		else if(dire ==3) {
			nextDirection = 1;
		}
		clean[robot.x][robot.y] = true;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		while(true) {
			int now = nextDirection;
			boolean move = false;
			for(int i = 0; i < 4; i++) {
				int nextX = robot.x + dx[nextDirection];
				int nextY = robot.y + dy[nextDirection];
				if(clean[nextX][nextY] == false && matrix[nextX][nextY] == 0) {
					nextDirection++;
					robot.x = nextX;
					robot.y = nextY;
					result++;
					clean[nextX][nextY] = true;
					if(nextDirection == 4) {
						nextDirection = 0;
					}
					move = true;
					break;
				}
				else {
					nextDirection++;
					if(nextDirection == 4) {
						nextDirection = 0;
					}
				}
			}
			if(now == nextDirection && move == false) {
				int pointX = robot.x + backX[nextDirection];
				int pointY = robot.y + backY[nextDirection];
				if(matrix[pointX][pointY] == 0) {
					robot.x = pointX;
					robot.y = pointY;
				}
				else {
					break;
				}
			}
		}
		System.out.println(result);
		sc.close();
	}

}
