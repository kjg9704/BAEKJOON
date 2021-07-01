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
			//�������� �˰����� �����鼭 ���� ����Ŭ�� ã��. �������� ���� ������� ��������Ŭ�� �߰ߵǱ⸸�ϸ� YES��
			int[] distance = new int[N + 1];
			for(int z = 1; z <= N; z++) {
				distance[z] = INF;
			}
			//�̶� ���� �������� 0���� ����ֳ�, �����Ұ� �������� �׷����� ���� �� ����(������� 1 2 3 4 ��尡 ������ 1���� �ƹ����� ����Ǿ����� ����)
			//�׷��Ƿ� ���� �������� ��������ʰ� �׳ɵ���
			boolean cycle = false;
			for(int z = 1; z <= N; z++) {
				for(int k = 1; k <= N; k++) {
					for(int q = 1; q <= N; q++) {
						if(matrix[k][q] != INF && distance[q] > distance[k] + matrix[k][q]) {
							distance[q] = distance[k] + matrix[k][q];
							if(z == N) {//���⼭ ������ �߻��ϸ� ��������Ŭ�� �����ϴ°���(������ �ݺ������� ������ �Ͼ)
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
