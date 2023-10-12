
public class Main {

	static boolean[] prime;
	public static void main(String[] args) {
		
		int N = 324893;
//		isPrime(11);
		prime = new boolean[N + 1];
		isPrime(N);
		for(int i = 2; i <= N; i++) {
			if(!prime[i]) {
				System.out.println(i + " 은(는) 소수입니다");
			}
		}
	}
	
	static void isPrime(int num) {
		prime[0] = true;
		prime[1] = true;
		
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(prime[i]) {
				continue;
			}
			
			for(int j = i * i; j <= prime.length - 1; j = j + i) {
				prime[j] = true;
			}
		}
		
		return;
		
	}

}