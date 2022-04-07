import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 과제06_탈주범검거 {
	static int T, N, M, R, C, L;
	static int board[][];
	static boolean visited[][];
	
	static int type1[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int type2[][] = {{1, 0}, {-1, 0}};
	static int type3[][] = {{0, 1}, {0, -1}};
	static int type4[][] = {{-1, 0}, {0, 1}};
	static int type5[][] = {{1, 0}, {0, 1}};
	static int type6[][] = {{1, 0}, {0, -1}};
	static int type7[][] = {{-1, 0}, {0, -1}};
	
	static int dirList[][][] = {{}, type1, type2, type3, type4, type5, type6, type7};
	static int res;
	
	// BFS 탐색으로 해결하는데 이 때 다음 칸으로 갔을 떄 다음 칸의 구조물 모양이 아귀가 맞는지 확인해야한다.
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			String line[] = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			R = Integer.parseInt(line[2]);
			C = Integer.parseInt(line[3]);
			L = Integer.parseInt(line[4]);
			
			board = new int[N][M];
			visited = new boolean[N][M];
			res = 0;
			
			for (int i = 0; i < N; i++) {
				line = br.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					board[i][j] = Integer.parseInt(line[j]);
				}
			}
			
			Queue<Point> q = new LinkedList<>();
			q.add(new Point(R, C, 0));
			
			while(!q.isEmpty()) {
				Point p = q.poll();
				int y = p.y;
				int x = p.x;
				int level = p.level;
				if (visited[y][x]) {
					continue;
				}
				visited[y][x] = true;
				res++;
				if (level + 1 == L) {
					continue;
				}
				int[][] dir = dirList[board[y][x]];
				for (int i = 0; i < dir.length; i++) {
					int nextY = y + dir[i][0];
					int nextX = x + dir[i][1];
					if (nextY < 0 || nextX < 0 || nextY >= N | nextX >= M) {
						continue;
					}
					if (visited[nextY][nextX]) {
						continue;
					}
					Point nextP = new Point(nextY, nextX, level + 1);
					if (isMovable(nextP, p)) {
						q.add(nextP);
					}
				}
			}
			
			System.out.printf("#%d %d\n", testCase, res);
		}
	}
	
	// p1 -> p2로 갈 수 있으면 true를 return;
	static boolean isMovable(Point p1, Point p2) {
		int[][] dir = dirList[board[p1.y][p1.x]];
		
		for (int i = 0; i < dir.length; i++) {
			int nextY = p1.y + dir[i][0];
			int nextX = p1.x + dir[i][1];
			if (nextY == p2.y && nextX == p2.x) {
				return true;
			}
		}
		return false;
	}
	

	static class Point {
		int y, x, level;
		Point(int y, int x, int level) {
			this.y = y;
			this.x = x;
			this.level = level;
		}
	}
}
