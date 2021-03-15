import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Pair{
	int start;
	int end;
	public Pair(int start, int end) {
		this.start = start;
		this.end = end;
	}
}
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Pair> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			list.add(new Pair(start, end));
		}
		br.close();
		Collections.sort(list, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.start < o2.start) {
					return -1;
				} else if (o1.start > o2.start) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		int result = 0;
		for(Pair pair : list) {
			if(result >= pair.start) {
				result += pair.end;
			}else {
				result = pair.start + pair.end;
			}
		}
		System.out.println(result);
	}

}