import java.io.*;
import java.util.*;

public class 실습09_벽돌깨기 {

	// 깨지는 벽돌 위치와 숫자 저장
	static class Point {
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}

	static int[] dr = {-1, 1, 0, 0}; // 행이동(상하좌우)
	static int[] dc = {0, 0, -1, 1}; // 열이동(상하좌우)
	static int N, W, H, min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // tc
		
		for(int tc=1; tc<=T; tc++) {
			String[] nwh = br.readLine().split(" ");
			N = Integer.parseInt(nwh[0]); // 구슬 개수
			W = Integer.parseInt(nwh[1]); // 벽돌 정보 가로(열개수)
			H = Integer.parseInt(nwh[2]); // 벽돌 정보 세로(행개수)
			
			int[][] map = new int[H][W]; // 벽돌 배치
			for(int i=0; i<H; i++) {
				String[] row = br.readLine().split(" ");
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(row[j]);
				}
			}
			min = Integer.MAX_VALUE; // 최소값 -> max로 초기값 설정
			
			go(0, map); // 현재 던진 구슬 수 0
			System.out.println("#"+tc+" "+min);
			
			// 깨지는 벽돌: (자신의수-1)만큼 사방 벽돌을 함께 제거, 깨지는 벽돌 - 1) 구슬에 맞은 벽돌, 2) 인접벽돌이 깨지면서 영향받은 벽돌
			// 벽돌수가 0이 되면 그 칸이 사라지며 위의 벽돌이 그 자리를 채움
			// 목표: N번 구슬 떨어뜨려서 최대한 많은 벽돌 깨기 -> 남은 벽돌 최소화
			// 떨어진 후 사방 연쇄작용 -> 주변 벽돌 부수고 0인 칸 있으면 벽돌 내리기
			// => 순서 1.구슬 떨어뜨리기(중복순열), 2.구슬에 맞는 벽돌찾고 영향받는 사방벽돌도 제거(bfs), 3.벽돌0인칸 치우고 아래로 내리기
			
		}

	}
	
	// 구슬 떨어뜨리는 순서에 따라 달라짐 -> 중복순열 이용해서 구슬 던지기(0 ~ N개)
	static boolean go(int count, int[][] map) { // 벽돌 다 부서지면 true, 아니면 false
		// 종료 조건 - 모든 벽돌이 다 깨지면 종료 or 구슬 다 던지면 종료
		int result = getRemain(map);
		if(result == 0) { // 벽돌 모두 깸
			min = result;
			return true;
		}
		if(count == N) { // 구슬 모두 던짐
			min = Math.min(min, result);
			return false;
		}
		
		int[][] newMap = new int[H][W];
		// 0 ~ (W-1)열까지 구슬 던져보기
		for(int c=0; c<W; c++) {
			// 구슬에 맞는 벽돌 찾기 -> 빈 공간이 아닌 벽돌 있는 위치 내려가며 찾기
			int r = 0;
			while(r<H && map[r][c]==0) ++r; // 벽돌 찾거나 경계 벗어날때까지 아래로 내려가며 탐색
			// 벽돌 없는 열 -> 다음열로 진행
			if(r == H) continue;
			// 구슬에 맞는 벽돌 찾음 -> 현재 상태 백업 & 벽돌 깨기
			copy(map, newMap); // 현재 벽돌 상태 백업
			boom(newMap, r, c); // 현재 벽돌 기준 사방에 가능한 벽돌까지 모두 처리
			down(newMap); // 부서진 벽돌 정리
			// true면 다 깨졌으므로 안 넘어가고 종료할 수 있게 return true, 아니면 다음 구슬로 넘어가서 벽돌 깨기 계속
			if(go(count+1, newMap)) return true;
		}
		return false; // 다 돌려도 true 없으면 계속해야하므로 return false
	}
	
	// 구슬 연쇄작용 -> 떨어지는 시작위치 기준(r, c)으로 사방 벽돌 부수기
	static void boom(int[][] map, int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		if(map[r][c] > 1) { // 다른 벽돌에 연쇄작용 있음 -> 추가 처리
			queue.offer(new Point(r, c, map[r][c]));
		}
		// 현재 벽돌만 없어지면 자신만 제거
		map[r][c] = 0; // visited 처리와 동일(없는건 다시 들어가지 않으므로)
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nr = p.r, nc= p.c; // 한 방향 끝까지 탐색 후 다음 방향 탐색
				
				for(int k=0; k<p.cnt-1; k++) { // 벽돌크기-1만큼 탐색
					// 현재 방향으로 이동
					nr += dr[i];
					nc += dc[i];
					if(nr<0 || nr>=H || nc<0 || nc>=W || map[nr][nc]==0) continue; // 범위 초과
					
					if(map[nr][nc] > 1) { // 다른 벽돌에 연쇄작용 있음 -> 추가 처리
						queue.offer(new Point(nr, nc, map[nr][nc]));
					}
					map[nr][nc] = 0; // 자기만 깨지거나 빈 공간이면 없애줌
				}
				
			}
		}
	}
	
	// 구슬 내리기 -> 벽돌 0인칸 위의 벽돌 밑으로 내리기(남은 벽돌 정리)
	static void down(int[][] map) {
		// 빈 공간이 아닌 벽돌만 자료구조에(stack 등) 집어넣은 후 뺀 벽돌은 0으로 바꿔주고 그대로 밑에서부터 배열에 넣어줌
		Stack<Integer> stack = new Stack<>();
		for(int c=0; c<W; c++) {
			for(int r=0; r<H; r++) {
				if(map[r][c] != 0) {
					stack.add(map[r][c]);
					map[r][c] = 0;
				}
			}
			int r = H - 1;
			while(!stack.isEmpty()) {
				map[r--][c] = stack.pop();
			}
		}
		
		
	}
	
	// 남은 벽돌수 return(이게 최소여야 함)
	static int getRemain(int[][] map) {
		int ans = 0;
		for(int r=0; r<H; r++) {
			for(int c=0; c<W; c++) {
				if(map[r][c] > 0) ans++;
			}
		}
		return ans;
	}
	
	// 기존 map 복사해서 작업 저장
	static void copy(int[][] map, int[][] newMap) {
		for(int r=0; r<H; r++) {
			for(int c=0; c<W; c++) {
				newMap[r][c] = map[r][c];
			}
		}
	}

}
