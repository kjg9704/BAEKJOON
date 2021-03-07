import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		int count = 1;
		if(N == 0) {
			System.out.println(count);
			return;
		}
		int[] numbers = new int[10];
		Arrays.fill(numbers, 1);
		while(N > 0) {
			int number = N % 10;
			N = N / 10;
			if(numbers[number] < 1) {
				if(number == 6) {
					if(numbers[9] != 1) {
						count++;
						buy(numbers);
						numbers[number]--;
					}else {
						numbers[9]--;
					}
				}
				else if(number == 9) {
					if(numbers[6] != 1) {
						count++;
						buy(numbers);
						numbers[number]--;
					}else {
						numbers[6]--;
					}
				}else {
					count++;
					buy(numbers);
					numbers[number]--;
				}
			}else {
				numbers[number]--;
			}
		}
		System.out.println(count);
	}
	static void buy(int[] numbers) {
		for(int i = 0; i < numbers.length; i++) {
			numbers[i]++;
		}
	}

}
