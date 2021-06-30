import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static char[][] matrix;
	static int R;
	static int C;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		matrix = new char[R + 1][C + 1];
		for(int i = 1; i <= R; i++) {
			String var = br.readLine();
			for(int j = 1; j <= C; j++) {
				matrix[i][j] = var.charAt(j - 1);
			}
		}
		int N = Integer.parseInt(br.readLine());
		temp = br.readLine().split(" ");
		int direction = 1;
		for(String height : temp) {
			ThrowStick(Integer.parseInt(height), direction);
			direction *= -1;
		}
	}
	
	static void ThrowStick(int height, int direction) {
		int startHeight = R - height  + 1;
		if(direction > 0) {
			for(int i = 1; i <= C; i++) {
				if(matrix[startHeight][i] == 'x') {
					matrix[startHeight][i] = '.';
					if(DropCheck()) {
						
					}
					break;
				}
			}
		}else {
			for(int i = C; i >= 1; i--) {
				if(matrix[startHeight][i] == 'x') {
					matrix[startHeight][i] = '.';
					if(DropCheck()) {
						
					}
					break;
				}
			}
		}
	}
	
	static boolean DropCheck() {
		boolean[][] visited = new boolean[R + 1][C + 1];
		Queue<Point> que = new LinkedList<>();
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				if(matrix[i][j] == 'x' && visited[i][j] == false) {
					Point now = new Point(i, j);
					que.add(now);
					while(!que.isEmpty()) {
						
					}
				}
			}
		}
		
		return false;
	}

}
