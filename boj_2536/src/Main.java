import java.io.*;
import java.util.*;

public class Main {

	static class Bus{
		int startX, startY, endX, endY;
		public Bus(int startX, int startY, int endX, int endY) {
			this.startX = startX;
			this.startY = startY;
			this.endX = endX;
			this.endY = endY;
		}
		
	}
	
	static class Status{
		int bus;
		int cnt;
		public Status(int bus, int cnt) {
			this.bus = bus;
			this.cnt = cnt;
		}
	}
	static int M, N, K;
	static Bus[] bus_arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		bus_arr = new Bus[K + 1];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int bus = Integer.parseInt(st.nextToken());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			bus_arr[bus] = new Bus(startX, startY, endX, endY);
		}
		st = new StringTokenizer(br.readLine());
		br.close();
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		int endX = Integer.parseInt(st.nextToken());
		int endY = Integer.parseInt(st.nextToken());
		
		Queue<Status> que = new LinkedList<>();
		boolean[] visited = new boolean[K + 1];
		
		int cnt = 0;
		for(int i = 1; i <= K; i++) {
			Bus bus = bus_arr[i];
			if(on_line(startX, startY, bus.startX, bus.startY, bus.endX, bus.endY)) {
				que.add(new Status(i, 1));
				visited[i] = true;
			}
		}
		while(!que.isEmpty()) {
			Status now = que.poll();
			Bus now_bus = bus_arr[now.bus];
			if(on_line(endX, endY, now_bus.startX, now_bus.startY, now_bus.endX, now_bus.endY)) {
				cnt = now.cnt;
				que.clear();
				break;
			}
			for(int i = 1; i <= K; i++) {
				if(!visited[i] && is_linked(now_bus, bus_arr[i])) {
					que.add(new Status(i, now.cnt + 1));
					visited[i] = true;
				}
			}
		}
		System.out.println(cnt);
	}
	
	static boolean is_linked(Bus bus1, Bus bus2) {
		int dir1 = getDir(bus1.startX, bus1.startY, bus1.endX, bus1.endY);
		int dir2 = getDir(bus2.startX, bus2.startY, bus2.endX, bus2.endY);
		
		if(dir1 == 0 && dir2 == 0) { // same dir, horizon
			if(bus1.startY != bus2.startY) {
				return false;
			}
			if((Math.min(bus1.startX, bus1.endX) <= Math.min(bus2.startX, bus2.endX) && 
					Math.min(bus2.startX, bus2.endX) <= Math.max(bus1.startX, bus1.endX)) ||
					(Math.min(bus2.startX, bus2.endX) <= Math.min(bus1.startX, bus1.endX) &&
					Math.min(bus1.startX, bus1.endX) <= Math.max(bus2.startX, bus2.endX))) {
				return true;
			}
			
		}else if(dir1 == 1 && dir2 == 1) { // same dir, vertical
			if(bus1.startX != bus2.startX) {
				return false;
			}
			if((Math.min(bus1.startY, bus1.endY) <= Math.min(bus2.startY, bus2.endY) && 
					Math.min(bus2.startY, bus2.endY) <= Math.max(bus1.startY, bus1.endY)) ||
					(Math.min(bus2.startY, bus2.endY) <= Math.min(bus1.startY, bus1.endY) &&
					Math.min(bus1.startY, bus1.endY) <= Math.max(bus2.startY, bus2.endY))) {
				return true;
			}
			
		}else { // diff dir
			if(dir1 == 1) {
				if(Math.min(bus2.startX, bus2.endX) <= bus1.startX && bus1.startX <= Math.max(bus2.startX, bus2.endX) &&
						Math.min(bus1.startY, bus1.endY) <= bus2.startY && bus2.startY <= Math.max(bus1.startY, bus1.endY)) {
					return true;
				}
			}else {
				if(Math.min(bus1.startX, bus1.endX) <= bus2.startX && bus2.startX <= Math.max(bus1.startX, bus1.endX) &&
						Math.min(bus2.startY, bus2.endY) <= bus1.startY && bus1.startY <= Math.max(bus2.startY, bus2.endY)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	static boolean on_line(int x, int y, int startX, int startY, int endX, int endY) {
		int dir = getDir(startX, startY, endX, endY);
		
		if(dir == 0) { // horizon
			if(startY == y && Math.min(startX, endX) <= x && x <= Math.max(startX, endX)) {
				return true;
			}
			
		}else { // vertical
			if(startX == x && Math.min(startY, endY) <= y && y <= Math.max(startY, endY)) {
				return true;
			}
		}
		
		return false;
	}
	
	static int getDir(int startX, int startY, int endX, int endY) {
		if(startY == endY) {
			return 0; // X좌표 다름, 수평
		}else {
			return 1; // Y좌표 다름, 수직
		}
	}

}
