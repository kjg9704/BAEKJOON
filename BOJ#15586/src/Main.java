import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


class Edge implements Comparable<Edge> {
	int start;
	int end;
	int usado;
	public Edge(int start, int end, int usado) {
		this.start = start;
		this.end = end;
		this.usado = usado;
	}
	@Override
	public int compareTo(Edge o) {
		return o.usado - this.usado;
	}
}

class Query implements Comparable<Query>{
	int index;
	int usado;
	int num;
	public Query(int index, int usado, int num) {
		this.index = index;
		this.usado = usado;
		this.num = num;
	}
	@Override
	public int compareTo(Query o) {
		return o.usado - this.usado;
	}
}
public class Main {

	static int[] parents;
	static Edge[] edgeList;
	static Query[] queryList;
	static int[] result;
	static int[] count;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int Q = Integer.parseInt(temp[1]);
		parents = new int[N + 1];
		queryList = new Query[Q];
		edgeList = new Edge[N - 1];
		result = new int[Q];
		count = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
			count[i] = 1;
		}
		for(int i = 0; i < N - 1; i++) {
			temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			int usado = Integer.parseInt(temp[2]);
			edgeList[i] = new Edge(start, end, usado);
		}
		Arrays.sort(edgeList);
		for(int i = 0; i < Q; i++) {
			temp = br.readLine().split(" ");
			int K = Integer.parseInt(temp[0]);
			int V = Integer.parseInt(temp[1]);
			queryList[i] = new Query(i, K, V);
		}
		Arrays.sort(queryList);
		int index = 0;
		for(int i = 0; i < queryList.length; i++) {
			Query query = queryList[i];
			while(index < edgeList.length && edgeList[index].usado >= query.usado) {
				union(edgeList[index].start, edgeList[index].end);
				index++;
			}
			result[query.index] = count[find(query.num)] - 1;
		}
		for(int i : result) {
			bw.write(i + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	static int find(int a){
		if(parents[a] != a) parents[a] = find(parents[a]);
		return parents[a];
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a < b) {
			parents[a] = b;
			count[b] += count[a];
		}else {
			parents[b] = a;
			count[a] += count[b];
		}
	}
}
