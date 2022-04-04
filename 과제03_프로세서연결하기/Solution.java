package SWEA1767;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	static ArrayList<processor> list;
	static int N, min, max, map[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	static class processor{
		int x, y;

		public processor(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[];
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			min = 144;
			max = 0;
			
			for(int i=0; i<N; i++) {
				s = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
					if(map[i][j] == 1) {
						if(i>0 && j>0 && i<N-1 && j<N-1) {
							list.add(new processor(i, j));
						}
					}
				}
			}
			
			dfs(0, 0, 0);
			
			System.out.println("#"+tc+" "+min);
		}
		
		br.close();
	}
	
	static void dfs(int index, int count, int length) {
		if(index == list.size()) {
			if(max < count) {
				max = count;
				min = length;
			}
			else if(max == count) {
				if(min > length) min = length;
			}
			return;
		}
		
		for(int i=0; i<4; i++) {
			int cnt = 0;
			int x = list.get(index).x;
			int y = list.get(index).y;
			int nx = x;
			int ny = y;
			
			while(true) {
				nx += dx[i];
				ny += dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=N) break;
				
				if(map[nx][ny] == 1) {
					cnt = 0;
					break;
				}
				
				cnt++;
			}
			
			for(int j=0; j<cnt; j++) {
				x += dx[i];
				y += dy[i];
				map[x][y] = 1;
			}
			
			if(cnt == 0) dfs(index+1, count, length);
			else {
				dfs(index+1, count+1, length+cnt);
				x = list.get(index).x;
				y = list.get(index).y;
				for(int j=0; j<cnt; j++) {
					x += dx[i];
					y += dy[i];
					map[x][y] = 0;
				}
			}
		}
	}
}