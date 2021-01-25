import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static ArrayList<Integer> results;
	static ArrayList<Point> houseList;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] matrix = new int[N + 1][N + 1];
		ArrayList<Point> chickenList = new ArrayList<Point>();
		houseList = new ArrayList<Point>();
		results = new ArrayList<Integer>();
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				int temp = sc.nextInt();
				matrix[i][j] = temp;
				if(temp == 2) {
					chickenList.add(new Point(i, j));
				}
				else if(temp == 1) {
					houseList.add(new Point(i, j));
				}
			}
		}
		boolean[] visited = new boolean[chickenList.size()];
		combi(chickenList, visited, 0, M);
		System.out.println(Collections.min(results));
		sc.close();
	}
	
	static void combi(ArrayList<Point> chickenList, boolean[] visited, int depth, int r) {
        if(r == 0) {
        	ArrayList<Point> points = new ArrayList<Point>();
        	for(int i = 0; i < chickenList.size(); i++) {
                if(visited[i] == true)
                    points.add(chickenList.get(i));
            }
        	calcChicken(points);
            return;
        }
        if(depth == chickenList.size()) {
            return;
        } else {
            visited[depth] = true;
            combi(chickenList, visited, depth + 1, r - 1);
 
            visited[depth] = false;
            combi(chickenList, visited, depth + 1, r);
        }
    }
	static void calcChicken(ArrayList<Point> points) {
		int path = 0;
		for(int i = 0; i < houseList.size(); i++) {
			int num = 999999;
			for(Point point : points) {
				int calc = Math.abs(houseList.get(i).x - point.x) + Math.abs(houseList.get(i).y - point.y);
				if(num > calc) {
					num = calc;
				}
			}
			path += num;
		}
		results.add(path);
	}

}
