import java.util.Scanner;

public class BOJ2839 {
	static int arr[];
	static int dp(int num) {
		if(num == 4 || num == 7) {
			return 999;
		}
		if(num == 3) {
			arr[num] = 1;
			return 1;
		}
		if(num % 5 == 0) {
			arr[num] = num / 5;
			return arr[num];
		}
		if(arr[num] > 0) {
			return arr[num];
		}
		arr[num] = Math.min(dp(num - 3), dp(num - 5)) + 1;
		return arr[num];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		arr = new int[num + 1];
		arr[1] = 100;
		arr[2] = 100;
		if(num == 4 || num == 7) {
			System.out.println(-1);
		}
		else {
			System.out.println(dp(num));
		}
	/*	int cnt = 0;	
		while(num > 0) {
			if(num % 5 == 0) {
				cnt = num / 5;
				break;
			}
			cnt++;
		}
		System.out.println(cnt);*/
		sc.close();

	}
}
