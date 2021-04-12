import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Ball{
	int redX;
	int redY;
	int blueX;
	int blueY;
	int count;
	String move;
	public Ball(int redX, int redY, int blueX, int blueY, int count) {
		this.redX = redX;
		this.redY = redY;
		this.blueX = blueX;
		this.blueY = blueY;
		this.count = count;
		move = "";
	}
	public Ball(int redX, int redY, int blueX, int blueY, int count, String move) {
		this.redX = redX;
		this.redY = redY;
		this.blueX = blueX;
		this.blueY = blueY;
		this.count = count;
		this.move = move;
	}
}

public class Main {

	static int[] dx = {-1, 0 , 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static char[][] matrix;
	static int N;
	static int M;
	static int holeX;
	static int holeY;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		matrix = new char[N][M];
		holeX = -1;
		holeY = -1;
		Ball start = new Ball(0, 0, 0, 0, 0);
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				char var = line.charAt(j);
				if(var == 'O') {
					holeX = i;
					holeY = j;
				}
				if(var == 'R') {
					start.redX = i;
					start.redY = j;
				}
				if(var == 'B') {
					start.blueX = i;
					start.blueY = j;
				}
				if(var == 'R' || var == 'B') {
					matrix[i][j] = '.';
				}else {
					matrix[i][j] = var;
				}
				
			}
		}
		Queue<Ball> que = new LinkedList<>();
		que.add(start);
		while(!que.isEmpty()) {
			Ball now = que.poll();
			if(now.redX == holeX && now.redY == holeY && (now.blueX != holeX || now.blueY != holeY)) {
				System.out.println(now.count);
				System.out.println(now.move);
				return;
			}
			if(now.count < 10) {
				for(int i = 0; i < 4; i++) {
					que.add(move(now, i));
				}
			}

		}
		System.out.println("-1");
	}

	static Ball move(Ball now, int direction) {
		int redX = now.redX;
		int redY = now.redY;
		int blueX = now.blueX;
		int blueY = now.blueY;
		int count = now.count;
		String move = now.move;
		switch(direction) {
		case 0:
			if(redX > blueX) {
				for(int i = 0; i < N; i++) {
					if(matrix[redX + dx[direction]][redY + dy[direction]] == '.' && !(redX == holeX && redY == holeY) && !(redX + dx[direction] == blueX && redY + dy[direction] == blueY)) {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					} else if(matrix[redX + dx[direction]][redY + dy[direction]] == 'O') {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					}
					
					if(matrix[blueX + dx[direction]][blueY + dy[direction]] == '.' && !(blueX == holeX && blueY == holeY) && !(blueX + dx[direction] == redX && blueY + dy[direction] == redY)) {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					} else if(matrix[blueX + dx[direction]][blueY + dy[direction]] == 'O') {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					}
				}
			}else {
				for(int i = 0; i < N; i++) {
					if(matrix[blueX + dx[direction]][blueY + dy[direction]] == '.' && !(blueX == holeX && blueY == holeY) && !(blueX + dx[direction] == redX && blueY + dy[direction] == redY)) {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					} else if(matrix[blueX + dx[direction]][blueY + dy[direction]] == 'O') {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					}
					
					if(matrix[redX + dx[direction]][redY + dy[direction]] == '.' && !(redX == holeX && redY == holeY) && !(redX + dx[direction] == blueX && redY + dy[direction] == blueY)) {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					} else if(matrix[redX + dx[direction]][redY + dy[direction]] == 'O') {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					}
				}
			}
			move += "U";
			break;
		case 1:
			if(redY > blueY) {
				for(int i = 0; i < M; i++) {
					if(matrix[redX + dx[direction]][redY + dy[direction]] == '.' && !(redX == holeX && redY == holeY) && !(redX + dx[direction] == blueX && redY + dy[direction] == blueY)) {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					} else if(matrix[redX + dx[direction]][redY + dy[direction]] == 'O') {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					}
					
					if(matrix[blueX + dx[direction]][blueY + dy[direction]] == '.' && !(blueX == holeX && blueY == holeY) && !(blueX + dx[direction] == redX && blueY + dy[direction] == redY)) {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					} else if(matrix[blueX + dx[direction]][blueY + dy[direction]] == 'O') {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					}
				}
			}else {
				for(int i = 0; i < M; i++) {
					if(matrix[blueX + dx[direction]][blueY + dy[direction]] == '.' && !(blueX == holeX && blueY == holeY) && !(blueX + dx[direction] == redX && blueY + dy[direction] == redY)) {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					} else if(matrix[blueX + dx[direction]][blueY + dy[direction]] == 'O') {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					}
					
					if(matrix[redX + dx[direction]][redY + dy[direction]] == '.' && !(redX == holeX && redY == holeY) && !(redX + dx[direction] == blueX && redY + dy[direction] == blueY)) {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					} else if(matrix[redX + dx[direction]][redY + dy[direction]] == 'O') {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					}
				}
			}
			move += "R";
			break;
		case 2:
			if(redX < blueX) {
				for(int i = 0; i < N; i++) {
					if(matrix[redX + dx[direction]][redY + dy[direction]] == '.' && !(redX == holeX && redY == holeY) && !(redX + dx[direction] == blueX && redY + dy[direction] == blueY)) {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					} else if(matrix[redX + dx[direction]][redY + dy[direction]] == 'O') {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					}
					
					if(matrix[blueX + dx[direction]][blueY + dy[direction]] == '.' && !(blueX == holeX && blueY == holeY) && !(blueX + dx[direction] == redX && blueY + dy[direction] == redY)) {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					} else if(matrix[blueX + dx[direction]][blueY + dy[direction]] == 'O') {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					}
				}
			}else {
				for(int i = 0; i < N; i++) {
					if(matrix[blueX + dx[direction]][blueY + dy[direction]] == '.' && !(blueX == holeX && blueY == holeY) && !(blueX + dx[direction] == redX && blueY + dy[direction] == redY)) {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					} else if(matrix[blueX + dx[direction]][blueY + dy[direction]] == 'O') {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					}
					
					if(matrix[redX + dx[direction]][redY + dy[direction]] == '.' && !(redX == holeX && redY == holeY) && !(redX + dx[direction] == blueX && redY + dy[direction] == blueY)) {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					} else if(matrix[redX + dx[direction]][redY + dy[direction]] == 'O') {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					}
				}
			}
			move += "D";
			break;
		case 3:
			if(redY < blueY) {
				for(int i = 0; i < M; i++) {
					if(matrix[redX + dx[direction]][redY + dy[direction]] == '.' && !(redX == holeX && redY == holeY) && !(redX + dx[direction] == blueX && redY + dy[direction] == blueY)) {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					} else if(matrix[redX + dx[direction]][redY + dy[direction]] == 'O') {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					}
					
					if(matrix[blueX + dx[direction]][blueY + dy[direction]] == '.' && !(blueX == holeX && blueY == holeY) && !(blueX + dx[direction] == redX && blueY + dy[direction] == redY)) {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					} else if(matrix[blueX + dx[direction]][blueY + dy[direction]] == 'O') {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					}
				}
			}else {
				for(int i = 0; i < M; i++) {
					if(matrix[blueX + dx[direction]][blueY + dy[direction]] == '.' && !(blueX == holeX && blueY == holeY) && !(blueX + dx[direction] == redX && blueY + dy[direction] == redY)) {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					} else if(matrix[blueX + dx[direction]][blueY + dy[direction]] == 'O') {
						blueX = blueX + dx[direction];
						blueY = blueY + dy[direction];
					}
					
					if(matrix[redX + dx[direction]][redY + dy[direction]] == '.' && !(redX == holeX && redY == holeY) && !(redX + dx[direction] == blueX && redY + dy[direction] == blueY)) {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					} else if(matrix[redX + dx[direction]][redY + dy[direction]] == 'O') {
						redX = redX + dx[direction];
						redY = redY + dy[direction];
					}
				}
			}
			move += "L";
			break;
		}
		return new Ball(redX, redY, blueX, blueY, count + 1, move);
	}

}
