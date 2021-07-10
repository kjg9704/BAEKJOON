import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static int[] dx = {-1, 0 , 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static char[][] matrix;
	static int result;
	static int R;
	static int C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		matrix = new char[R][C];
		result = 1;
		ArrayList<Character> list = new ArrayList<>();
		for(int i = 0; i < R; i++) {
			String row = br.readLine();
			for(int j = 0; j < C; j++) {
				matrix[i][j] = row.charAt(j);
			}
		}
		list.add(matrix[0][0]);
		dfs(0, 0, list, 1);
		System.out.println(result);
	}
	
	static void dfs(int x, int y, ArrayList<Character> list, int count) {
		if(count > result) {
			result = count;
		}
		for(int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if(nextX >= 0 && nextX < R && nextY >= 0 && nextY < C && !list.contains(matrix[nextX][nextY])) {
				list.add(matrix[nextX][nextY]);
				dfs(nextX, nextY, list, count + 1);
				list.remove(Character.valueOf(matrix[nextX][nextY]));
			}
		}
	}

}