import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Point implements Comparable<Point>{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Point o) {
		return o.x - this.x;
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
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	static void ThrowStick(int height, int direction) {
		int startHeight = R - height  + 1;
		if(direction > 0) {
			for(int i = 1; i <= C; i++) {
				if(matrix[startHeight][i] == 'x') {
					matrix[startHeight][i] = '.';
					DropCheck();
					break;
				}
			}
		}else {
			for(int i = C; i >= 1; i--) {
				if(matrix[startHeight][i] == 'x') {
					matrix[startHeight][i] = '.';
					DropCheck();
					break;
				}
			}
		}
	}
	
	static void DropCheck() {
		boolean[][] visited = new boolean[R + 1][C + 1];
		Queue<Point> que = new LinkedList<>();
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				if(matrix[i][j] == 'x' && visited[i][j] == false) { // 미네랄이 나오면 해당미네랄부터 bfs돌려서 클러스터 전부 순회함
					ArrayList<Point> cluster = new ArrayList<>();
					Point start = new Point(i, j);
					que.add(start);
					visited[i][j] = true;
					boolean drop = true;
					while(!que.isEmpty()) {
						Point now = que.poll();
						cluster.add(now);
						if(now.x == R) { // 클러스터내의 미네랄 중 하나라도 바닥에 닿아있으면 떨어지지않음
							drop = false;
						}
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							if(nextX > 0 && nextY > 0 && nextX <= R && nextY <= C && matrix[nextX][nextY] == 'x' && visited[nextX][nextY] == false) {
								visited[nextX][nextY] = true;
								que.add(new Point(nextX, nextY));
							}
						}
					}
					if(drop) {
						Drop(cluster);
						return;
					}
				}
			}
		}
		
	}
	
	static void Drop(ArrayList<Point> cluster) {
		Collections.sort(cluster); // 사실 정렬안해도됨
		int dropCount = Integer.MAX_VALUE;
		//떨어뜨릴 칸수 계산
		boolean[][] visited = new boolean[R + 1][C + 1];
		for(int i = 0; i < cluster.size(); i++) {
			Point now = cluster.get(i);
			visited[now.x][now.y] = true;
			if(matrix[now.x + 1][now.y] == '.') {
				int count = 1;
				int nowX = now.x + 1;
				while(nowX < R && matrix[nowX + 1][now.y] == '.') {
					count++;
					nowX++;
				}
				if(nowX < R && visited[nowX + 1][now.y]) { // 아랫부분이 같은 클러스터의 미네랄 이라면 계산에서 제외
					continue;
				}
				dropCount = Math.min(dropCount, count);
			}
		}
		//드랍
		for(int i = 0; i < cluster.size(); i++) {
			Point now = cluster.get(i);
			matrix[now.x][now.y] = '.';
			matrix[now.x + dropCount][now.y] = 'x';
		}
	}

}
