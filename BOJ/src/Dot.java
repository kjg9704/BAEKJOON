import java.util.Scanner;

public class Dot {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		sc.nextLine();
		String [] sen = new String[number];
		for(int i = 0; i <number;i++) {
			sen[i] = sc.nextLine();
		}
		for(int j = 0; j<number; j++) {
			String [] word = sen[j].split(" ");
				for(int k = 0; k < word.length; k++) {
					for(int a = word[k].length()-1; a>=0;a--) {
						System.out.print(word[k].charAt(a));
						}
					System.out.print(" ");
		}
				System.out.println();
	}
	}
}
