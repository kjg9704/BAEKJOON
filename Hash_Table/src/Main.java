
public class Main {

	static final int HASH_SIZE = 1000;
	static final int HASH_LEN = 400;
	static final int HASH_VAL = 17; // 소수로 할 것
	
	static class HashMap{
		int[][] values = new int[HASH_SIZE][HASH_LEN];
		int[] length = new int[HASH_SIZE];
		String[][] keys = new String[HASH_SIZE][HASH_LEN];
		
		public HashMap() {
			this.values = new int[HASH_SIZE][HASH_LEN];
			this.length = new int[HASH_SIZE];
			this.keys = new String[HASH_SIZE][HASH_LEN];
		}
		
		public int getHashKey(String str) {
			int key = 0;
			
			for(int i = 0; i < str.length(); i++) {
				key += key * HASH_VAL + str.charAt(i);
			}
			
			if(key < 0) {
				key = -key;
			}
			
			return key % HASH_SIZE;
		}
		
		public int getPos(String str, int key) {
			int pos = length[key];
			if(pos > 0) {
				for(int i = 0; i < pos; i++) {
					if(keys[key][i].equals(str)) {
						return i;
					}
				}
			}
			return pos;
		}
		public void put(String str, int value) {
			int key = getHashKey(str);
			int pos = getPos(str, key);
			values[key][pos] = value;
			keys[key][pos] = str;
			length[key]++;
		}
		
		public int getValue(String str) {
			int key = getHashKey(str);
			int pos = getPos(str, key);
			if(pos == length[key]) {
				return -1;
			}
			
			return values[key][pos];
		}
		
	}
	public static void main(String[] args) {
		
		HashMap map = new HashMap();
		
		map.put("apple", 10);
		System.out.println(map.getValue("apple"));
		map.put("apple", 20);
		System.out.println(map.getValue("apple"));
		map.put("banana", 10);
		System.out.println(map.getValue("banana"));
		map.put("pineapple", 10);
		System.out.println(map.getValue("pineapple"));

		map.put("sex", 30);
		System.out.println(map.getValue("sex"));

		map.put("zzzzzzz", 10);
		System.out.println(map.getValue("zzzzzzz"));
		System.out.println(map.getValue("xxxxx"));

		
	}

}
