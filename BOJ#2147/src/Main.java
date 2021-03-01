import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Robot{
	int x;
	int y;
	int direction;

	public Robot(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
}
public class Main {

	static int[] dx = {-1, 0, 1, 0};//ºÏ -> µ¿ -> ³² -> ¼­
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int A = Integer.parseInt(temp[0]);
		int B = Integer.parseInt(temp[1]);
		temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		ArrayList<Robot> robots = new ArrayList<>();
		int[][] matrix = new int[B + 1][A + 1];
		robots.add(new Robot(-1, -1, -1));
		for(int i = 1; i <= N; i++) {
			temp = br.readLine().split(" ");
			int dir = 0;
			int x = Integer.parseInt(temp[0]);
			int y = Integer.parseInt(temp[1]);
			switch(temp[2]) {
			case "N":
				dir = 0;
				break;
			case "E":
				dir = 1;
				break;
			case "S":
				dir = 2;
				break;
			case "W":
				dir = 3;
				break;
			}
			robots.add(new Robot(B + 1 - y, x, dir));
			matrix[B + 1 - y][x] = i;
		}

		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			int bot = Integer.parseInt(temp[0]);
			String order = temp[1];
			int change = 0;
			switch(order) {
			case "L":
				change = -1;
				break;
			case "R":
				change = 1;
				break;
			case "F":
				change = 2;
				break;
			}
			int loop = Integer.parseInt(temp[2]);
			for(int j = 0; j < loop; j++) {
				if(change < 2) {
					int nextDir = robots.get(bot).direction + change;
					if(nextDir < 0) {
						robots.get(bot).direction = 3;
					}else if(nextDir > 3) {
						robots.get(bot).direction = 0;
					}
					else {
						robots.get(bot).direction = nextDir;
					}
				}else {
					Robot robot = robots.get(bot);
					int nextX = robot.x + dx[robot.direction];
					int nextY = robot.y + dy[robot.direction];
					if(nextX <= 0 || nextY <= 0 || nextX > A || nextY > B) {
						System.out.println("Robot " + bot + " crashes into the wall");
						return;
					}else {
						if(matrix[nextX][nextY] != 0) {
							System.out.println("Robot " + bot + " crashes into robot "+ matrix[nextX][nextY]);
							return;
						}else {
							int nowX = robot.x;
							int nowY = robot.y;
							matrix[nowX][nowY] = 0;
							matrix[nextX][nextY] = bot;
							robots.get(bot).x = nextX;
							robots.get(bot).y = nextY;
						}
					}
				}
			}
		}
		System.out.println("OK");
	}

}
