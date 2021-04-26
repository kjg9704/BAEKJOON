import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Point{
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static int R;
	static int C;
	static int T;
	static int[][] matrix;
	static Point conditionerUp;
	static Point conditionerDown;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		T = Integer.parseInt(temp[2]);
		matrix = new int[R + 1][C + 1];
		boolean check = false;
		for(int i = 1; i <= R; i++) {
			temp = br.readLine().split(" ");
			for(int j = 1; j <= C; j++) {
				int now = Integer.parseInt(temp[j - 1]);
				if(now == -1 && !check) {
					conditionerUp = new Point(i, j);
					conditionerDown = new Point(i + 1, j);
					check = true;
				}
				matrix[i][j] = now;
			}
		}
		for(int i = 0; i < T; i++) {
			clean();
		}
		int result = 0;
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				if(matrix[i][j] > 0) {
					result += matrix[i][j];
				}
			}
		}
		System.out.println(result);
		br.close();
	}

	static void clean() {
		int[][] matrix2 = new int[R + 1][C + 1];
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				if(matrix[i][j] > 0) {
					int count = 0;
					int now = matrix[i][j];
					for(int z = 0; z < 4; z++) {
						int nextX = i + dx[z];
						int nextY = j + dy[z];
						if(nextX > 0 && nextY > 0 && nextX <= R && nextY <= C && matrix[nextX][nextY] != -1) {
							count++;
							matrix2[nextX][nextY] += now / 5;
						}
					}
					matrix[i][j] = now - (now / 5 * count);
				}
			}
		}
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				matrix[i][j] += matrix2[i][j];
			}
		}
		//확산 끝 청소 시작
		for(int i = conditionerUp.x - 1; i > 1; i--) {
			matrix[i][1] = matrix[i - 1][1];
		}
		for(int i = 1; i < C; i++) {
			matrix[1][i] = matrix[1][i + 1];
		}
		for(int i = 1; i < conditionerUp.x; i++) {
			matrix[i][C] = matrix[i + 1][C];
		}
		for(int i = C; i >= 2; i--) {
			if(i == 2) {
				matrix[conditionerUp.x][i] = 0;
				break;
			}
			matrix[conditionerUp.x][i] = matrix[conditionerUp.x][i - 1];
		}
		
		for(int i = conditionerDown.x + 1; i < R; i++) {
			matrix[i][1] = matrix[i + 1][1];
		}
		for(int i = 1; i < C; i++) {
			matrix[R][i] = matrix[R][i + 1];
		}
		for(int i = R; i > conditionerDown.x; i--) {
			matrix[i][C] = matrix[i - 1][C];
		}
		for(int i = C; i >= 2; i--) {
			if(i == 2) {
				matrix[conditionerDown.x][i] = 0;
				break;
			}
			matrix[conditionerDown.x][i] = matrix[conditionerDown.x][i - 1];
		}
		
		
	}

}
