import java.util.Scanner;
public class BOJ1931 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[] start = new int[num];
		int[] end = new int[num];
		int result = 0;
		int con = 0; 
		int min;
		boolean flag = false;
		for(int i = 0; i < num; i++) {
			start[i] = sc.nextInt();
			end[i] = sc.nextInt();
		}
		for(int i =0; i < num; i++) {
			min = end[i];
			for(int j = 0; j < num; j++) {
				if(con <= start[j] && end[j] <= min) {
					min = end[j];
					flag = true;
				}
				}
			if(flag) {
			con = min;
			result++;
			flag = false;
			}
		}
		System.out.println(result);

	}

}
/*if(con == 0) {
	con = end[i];
	result++;
}
else if(con > end[i]) {
	con = end[i];
}
else if(start[i] >= con) {
	result++;
	con = end[i];
}*/