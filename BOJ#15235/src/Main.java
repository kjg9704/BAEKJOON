import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class People{
	int index;
	int pizza;
	public People(int index, int pizza) {
		this.index = index;
		this.pizza = pizza;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<People> que = new LinkedList<>();
		String[] temp = br.readLine().split(" ");
		int[] clear = new int[N];
		int second = 0;
		
		for(int i = 0; i < N; i++) {
			que.add(new People(i, Integer.parseInt(temp[i])));
		}
		
		while(!que.isEmpty()) {
			second++;
			People p = que.poll();
			if(p.pizza > 1) {
				p.pizza--;
				que.add(p);
			}else {
				clear[p.index] = second;
			}
			
		}
		
		for(int i : clear) {
			System.out.print(i + " ");
		}
		
	}

}
