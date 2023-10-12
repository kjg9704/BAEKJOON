package boj_2910;

import java.util.*;

public class Main {

	public static class Info{
		int num;
		int cnt;
		int order;
		public Info(int num, int cnt, int order) {
			this.num = num;
			this.cnt = cnt;
			this.order = order;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer, Info> map = new HashMap<>();
		
		String[] temp = sc.nextLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int C = Integer.parseInt(temp[1]);
		
		for(int i = 0; i < N; i++) {
			int num = sc.nextInt();
			
			if(map.containsKey(num)) {
				Info tmp = new Info(num, map.get(num).cnt + 1, map.get(num).order);
				map.put(num, tmp);
				
			}else {
				map.put(num, new Info(num, 1, i));
			}	
		}
		
		ArrayList<Info> list = new ArrayList<>(map.values());
		

		Collections.sort(list, new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				if (o1.cnt == o2.cnt) {
		        	//만약, 등장 횟수가 같다면 등장 횟수가 빠른 것을 앞으로
				return Integer.compare(o1.order, o2.order);
			} else {
	        		//만약, 등장 횟수가 많은 순서대로
				return -Integer.compare(o1.cnt, o2.cnt);
			}
			}
			
		});
		for(Info now:list) {
			for(int i = 0; i < now.cnt; i++) {
				System.out.print(now.num + " ");
			}
		}
		
		
	}

}
