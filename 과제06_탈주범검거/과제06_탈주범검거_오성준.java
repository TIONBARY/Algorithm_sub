import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] moveX = { -1, 1, 0, 0 };
		int[] moveY = { 0, 0, -1, 1 };
		
		HashMap<Integer, int[]> map = new HashMap<>();
		map.put(1, new int[] { 0, 1, 2, 3 });
		map.put(2, new int[] { 0, 1 });
		map.put(3, new int[] { 2, 3 });
		map.put(4, new int[] { 0, 3 });
		map.put(5, new int[] { 1, 3 });
		map.put(6, new int[] { 1, 2 });
		map.put(7, new int[] { 0, 2 });

		boolean[][] check = new boolean[4][8]; // 상하좌우로 갈때 갈 수 있는지 확인한다.
		check[0][1] = check[0][2] = check[0][5] = check[0][6] = true; // 위로 갈때
		check[1][1] = check[1][2] = check[1][4] = check[1][7] = true; 
		check[2][1] = check[2][3] = check[2][4] = check[2][5] = true; 
		check[3][1] = check[3][3] = check[3][6] = check[3][7] = true; 
				
		for (int tc = 1; tc <= T; tc++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int M = Integer.parseInt(input[1]);
			int X = Integer.parseInt(input[2]);
			int Y = Integer.parseInt(input[3]);
			int L = Integer.parseInt(input[4]); // 탈출 시간

			boolean[][] visit = new boolean[N][M];
			int[][] arr = new int[N][M];
			for (int i = 0; i < N; i++) {
				input = br.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(input[j]);
				}
			}

			// BFS로 구현
			int cnt = 0;
			int time = 1;
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] { X, Y });
			visit[X][Y] = true;
			cnt++;

			while (!q.isEmpty() && time < L) {

				int size = q.size();

				for (int t = 0; t < size; t++) {

					int x = q.peek()[0];
					int y = q.peek()[1];
					q.remove();

					int[] route = map.get(arr[x][y]); // 방향 저장되어 있음
					for (int i = 0; i < route.length; i++) {
						int r = route[i]; // move 배열들에서 몇번에 해당하는 것인지 표현

						int xx = x + moveX[r];
						int yy = y + moveY[r];

						// 범위 및 방문 여부 확인
						if (xx < 0 || yy < 0 || xx >= N || yy >= M || visit[xx][yy]) continue;
						
						// r과 arr[xx][yy]가 연결된건지 확인
						if (!check[r][arr[xx][yy]]) continue;
						
						q.offer(new int[] {xx, yy});
						visit[xx][yy] = true;
						cnt++;
					}
				}
				time++;
			}
			
			System.out.println("#" + tc + " " + cnt);
		}
	}

}

