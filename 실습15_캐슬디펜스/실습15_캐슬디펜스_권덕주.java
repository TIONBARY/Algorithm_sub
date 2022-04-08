import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 실습15_캐슬디펜스 {

	static int N, M, D;
	static int board[][];
	static int selected[] = {0, 0, 0};
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		//필요한 모듈
		// 1. 조합 2. 배열 복사 3. 궁수로 적 제거 4. 한칸씩 내려오기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		D = Integer.parseInt(line[2]);
		
		board = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		combination(0, 0);
		
		System.out.println(answer);
	}
	
	static void combination(int cnt, int start) {
		if (cnt == 3) {
			int res = play();
			if (res > answer) {
				answer = res;
			}
			return;
		}
		
		for (int i = start; i < M; i++) {
			selected[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}
	
	
	static int play() {
		
		int arr[][] = new int[N][M];
		deepCopy(arr, board);
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			boolean visited[][] = new boolean[N][M];
			for (int j = 0; j < 3; j++) {
				attack(arr, visited, N - 1, selected[j]);
			}
			count += countVisited(visited, arr);
			goDown(arr);
		}
		
		return count;
		
	}
	
	
	// arr1 = arr2;
	static void deepCopy(int arr1[][], int arr2[][]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
	}
	
	static int countVisited(boolean visited[][], int arr[][]) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j]) {
					count++;
					arr[i][j] = 0;
				}
			}
		}
		return count;
	}
	
	static void goDown(int arr[][]) {
		for (int i = N - 1; i > 0; i--) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = arr[i - 1][j];
			}
		}
		for (int i = 0; i < M; i++) {
			arr[0][i] = 0;
		}
	}
	
	static void attack(int arr[][], boolean visited[][], int startY, int startX) {
		
		if (arr[startY][startX] == 1) {
			visited[startY][startX] = true;
			return;
		}
		int d = 0;
		while (d < D) {
			for (int xPos = d; xPos >= 0; xPos--) {
				int yPos = d - xPos;
				
				int curX = startX - xPos;
				int curY = startY - yPos;
				if (curY < 0 || curX < 0 || curY >= N || curX >= M) {
					continue;
				}
				if (arr[curY][curX] == 1) {
					visited[curY][curX] = true;
					return;
				}
			}
			
			for (int xPos = 1; xPos <= d; xPos++) {
				int yPos = d - xPos;
				
				int curX = startX + xPos;
				int curY = startY - yPos;
				if (curY < 0 || curX < 0 || curY >= N || curX >= M) {
					continue;
				}
				if (arr[curY][curX] == 1) {
					visited[curY][curX] = true;
					return;
				}
			}
			
			d++;
		}
		
	}

}
