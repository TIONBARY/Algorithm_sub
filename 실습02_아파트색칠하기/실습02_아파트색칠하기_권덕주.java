
public class 실습02_아파트색칠하기 {
	
	static int dp[][];
	static int N = 8;
	
	
	public static void main(String[] args) {
		dp = new int[N + 10][2];
		dp[1][0] = 1;
		dp[1][1] = 1;
		
		for (int i = 2; i <= N; i++) {
			dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
			dp[i][1] = dp[i - 1][0];
		}
		
		System.out.print("f(" + N + ") = ");
		System.out.println(dp[N][0] + dp[N][1]); // f(8) = 55
		
	}
	
	
}
