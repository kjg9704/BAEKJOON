import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node{
	int node;
	int cost;
	public Node(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
}
public class Main {
	static int INF = 20000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for(int i = 0; i < TC; i++) {
			String[] temp = br.readLine().split(" ");
			int N = Integer.parseInt(temp[0]);
			int M = Integer.parseInt(temp[1]);
			int W = Integer.parseInt(temp[2]);
			int[][] matrix = new int[N + 1][N + 1];
			for(int j = 1; j <= N; j++) {
				for(int z = 1; z <= N; z++) {
					if(j != z) {
						matrix[j][z] = INF;
					}
				}
			}
			for(int j = 0; j < M; j++) {
				temp = br.readLine().split(" ");
				int S = Integer.parseInt(temp[0]);
				int E = Integer.parseInt(temp[1]);
				int T = Integer.parseInt(temp[2]);
				if(matrix[S][E] > T) {
					matrix[S][E] = T;
					matrix[E][S] = T;
				}
			}

			for(int j = 0; j < W; j++) {
				temp = br.readLine().split(" ");
				int S = Integer.parseInt(temp[0]);
				int E = Integer.parseInt(temp[1]);
				int T = Integer.parseInt(temp[2]);
				matrix[S][E] = -T;
			}
			//벨만포드 알고리즘을 돌리면서 음수 사이클을 찾음. 시작점이 어디든 관계없이 음수사이클이 발견되기만하면 YES임
			int[] distance = new int[N + 1];
			for(int z = 1; z <= N; z++) {
				distance[z] = INF;
			}
			//이때 보통 시작점을 0으로 잡아주나, 연결요소가 여러개인 그래프가 들어올 수 있음(예를들어 1 2 3 4 노드가 있을때 1번은 아무데도 연결되어있지 않음)
			//그러므로 따로 시작점을 잡아주지않고 그냥돌림
			boolean cycle = false;
			for(int z = 1; z <= N; z++) {
				for(int k = 1; k <= N; k++) {
					for(int q = 1; q <= N; q++) {
						if(matrix[k][q] != INF && distance[q] > distance[k] + matrix[k][q]) {
							distance[q] = distance[k] + matrix[k][q];
							if(z == N) {//여기서 갱신이 발생하면 음수사이클이 존재하는것임(마지막 반복문에서 갱신이 일어남)
								cycle = true;
								break;
							}
						}
					}
					if(cycle) break;
				}
			}

			if(cycle) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}

}
