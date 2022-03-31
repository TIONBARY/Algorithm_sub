
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 실습04_1로만들기 {

	
	static int N;
	static int dp[];
	
	static int minOfThree(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 5];
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		
		for (int i = 4; i <= N; i++) {
			int a = Integer.MAX_VALUE;
			int b = Integer.MAX_VALUE;
			int c = Integer.MAX_VALUE;
			
			if (i % 3 == 0) {
				a = dp[i / 3] + 1;
			}
			if (i % 2 == 0) {
				b = dp[i / 2] + 1;
			}
			c = dp[i - 1] + 1;
			dp[i] = minOfThree(a, b, c);
		}
		
		System.out.println(dp[N]);
	}

}
