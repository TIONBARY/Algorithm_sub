package algo2;

import java.io.*;

public class 실습08_사람네트워크2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			// 입력: 사람수 N 인접 행렬(각 행에 대한 행렬값, N*N)
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);

			int[][] graph = new int[N][N]; // 인접행렬
			for(int i=1; i<=N*N; i++) {
				int r = (i - 1) / N;
				int c = i % N - 1;
				if(c == -1) c = N - 1;
				graph[r][c] = Integer.parseInt(input[i]);
				if(r!=c && graph[r][c]==0) { // 초기값: 자기 자신이 아니며 인접한 정점이 없는 부분을 최대값으로 설정
					graph[r][c] = N*N+1;					
				}
			}

			int min = Integer.MAX_VALUE;
			// 플로이드-워샬(경유지 -> 출발지 -> 도착지)
			// 출발지, 경유지, 목적지는 다른 값이어야함 -> 같은 값이면 continue로 넘겨줌
			for(int k=0; k<N; ++k) {
				for(int i=0; i<N; ++i) {
					if(i == k) continue;
					for(int j=0; j<N; ++j) {
						if(i==j || k==j) continue;
						// 직전 단계의 최적해보다 현재 경유지를 거쳐가는 거리가 작으면 최적해 업데이트
						// 간선이 양수이므로 이렇게 갈 수 있음
						if(graph[i][j] > graph[i][k]+graph[k][j]) {
							graph[i][j] = graph[i][k] + graph[k][j];
						}
					}
				}
			}
			
			// 노드마다 최소값의 합 -> 각 행의 합이 그 노드의 최소값(cc) -> 그 중 가장 작은 값이 min
			for(int i=0; i<N; i++) {
				int cc = 0;
				for(int j=0; j<N; j++) {
					cc += graph[i][j];
				}
				min = Math.min(min, cc);
			}
			
			System.out.println("#"+tc+" "+min);
		}

	}

}
