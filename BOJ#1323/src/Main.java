import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		StringBuilder sb = new StringBuilder(String.valueOf(N));
		
		//N�� K�� ���� �������� N�� �̾���̰� K�� �����°��� N�� �̾�ٿ��� ���������� ���ϴ°Ͱ� ����
		for(int i = 1; i <= K; i++) {
			long mod = Long.parseLong(sb.toString()) % K;
			if(mod == 0) {
				System.out.println(i);
				return;
			}else {
				sb = new StringBuilder(String.valueOf(mod) + String.valueOf(N));
			}
		}
		//K�� ������ ������ �������� ������ ������ -1
		System.out.println(-1);
	}

}