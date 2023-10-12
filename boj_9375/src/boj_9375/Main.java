package boj_9375;

import java.util. *;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = Integer.parseInt(sc.nextLine());
		
		for(int i = 0; i < testCase; i++) {
			int N = Integer.parseInt(sc.nextLine());
			HashMap<String, Integer> map = new HashMap<>();
			int cnt = N;
			String[] clothes = new String[N];
			String[] category = new String[N];
			int result = 1;
			for(int j = 0; j < N; j++) {
				String[] temp = sc.nextLine().split(" ");
				
				if(map.containsKey(temp[1])) {
					map.put(temp[1], map.get(temp[1]) + 1);
				}else {
					map.put(temp[1], 1);
				}
			}
			
			Collection<Integer> list = map.values();
			for(int num:list) {
				result *= (num + 1);
			}
			
			result --;

			System.out.println(result);
		}
	}
	
	static void comb(String[] arr, boolean[] visited, int depth, int r) {
		
	}

}
