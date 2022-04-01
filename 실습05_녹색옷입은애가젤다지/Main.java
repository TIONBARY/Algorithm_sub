package Baekjoon4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N, cave[][], dijk[][];
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	static class Link implements Comparable<Link>{
		int x, y, thiefRupee;

		public Link(int x, int y, int thiefRupee) {
			super();
			this.x = x;
			this.y = y;
			this.thiefRupee = thiefRupee;
		}

		@Override
		public int compareTo(Link o) {
			return this.thiefRupee - o.thiefRupee;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[];
		int count = 1;
		
		N = Integer.parseInt(br.readLine());
		while (N != 0) {
			cave = new int[N][N];
			dijk = new int[N][N];

			for (int i = 0; i < N; i++) {
				s = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(s[j]);
					dijk[i][j] = Integer.MAX_VALUE;
				}
			}
			System.out.println("Problem "+(count++)+": "+move());
			N = Integer.parseInt(br.readLine());
		}

		br.close();
	}
	
	static int move() {
		PriorityQueue<Link> queue = new PriorityQueue<>();
		dijk[0][0] = cave[0][0];
		queue.add(new Link(0, 0, cave[0][0]));
		
		while(!queue.isEmpty()) {
			Link link = queue.poll();

			for(int i=0; i<4; i++) {
				int nx = link.x + dx[i];
				int ny = link.y + dy[i];
				
				if(nx>=0 && ny>=0 && nx<N && ny<N) {
					if(dijk[nx][ny] > dijk[link.x][link.y] + cave[nx][ny]) {
						dijk[nx][ny] = dijk[link.x][link.y] + cave[nx][ny];
						queue.add(new Link(nx, ny, dijk[nx][ny]));
					}
				}
			}
		}
		return dijk[N-1][N-1];
	}
}