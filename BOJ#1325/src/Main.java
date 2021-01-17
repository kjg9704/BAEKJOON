import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static boolean[]visited;
	static ArrayList<Integer> [] list;
	static int answer;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n + 1];
        int [] result = new int[n + 1];
		int max = -1;
		for(int i = 0 ; i < n + 1 ; i ++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
	        int start = Integer.parseInt(st.nextToken());
	        int end = Integer.parseInt(st.nextToken());
			list[end].add(start);
		}
		for(int i = 1; i <= n; i++) {
			visited = new boolean[n + 1];
			answer = 0;
			dfs(i);
			if(max < result[i]) {
				max = answer;
			}
			result[i] = answer;
		}
		for(int i = 1; i <= n; i++) {
			if(result[i] == max) {
				bw.write(i + " ");
			}
		}
		br.close();
		bw.close();
	}
	static void dfs(int index) {
		visited[index] = true;
		for(int next : list[index]) {
			if(!visited[next]) {
				answer++;
				dfs(next);
			}
		}
	}

}
//이건 검색해서나온 맞은코드인데 별차이없는거같은데 왜 쟤는 시간초과 안남??
/*public class Main {
 
    static int n,m;
    static ArrayList<Integer>[] list;
    static int[] dp;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[n+1];
        dp = new int[n+1];
        
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
        }
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            solve(i);
        }
        
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dp[i]);
        }
        
        StringBuilder sb = new StringBuilder();
 
        for (int i = 1; i <= n; i++) {
            if(dp[i] == max) 
                sb.append(i+" ");
        }
        System.out.println(sb.toString());
    }
    
    static void solve(int cur) {
        visited[cur] = true;
        
        for( int next : list[cur]) {
            if(visited[next])
                continue;
            dp[next]++;
            solve(next);
        }
    }
    
}
 */
