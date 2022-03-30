import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 실습01_치즈 {

	static int board[][] = new int[101][101];
	static boolean visited[][];
	static int H, W;
	
	
	public static void dfs(int y, int x) {
		if (y < 0 || x < 0 || y >= H || x >= W) {
			return;
		}
		if (visited[y][x]) {
			return;
		}
		visited[y][x] = true;
		if (board[y][x] == 1) {
			board[y][x] = 2;
			return;
		}
		
		dfs(y - 1, x);
		dfs(y + 1, x);
		dfs(y, x - 1);
		dfs(y, x + 1);
	}
	

    // 가장자리는 항상 비어있기 때문에 가장자리에서 dfs를 돌려서 맞닿은 치즈면에 표시를 해둔다.
    // 표시를 해놓은 칸을 세면서 제거한다. 치즈가 모두 없어질때까지 반복한다.

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		H = Integer.parseInt(line[0]);
		W = Integer.parseInt(line[1]);
		
		int cheezeCount = 0;
		int lastRemoved = 0;
		int time = 0;
		
		for (int i = 0; i < H; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(line[j]);
				if (board[i][j] == 1) {
					cheezeCount++;
				}
			}
		}
		
		while (cheezeCount > 0) {
			time++;
			visited = new boolean[H][W];
			lastRemoved = 0;
			dfs(0, 0);
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (board[i][j] == 2) {
						lastRemoved++;
						board[i][j] = 0;
					}
				}
			}
			cheezeCount = cheezeCount - lastRemoved;
		}
		
		System.out.println(time);
		System.out.println(lastRemoved);
		
		
	}
}
