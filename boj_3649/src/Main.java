import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input;
        while ((input = br.readLine()) != null) {
        	int x = Integer.parseInt(input);
    		int N = Integer.parseInt(br.readLine());
    		
    		int[] arr = new int[N];
    		for(int i = 0; i < N; i++) {
    			arr[i] = Integer.parseInt(br.readLine());
    		}
    		
    		Arrays.sort(arr);
    		
    		int left = 0;
    		int right = N - 1;
    		
    		x = x * 10000000;
    		int t1 = 0;
    		int t2 = 0;
    		
    		while(left < right) {
    			if(arr[left] + arr[right] == x) {
    				if(Math.abs(t1 - t2) <= Math.abs(arr[left] - arr[right])) {
    					t1 = arr[left];
    					t2 = arr[right];
    				}
					left++;

    			}else if(arr[left] + arr[right] < x) {
    				left++;
    			}else {
    				right--;
    			}
    		}
    		
    		if(t1 != 0 && t2 != 0) {
    			System.out.println("yes " + t1 + " " + t2);
    		}else {
    			System.out.println("danger");
    		}
    		
        }
		
	}

}
