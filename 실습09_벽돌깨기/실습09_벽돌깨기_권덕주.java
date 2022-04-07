import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 실습09_벽돌깨기 {

	static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int T, N, W, H;
	static int[][] board;
	static int minCount = Integer.MAX_VALUE;
	
	static void spread(int arr[][], int y, int x) {
		if (y < 0 || x < 0 || y >= H || x >= W) {
			return;
		}
		if (arr[y][x] <= 0) {
			return;
		}
		int level = arr[y][x] - 1;
		arr[y][x] = -1;

		
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= level; j++) {
				spread(arr, y + dir[i][0] * j, x + dir[i][1] * j);
			}
			
		}
	}
	
	// arr1 = arr2;
	static void deepCopy(int arr1[][], int arr2[][]) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
	}
	
	static void reorder(int arr[][]) {
		for (int i = 0; i < W; i++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			for (int j = H - 1; j >= 0; j--) {
				if (arr[j][i] > 0) {
					tmp.add(arr[j][i]);
				}
			}
			int pos = 0;
			int tSize = tmp.size();
			for (int j = H - 1; j >= 0; j--) {
				if (pos >= tSize) {
					arr[j][i] = 0;
				}else {
					arr[j][i] = tmp.get(pos++);
				}
				
			}
		}
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] < 0) {
					arr[i][j] = 0;
				}
			}
		}
	}
	
	static void drop(int arr[][], int level) {
		if (level == N) {
			int cnt = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (arr[i][j] > 0) {
						cnt++;
					}
				}
			}
			if (cnt < minCount) {
				minCount = cnt;
			}
			
			return;
		}
		
		// 먼저 제일 위 벽돌을 찾는다.
		boolean flag = false;
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (arr[j][i] > 0) {
					// 첫번째 구슬에 도착하면
					// 먼저 배열을 하나 복사한다.
					flag = true;
					int arr2[][] = new int[H][W];
					deepCopy(arr2, arr);
					
					spread(arr2, j, i);
					
					
					// 표시해 놓은 것을 다 반영한다.
					reorder(arr2);

					drop(arr2, level + 1);
					break;
				}
			}
		}
		if (!flag) {
			minCount = 0;
		}
		
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			minCount = Integer.MAX_VALUE;
			
			String line[] = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			W = Integer.parseInt(line[1]);
			H = Integer.parseInt(line[2]);
			
			board = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				line = br.readLine().split(" ");
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(line[j]);
				}
			}
			drop(board, 0);
			
			System.out.printf("#%d %d\n", testCase, minCount);
		}
		
		
	}

}
