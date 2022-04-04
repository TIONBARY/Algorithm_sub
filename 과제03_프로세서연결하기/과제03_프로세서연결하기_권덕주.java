import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 과제03_프로세서연결하기 {
	
	static int T;
	static int N;
	static int arr[][];
	static ArrayList<Pair> nodeList;;
	
	static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int maxSelected = -1;
	static int minResult = Integer.MAX_VALUE;
	
	static boolean connect(int y, int x, int direction) {
		if (y < 0 || x < 0 || y >= N || x >= N) {
			return true;
		}
		if (arr[y][x] > 0) {
			arr[y][x] += 3;
			return false;
		}
		arr[y][x] = 3;
		return connect(y + dir[direction][0], x + dir[direction][1], direction);
	}
	
	static void unconnect(int y, int x, int direction) {
		if (y < 0 || x < 0 || y >= N || x >= N) {
			return;
		}
		if (arr[y][x] > 3) {
			arr[y][x] -= 3;
			return;
		}
		arr[y][x] = 0;
		unconnect(y + dir[direction][0], x + dir[direction][1], direction);
	}
	
	static void connectAll(int total, int cnt, int selected) {
		if (total == cnt) {
			if (selected >= maxSelected) {
				int res = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (arr[i][j] == 3) {
							res++;
						}
					}
				}
				if (selected > maxSelected) {
					minResult = res;
					maxSelected = selected;
				} else {
					if (res < minResult) {
						minResult = res;
					}
				}
				
				
			}
			return;
		}
		
		Pair p = nodeList.get(cnt);
		int y = p.y;
		int x = p.x;
		for (int j = 0; j < 4; j++) {
			if (connect(y + dir[j][0], x + dir[j][1], j)) {
				connectAll(total, cnt + 1, selected + 1);
			}
			unconnect(y + dir[j][0], x + dir[j][1], j);
		}
		connectAll(total, cnt + 1, selected);
		
	}
	

    // 백트래킹을 이용하여 모든 가능한 경우를 탐색한다.

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[];
		T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			maxSelected = -1;
			minResult = Integer.MAX_VALUE;
			nodeList = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			
			for (int i = 0; i < N; i++) {
				line = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(line[j]);
					if (arr[i][j] == 1) {
						if (i != 0 && j != 0) {
							nodeList.add(new Pair(i, j));
						}
					}
				}
			}
			int total = nodeList.size();
		
			connectAll(total, 0, 0);
			
			System.out.printf("#%d %d\n", testCase, minResult);
			
			
		}
		
	}
	
	static class Pair {
		int y, x;
		Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
