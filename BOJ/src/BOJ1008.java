import java.util.Scanner;

public class BOJ1008 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		for(int i = 0; i < num; i++) {
			String info = sc.nextLine();
			String arr[] = info.split(" ");
			int sum = 0;
			double avg = 0;
			int count = 0;
			for(int j = 1; j < arr.length; j++) {
				sum += Integer.parseInt(arr[j]);
			}
			avg = (double)sum / Integer.parseInt(arr[0]);
			for(int k = 1; k < arr.length; k++) {
				if(avg < Integer.parseInt(arr[k])) {
					count++;
				}
			}
			double result = (double)count / Double.parseDouble(arr[0]) * 100;
			System.out.printf("%.3f%s%n", result, "%" );
		}

		sc.close();
	}
}
