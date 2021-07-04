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
	public int compareTo(Jewelry o) {// ������ ���Դ� ������, ���԰� ���ٸ� ��Ѽ����� �����ϱ� ����
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
		Collections.sort(arr); // ������ ���Դ� ������, ���԰� ���ٸ� ��Ѽ����� ����
		for(int i = 0; i < K; i++) {
			int C = Integer.parseInt(br.readLine());
			bag[i] = C;
		}
		Arrays.sort(bag); // ���� �ִ빫�԰� ���������� ���� 
		long result = 0;//int ���� �ʰ��� ���� ����!
		
		int index = 0;
		PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0; i < K; i++) {
			for(int j = index; j < arr.size(); j++) { // �켱����ť�� ���簡�濡 �������ִ¾ֵ��� �ٳ��� 
				if(arr.get(j).weight <= bag[i]) { 
					que.add(arr.get(j).price);
					index++; // �ѹ������ֵ� �Ǿȳְ��ҷ���
				}else {
					break;
				}
			}
			if(!que.isEmpty()) { // ť�� �ֻ���� ������ �׸����� �κ��ذ���(�ֻ�� ���� ������� ����)
				result += que.poll();
			}
		}
		System.out.println(result);
	}
}