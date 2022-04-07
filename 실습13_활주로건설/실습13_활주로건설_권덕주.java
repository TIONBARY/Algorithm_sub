import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 실습13_활주로건설 {

	static int T, N, X;
	static int board[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			String line[] = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			X = Integer.parseInt(line[1]);
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				line = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(line[j]);
				}
			}
		
			
			/* 
			 
			 1. 최초 상태일 경우 그냥 콤보 개수를 센다
			 2. 콤보를 센 이후에는 다음 숫자(높이가 달라지는 지점)가 더 큰 경우와 작은 경우로 나눈다.
			 3. 우선 양쪽 경우 모두 차이가 1보다 크면 그냥 넘긴다
			 4. 차이가 1일 경우에는 더 큰 경우에는 현재까지 모은 콤보 개수를 평가한 후 다시 최초 상태로 만든다.
			 5. 더 작은 경우에는 다음 콤보를 한번 더 센다.
			 6. 콤보 개수가 충족이 될 경우(경사로의 개수만큼) 콤보 개수에서 X만큼 빼준 후(경사로 건설에 써야한다)
			       다음으로 넘긴다. 남은 콤보는 그 다음 루프에서 4번 케이스에서 다시 쓸 수도 있기 때문에 남기는 것이다.  
			  
			 */
			
			
			// 가로 방향
			
			int answer = 0;
			for (int i = 0; i < N; i++) {
				int nextPos = 0;
				int prev = board[i][0];
				boolean flag = true;
				int combo = 0;
				while (nextPos < N) {
					int cur = board[i][nextPos];
					int curPos = nextPos;
					if (prev == cur) {
						nextPos = collectCombo1(i, curPos);
						combo = nextPos - curPos;
					} else if (prev < cur) {
						if (cur - prev != 1) {
							flag = false;
							break;
						} else {
							if (combo < X) {
								flag = false;
								break;
							}
							prev = board[i][nextPos];
						}
					} else if (prev > cur) {
						if (prev - cur != 1) {
							flag = false;
							break;
						}
						prev = board[i][nextPos];
						nextPos = collectCombo1(i, curPos);
						combo = nextPos - curPos;
						if (combo < X) {
							flag = false;
							break;
						}
						combo -= X;
						
					}
				}
				if (flag) {
					answer++;
				}
				
			}
			
			
			// 세로 방향
			for (int i = 0; i < N; i++) {
				int nextPos = 0;
				int prev = board[0][i];
				boolean flag = true;
				int combo = 0;
				while (nextPos < N) {
					int cur = board[nextPos][i];
					int curPos = nextPos;
					if (prev == cur) {
						nextPos = collectCombo2(i, curPos);
						combo = nextPos - curPos;
					} else if (prev < cur) {
						if (cur - prev != 1) {
							flag = false;
							break;
						} else {
							if (combo < X) {
								flag = false;
								break;
							}
							prev = board[nextPos][i];
						}
					} else if (prev > cur) {
						if (prev - cur != 1) {
							flag = false;
							break;
						}
						prev = board[nextPos][i];
						nextPos = collectCombo2(i, curPos);
						combo = nextPos - curPos;
						if (combo < X) {
							flag = false;
							break;
						}
						combo -= X;
						
					}
				}
				if (flag) {
					answer++;
				}
				
			}

			System.out.printf("#%d %d\n", testCase, answer);
		}
	}

	// 가로 방향
	static int collectCombo1(int row, int start) {
		int prev = board[row][start];
		
		for (int i = start + 1; i < N; i++) {
			int cur = board[row][i];
			if (cur != prev) {
				return i;
			}
		}
		return N;
	}
	
	// 세로 방향
	static int collectCombo2(int col, int start) {
		int prev = board[start][col];
		
		for (int i = start + 1; i < N; i++) {
			int cur = board[i][col];
			if (cur != prev) {
				return i;
			}
		}
		return N;
	}

}
