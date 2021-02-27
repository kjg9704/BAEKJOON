import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		ArrayList<Point> arr = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			arr.add(new Point(sc.nextInt(), sc.nextInt()));
		}
		sc.close();
		Collections.sort(arr, new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                if(o1.x > o2.x) {
                    return 1;
                }else if(o1.x <o2.x) {
                    return -1;
                }else if(o1.x == o2.x){
                	if(o1.y > o2.y) {
                		return 1;
                	}
                	else if(o1.y < o2.y){
                		return -1;
                	}
                }else {
                	return 0;
                }
				return 0;
            }
        });
		for(Point point : arr) {
			System.out.print(point.x + " " +  point.y);
			System.out.println();
		}
	}
}
