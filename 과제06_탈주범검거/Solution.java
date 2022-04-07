package SWEA1953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, R, C, L, result, map[][];
	static final int[] dx = { -1, 0, 1, 0 };
	static final int[] dy = { 0, 1, 0, -1 };
	static boolean visited[][], check[][];

	static final int[][] tunnel = { 
			{ 0, 0, 0, 0 }, 
			{ 1, 1, 1, 1 },
			{ 1, 0, 1, 0 }, 
			{ 0, 1, 0, 1 }, 
			{ 1, 1, 0, 0 }, 
			{ 0, 1, 1, 0 }, 
			{ 0, 0, 1, 1 }, 
			{ 1, 0, 0, 1 } 
	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[];
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			s = br.readLine().split(" ");
			N = Integer.parseInt(s[0]);
			M = Integer.parseInt(s[1]);
			R = Integer.parseInt(s[2]);
			C = Integer.parseInt(s[3]);
			L = Integer.parseInt(s[4]);
			map = new int[N][M];
			visited = new boolean[N][M];
			check = new boolean[N][M];
			for (int i=0; i<N; i++) {
				s = br.readLine().split(" ");
				for (int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(s[j]);
				}
			} 

			result = 0;
			
			dfs(R, C, 1);

			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (check[i][j])
						++result;
				}
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	private static void dfs(int x, int y, int hours) {
		check[x][y] = true;
		int nx, ny;

		if (hours >= L) {
			return;
		}
		
		int[] dirs = tunnel[map[x][y]];
		for (int i=0; i<4; i++) {
			if (dirs[i] == 0) continue;
			
			nx = x + dx[i];
			ny = y + dy[i];
			
			if (nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny]==0 || visited[nx][ny]) continue;

			if (tunnel[map[nx][ny]][(i + 2) % 4] == 1) { 
				visited[nx][ny] = true;
				dfs(nx, ny, hours + 1);
				visited[nx][ny] = false; 
			}
		}
	}
}