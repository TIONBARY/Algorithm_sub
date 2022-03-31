
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 과제02_RGB거리 {
	static int dp[][];
	static int cost[][];
	static int N;
	
	static int minOfThree(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		String line[];
		cost = new int[N + 1][3];
		dp = new int[N + 1][3];
		
		for (int i = 1; i <= N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		dp[1][0] = cost[1][0];
		dp[1][1] = cost[1][1];
		dp[1][2] = cost[1][2];
		
		for (int i = 2; i <= N; i++) {
			dp[i][0] = Math.min(dp[i -1][1], dp[i - 1][2]) + cost[i][0];
			dp[i][1] = Math.min(dp[i -1][0], dp[i - 1][2]) + cost[i][1];
			dp[i][2] = Math.min(dp[i -1][0], dp[i - 1][1]) + cost[i][2];
		}
		
		System.out.println(minOfThree(dp[N][0], dp[N][1], dp[N][2]));
		
	}
}
