
public class 실습03_막대색칠하기 {

	static int N = 6;
	
	static int dp[];
	
	public static void main(String[] args) {
		
		dp = new int[N + 5];
		dp[0] = 1;
		dp[1] = 2;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] * 2 + dp[i - 2];
		}
		
		System.out.print("f(" + N + ") = ");
		System.out.println(dp[N]);  // f(6) = 169		
	}

}
