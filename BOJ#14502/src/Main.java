import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N;
	static int M;
	static int result;
	static int[][] matrix;
	static ArrayList<Point> virus;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
	    M = Integer.parseInt(temp[1]);
		matrix = new int[N][M];
		virus = new ArrayList<>();
		ArrayList<Point> safeZone = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				int var = Integer.parseInt(temp[j]);
				if(var == 2) {
					virus.add(new Point(i, j));
				}else if(var == 0) {
					safeZone.add(new Point(i, j));
				}
				matrix[i][j] = var;
			}
		}
		result = 0;
		boolean[] visited = new boolean[safeZone.size()];
		combi(safeZone, visited, 0, 3);
		System.out.println(result);
		
	}
	static void combi(ArrayList<Point> safeZone, boolean[] visited, int depth, int r) {
        if(r == 0) {
        	ArrayList<Point> points = new ArrayList<Point>();
        	for(int i = 0; i < safeZone.size(); i++) {
                if(visited[i] == true)
                    points.add(safeZone.get(i));
            }
        	infection(points);
            return;
        }
        if(depth == safeZone.size()) {
            return;
        } else {
            visited[depth] = true;
            combi(safeZone, visited, depth + 1, r - 1);
 
            visited[depth] = false;
            combi(safeZone, visited, depth + 1, r);
        }
		
	}
	
	static void infection(ArrayList<Point> points) {
		Queue<Point> que = new LinkedList<>();
		int[][] tempMatrix = deepCopy(matrix);
		boolean[][] visited = new boolean[N][M];
		for(Point p : points) {
			tempMatrix[p.x][p.y] = 1;
		}
		for(Point p : virus) {
			visited[p.x][p.y] = true;
			que.add(p);
		}
		while(!que.isEmpty()) {
			Point now = que.poll();
			for(int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && tempMatrix[nextX][nextY] == 0 && visited[nextX][nextY] == false) {
					visited[nextX][nextY] = true;
					tempMatrix[nextX][nextY] = 2;
					que.add(new Point(nextX, nextY));
				}
			}
		}
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(tempMatrix[i][j] == 0) {
					count++;
				}
			}
		}
		result = Math.max(result, count);
	}
	
	static int[][] deepCopy(int[][] matrix){
		int[][] result = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				result[i][j] = matrix[i][j];
			}
		}
		return result;
	}

}
