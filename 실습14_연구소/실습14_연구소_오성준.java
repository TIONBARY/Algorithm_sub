import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N,M,arr[][], ans = 0;
	static boolean visit[][];
	static int[] moveX = { -1, 1, 0, 0 };
	static int[] moveY = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		arr = new int[N][M]; // 2: 바이러스 - 1: 벽 - 0: 빈 칸
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}

		setWall(0, 0, 0);

		System.out.println(ans);
	}

	private static void setWall(int x, int y, int wallCnt) {

		if (wallCnt == 3) {

			// visit 배열 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					visit[i][j] = false; // false면 아닌 거
				}
			}
			
			// dfs 돌면서 실행하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(arr[i][j] == 2 && !visit[i][j]) {
						visit[i][j] = true;
						dfs(i, j);
					}
				}
			}
			
			// 개수 세기
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(arr[i][j] == 0 && !visit[i][j]) cnt++;
				}
			}
			
			// ans 갱신
			ans = Math.max(ans, cnt);
			
			return;
		}

		if (x >= N || y >= M || wallCnt > 3) {
			return;
		}

		// 현재 점의 다음점 표시
		int nextY = y + 1;
		int nextX = x;
		if (nextY >= M) {
			nextY = 0;
			nextX++;
		}

		// 해당 칸에 벽을 세울 수 있을 때
		if (arr[x][y] == 0) {
			arr[x][y] = 1;
			setWall(nextX, nextY, wallCnt + 1);
			arr[x][y] = 0;
		}

		setWall(nextX, nextY, wallCnt);
	}

	private static void dfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int xx = x + moveX[i];
			int yy = y + moveY[i];
			
			if(xx < 0 || yy < 0 || xx >= N || yy >= M || arr[xx][yy] != 0 || visit[xx][yy]) continue;
			
			visit[xx][yy] = true;
			dfs(xx, yy);
		}
	}

}
