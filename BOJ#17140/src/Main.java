import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Main {

	static int[][] matrix;
	static int r;
	static int c;
	static int k;
	static int result;
	static int maxRow;
	static int maxColumn;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		r = Integer.parseInt(temp[0]);
		c = Integer.parseInt(temp[1]);
		k = Integer.parseInt(temp[2]);
		matrix = new int[101][101];
		maxRow = maxColumn = 3;
		for(int i = 1; i <= 3; i++) {
			temp = br.readLine().split(" ");
			for(int j = 1; j <= 3; j++) {
				matrix[i][j] = Integer.parseInt(temp[j - 1]);
			}
		}
		br.close();
		result = 0;
		while(result < 101) {
			if(matrix[r][c] == k) {
				System.out.println(result);
				return;
			}
			sort();
		}
		System.out.println(-1);
		return;
	}
	
	static void sort() {
		if(maxRow <= maxColumn) {
			int newRow = 0;
			for(int i = 1; i <= maxColumn; i++) {
				HashMap<Integer, Integer> map = new HashMap<>();
				for(int j = 1; j <= maxRow; j++) {
					if(matrix[i][j] > 0) {
						if(map.containsKey(matrix[i][j])) {
							map.put(matrix[i][j], map.get(matrix[i][j]) + 1);
						}else {
							map.put(matrix[i][j], 1);
						}
						matrix[i][j] = 0;
					}
				}
				List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(map.entrySet());
				Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {
					public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2) {
						if(obj1.getValue() > obj2.getValue()) {
							return 1;
						}else if(obj1.getValue() < obj2.getValue()) {
							return -1;
						} else {
							if(obj1.getKey() > obj2.getKey()) {
								return 1;
							}else {
								return -1;
							}
						}
					}
				});
				for(int j = 1; j <= list.size() * 2; j = j + 2) {
					matrix[i][j] = list.get(j / 2).getKey();
					matrix[i][j + 1] = list.get(j / 2).getValue();
				}
				newRow = Math.max(newRow, list.size() * 2);
			}
			maxRow = newRow;
		}else {
			int newColumn = 0;
			for(int i = 1; i <= maxRow; i++) {
				HashMap<Integer, Integer> map = new HashMap<>();
				for(int j = 1; j <= maxColumn; j++) {
					if(matrix[j][i] > 0) {
						if(map.containsKey(matrix[j][i])) {
							map.put(matrix[j][i], map.get(matrix[j][i]) + 1);
						}else {
							map.put(matrix[j][i], 1);
						}
						matrix[j][i] = 0;
					}
				}
				List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(map.entrySet());
				Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {
					public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2) {
						if(obj1.getValue() > obj2.getValue()) {
							return 1;
						}else if(obj1.getValue() < obj2.getValue()) {
							return -1;
						} else {
							if(obj1.getKey() > obj2.getKey()) {
								return 1;
							}else {
								return -1;
							}
						}
					}
				});
				for(int j = 1; j <= list.size() * 2; j = j + 2) {
					matrix[j][i] = list.get(j / 2).getKey();
					matrix[j + 1][i] = list.get(j / 2).getValue();
				}
				newColumn = Math.max(newColumn, list.size() * 2);
			}
			maxColumn = newColumn;
		}
		result++;
	}

}
