import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 과제01_말이되고픈원숭이 {
	static int board[][];
	static boolean visited[][][];
	
	static int H, W, K;
	
	
	static class Monkey {
		Monkey(int currentY, int currentX, int remainingJump) {
			this.currentX = currentX;
			this.currentY = currentY;
			this.remainingJump = remainingJump;
		}
		
		int currentY;
		int currentX;
		int remainingJump;
	}
	
	// BFS 탐색인데 일정 횟수만큼 점프를 허용한다.
	// 주의해야 할 점이 일반적인 경로탐색에서 한번 방문했던 점은 최단경로가 될 수 없지만 이 경우
	// 다시 방문하더라도 남은 점프 개수가 이전보다 많은 상태면 최단경로의 일부일 수 있다는 점이다.

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		String[] line = br.readLine().split(" ");
		W = Integer.parseInt(line[0]);
		H = Integer.parseInt(line[1]);	
		
		board = new int[H][W];
		visited = new boolean[H][W][K + 1];
		
		for (int i = 0; i < H; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		
		Queue<Monkey> q = new LinkedList<>();
		q.add(new Monkey(0, 0, K));
		
		
		int answer = 0;
		while (!q.isEmpty()) {
			
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Monkey m = q.poll();
				int y = m.currentY;
				int x = m.currentX;
				int j = m.remainingJump;
				if (y < 0 || x < 0 || y >= H || x >= W) {
					continue;
				}
				if (board[y][x] == 1) {
					continue;
				}
				if (visited[y][x][j]) {
					continue;
				}
				visited[y][x][j] = true;
				
				if (y == H - 1 && x == W - 1) {
					System.out.println(answer);
					return;
				}
				if (j > 0) {
					q.add(new Monkey(y + 2, x + 1, j - 1));
					q.add(new Monkey(y + 1, x + 2, j - 1));
					q.add(new Monkey(y - 1, x + 2, j - 1));
					q.add(new Monkey(y - 2, x + 1, j - 1));
					q.add(new Monkey(y + 2, x - 1, j - 1));
					q.add(new Monkey(y + 1, x - 2, j - 1));
					q.add(new Monkey(y - 1, x - 2, j - 1));
					q.add(new Monkey(y - 2, x - 1, j - 1));
				}
				q.add(new Monkey(y + 1, x, j));
				q.add(new Monkey(y - 1, x, j));
				q.add(new Monkey(y, x + 1, j));
				q.add(new Monkey(y, x - 1, j));
			}
			
			answer++;
		}
		
		System.out.println(-1);
		
	}
	
}
