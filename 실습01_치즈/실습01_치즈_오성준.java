import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Boj2636 {

	static int[] moveX = {-1,1,0,0};
	static int[] moveY = {0,0,1,-1};
	static int X,Y, cheeseCnt = 0;
	static int[][] arr;
	static boolean[][] visit;
	static Queue<int[]> qOne = new LinkedList<int[]>();
	static Queue<int[]> qZero = new LinkedList<int[]>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		X = Integer.parseInt(input[0]);
		Y = Integer.parseInt(input[1]);
		
		arr = new int[X][Y];
		visit = new boolean[X][Y];
		
		for(int i = 0; i < X; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < Y; j++) {
				arr[i][j] = Integer.parseInt(input[j]);
				if(arr[i][j] == 1) cheeseCnt++;
			}
		}
		
		int level = 0;
		int cnt = 0;
		while(cheeseCnt > 0) {
			level++;
			cnt = cheeseCnt;
			
			for(int i = 0; i < X; i++) {
				Arrays.fill(visit[i], false);
			}
			
			visit[0][0] = true;
			dfs(0,0);
		}
		
		System.out.println(level);
		System.out.println(cnt);
	}

	private static void dfs(int i, int j) {
		if(arr[i][j] == 0 || !visit[i][j]) {
			for(int ii = 0; ii < 4; ii++) {
				int x = i + moveX[ii];
				int y = j + moveY[ii];
				
				if(x<0||y<0||x>=X||y>=Y||visit[x][y]) continue;
				visit[x][y] = true;
				dfs(x,y);
			}
		} else {
			arr[i][j] = 0;
			cheeseCnt--;
			visit[i][j] = true;
		}
	}


}
