import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Cotes03 {
	static StringBuilder sb;
	static List<Character> list1;
	static List<Character> list2;
	
	static void preOrder(List list1, List list2) {
		if(list1.size() == 0) {
			return;
		}
		char root = (char) list2.get(list2.size() - 1);
		int mid = list1.indexOf(root);
		int l = list1.size();
		int r = list2.size() - 1;
		if(list1.equals(list2)) {
			for(int j = list1.size(); j > 0; j--) {
				sb.append(list1.get(j - 1));
			}
			return;
		}
		sb.append(root);
		preOrder(list1.subList(0, mid), list2.subList(0, mid));
		preOrder(list1.subList(mid + 1, l), list2.subList(mid, r));
	}
	static void postOrder(List list1, List list2) {
		int mid = 0;
		char root = (char) list1.get(0);
		int count = list1.size();
		for(int i = 0; i < list2.size(); i++) {
			if((char)list2.get(i) == root && i == 0) {
				sb.append(root);
				return;
			}
			if(list1.equals(list2)) {
				for(int j = list1.size(); j > 0; j--) {
					sb.append(list1.get(i - 1));
				}
				return;
			}
			if((char)list2.get(i) == root){
				mid = i;
				break;
			}
		}
		int r = count - mid - 1;
		postOrder(list1.subList(1, 1 + mid), list2.subList(0, mid));
		if(r > 0) {
			postOrder(list1.subList(mid + 1, mid + 1 + r), list2.subList(mid + 1, mid + 1 + r));
		}
		sb.append(root);

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String order1 = sc.next();
		String data1 = sc.next();
		String order2 = sc.next();
		String data2 = sc.next();
		sc.close();
		sb = new StringBuilder();
		list1 = new LinkedList<Character>();
		list2 = new LinkedList<Character>();
		for(int i = 0; i < data1.length(); i++) {
			list1.add(data1.charAt(i));
		}
		for(int i = 0; i < data2.length(); i++) {
			list2.add(data2.charAt(i));
		}
		if(order1.equals("Inorder")){
			if(order2.equals("Preorder")) {
				postOrder(list2, list1);
				System.out.print("Postorder ");
				System.out.println(sb);
			}
			else {
				preOrder(list1, list2);
				System.out.print("Preorder ");
				System.out.println(sb);
			}
		}
		else if(order1.equals("Preorder")) {
			postOrder(list1, list2);
			System.out.print("Postorder ");
			System.out.println(sb);
		}
		else {
			preOrder(list2, list1);
			System.out.print("Preorder ");
			System.out.println(sb);
		}
		
		

	}

}
