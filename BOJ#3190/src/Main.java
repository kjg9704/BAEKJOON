import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class ChangeDirection{
	int second;
	String direction;
	public ChangeDirection(int second, String direction) {
		this.second = second;
		this.direction = direction;
	}
}
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Snake{
	Deque<Point> body;
	int direction;
	public Snake(int direction) {
		body = new ArrayDeque<Point>();
		body.addFirst(new Point(1, 1));
		this.direction = direction;
	}
}

public class Main {

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int N = sc.nextInt();
		final int K = sc.nextInt();
		int[][] matrix = new int[N + 1][N + 1];
		int result = 0;

		for(int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			matrix[x][y] = 1;
		}
		Snake snake = new Snake(2);
		final int L = sc.nextInt();
		Queue<ChangeDirection> changeList = new LinkedList<ChangeDirection>();
		for(int i = 0; i < L; i++) {
			int second = sc.nextInt();
			String dire = sc.next();
			changeList.add(new ChangeDirection(second, dire));
		}
		while(true) {
			result++;
			Point now = snake.body.peekFirst();
			int nextDirection = snake.direction;
			int nextX = now.x + dx[nextDirection];
			int nextY = now.y + dy[nextDirection];
			Point next = new Point(nextX, nextY);
			if(next.x > N || next.y > N || next.x == 0 || next.y == 0 || check(snake.body, next)) {
				break;
			}
			snake.body.addFirst(next);
			if(matrix[nextX][nextY] == 1) {
				matrix[nextX][nextY] = 0;
			}
			else {
				snake.body.pollLast();
			}
			if(!changeList.isEmpty()) {
				if(result == changeList.peek().second) {
					String change = changeList.poll().direction;
					if(change.equals("L")) {
						snake.direction += 1;
						if(snake.direction == 4) {
							snake.direction = 0;
						}
					}
					else if(change.equals("D")) {
						snake.direction -= 1;
						if(snake.direction == -1) {
							snake.direction = 3;
						}
					}
				}
			}	
		}
		System.out.println(result);
		sc.close();
	}
	static boolean check(Deque<Point> deque, Point next) {
		if(deque != null) {
			Iterator<Point> itr = deque.iterator();
			while(itr.hasNext()){
				Point temp = itr.next();
				if(temp.x == next.x && temp.y == next.y) {
					return true;
				}
			}
		}
		return false;
	}
}
