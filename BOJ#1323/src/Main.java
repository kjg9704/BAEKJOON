import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		StringBuilder sb = new StringBuilder(String.valueOf(N));
		
		//N을 K로 나눈 나머지에 N을 이어붙이고 K로 나누는것은 N을 이어붙여서 나머지값을 구하는것과 같다
		for(int i = 1; i <= K; i++) {
			long mod = Long.parseLong(sb.toString()) % K;
			if(mod == 0) {
				System.out.println(i);
				return;
			}else {
				sb = new StringBuilder(String.valueOf(mod) + String.valueOf(N));
			}
		}
		//K번 돌려서 나누어 떨어지지 않으면 무조건 -1
		System.out.println(-1);
	}

}