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

	//순열 구하기
	static void dfs(int[] arr, int[] output, boolean[] visited, int weight, int depth, int n, int r) {
		if(depth == r) {
			result++; // 횟수 카운트
			return;
		}

		for(int i = 0; i < n; i++) {
			if(visited[i] != true) {
				if(weight - K + arr[i] < 500) {
					continue; // 만약 500이 안되면 다음 depth로 갈 필요가 없음(백트래킹)
				}
				visited[i] = true;
				output[depth] = arr[i];
				dfs(arr, output, visited, weight - K + arr[i], depth + 1, n, r);    
				visited[i] = false;
				
			}
		}
	}

}