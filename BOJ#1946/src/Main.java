import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Score{
	int first;
	int second;
	public Score(int first, int second) {
		this.first = first;
		this.second = second;
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			Score [] scoreTable = new Score[N];
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				scoreTable[j] = new Score(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(scoreTable, new Comparator<Score>() {
				@Override
				public int compare(Score o1,Score o2) {
					if(o1.first > o2.first) {
						return 1;
					}
					else {
						return -1;
					}
				}
			});
			int count = 1;
			int high = scoreTable[0].second;
			for(int j = 1; j < N; j++) {
				if(scoreTable[j].second < high) {
					count++;
					high = scoreTable[j].second;
				}
			}
			System.out.println(count);
		} 
		br.close();
	}

}
