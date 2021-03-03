import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

class Food{
	int x;
	int y;
	int distance;
	public Food(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
}

public class Main {

	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	static int size;
	static int N;
	static int[][] matrix;
	static ArrayList<Food> fish;
	static Point shark;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		N = Integer.parseInt(str);
		matrix = new int[N][N];
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				int var = Integer.parseInt(temp[j]);
				if(var == 9) {
					shark = new Point(i, j);
				}
				matrix[i][j] = var;
			}
		}
		int count = 0;
		size = 2;
		int grow = 0;
		while(true) {
			check();
			if(fish.size() != 0) {
				Food food = fish.get(0);
				int time = food.distance;
				count += time;
				matrix[food.x][food.y] = 9;
				matrix[shark.x][shark.y] = 0;
				shark.x = food.x;
				shark.y = food.y;
				grow++;
				if(size == grow) {
					size++;
					grow = 0;
				}
			}
			else {
				System.out.println(count);
				return;
			}
			System.out.println("--------------------");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("현재 카운트----- : " + count + "  size : " + size);
		}
	}

	static void check() {
		Queue<Food> que = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		fish = new ArrayList<>();
		visited[shark.x][shark.y] = true;
		que.add(new Food(shark.x, shark.y, 0));
		while(!que.isEmpty()) {
			Food now = que.poll();
			for(int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < N && visited[nextX][nextY] == false && matrix[nextX][nextY] <= size) {
					if(matrix[nextX][nextY] < size && matrix[nextX][nextY] != 0) {
						if(fish.isEmpty()) {
							fish.add(new Food(nextX, nextY, now.distance + 1));
						}
						else if(fish.get(0).distance == now.distance + 1){
							if(fish.get(0).x > nextX) {
								fish.set(0, new Food(nextX, nextY, now.distance + 1));
							}else if(fish.get(0).x == nextX) {
								if(fish.get(0).y > nextY) {
									fish.set(0, new Food(nextX, nextY, now.distance + 1));
								}
							}
						}
					}
					visited[nextX][nextY] = true;
					que.add(new Food(nextX, nextY, now.distance + 1));
				}
			}
		}
	}

}
