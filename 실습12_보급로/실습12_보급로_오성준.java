import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {

	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] moveX = {-1,1,0,0};
		int[] moveY = {0,0,-1,1};
		
		for(int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			int[][] dist = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				char[] input = br.readLine().toCharArray();
				for(int j = 0; j < N; j++) {
					arr[i][j] = input[j] - '0';
					dist[i][j] = INF;
				}
			}
			
			// int[3] = {계산 값, x, y}
			PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1, int[] o2) -> {
				return o1[0] - o2[0];
			});
			dist[0][0] = 0;
			pq.offer(new int[] {dist[0][0], 0, 0}); 
			
			while(!pq.isEmpty()) {
				int d = pq.peek()[0];
				int x = pq.peek()[1];
				int y = pq.peek()[2];
				pq.remove();
				
				if(dist[x][y] < d) continue;
				if(x == N - 1 && y == N - 1) break;
				
				for(int i = 0; i < 4; i++) {
					int xx = x + moveX[i];
					int yy = y + moveY[i];
					
					if(xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
					
					if(dist[xx][yy] > d + arr[xx][yy]) {
						dist[xx][yy] = d + arr[xx][yy];
						pq.offer(new int[] {dist[xx][yy], xx, yy});
					}
				}
			}
			
			System.out.println("#" + tc + " " + dist[N-1][N-1]);
		}
	}

}

