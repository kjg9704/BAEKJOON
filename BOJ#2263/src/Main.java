import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author KIM
 * @BOJ#2263
 * @트리의 순회
 *
 */
public class Main {

	static int N;
	static int[] inOrder;
	static int[] postOrder;
	static int[] preOrder;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] temp;
		inOrder = new int[N];
		postOrder = new int[N];
		preOrder = new int[N];
		temp = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			inOrder[i] = Integer.parseInt(temp[i]);
		}
		temp = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			postOrder[i] = Integer.parseInt(temp[i]);
		}
		int root = postOrder[N - 1];
		int rootIndex = 0;
		for(int i = 0; i < N; i++) {
			if(inOrder[i] == root) rootIndex = i;
		}
		preOrder(0, N - 1, 0, N - 1);

	}

	static void preOrder(int inStart, int inEnd, int poStart, int poEnd) {
		if(inStart > inEnd || poStart > poEnd) return;
		int root = postOrder[poEnd];
		System.out.print(root + " ");
		int rootIndex = 0;
		for(int i = 0; i < N; i++) {
			if(inOrder[i] == root) {
				rootIndex = i;
				break;
			}
		}
		preOrder(inStart, rootIndex - 1, poStart, rootIndex - inStart + poStart - 1);
		preOrder(rootIndex + 1, inEnd, rootIndex - inStart + poStart, poEnd - 1);
	}

}