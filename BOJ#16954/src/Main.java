import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point{
	int x;
	int y;
	int time; // 현재 위치와 시간
	public Point(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}
public class Main {
	static char[][] matrix;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		matrix = new char[8][8];
		for(int i = 0; i < 8; i++) {
			String temp = br.readLine();
			for(int j = 0; j < 8; j++) {
				matrix[i][j] = temp.charAt(j);
			}
		}
		Point start = new Point(7, 0, 0);
		Queue<Point> que = new LinkedList<>();
		que.add(start);
		int time = 0;
		while(!que.isEmpty()) {
			Point now = que.poll();
			if(now.time != time) { // 큐에서 계속 빼다가 현재시간과 다른 Point 가 나오면 벽을 움직임.
				moveWall(); // 벽 1칸씩 내리기
				time++;
			}
			if(now.x == 0 && now.y == 7) { // 오른쪽 윗칸에 도달하면 1을 출력하고 종료
				System.out.println(1);
				return;
			}
			for(int i = 0; i < 9; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8 && matrix[nextX][nextY] == '.') {
					if(nextX > 0 && matrix[nextX - 1][nextY] == '#') {
						continue;
					}
					que.add(new Point(nextX, nextY, time + 1));
				}
			}
		}
		//여기까지 왔다는것은 오른쪽윗칸에 도달할 수 없다는 것, 즉 0을 출력 하고 종료
		System.out.println(0);
	}
	static void moveWall() {
		for(int i = 7; i > 0; i--) {
			for(int j = 0; j < 8; j++) {
				if(matrix[i - 1][j] == '#') {
					matrix[i][j] = '#';
					matrix[i - 1][j] = '.';
				}else if(i == 7 && matrix[i][j] == '#') {
					matrix[i][j] = '.';
				}
			}
		}
	}
}
