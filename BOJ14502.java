import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Room{
	int x;
	int y;
	public Room(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class BOJ14502 {
	static int result;
	static int n;
	static int m;
	static void dfs(int collum, int row, int[][]matrix, boolean[]visited, int addWall) {
		if(addWall != 3) {
			if(matrix[collum][row] == 0) {
				matrix[collum][row] = 1;
				
				
				dfs(collum, row + 1, matrix, visited, addWall + 1);
				}
			dfs(collum, row + 1, matrix, visited, addWall);
		}
		else {
			bfs(matrix, visited);
		}
		dfs(collum, row + 1, matrix, visited, addWall + 1);
		matrix[collum][row] = 0;
		
	}
	static void bfs(int[][] matrix, boolean[] visited) {
		int[][] virus = new int[n][m];
		Queue<Room> que = new LinkedList<Room>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				virus[i][j] = matrix[i][j];
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(virus[i][j] == 2) {
					que.add(new Room(i, j));
				}
			}
		}
		while(!que.isEmpty()) {
			Room room = que.poll();
			int x = room.x;
			int y = room.y;
			for(int i = 0; i < 4; i++) {
				if()
				
			}
		}
		
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		result = 0;
		boolean[]visited = new boolean[n];
		int [][] matrix = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		dfs(0, 0, matrix, visited, 0);
		sc.close();
		System.out.println();
	}

}
