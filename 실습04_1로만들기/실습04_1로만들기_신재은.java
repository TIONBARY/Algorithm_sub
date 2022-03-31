import java.io.*;

public class B1463_1로만들기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		
		for(int i=0; i<=N; i++) { // 1에서 N까지 올라가며 dp
			if(i <= 1) dp[i] = 0;
			else if(i <= 3) dp[i] = 1;
			else {
				dp[i] = dp[i-1] + 1; // -1인 경우 -> 기존값에 1번 연산
				// %2 or %3인 경우 -1일때와 나눈 최근값+1과 횟수 비교
				if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1);
				if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1);
			}
		}
		
		System.out.println(dp[N]);

	}

}
