// 메모리 초과 뜸

import java.io.*;
import java.util.*;

public class B1600_말이되고픈원숭이 {
	
	static int K, W, H; // 말처럼이동횟수, 열, 행
	static int[][] map; // 격자판
	static int[] hdr = {-2, -2, -1, -1, 1, 1, 2, 2}; // 말처럼 행이동
	static int[] hdc = {-1, 1, -2, 2, -2, 2, -1, 1}; // 말처럼 열이동
	static int[] dr = {-1, 0, 1, 0}; // 원숭이 행이동
	static int[] dc = {0, 1, 0, -1}; // 원숭이 열이동
	static boolean visited[][][]; // 방문체크(좌표+현재말처럼이동 사용횟수)

	public static class Monkey {
		int nowR, nowC, reK, cnt; // 현재 좌표, 남은 말이동 횟수, 현재 이동횟수
		
		public Monkey(int nowR, int nowC, int reK, int cnt) {
			this.nowR = nowR;
			this.nowC = nowC;
			this.reK = reK;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		String[] wh = br.readLine().split(" ");
		W = Integer.parseInt(wh[0]); // 열
		H = Integer.parseInt(wh[1]); // 행

		map = new int[H][W];
		visited = new boolean[H][W][K+1];
		for(int i=0; i<H; i++) {
			String[] row = br.readLine().split(" ");
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(row[j]);
			}
		}
		
		// bfs로 시작부터 끝까지 이동하며 최소 이동횟수 찾기
		Queue<Monkey> queue = new LinkedList<>();
		visited[0][0][K] = true;
		queue.offer(new Monkey(0, 0, 0, 0)); // 시작
		
		while(!queue.isEmpty()) {
			Monkey mon = queue.poll();
			// 종료조건 -> 목적지 도착시 현재 이동횟수
			if(mon.nowR==(H-1) && mon.nowC==(W-1)) {
				System.out.println(mon.cnt);
				return;
			}
			
			// 원숭이 이동
			for(int i=0; i<4; i++) {
				int nr = mon.nowR + dr[i];
				int nc = mon.nowC + dc[i];
				// 방문 불가 조건(범위 벗어남, 이미 현재 횟수에서 방문, 장애물)
				if(nr<0 || nr>=H || nc<0 || nc>=W || visited[nr][nc][mon.reK] || map[nr][nc]!=0) continue;
				// 방문 가능 -> 이동하고 방문 표기
				visited[nr][nc][mon.reK] = true;
				queue.offer(new Monkey(nr, nc, mon.reK, mon.cnt+1));
			}
			
			if(mon.reK >= K) continue; // 이동가능 횟수 전부 사용시 말처럼 이동 불가
			// 말처럼 이동
			for(int i=0; i<8; i++) {
				int nr = mon.nowR + hdr[i];
				int nc = mon.nowC + hdc[i];
				// 방문 불가 조건(범위 벗어남, 이미 현재 횟수에서 방문, 장애물)
				if(nr<0 || nr>=H || nc<0 || nc>=W || visited[nr][nc][mon.reK] || map[nr][nc]!=0) continue;
				// 방문 가능 -> 이동하고 방문 표기(한 번 말처럼 이동 사용했으므로 +1)
				visited[nr][nc][mon.reK+1] = true;
				queue.offer(new Monkey(nr, nc, mon.reK+1, mon.cnt+1));
			}
			
		}
		
		// bfs 종료후에도 minM이 변화 없으면 이동 못했다는 의미
		System.out.println(-1);

	}

}
