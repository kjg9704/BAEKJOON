import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int H = Integer.parseInt(temp[0]);
		int W = Integer.parseInt(temp[1]);
		int X = Integer.parseInt(temp[2]);
		int Y = Integer.parseInt(temp[3]);
		int[][] matrixB = new int[H + X][W + Y];
		for(int i = 0; i <  H + X; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < W + Y; j++) {
				matrixB[i][j] = Integer.parseInt(temp[j]);
			}
		}
		int[][] matrixA = new int[H][W];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(i < X) {
					matrixA[i][j] = matrixB[i][j];
				}else {
					if(j < Y) {
						matrixA[i][j] = matrixB[i][j];
					}else {
						matrixA[i][j] = matrixB[i][j] - matrixA[i - X][j - Y];
					}
				}
			}
		}
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				System.out.print(matrixA[i][j] + " ");
			}
			System.out.println();
		}
	}
}