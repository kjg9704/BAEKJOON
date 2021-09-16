import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int K;
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		K = Integer.parseInt(temp[1]);
		int[] training = new int[N];
		boolean[] visited = new boolean[N];
		int[] output = new int[N];
		temp = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			training[i] = Integer.parseInt(temp[i]);
		}


		dfs(training, output, visited, 500, 0, N, N);
		
		System.out.println(result);

	}

	//���� ���ϱ�
	static void dfs(int[] arr, int[] output, boolean[] visited, int weight, int depth, int n, int r) {
		if(depth == r) {
			result++; // Ƚ�� ī��Ʈ
			return;
		}

		for(int i = 0; i < n; i++) {
			if(visited[i] != true) {
				if(weight - K + arr[i] < 500) {
					continue; // ���� 500�� �ȵǸ� ���� depth�� �� �ʿ䰡 ����(��Ʈ��ŷ)
				}
				visited[i] = true;
				output[depth] = arr[i];
				dfs(arr, output, visited, weight - K + arr[i], depth + 1, n, r);    
				visited[i] = false;
				
			}
		}
	}

}