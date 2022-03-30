package Baekjoon1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int K, W, H, map[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	static int likeHorse[][] = {{-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}, {-2,-1}};
	static boolean visited[][][];
	
	static class Monkey{
		int x, y, k, actCount;

		public Monkey(int x, int y, int k, int actCount) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.actCount = actCount;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Monkey> queue = new LinkedList<>();
		String s[];
		K = Integer.parseInt(br.readLine());
		s = br.readLine().split(" ");
		W = Integer.parseInt(s[0]);
		H = Integer.parseInt(s[1]);
		map = new int[H][W];
		visited = new boolean[K+1][H][W];
		visited[0][0][0] = true;
		
		for(int i=0; i<H; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		queue.add(new Monkey(0, 0, 0, 0));
		
		while(!queue.isEmpty()) {
			Monkey m = queue.poll();
			
			if(m.x == H-1 && m.y == W-1) {
				System.out.println(m.actCount);
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = m.x + dx[i];
				int ny = m.y + dy[i];
				int count = m.actCount;
				int tempK = m.k;
				
				if(nx>=0 && ny>=0 && nx<H && ny<W && map[nx][ny]==0 && visited[tempK][nx][ny]==false) {
					visited[tempK][nx][ny] = true;
					queue.add(new Monkey(nx, ny, tempK, ++count));
				}
			}
			
			if(m.k >= K) continue;
			
			for(int i=0; i<8; i++) {
				int nx = m.x + likeHorse[i][0];
				int ny = m.y + likeHorse[i][1];
				int count = m.actCount;
				int tempK = m.k + 1;
				
				if(nx>=0 && ny>=0 && nx<H && ny<W && map[nx][ny]==0 && visited[tempK][nx][ny]==false) {
					visited[tempK][nx][ny] = true;
					queue.add(new Monkey(nx, ny, tempK, ++count));
				}
			}
		}
		System.out.println("-1");
		
		br.close();
	}
}