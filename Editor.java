import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
public class Editor {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<String> list = new LinkedList<String>();
		StringBuilder result = new StringBuilder();
		String str = bf.readLine();
		result.append(str);
		ListIterator<String> iter = list.listIterator();
		for(int i = 0; i <str.length(); i++) {
			list.add(String.valueOf(str.charAt(i)));
		}
		int cursor = str.length() ;
		int num = Integer.parseInt(bf.readLine());
		for(int j = 0; j < num; j++) {
			String input = bf.readLine();
			switch(String.valueOf(input.charAt(0))) {
			case "L":
				if(cursor != 0) {
				cursor = cursor - 1;
				}
				break;
			case "D":
				if(cursor != list.size()) {
				cursor = cursor + 1;
				}
				break;
			case "B":
				if(cursor != 0) {
				result.deleteCharAt(cursor - 1);
				list.remove(cursor - 1);
				cursor = cursor - 1;
				}
				break;
			case "P":
				result.insert(cursor, String.valueOf(input.charAt(2)));
				list.add(cursor , String.valueOf(input.charAt(2)));
				cursor = cursor + 1;
				break;
			}
			
		}
/*		while(iter.hasNext()) {
			System.out.print(iter.next());
		}  */
			System.out.println(result);
}

}
