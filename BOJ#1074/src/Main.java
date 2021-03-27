import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int r;
	static int c;
	static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		r = Integer.parseInt(temp[1]);
		c = Integer.parseInt(temp[2]);
		result = 0;
		divide(N, 0, 0, (int)Math.pow(2, N) - 1, (int)Math.pow(2, N) - 1);

	}

	static void divide(int N, int startX, int startY, int endX, int endY) {
		if(N == 1) {
			if(startX == r && startY == c) {
				System.out.println(result);
			}else if(startX == r && startY + 1 == c) {
				System.out.println(result + 1);
			}else if(startX + 1 == r && startY == c) {
				System.out.println(result + 2);
			}else if(startX + 1 == r && startY + 1 == c) {
				System.out.println(result + 3);
			}
			return ;
		}
		int temp = (int)Math.pow(2, N - 1);
		int count = (int)Math.pow(2, N) * (int)Math.pow(2, N) / 4;
		if(r >= startX && r <= endX - temp && c >= startY && c <= endY - temp) {
			divide(N- 1, startX, startY, endX - temp, endY - temp);
		}else if(r >= startX && r <= endX - temp && c >= endY - temp + 1 && c <= endY) {
			result = result + count;
			divide(N- 1, startX, endY - temp + 1, endX - temp, endY);
		}else if(r >= endX -temp + 1 && r <= endX && c >= startY && c <= endY - temp) {
			result = result + count * 2;
			divide(N- 1, endX -temp + 1, startY, endX, endY - temp);
		}else if(r >= endX - temp + 1 && r <= endX && c >= endY - temp + 1 && c <= endY) {
			result = result + count * 3;
			divide(N- 1, endX - temp + 1, endY - temp + 1, endX, endY);
		}
		return ;
	}

}
