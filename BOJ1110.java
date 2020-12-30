import java.util.Scanner;
public class BOJ1110 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int cnt = 0;
		String var = num + "";
		if(num < 10) {
			var = "0" + var;
		}
		int result = 0;
		while(result != num || var != num + "") {
			if(result ==0) {
				result = var.charAt(0) - '0' + var.charAt(1) - '0';
				cnt++;
			}
			String s = result + "";
			var = (var.charAt(1)+ "") + (s.charAt(s.length() - 1) + "");
			result = (var.charAt(0) - '0' + var.charAt(1) - '0');
			if(Integer.parseInt(var) == num) {
				break;
			}
			cnt++;
		}
		System.out.println(cnt);
	}

}
