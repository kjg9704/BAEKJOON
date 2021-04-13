import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

class Ground{
	int nurtient;
	int increase;
	Deque<Integer> tree = new ArrayDeque<>();
	
	public Ground(int increase) {
		this.nurtient = 5;
		this.increase = increase;
	}

}
public class Main {

	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int N;
	static int M;
	static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		K = Integer.parseInt(temp[2]);
		Ground[][] matrix = new Ground[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				int increase = Integer.parseInt(temp[j]);
				matrix[i][j + 1] = new Ground(increase);
			}
		}
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			int X = Integer.parseInt(temp[0]);
			int Y = Integer.parseInt(temp[1]);
			int age = Integer.parseInt(temp[2]);
			matrix[X][Y].tree.add(age);
		}

		for(int i = 0; i < K; i++) {
			cycle(matrix);
		}
		int result = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				result += matrix[i][j].tree.size();
			}
		}
		System.out.println(result);
	}

	static void cycle(Ground[][] matrix) {
		for(int i = 1; i <= N; i++) { //º½
			for(int j = 1; j <= N; j++) {
				if(!matrix[i][j].tree.isEmpty()) {
					Stack<Integer> grow = new Stack<>();
					int index = 0;
					int size = matrix[i][j].tree.size();
					for(int z = 0; z < size; z++) {
						if(matrix[i][j].nurtient >= matrix[i][j].tree.peekFirst()) {
							int nut = matrix[i][j].tree.pollFirst();
							grow.push(nut + 1);
							matrix[i][j].nurtient -= nut;
							index = z + 1;
						}else {
							break;
						}
					}
					for(int z = index; z < size; z++) {
						matrix[i][j].nurtient += matrix[i][j].tree.pollLast() / 2;
					}
					while(!grow.isEmpty()) {
						matrix[i][j].tree.offerFirst(grow.pop());
					}
				}

			}
		}


		for(int i = 1; i <= N; i++) { //°¡À»
			for(int j = 1; j <= N; j++) {
				for(int tree : matrix[i][j].tree) {
					if(tree % 5 == 0) {
						for(int z = 0; z < 8; z++) {
							int nextX = i + dx[z];
							int nextY = j + dy[z];
							if(nextX > 0 && nextY > 0 && nextX <= N && nextY <= N) {
								matrix[nextX][nextY].tree.offerFirst(1);
							}
						}
					}
				}
			}

		}

		for(int i = 1; i <= N; i++) { //°Ü¿ï
			for(int j = 1; j <= N; j++) {
				matrix[i][j].nurtient += matrix[i][j].increase;
			}

		}
	}

}