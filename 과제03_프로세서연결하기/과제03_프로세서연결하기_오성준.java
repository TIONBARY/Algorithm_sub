import java.util.Scanner;

public class Solution {

	static int pc = 0;
	static int ans = Integer.MAX_VALUE;
	static int moveX[] = {-1,1,0,0};
	static int moveY[] = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			pc = 0;
			ans = Integer.MAX_VALUE;
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			boolean[][] visit = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			dfs(arr, N, visit, 0, 0, 0, 0);
			
			System.out.println("#" + test_case + " " + ans);
		}
	}

	private static void dfs(int[][] arr, int N, boolean[][] visit, int x, int y, int pCnt, int eCnt) {
		if(x >= N || y >= N) {
			//System.out.println(pCnt + " " + eCnt);
			if(pc == pCnt) {
				ans = Math.min(ans, eCnt);
			} else if(pc < pCnt) {
				pc = pCnt;
				ans = eCnt;
			}
			return;
		}
		
		// 다음칸으로 넘어갈 수 있도록 처리
		int nextY = y + 1;
		int nextX = x;
		if(nextY == N) {
			nextY = 0;
			nextX++;
		}
		
		if(arr[x][y] == 0) {
			dfs(arr, N, visit, nextX, nextY, pCnt, eCnt);
		} else if(arr[x][y] == 1){
			// 벽에 붙어있으면 자동으로 되는 거
			if(x == 0 || x == N - 1 || y == 0 || y == N - 1) {
				visit[x][y] = true;
				dfs(arr, N, visit, nextX, nextY, pCnt + 1, eCnt);
				visit[x][y] = false;
				return;
			}
			
			for(int i = 0; i < 4; i++) { // 방향을 결정
				
				// 해당 방향으로 visit과 arr를 통해서 코어의 존재와 간선의 유무 확인
				int xx = x;
				int yy = y;
				boolean flag = true;
				while(true) {
					xx += moveX[i];
					yy += moveY[i];
					
					if(xx < 0 || yy < 0 || xx >= N || yy >= N) break;
					
					if(arr[xx][yy] == 1 || visit[xx][yy]) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					// 전부 통과한다면 true 작업 후 dfs
					int e = 0;
					xx=x; yy=y;
					while(true) {
						xx += moveX[i];
						yy += moveY[i];
						
						if(xx < 0 || yy < 0 || xx >= N || yy >= N) break;
						visit[xx][yy] = true;
						e++;
					}
					
					dfs(arr, N, visit, nextX, nextY, pCnt + 1, eCnt + e);
					// 다녀오면 false작업
					xx=x; yy=y;
					while(true) {
						xx += moveX[i];
						yy += moveY[i];
						
						if(xx < 0 || yy < 0 || xx >= N || yy >= N) break;
						visit[xx][yy] = false;
					}
				}
			}
			
			// 해당 프로세서가 가능하지만 작업하지 않는 경우를 포함해야 함 이거 안하면 50개중 한개 틀림 -> 아래와 같은 경우
			dfs(arr, N, visit, nextX, nextY, pCnt, eCnt);
		}
		
	}

}
/*
1
7
1 1 1 1 1 1 1
1 1 1 1 1 1 1
1 1 1 1 1 1 1
1 1 1 1 1 1 1
1 1 1 1 1 1 1
1 1 1 1 1 1 1
1 1 1 1 1 1 1
*/
