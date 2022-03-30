import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B2636_치즈 {

	static int[][] map; // 치즈판
	static int R, C; // 행, 열
	static int time, cheese; // 치즈 녹는데 걸리는 시간, 남은 치즈 조각 개수
	static int[] dr = {-1, 0, 1, 0}; // 행이동-상우하좌(시계방향)
	static int[] dc = {0, 1, 0, -1}; // 열이동-상우하좌(시계방향)
	static boolean[][] visited; // 방문체크
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] rc = br.readLine().split(" ");
		R = Integer.parseInt(rc[0]);
		C = Integer.parseInt(rc[1]);
		map = new int[R][C];
		
		for(int i=0; i<R; i++) {
			String[] row = br.readLine().split(" ");
			for(int j=1; j<C; j++) {
				map[i][j] = Integer.parseInt(row[j]);
				if(map[i][j] == 1) cheese++;
			}
		}

		// 치즈가 모두 사라질때까지 반복
		int cnt = 0;
		while(cheese != 0) {
			// 이전 시간 기준으로 update, 새로 탐색해서 치즈 지움
			cnt = cheese;
			time++;
			checkCheese();
		}
		
		System.out.println(time);
		System.out.println(cnt);
	}
	
	// 공기에 맞닿은 치즈는 지워주고 근처에 치즈 없으면 좌표 저장후 이동(bfs)
	public static void checkCheese() {
		visited = new boolean[R][C];
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {0, 0}); // 시작은 첫 좌표부터(가장자리는 무조건 치즈가 존재하지 않는 빈 공간)
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i=0; i<4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				// 방문할 수 없는 위치거나 방문한 적 있으면 넘어감
				if(nr<0 || nr>=R || nc<0 || nc>=C || visited[nr][nc]) continue;
				// 치즈와 닿아있으면 치즈 없애주고 기록, 닿아있지 않으면 좌표 이동 -> 방문 기록
				if(map[nr][nc] == 1) {
					cheese--;
					map[nr][nc] = 0;
				} else if(map[nr][nc] == 0) {
					queue.offer(new int[] {nr, nc});
				}
				visited[nr][nc] = true;
			}
		}
		
	}

}
