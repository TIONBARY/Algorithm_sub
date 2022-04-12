import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int X = 1234567891;
	static int N, R;
	static long factorial[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[];
		int T = Integer.parseInt(br.readLine());
		factorial = new long[1000001];
		factorial[0] = 1;
		for(int i=1; i<=1000000; i++) {
			factorial[i] = factorial[i-1] * i % X;
		}
		
		for(int tc=1; tc<=T; tc++) {
			s = br.readLine().split(" ");
			N = Integer.parseInt(s[0]);
			R = Integer.parseInt(s[1]);
			
			long top = factorial[N] % X;
			long bottom = (factorial[N-R] % X) * (factorial[R] % X) % X;
			
			long toTop = calc(bottom, X-2);
			
			System.out.println("#"+tc+" "+(top * toTop) % X);
		}
		
		br.close();
	}

	private static long calc(long bottom, int i) {
		if(i == 0) return 1;
		
		long half = calc(bottom, i/2);
		
		if(i%2 == 0) return (half % X) * (half % X) % X;
		else return ((half * bottom) % X) * (half % X) % X;
	}
}
