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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // �ð����Ѷ����� ���۷� ���
		PriorityQueue<Integer> smallQue = new PriorityQueue<>(Collections.reverseOrder()); // ���ذ�(mid)���� ���������� �ִ���
		PriorityQueue<Integer> bigQue = new PriorityQueue<>(); // ���ذ� ���� ū ������ �ּ���
		int N = Integer.parseInt(br.readLine());
		int mid = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(smallQue.isEmpty()) { // �ִ����� ��������(ù��°�Է�)
				smallQue.add(num);
				bw.write(num +"\n");
				continue;
			}else if(bigQue.isEmpty()) { // �ּ����� ��������(�ι�° �Է�)
				if(smallQue.peek() <= num) {
					bigQue.add(num);
				}else {
					bigQue.add(smallQue.poll());
					smallQue.add(num);
				}
				bw.write(smallQue.peek() +"\n");
				continue;
			}else if(mid > 10001){ // ���ذ��� �������� �ʾ������(����° �Է�)
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
			}else { // �׹�° �Էº��ʹ� �Է°������� ����������, ū�����տ� ����, mid��ü�� ������
				if(num >= mid) { //�Է°��� �߰������� ũ�� �ּ����� ����
					bigQue.add(num);
				}else if(num < mid) { // �߰������� ������ �ִ���
					smallQue.add(num);
				}
				
				if(Math.abs(smallQue.size() - bigQue.size()) >= 2) { // �ִ����� �ּ����� ũ�����̰� 2�̻��ΰ�� mid���� ��ü�������
					if(smallQue.size() > bigQue.size()) {
						bigQue.add(mid);
						mid = smallQue.poll();
					}else {
						smallQue.add(mid);
						mid = bigQue.poll();
					}
				}
				
				if((smallQue.size() + bigQue.size() + 1) % 2 == 0) { // ������� �Է°��� ���� ¦���ΰ�쿡�� mid���� �������� ����ū���� ���������.(�߰����� �ΰ�)
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