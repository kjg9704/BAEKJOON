import java.util.Scanner;
public class BOJ1463 {
	static int [] array;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
        array = new int[num+1];
		System.out.println(dp(num));
		sc.close();
	}
	static int dp(int num) {
		if(num == 1) {
			return 0;
		}
		if(array[num] > 0) {
			return array[num];
		}
		array[num] = dp(num - 1) + 1;
		if(num%3 == 0) {
			array[num] = Math.min(array[num], dp(num/3) + 1);
		}
		if(num%2 == 0) {
			array[num] = Math.min(array[num], dp(num/2) + 1);
		}
		return array[num];
	}
}

/*
if(num%3 != 0 && num%2 ==1) {
	array[num] = dp(num - 1) + 1;
}
else if(num%3 == 0 && num%2 == 1) {
	array[num] = dp(num/3) + 1;
}
else if(num%3 != 0 && num%2 == 0) {
	array[num] =  Math.min(dp(num / 2), dp(num - 1)) + 1;
}
else if(num%3 == 0 && num % 2 == 0) {
	array[num] = Math.min(dp(num / 3), dp(num / 2)) + 1;
}
else {
	array[num] = dp(num - 1) + 1;
}
*/
