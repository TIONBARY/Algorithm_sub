import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 실습14_연구소 {

	static int N, M, D;
	static int board[][];
	static int selected[] = {0, 0, 0};
	static int answer = -1;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
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
			int arr[][] = new int[N][M];
			boolean visited[][] = new boolean[N][M];
			deepCopy(arr, board);
			
			for (int i = 0; i < 3; i++) {
	            Point p = convertCoord(selected[i]);
	            if (arr[p.y][p.x] != 0) {
	                return;
	            }
	            arr[p.y][p.x] = 1;
	        }

	        fill(arr, visited);

	        int area = 0;
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < M; j++) {
	                if (arr[i][j] == 0) {
	                    area++;
	                }
	            }
	        }
	        if (area > answer) {
	            answer = area;
	        }

	        return;
		}
		
		for (int i = start; i < N * M; i++) {
			selected[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	// arr1 = arr2;
	static void deepCopy(int arr1[][], int arr2[][]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
	}
	
	static Point convertCoord(int val) {
		int y = val / M;
		int x = val % M;
		return new Point(y, x);
	}
	
	static void dfs(int y, int x, boolean visited[][], int arr[][]) {
	    if (y < 0 || x < 0 || y >= N || x >= M) {
	        return;
	    } 
	    if (visited[y][x]) {
	        return;
	    }
	    visited[y][x] = true;
	    if (arr[y][x] == 1) {
	        return;
	    }
	    arr[y][x] = 2;
	    dfs(y - 1, x, visited, arr);
	    dfs(y + 1, x, visited, arr);
	    dfs(y, x - 1, visited, arr);
	    dfs(y, x + 1, visited, arr);
	}
	
	static void fill(int arr[][], boolean visited[][]) {
	    
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < M; j++) {
	            if (arr[i][j] == 2 && !visited[i][j]) {
	                dfs(i, j, visited, arr);
	            }
	        }
	    }
	}
	
	static class Point {
		int y, x;
		Point (int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}

