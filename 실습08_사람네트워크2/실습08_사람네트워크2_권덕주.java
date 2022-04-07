import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 실습08_사람네트워크2 {

	static int T, N;
	static int arr[][];
	static final int INF = 999999;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			String line[] = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			arr = new int[N][N];
			int cur = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(line[cur++]);
					if (i != j && arr[i][j] == 0) {
						arr[i][j] = INF;
					}
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (arr[i][k] == INF || arr[j][k] == INF) {
							continue;
						}
						if (arr[i][k] + arr[j][k] < arr[i][j]) {
							arr[i][j] = arr[i][k] + arr[j][k];
						}
					}
				}
			}
			
			int min = INF;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					if (arr[i][j] != INF) {
						sum += arr[i][j];
					}
				}
				if (sum < min) {
					min = sum;
				}
			}
			
			System.out.printf("#%d %d\n", testCase, min);
		}
	}

}
