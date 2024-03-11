package boj_2310;

import java.io.*;
import java.util.*;

public class Main {

	static class Room{
		int type, money;
		ArrayList<Integer> doors;
		
		public Room(int type, int money) {
			this.type = type;
			this.money = money;
			doors = new ArrayList<Integer>();
		}
	}
	
	static class Status{
		int room_num, money;
		public Status(int room_num, int money) {
			this.room_num = room_num;
			this.money = money;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			
			if(N == 0) break;
			
			Room[] rooms = new Room[N + 1];
			
			for(int i = 1; i <= N; i++) {
				String[] input = br.readLine().split(" ");
				
				Room room = new Room(0, 0);
				if(input[0].charAt(0) == 'L') {
					room.type = 1;
					room.money = Integer.parseInt(input[1]);
				}else if(input[0].charAt(0) == 'T') {
					room.type = 2;
					room.money = Integer.parseInt(input[1]);
				}
				for(int j = 2; j < input.length - 1; j++) {
					room.doors.add(Integer.parseInt(input[j]));
				}
				rooms[i] = room;
			}
			
			Queue<Status> que = new LinkedList<>();
			
			int[] visited = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				visited[i] = -1;
			}
			boolean flag = false;
			que.add(new Status(1, 0));
			while(!que.isEmpty()) {
				Status now = que.poll();
				
				for(int next : rooms[now.room_num].doors) {
					
					int next_money = now.money;
					if(rooms[next].type == 2) {
						if(now.money < rooms[next].money) {
							continue;
						}else {
							next_money -= rooms[next].money;
						}
					}else if(rooms[next].type == 1) {
						if(now.money < rooms[next].money) {
							next_money = rooms[next].money;
						}
					}
					
					if(next == N) {
						flag = true;
						break;
					}
					
					if(visited[next] >= next_money) {
						continue;
					}
					visited[next] = next_money;
					que.add(new Status(next, next_money));
				}
			}
			
			if(flag) {
				System.out.println("Yes");
			}else {
				System.out.println("No");
			}
		}
		
	}

}
