import java.io.*;
import java.util.*;

public class Main {

	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int L = Integer.parseInt(input[2]);
		int K = Integer.parseInt(input[3]);
		int max = 0;
		Point[] stars = new Point[K];
		for(int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			stars[i] = new Point(x, y);
		}
		
		
		for(int i = 0; i < K; i++) {
			for(int j = i; j < K; j++) {
				int baseX = Math.min(stars[i].x, stars[j].x);
				int baseY = Math.min(stars[i].y, stars[j].y);
				int cnt = 0;
				for(int z = 0; z < K; z++) {
					if(stars[z].x >= baseX && stars[z].x <= baseX + L && stars[z].y >= baseY && stars[z].y <= baseY + L) {
						cnt++;
					}
				}
				
				max = Math.max(max, cnt);
				
			}
		}
		
		System.out.println(K - max);

	}

}
