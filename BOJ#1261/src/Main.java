import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point{
	int x;
	int y;
	int count; //부순 벽 개수
	public Point(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
	}
}
public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int M = Integer.parseInt(temp[0]);
		int N = Integer.parseInt(temp[1]);
		int[][] matrix = new int[N + 1][M + 1];
		int[][] count = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			String str = br.readLine();
			for(int j = 0; j < str.length(); j++) {
				matrix[i][j + 1] = Integer.parseInt(str.charAt(j) + "");
				if(!(i == 1 && j + 1 == 1))
					count[i][j + 1] = Integer.MAX_VALUE;
			}
		}
		br.close();
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(1, 1, 0));
		while(!que.isEmpty()) {    // bfs로 1,1 부터 돌면서 다음칸이 유효하면서 count 수가 기존의 값보다 작을때 que에 넣고 count 수 갱신
			Point now = que.poll();
			for(int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX > 0 && nextY > 0 && nextX <= N && nextY <= M) {
					if(matrix[nextX][nextY] == 0) {
						if(now.count < count[nextX][nextY]) {
							que.add(new Point(nextX, nextY, now.count));
							count[nextX][nextY] = now.count;
						}
					}else if(matrix[nextX][nextY] == 1) {
						if(now.count + 1 < count[nextX][nextY]) {
							que.add(new Point(nextX, nextY, now.count + 1));
							count[nextX][nextY] = now.count + 1;
						}
					}
				}

			}
			System.out.println("--------------------");
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					System.out.print(count[i][j] + " ");
				}
				System.out.println();
			}
		}
		System.out.println(count[N][M]);
	}
}
