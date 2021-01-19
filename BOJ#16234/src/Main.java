import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int n = sc.nextInt();
		final int l = sc.nextInt();
		final int r = sc.nextInt();
		int[][] matrix = new int[n][n];
		boolean[][] visited1;
		boolean[][] visited2;
		int answer = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		boolean check = false;
		ArrayList<Point> [] list;
		do {
			list = new ArrayList[n * n + 1];
			for(int i = 0; i <= n * n; i++) {
				list[i] = new ArrayList<Point>();
			}
			visited1 = new boolean[n][n];
			visited2 = new boolean[n][n];
			Queue<Point> que = new LinkedList<Point>();
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!visited1[i][j]) {
						for(int k = 0; k < 4; k++) {
							int nextX = i + dx[k];
							int nextY = j + dy[k];
							if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) {
								continue;
							}
							else {
								if(visited1[nextX][nextY] == false) {
									int minus = Math.abs(matrix[i][j] - matrix[nextX][nextY]);
									if(minus >= l && minus <= r) {
										list[i * n + j].add(new Point(nextX, nextY));
										check = true;
									}
								}
								}
							}
					}
				}
			}
			if(!check) {
				break;
			}
			boolean [] listCheck = new boolean[n * n + 1];
			for(int i = 0; i < n * n + 1; i++) {
				int size = list[i].size();
				if(size != 0 && listCheck[((i / n) * n) + (i % n)] == false) {
					ArrayList<Point> union = new ArrayList<Point>();
					int max = 0;
					que.add(new Point(i / n, i % n));
					visited2[i / n][i % n] = true;
					listCheck[((i / n) * n) + (i % n)] = true;
					while(!que.isEmpty()) {
						int nowX = que.peek().x;
						int nowY = que.poll().y;
						max += matrix[nowX][nowY];
						union.add(new Point(nowX, nowY));
						listCheck[(nowX * n) + (nowY % n)] = true;
						for(Point next : list[nowX * n + nowY]) {
							if(!visited2[next.x][next.y]) {
								que.add(new Point(next.x, next.y));
								visited2[next.x][next.y] = true;
							}
						}
					}
					for(int j = 0; j < union.size(); j++) {
						Point country = union.get(j);
						matrix[country.x][country.y] = max / union.size();
					}
					check = false;
				}
			}
			answer++;
		}while(!check);
		System.out.println(answer);
		sc.close();
	}

}
