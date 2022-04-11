import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 과제07_조합 {

	static final long m = 1234567891;
	static int T, N, R;
	
	static long fact[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		fact = new long[1000001];
		fact[0] = 1;
		for (int i = 1; i <= 1000000; i++) {
			fact[i] = fact[i - 1] * i % m;
		}
		
		
		for (int testCase = 1; testCase <= T; testCase++) {
			String line[] = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			R = Integer.parseInt(line[1]);
			long res = fact[N] * pow(fact[R] * fact[N - R] % m, m - 2) % m;
			System.out.println("#" + testCase + " " + res);
		}
	}

	
	static long pow(long a, long b) {
		if (b == 0) {
			return 1;
		}
		
		long half = pow(a, b / 2) % m;
		if (b % 2 == 1) {
			return a * half % m * half % m;
		}else {
			return half * half % m;
		}
		
	}
	
}
