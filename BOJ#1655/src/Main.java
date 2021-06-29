import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 시간제한때문에 버퍼로 출력
		PriorityQueue<Integer> smallQue = new PriorityQueue<>(Collections.reverseOrder()); // 기준값(mid)보다 작은수들의 최대힙
		PriorityQueue<Integer> bigQue = new PriorityQueue<>(); // 기준값 보다 큰 수들의 최소힙
		int N = Integer.parseInt(br.readLine());
		int mid = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(smallQue.isEmpty()) { // 최대힙이 비었을경우(첫번째입력)
				smallQue.add(num);
				bw.write(num +"\n");
				continue;
			}else if(bigQue.isEmpty()) { // 최소힙만 비었을경우(두번째 입력)
				if(smallQue.peek() <= num) {
					bigQue.add(num);
				}else {
					bigQue.add(smallQue.poll());
					smallQue.add(num);
				}
				bw.write(smallQue.peek() +"\n");
				continue;
			}else if(mid > 10001){ // 기준값이 설정되지 않았을경우(세번째 입력)
				if(num > smallQue.peek() && num < bigQue.peek()) {
					mid = num;
				}else if(num <= smallQue.peek()) {
					mid = smallQue.poll();
					smallQue.add(num);
				}else if(num >= bigQue.peek()) {
					mid = bigQue.poll();
					bigQue.add(num);
				}
				bw.write(mid +"\n");
			}else { // 네번째 입력부터는 입력값에따라 작은수집합, 큰수집합에 삽입, mid교체를 수행함
				if(num >= mid) { //입력값이 중간값보다 크면 최소힙에 넣음
					bigQue.add(num);
				}else if(num < mid) { // 중간값보다 작으면 최대힙
					smallQue.add(num);
				}
				
				if(Math.abs(smallQue.size() - bigQue.size()) >= 2) { // 최대힙과 최소힙의 크기차이가 2이상인경우 mid값을 교체해줘야함
					if(smallQue.size() > bigQue.size()) {
						bigQue.add(mid);
						mid = smallQue.poll();
					}else {
						smallQue.add(mid);
						mid = bigQue.poll();
					}
				}
				
				if((smallQue.size() + bigQue.size() + 1) % 2 == 0) { // 현재까지 입력값의 수가 짝수인경우에는 mid값과 작은수중 가장큰값을 비교해줘야함.(중간값이 두개)
					if(smallQue.size() > bigQue.size()) {
						bw.write(smallQue.peek() +"\n");
					}else {
						bw.write(mid +"\n");
					}
				}else {
					bw.write(mid +"\n");
				}
			}
		}
		bw.flush();
		bw.close();
		
	}

}