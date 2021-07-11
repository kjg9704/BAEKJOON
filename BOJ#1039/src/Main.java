import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Transfer{
	int num;
	int count;
	public Transfer(int num, int count) {
		this.num = num;
		this.count = count;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		br.close();
		int result = 0;
		Queue<Transfer> que = new LinkedList<>();
		que.add(new Transfer(N, 0));
		boolean check = false;
		boolean[][] visited = new boolean[1000001][11]; // �湮 Ȯ�ι迭(ť�� �ʹ����̳־ �޸��ʰ������ ����)
		while(!que.isEmpty()) {
			Transfer now = que.poll();
			char[] arr = String.valueOf(now.num).toCharArray();
			if(now.count < K) { // count �� K �̸��϶� 
				int len = arr.length;
				for(int i = 0; i < len - 1; i++) { // 0 <= i <= j <= M ���� ����������
					for(int j = i + 1; j < len; j++) {
						char[] newarr = arr.clone();
						char temp = arr[i];
						newarr[i] = arr[j];
						newarr[j] = temp;
						int newInt = Integer.parseInt(new String(newarr));
						if(newarr[0] != '0' && visited[newInt][now.count + 1] == false) { // �迭 ù�ڰ� 0�� �ƴϰ� �湮������ ���ٸ� ť������
							Transfer next = new Transfer(newInt, now.count + 1);
							visited[newInt][next.count] = true;
							que.add(next);
						}
					}
				}
			}else { // count�� K �϶� ����� ����
				check = true;
				result = Math.max(result, now.num);
			}
		}
		if(check) {
			System.out.println(result);
		}else {
			System.out.println(-1);
		}
	}

}