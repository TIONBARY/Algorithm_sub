import java.io.*;

// input: 6 -> output: 169
public class WS03_막대색칠하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 총 길이

		int[] dp = new int[N+1];
		dp[1] = 2; // 1cm 막대
		dp[2] = 5; // 2cm 막대
		// 막대길이: 1cm(파, 노), 2cm(빨)
		// 1cm 추가: -1이므로 이전값에 2가지 경우(파, 노)
		// 2cm 추가: -2이므로 2번전 값에 1가지 경우(빨) 
		for(int i=3; i<=N; i++) {
			dp[i] = dp[i-1]*2 + dp[i-2]; // 1cm 막대, 2cm 막대
		}
		
		System.out.println(dp[N]);
	}

}
