import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Stones{
	int[] stone;
	public Stones(int A, int B, int C) {
		stone = new int[3];
		stone[0] = A;
		stone[1] = B;
		stone[2] = C;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int A = Integer.parseInt(temp[0]);
		int B = Integer.parseInt(temp[1]);
		int C = Integer.parseInt(temp[2]);
		br.close();
		int sum = A + B + C;
		boolean[][] visited = new boolean[1500][1500]; // bfs 방문처리, 3값의 합이 3으로 나누어지는경우에만 사용하기때문에 앞의 두값의 합이 같다면 마지막값은 항상같음
		if(sum % 3 != 0) { // 합이 3으로 나누어떨어지지 않으면 무조건 0 출력
			System.out.println(0);
			return;
		}
		Queue<Stones> que = new LinkedList<>();
		que.add(new Stones(A, B, C));
		visited[Math.min(A, B)][Math.max(A, B)] = true; // 첫 입력값 방문처리
		visited[Math.min(A, C)][Math.max(A, C)] = true;
		visited[Math.min(B, C)][Math.max(B, C)] = true;
		while(!que.isEmpty()) {
			Stones now = que.poll();
			for(int i = 0; i < 2; i++) { // A B C가 있을때 각 단계에서 할수있는 경우의 수는 A B, A C, B C 세개이므로 각각 계산후 큐에 넣음
				for(int j = i + 1; j < 3; j++) {
					if(now.stone[i] < now.stone[j]) {
						int newI = now.stone[i] + now.stone[i];
						int newJ = now.stone[j] - now.stone[i];
						if(!visited[Math.min(newI, newJ)][Math.max(newI, newJ)]) {
							que.add(new Stones(newI, newJ, sum-(newI + newJ)));
							visited[Math.min(newI, newJ)][Math.max(newI, newJ)] = true;
						}
					}else if(now.stone[i] > now.stone[j]) {
						int newI = now.stone[i] - now.stone[j];
						int newJ = now.stone[j] + now.stone[j];
						if(!visited[Math.min(newI, newJ)][Math.max(newI, newJ)]) {
							que.add(new Stones(newI, newJ, sum-(newI + newJ)));
							visited[Math.min(newI, newJ)][Math.max(newI, newJ)] = true;
						}
					}
				}
			}
		}
		if(visited[sum / 3][sum / 3]) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		
	}
}