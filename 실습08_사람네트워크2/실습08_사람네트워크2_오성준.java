import java.util.Scanner;

public class Solution {

	static final int INF = 1000000000;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {
			// 오늘 배운 플로이드 와샬 구현
			int N = sc.nextInt();
			int[][] adjMatrix = new int[N][N];
			int[][] dist = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					adjMatrix[i][j] = sc.nextInt();
					
					if(i == j) continue;
					if(adjMatrix[i][j] > 0) dist[i][j] = adjMatrix[i][j];
					else dist[i][j] = INF;
				}
			}
			
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(i == k || j == i) continue;
						
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
			
			int ret = INF;
			
			for(int i = 0; i < N; i++) {
				int sum = 0;
				for(int j = 0; j < N; j++) {
					sum += dist[i][j];
				}
				ret = Math.min(ret, sum);
			}
			
			System.out.println("#" + test_case + " " + ret);
		}
	}

}

