
public class Map {
/*	public static String[] solution(int n, int[] arr1, int[] arr2) {
	      String[] answer = {};
	      for(int i = 0; i < n; i++) {
	    	  String binary1 = Integer.toBinaryString(arr1[i]);
	    	  String binary2 = Integer.toBinaryString(arr2[i]);
	    	  for(int j = 0; j<n;j++) {
	    		  if(binary1.indexOf(j) == '1' || binary2.indexOf(j) == '1') {
	    			  answer[i] = answer[i] + "#";
	    		  }
	    		  else {
	    			  answer[i] = answer[i] + " ";
	    		  }
	    	  }
	      }
	      return answer;
	  }
*/
	public static void main(String[] args) {
		int i = 10;
		StringBuilder sb = new StringBuilder();
		while(i !=0) {
			sb.append(i%2);
			i = i / 2;
		}
		while(sb.length()!=5) {
			sb.append(0);
		}
		System.out.println(sb.reverse());
	}

}
