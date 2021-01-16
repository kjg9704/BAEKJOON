import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Main {
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		for(int i = 0; i < testCase; i++) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			int k = sc.nextInt();
			Stack<Point> stack = new Stack<Point>();
			ArrayList<Point> list = new ArrayList<Point>();
			int [][] matrix = new int[n][m];
			boolean [][] visited = new boolean[n][m];
			int answer = 0;
			for(int j = 0; j < k; j++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				matrix[y][x] = 1;
				list.add(new Point(x, y));
			}
			for(int z = 0; z < list.size(); z++) {
				int x = list.get(z).x;
				int y = list.get(z).y;
				if(!visited[y][x]) {
					stack.push(list.get(z));
					visited[y][x] = true;
					while(!stack.isEmpty()) {
						x = stack.peek().x;
						y = stack.pop().y;
						visited[y][x] = true;
						for(int j = 0; j < 4; j++) {
							int nextX = x + dx[j];
							int nextY = y + dy[j];
							if(nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {
								continue;
							}
							else {
								if(matrix[nextY][nextX] == 1 && visited[nextY][nextX] == false) {
									stack.push(new Point(nextX, nextY));
								}
							}
						}
					}
					answer++;
				}	
			}
			
			System.out.println(answer);
		}
		sc.close();
	}

}
