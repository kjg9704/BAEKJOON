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
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] temp;
		inOrder = new int[N];
		postOrder = new int[N];
		preOrder = new int[N];
		visited = new boolean[N + 1];
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
		preOrder(root, rootIndex, N - 1);
		
		
	}
	
	static void preOrder(int root, int rootIndex, int postRootIndex) {
//		visited[root] = true;
		if(rootIndex == 0) {
			System.out.print(root + " ");
		}else {
			int leftRoot = postOrder[rootIndex - 1];
			int leftRootIndex = 0;
			for(int i = 0; i < rootIndex; i++) {
				if(inOrder[i] == leftRoot) {
					leftRootIndex = i;
					break;
				}
			}
			int rightRoot = postOrder[postRootIndex - 1];
			int rightRootIndex = 0;
			for(int i = rootIndex + 1; i < N; i++) {
				if(inOrder[i] == rightRoot) {
					rightRootIndex = i;
					break;
				}
			}
			System.out.print(root + " ");
				if(rootIndex > postRootIndex) {
					preOrder(leftRoot, rightRoot, postRootIndex - 1);
				}else {
					preOrder(leftRoot, leftRootIndex, rootIndex - 1);
				}
				preOrder(rightRoot, rightRootIndex, postRootIndex - 1);
		}
	}

}