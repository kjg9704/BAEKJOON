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
		boolean[][] visited = new boolean[1500][1500]; // bfs �湮ó��, 3���� ���� 3���� ���������°�쿡�� ����ϱ⶧���� ���� �ΰ��� ���� ���ٸ� ���������� �׻���
		if(sum % 3 != 0) { // ���� 3���� ����������� ������ ������ 0 ���
			System.out.println(0);
			return;
		}
		Queue<Stones> que = new LinkedList<>();
		que.add(new Stones(A, B, C));
		visited[Math.min(A, B)][Math.max(A, B)] = true; // ù �Է°� �湮ó��
		visited[Math.min(A, C)][Math.max(A, C)] = true;
		visited[Math.min(B, C)][Math.max(B, C)] = true;
		while(!que.isEmpty()) {
			Stones now = que.poll();
			for(int i = 0; i < 2; i++) { // A B C�� ������ �� �ܰ迡�� �Ҽ��ִ� ����� ���� A B, A C, B C �����̹Ƿ� ���� ����� ť�� ����
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