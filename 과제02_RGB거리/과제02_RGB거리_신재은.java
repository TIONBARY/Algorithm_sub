import java.io.*;

public class B1149_RGB거리 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] HC = new int[N][3]; // 입력값
		int[][] dp = new int[N][3]; // 현재 최소값, 사용한 색상(RGB-012)
		for(int i=0; i<N; i++) {
			String[] color = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				HC[i][j] = Integer.parseInt(color[j]);
				if(i == 0) dp[i][j] = HC[i][j]; // dp 초기 시작값 설정
			}
		}

		// 현재 색깔을 선택했을때 구할 수 있는 최소값을 모두 구해서 저장
		// 현재색은 이전색과 겹치면 안됨 -> 현재색 최소값 = 현재색 값 + 이전 다른색 최소값
		for(int i=1; i<N; i++) {
			dp[i][0] = HC[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = HC[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
			dp[i][2] = HC[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
		}
		
		// 최종 색깔을 선택했을때 나오는 최소값을 출력
		System.out.println(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));

	}

}
