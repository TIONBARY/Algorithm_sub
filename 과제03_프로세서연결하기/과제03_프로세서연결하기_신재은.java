import java.io.*;
import java.util.ArrayList;

public class S1767_프로세서연결하기 {

	static int N, needE, maxC, min; // process크기, 가장자리 아닌 코어수, 최대연결 코어수, 전선 길이 최소합(출력)
	static int[][] process;
	static ArrayList<int[]> core; // 전원을 연결해야하는 코어 리스트(위치)
	static int[] dr = {-1, 0, 1, 0}; // 행이동(상우하좌) - 시계
	static int[] dc = {0, 1, 0, -1}; // 열이동(상우하좌) - 시계
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			process = new int[N][N];
			core = new ArrayList<int[]>();
			
			needE = 0;
			maxC = 0;
			min = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				String[] row = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					process[i][j] = Integer.parseInt(row[j]);
					if(process[i][j] == 1) {
						if(i==0 || i==(N-1) || j==0 || j==(N-1)) continue; // 가장자리는 제외
						core.add(new int[] {i, j}); // 코어를 리스트에 추가
						needE++;
					}
				}
			}
			
			go(0, 0); // 연결 시작
			System.out.println("#"+tc+" "+min);
		}

	}
	
	// 부분집합으로 코어 전선놓기 시도(1~needE)
	static void go(int idx, int cnt) { // 코어리스트 idx, 전원과 연결된 코어수
		// 종료 조건 - 모든 코어를 다 돌았을때 종료
		if(idx == needE) {
			int res = getLength();
			if(maxC < cnt) { // 최대 연결 코어수 늘어나면 업데이트(연결 코어수와 최소합 모두 갱신)
				maxC = cnt;
				min = res;
			} else if(maxC == cnt) { // 연결 코어수 동일하면 최소합 비교해서 더 작은 최소합이면 업데이트
				if(min > res) min = res;
			}
			return;
		}
		
		// 현재 코어 좌표
		int r = core.get(idx)[0];
		int c = core.get(idx)[1];
		
		// 1) 전선 놓기(사방)
		for(int d=0; d<4; d++) {
			if(isAvailable(r, c, d)) { // 현재 코어가 d 방향으로 전선 놓기 가능
				setStatus(r, c, d, 2); // 전선 놓아진 위치에 2를 넣어줌(0이 빈 공간, 1이 코어)
				go(idx+1, cnt+1); // 다음 코어로 이동
				setStatus(r, c, d, 0); // 돌아오면 전선 지움(되돌려놓기)
			}
		}
		// 2) 전선 놓지 않기
		go(idx+1, cnt); // 연결 안됐으므로 코어수 변화 X
		
	}
	// 현재 위치(r, c)에서 d 방향으로 전선 놓기 가능한지 체크
	static boolean isAvailable(int r, int c, int d) {
		int nr=r, nc=c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break;
			// 다른 코어나 전선 만나면 false 보내고 종료
			if(process[nr][nc] != 0) return false;
		}
		return true; // 다른 장애물 없으면 끝까지 가서 종료
	}
	// 현재 위치(r, c)에서 d 방향으로 전선 놓기(2) or 치우기(0)
	static void setStatus(int r, int c, int d, int s) {
		int nr=r, nc=c;
		while(true) {
			// 진행방향으로 뻗어나감(코어 다음 위치부터)
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break; // 범위 초과시 종료
			process[nr][nc] = s; // 현재 방향으로 상태 채워줌(전선 or 빈칸)
		}
	}
	// 전선길이합 계산
	static int getLength() {
		int length = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(process[r][c] == 2) length++;
			}
		}
		return length;
	}

}
