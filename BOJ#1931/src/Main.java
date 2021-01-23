import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Time{
	int start;
	int end;
	public Time(int start, int end) {
		this.start = start;
		this.end = end;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Time [] timeTable = new Time[N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			timeTable[i] = new Time(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(timeTable, new Comparator<Time>() {
			@Override
			public int compare(Time o1,Time o2) {
				if(o1.end == o2.end) {
					return o1.start - o2.start;
				}
				return o1.end - o2.end;
			}
		});
		int max = 1;
		int start = timeTable[0].start;
		int end = timeTable[0].end;
		for(int i = 1; i < N; i++) {
			if(timeTable[i].start >= end) {
				start = timeTable[i].start;
				end = timeTable[i].end;
				max++;
			}
		}
		System.out.println(max);
		br.close();
	}

}
