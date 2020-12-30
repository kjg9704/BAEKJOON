import java.util.Scanner;

public class BOJ2747 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		long[] fibo = new long[100];
		fibo[0] = 0L;
		fibo[1] = 1L;
		fibo[2] = 1L;
		for(int i = 3; i <= num; i++) {
			fibo[i] = fibo[i - 2] + fibo[i - 1];
		}
		System.out.println(fibo[num]);
	}

}
