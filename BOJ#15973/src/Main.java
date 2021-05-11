import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Square{
	Point LD;
	Point RU;
	public Square(Point LD, Point RU) {
		this.RU = RU;
		this.LD = LD;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		Square one = new Square(new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])), new Point(Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
		temp = br.readLine().split(" ");
		Square two = new Square(new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])), new Point(Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
		if(one.LD.x <= two.LD.x) {
			System.out.println(check(one, two));
		}else {
			System.out.println(check(two, one));
		}
	}
	static String check(Square one, Square two) {
		if(one.RU.x == two.LD.x) {
			if(one.RU.y == two.LD.y || one.LD.y == two.RU.y) {
				return "POINT";
			}else if(one.RU.y < two.LD.y || one.LD.y > two.RU.y) {
				return "NULL";
			}else {
				return "LINE";
			}
		}else if(one.RU.x > two.LD.x){
			if(one.RU.y == two.LD.y || one.LD.y == two.RU.y) {
				return "LINE";
			}else if((one.RU.y >= two.RU.y && one.LD.y <= two.LD.y) || (one.RU.y <= two.RU.y && one.LD.y >= two.LD.y)) {
				return "FACE";
			}else if((one.RU.y > two.LD.y && one.LD.y < two.LD.y) || (one.LD.y < two.RU.y && one.RU.y > two.RU.y)) {
				return "FACE";
			}
		}
		return "NULL";
	}

}
