import java.util.Stack;
class Solution {
	    public static int solution(int[][] board, int[] moves) {
	    	Stack<Integer> stack = new Stack();
	        int answer = 0;
	        for(int i = 0; i < moves.length; i++) {
	        	for(int j = 0; j < board.length; j++) {
	        		int var = board[j][moves[i] - 1];
	        		if(var != 0) {
	        			if(stack.isEmpty()) {
	        				stack.push(var);	
	        			}
	        			else {
	        				if(stack.peek() == var) {
	        					stack.pop();
	        					answer = answer + 2;
	        				}
	        				else {
	        					stack.push(var);
	        				}
	        			}
	        			board[j][moves[i] - 1] = 0;
		        		break;
	        		}
	        		
	        	}
	        }
	        return answer;
	    }
	public static void main(String[] args) {
		int [][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int [] moves = {1,5,3,5,1,2,1,4};
		System.out.println(solution(board, moves));;

	}

}
