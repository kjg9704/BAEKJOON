import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] dx = {-1, 0 , 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int endX = 0;
	static int endY;
	static int R;
	static int C;
	static int K;
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		K = Integer.parseInt(temp[2]);
		char[][] matrix = new char[R][C];
		endY = C - 1;
		boolean[][] visited = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				matrix[i][j] = str.charAt(j);
			}
		}
		
		// �������� �湮ó��
		visited[R - 1][0] = true;
		dfs(matrix, visited, R - 1, 0, 1);
		
		System.out.println(result);
	}
	
	static void dfs(char[][] matrix, boolean[][] visited, int startX, int startY, int count) {
		// ���������� �������� ��
		if(startX == endX && startY == endY) {
			if(count == K) {
				result++;
			}
			return;
		}
		
		//4�������� dfs����, �̶� ����ĭ�� T�̸� �����ʰ� �̹� �湮�Ѱ� ���� ��������
		for(int i = 0; i < 4; i++) {
			int nextX = startX + dx[i];
			int nextY = startY + dy[i];
			if(nextX >= 0 && nextY >= 0 && nextX < R && nextY < C && matrix[nextX][nextY] != 'T' && !visited[nextX][nextY]) {
				visited[nextX][nextY] = true; // dfs �� �� �湮ó�� ���ְ� ���ö� �ٽ� false�� �ٲ� 
				dfs(matrix, visited, nextX, nextY, count + 1);
				visited[nextX][nextY] = false;
			}
		}
	}

}