import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 실습12_보급로 {

	static int T, N;
	static int board[][];
	static int dist[][];
	static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	

    // 다익스트라 알고리즘을 이용하여 최단거리를 구한다.
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				char line[] = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					board[i][j] = line[j] - '0';
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			PriorityQueue<Point> pq = new PriorityQueue<>();
			pq.add(new Point(0, 0, board[0][0]));
			dist[0][0] = 0;
			while(!pq.isEmpty()) {
				Point p = pq.poll();
				int y = p.y;
				int x =  p.x;
				
				int val = p.value;
				if (val > dist[y][x]) {
					continue;
				}
				if (y == N - 1 && x == N - 1) {
					break;
				}
				for (int i = 0; i < 4; i++) {
					int newY = y + dir[i][0];
					int newX = x + dir[i][1];
					if (newY < 0 || newX < 0 || newY >= N || newX >= N) {
						continue;
					}
					if (val + board[newY][newX] < dist[newY][newX]) {
						dist[newY][newX] = val + board[newY][newX];
						pq.add(new Point(newY, newX, dist[newY][newX]));
					}
				}
				
			}
			System.out.printf("#%d %d\n", testCase, dist[N - 1][N - 1]);
		}
		
	}
	
	static class Point implements Comparable<Point> {
		int y, x, value;
		Point(int y, int x, int value) {
			this.y = y;
			this.x = x;
			this.value = value;
		}
		@Override
		public int compareTo(Point o) {
			return this.value - o.value;
		}
	}

}
