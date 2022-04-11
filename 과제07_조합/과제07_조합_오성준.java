import java.util.Scanner;

public class Solution {

	static final long d = 1234567891;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			long N = sc.nextLong();
			long K = sc.nextLong();

			long top = fact(N) % d;
			long bottom = divSquare((fact(K) % d) * (fact(N - K) % d) % d, d - 2);
			System.out.println("#" + tc + " " + top * bottom % d);
		}
	}

	public static long divSquare(long X, long B) {
		if (B == 1) {
			return X;
		}

		long tmp = divSquare(X, B / 2) % d;
		long ret = tmp * tmp % d;
		if (B % 2 == 0) {
			return ret;
		} else {
			return ret * X % d;
		}
	}

	public static long fact(long x) {
		long ret = 1;
		for (long i = 1; i <= x; i++) {
			ret *= i % d;
			ret %= d;
		}
		return ret;
	}

}

