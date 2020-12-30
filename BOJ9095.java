import java.util.Scanner;

public class BOJ9095 {
	static int [] arr;
	static int solution(int n) {
		if(arr[n] > 0) {
			return arr[n];
		}
		return arr[n] = solution(n-1) + solution(n-2) + solution(n-3); 
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		arr = new int[11];
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;
		for(int i = 0; i < num; i++) {
			int n = sc.nextInt();
			System.out.println(solution(n));
		}
		sc.close();

	}

}
