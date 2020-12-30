import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ4256 {
	static List<Integer> preArr;
	static List<Integer> inArr;
	
		static void postOrder(List preArr, List inArr) {
			int mid = 0;
			int root = (int)preArr.get(0);
			int count = preArr.size();
			for(int i = 0; i < inArr.size(); i++) {
				if(preArr.equals(inArr)) {
					for(int j = preArr.size(); j > 0; j--) {
						System.out.print(preArr.get(j - 1) + " ");
					}
					return;
				}
				if((int)inArr.get(i) == root && i == 0) {
					System.out.print(root + " ");
					return;
				}
				if((int)inArr.get(i) == root) {
					mid = i;
					break;
				}
			}
			int r = count - mid - 1;
			postOrder(preArr.subList(1, 1 + mid), inArr.subList(0, mid));
			if(r > 0) {
				postOrder(preArr.subList(mid + 1, mid + 1 + r), inArr.subList(mid + 1, mid + 1 + r));
			}
			System.out.print(root + " ");
		}
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		for(int i = 0; i < num; i++) {
			int items = sc.nextInt();
			preArr = new ArrayList<Integer>();
			inArr = new ArrayList<Integer>();
			for(int j = 0; j < items; j++) {
				preArr.add(sc.nextInt());
			}
			for(int j = 0; j < items; j++) {
				inArr.add(sc.nextInt());
			}
			postOrder(preArr, inArr);
			System.out.println("");
		}
		sc.close();
	}

}
