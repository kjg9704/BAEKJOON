import java.util.Scanner;

public class BOJ10974 {
	static int num;
	static void solution(int[] arr, int[] result, boolean[] visited, int count) {
		if(num > count) {
			for(int i = 0; i < num; i++) {
				if(!visited[i]) {
					result[count] = arr[i];
					visited[i] = true;
					solution(arr, result, visited, count + 1);
					visited[i] = false;
				}
			}
		}
		else {
			for(int i : result) {
				System.out.print(i + " ");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		num = sc.nextInt();
		sc.close();
		boolean [] visited = new boolean[num];
		int[] result = new int[num];
		int [] arr = new int[num];
		for(int i = 0; i < num; i++) {
			arr[i] = i + 1;
		}
		solution(arr, result, visited, 0);



	}

}
