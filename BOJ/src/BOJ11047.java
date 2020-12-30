import java.util.Scanner;
public class BOJ11047 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int sum = sc.nextInt();
        int result = 0;
        int [] arr = new int[num];
        for(int i = 0; i < num; i++) {
        	arr[i] = sc.nextInt();
        }
        for(int i = 0; i < num; i++) {
        	if(arr[i] > sum || i == num - 1) {
        		int index = 0;
        		if(arr[i] > sum) {
    			index = i - 1;
        		}
        		else if(i == num - 1) {
        			index = i;
        		}
        		while(sum != 0) {
        			int indexNum = arr[index];
        			if(indexNum <= sum) {
        			sum = sum - indexNum;
            		result++;
        			}
            		if(indexNum > sum) {
            			index--;
            		}
        		}
        		if(sum == 0) {
        			break;
        		}
        		}
        	}
        System.out.println(result);
        sc.close();

	}

}
