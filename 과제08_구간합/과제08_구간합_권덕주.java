import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 과제08_구간합 {
	static int T;
	static long S[] = {0, 1, 3, 6, 10, 15, 21, 28, 36, 45};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			long A, B;
			String line[] = br.readLine().split(" ");
			A = Long.parseLong(line[0]) - 1;
			B = Long.parseLong(line[1]);
			System.out.println("#" + testCase + " " + (sum(B) - sum(A)));
		}
		
	}
	
	static long sum(long num) {
		long res = 0;
		
	
		res += num / 10 * 45;
		if ((int)(num % 10) >= 0) {
			res += S[(int)(num % 10)];
		}
		
		
		long div = 10;
		long cof = 45;
			
		while(num / div > 0) {
			long tmp = num / div;
			long a = tmp % 10;
			long b=  num % div + 1;
			long c = tmp - a;
			long d = a -1;
			res += a * b;
			res += c * cof;
			if ((int)d >= 0) {
				res += div * S[(int)d];
			}
			
			div *= 10;
			cof *= 10;
		}
		return res;
		
	}

}

