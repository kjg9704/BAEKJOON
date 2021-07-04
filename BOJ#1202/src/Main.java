import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
class Jewelry implements Comparable<Jewelry>{
	int weight;
	int price;
	public Jewelry(int weight, int price) {
		this.weight = weight;
		this.price = price;
	}
	@Override
	public int compareTo(Jewelry o) {// 보석을 무게는 낮은순, 무게가 같다면 비싼순으로 정렬하기 위함
		if(this.weight == o.weight) {
			return o.price - this.price;
		}
		return this.weight - o.weight;
	}
	
}
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		int [] bag = new int[K];
		ArrayList<Jewelry> arr = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			int M = Integer.parseInt(temp[0]);
			int V = Integer.parseInt(temp[1]);
			arr.add(new Jewelry(M, V));
		}
		Collections.sort(arr); // 보석을 무게는 낮은순, 무게가 같다면 비싼순으로 정렬
		for(int i = 0; i < K; i++) {
			int C = Integer.parseInt(br.readLine());
			bag[i] = C;
		}
		Arrays.sort(bag); // 가방 최대무게가 낮은순으로 정렬 
		long result = 0;//int 범위 초과할 수도 있음!
		
		int index = 0;
		PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0; i < K; i++) {
			for(int j = index; j < arr.size(); j++) { // 우선순위큐에 현재가방에 넣을수있는애들은 다넣음 
				if(arr.get(j).weight <= bag[i]) { 
					que.add(arr.get(j).price);
					index++; // 한번넣은애들 또안넣게할려고
				}else {
					break;
				}
			}
			if(!que.isEmpty()) { // 큐의 최상단의 보석이 그리디의 부분해가됨(최상단 값을 결과값에 더함)
				result += que.poll();
			}
		}
		System.out.println(result);
	}
}