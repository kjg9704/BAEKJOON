import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		HashMap<Integer, Integer> map = new HashMap<>();
		int count = 0;
		for(int i = 0; i < N; i++) {
			int cow = sc.nextInt();
			int place = sc.nextInt();
			if(map.containsKey(cow)) {
				if(map.get(cow) != place) {
					count++;
					map.put(cow, place);
				}
			}else {
				map.put(cow, place);
			}
		}
		sc.close();
		System.out.println(count);
	}
}
