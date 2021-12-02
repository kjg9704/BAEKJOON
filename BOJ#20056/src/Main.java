import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Fire{
	int r, c, m, s, d;

	public Fire(int r, int c, int m, int s, int d) {
		this.r = r;
		this.c = c;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}

public class Main {
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayList<Fire> list;
	static int[][] matrix;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		int K = Integer.parseInt(temp[2]);
		list = new ArrayList<>();
		matrix = new int[N + 1][N + 1];

		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			int r = Integer.parseInt(temp[0]);
			int c = Integer.parseInt(temp[1]);
			int m = Integer.parseInt(temp[2]);
			int s = Integer.parseInt(temp[3]);
			int d = Integer.parseInt(temp[4]);
			Fire fire = new Fire(r, c, m, s, d);
			matrix[r][c] = 1;
			list.add(fire);
		}

		for(int i = 0; i < K; i++) {
			move();

			for(int j = 1; j <= N; j++) {
				for(int z = 1; z <= N; z++) {
					if(matrix[j][z] > 1) {
						mix(j, z);
					}
				}
			}

		}
		int result = 0;
		for(int i = 0; i < list.size(); i++) {
			result += list.get(i).m;
		}
		System.out.println(result);

	}

	static void move() {
		for(int i = 0; i < list.size(); i++) {
			Fire now = list.get(i);
			int nextX = now.r + ((now.s % N) * dx[now.d]);
			int nextY = now.c + ((now.s % N) * dy[now.d]);
			
			if(nextX < 1) {
				nextX = N - (Math.abs(nextX) % N);
			}else if(nextX > N) {
				nextX = nextX % N;
			}
			
			if(nextY < 1) {
				nextY = N - (Math.abs(nextY) % N);
			}else if(nextY > N) {
				nextY = nextY % N;
			}
			
			matrix[now.r][now.c]--;
			matrix[nextX][nextY]++;
			now.r = nextX;
			now.c = nextY;
		}
	}

	static void mix(int x, int y) {
		int maxM = 0;
		int maxS = 0;
		int cnt = matrix[x][y];
		boolean flag = true; //방향이 모두 홀수 또는 짝수인지 확인
		int mod = 2; // 1 == 홀수, 0 == 짝수, 2 == 초기값
		for(int i = 0; i < list.size(); i++) {
			Fire now = list.get(i);
			if(now.r == x && now.c == y) {
				maxM += now.m;
				maxS += now.s;
				if(mod == 2) {
					mod = now.d % 2;
				}else if(now.d % 2 != mod) {
					flag = false;
				}
				list.remove(i);
				i--;
			}
		}
		
		int nextM = maxM / 5;
		int nextS = maxS / cnt;
		if(nextM > 0) {
			if(flag) {
				for(int i = 0; i < 8; i+=2) {
					list.add(new Fire(x, y, nextM, nextS, i));
				}
			}else {
				for(int i = 1; i < 8; i+=2) {
					list.add(new Fire(x, y, nextM, nextS, i));
				}
			}
			matrix[x][y] = 4;
		}else {
			matrix[x][y] = 0;
		}
	}

}
