import java.io.*;

// input: 8 -> output: 55
public class WS02_아파트색칠하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 층수

		int[] dp = new int[N];
		dp[0] = 2; // 1층 칠하는 방법수
		dp[1] = 3; // 2층 칠하는 방법수
		// 노란색 -> 이전색과 상관X -> 모든 이전층에 쌓을 수 있음
		// 파란색 -> 이전층이 노란색인것만 쌓을 수 있음-> 2층이 고정됨(노파) -> 노란색은 모든 이전층에 쌓을 수 있으므로 2번째 전 층만큼 쌓을 수 있음
		for(int i=2; i<N; i++) {
			dp[i] = dp[i-1] + dp[i-2]; // 마지막이 노란층 + 파란층
		}

		System.out.println(dp[N-1]);
	}

}
